package com.pravisht.firedb.protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class Header {
    @NonNull
    private String key;
    private Object value;

    public <T> T getByType(Class<T> aClass) {
        return aClass.cast(value);
    }
}
