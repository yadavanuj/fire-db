package com.pravisht.firedb;

import com.pravisht.firedb.protocol.FireDbException;
import com.pravisht.firedb.protocol.Packet;
import lombok.Builder;
import lombok.Getter;
import org.javatuples.Pair;

/**
 * @author Anuj Yadav
 * We are not implementing any Connection Registry as of now. This
 * may come later. Responsibility of maintaining and using multiple
 * connections is on consuming application. This keeps it flexible
 * and extensible.
 */
public interface FireDb {
    static FireDb fromProvider(ConnectionConfig<?> config, FireDbProvider provider) {
        return provider.getDriver(config);
    }
    Connection getConnection();
    public enum ProviderType {
        MySQl
    }

    public interface FireDbProvider {
        FireDb getDriver(ConnectionConfig<?> connectionConfig);
    }

    interface Connection {
        Packet execute(Packet packet, ExecutionContext<?> executionContext) throws FireDbException;
    }
    public abstract class BaseConnection<T> implements Connection, Observer {
        protected final ConnectionConfig<T> config;
        protected BaseConnection(ConnectionConfig<T> config) {
            this.config = config;
        }

        @Override
        public Packet execute(Packet packet, ExecutionContext<?> executionContext) throws FireDbException {
            return this.config.getRpcExecutor().execute(packet, executionContext);
        }

        @Override
        public void handle(Pair<Packet, FireDbException> result) {
            this.config.getRpcObserver().handle(result);
        }
    }

    @Builder
    @Getter
    public class ExecutionContext<E> {
        private E props;
    }

    public interface Executor {
        Packet execute(Packet packet, ExecutionContext<?> executionContext) throws FireDbException;
    }

    public interface Observer {
        void handle(Pair<Packet, FireDbException> result);
    }
}
