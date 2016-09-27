package com.perfectomobile.intellij.shared.connector.impl.client;

import com.google.common.base.Supplier;
import com.perfectomobile.intellij.shared.connector.ConnectorFilePathProvider;
import com.perfectomobile.intellij.shared.connector.ConnectorFileProvider;
import com.perfectomobile.intellij.shared.connector.LogAdapter;
import com.perfectomobile.intellij.shared.systemtools.SystemToolCallException;
import com.perfectomobile.intellij.shared.systemtools.process.ProcessTool;

import java.io.File;
import java.io.IOException;

public class ExistingConnectorFileProvider implements ConnectorFileProvider {
    private final ConnectorFilePathProvider connectorFilePathProvider;

    public ExistingConnectorFileProvider(final LogAdapter logger) {
        this(new ConnectorFilePathProvider(new Supplier<Long>() {
            @Override
            public Long get() {
                ProcessTool processTool = new ProcessTool(logger);
                try {
                    return processTool.getParentProcessPid(processTool.getCurrentProcessPid());
                } catch (SystemToolCallException e) {
                    throw new IllegalStateException(e);
                }
            }
        }));
    }

    ExistingConnectorFileProvider(ConnectorFilePathProvider connectorFilePathProvider) {
        this.connectorFilePathProvider = connectorFilePathProvider;
    }

    @Override
    public File getConnectorFile() throws IOException {
        File connectorFile = new File(connectorFilePathProvider.getConnectorFilePath());
        if (!connectorFile.exists()) {
            return null;
        }
        if (connectorFile.isDirectory()) {
            throw new IOException(String.format("[%s] is a directory, it is expected to be a file", connectorFile.getAbsolutePath()));
        }
        return connectorFile;
    }
}
