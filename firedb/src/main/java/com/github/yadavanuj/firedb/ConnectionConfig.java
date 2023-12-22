package com.github.yadavanuj.firedb;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class ConnectionConfig<R> {
    private FireDb.Executor rpcExecutor;
    private FireDb.Observer rpcObserver;
    @NonNull
    private String providerName;
    private List<R> rpcConfigs;
}
