//package com.pravisht.firedb;
//
//import com.github.benmanes.caffeine.cache.CacheLoader;
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.LoadingCache;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NonNull;
//import org.checkerframework.checker.nullness.qual.Nullable;
//
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//public interface FireMemCache<R> {
//    R get(String key);
//    R getOrDefault(String key, R defaultValue);
//    void invalidate(String key);
//    boolean put(String key, R value);
//
//    static <R> FireMemCache<?> from(FireMemCacheConfig<R> config) {
//        return FireMemCacheFactory.create(config);
//    }
//
//    public interface DataStore<R> {
//        R get(String key);
//    }
//
//    @Builder
//    @Getter
//    public class FireMemCacheConfig<R> {
//        @Builder.Default
//        private long maximumSize = 10_000;
//        @Builder.Default
//        private long refreshAfterWrite = 60;
//        @Builder.Default
//        private TimeUnit refreshAfterWriteTimeUnit = TimeUnit.MINUTES;
//        @NonNull
//        private DataStore<R> dataStore;
//    }
//
//    public class MemCache<R> implements FireMemCache<R> {
//        private final LoadingCache<String, R> cache;
//
//        public MemCache(LoadingCache<String, R> cache) {
//            this.cache = cache;
//        }
//
//        @Override
//        public R get(String key) {
//            return cache.get(key);
//        }
//
//        @Override
//        public R getOrDefault(String key, R defaultValue) {
//            final R value = cache.getIfPresent(key);
//            if (!Objects.isNull(value)) {
//                return value;
//            }
//
//            // Load data async
//            cache.refresh(key);
//            return defaultValue;
//        }
//
//        @Override
//        public void invalidate(String key) {
//            cache.invalidate(key);
//        }
//
//        @Override
//        public boolean put(String key, R value) {
//            try {
//                cache.put(key, value);
//                return true;
//            } catch (Exception e) {
//                // TODO: e
//                return false;
//            }
//        }
//    }
//
//    public class FireMemCacheFactory {
//        public static <R> FireMemCache<?> create(FireMemCacheConfig<R> config) {
//            final LoadingCache<String, R> cache =  Caffeine.newBuilder()
//                    .maximumSize(config.getMaximumSize())
//                    .refreshAfterWrite(config.getRefreshAfterWrite(), config.getRefreshAfterWriteTimeUnit())
//                    .build(new CacheLoader<>() {
//                        @Override
//                        public @Nullable R load(String key) throws Exception {
//                            return config.getDataStore().get(key);
//                        }
//                    });
//            return new MemCache<>(cache);
//        }
//    }
//}
