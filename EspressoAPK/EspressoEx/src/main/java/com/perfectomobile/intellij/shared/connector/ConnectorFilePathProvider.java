package com.perfectomobile.intellij.shared.connector;

import com.google.common.base.Supplier;

import java.io.File;

public class ConnectorFilePathProvider {
    private static final String PERFECTO_SUBDIR = "prefecto-mobile";
    private final Supplier<Long> serverProcessPidSupplier;

    public ConnectorFilePathProvider(Supplier<Long> serverProcessPidSupplier) {
        this.serverProcessPidSupplier = serverProcessPidSupplier;
    }

    public String getConnectorFilePath() {
        return tempDir() + File.separatorChar + PERFECTO_SUBDIR + File.separatorChar + connectorFileName();
    }

    private String tempDir() {
        return System.getProperty("java.io.tmpdir");
    }

    private String connectorFileName() {
        return String.format("connector-%d.json", getJvmPid());
    }

    private long getJvmPid() {
        return serverProcessPidSupplier.get();
    }
}
