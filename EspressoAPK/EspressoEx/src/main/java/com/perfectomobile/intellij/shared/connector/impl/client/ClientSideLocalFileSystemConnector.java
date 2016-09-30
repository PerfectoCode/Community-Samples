package com.perfectomobile.intellij.shared.connector.impl.client;

import com.google.gson.Gson;
import com.perfectomobile.intellij.shared.connector.ConnectorConfiguration;
import com.perfectomobile.intellij.shared.connector.ConnectorFileProvider;
import com.perfectomobile.intellij.shared.connector.LogAdapter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ClientSideLocalFileSystemConnector {
    private final ConnectorFileProvider connectorFilePathProvider;
    private final LogAdapter logger;

    public ClientSideLocalFileSystemConnector(LogAdapter logger) {
        this(new ExistingConnectorFileProvider(logger), logger);
    }

    ClientSideLocalFileSystemConnector(ConnectorFileProvider connectorFilePathProvider, LogAdapter logger) {
        this.connectorFilePathProvider = connectorFilePathProvider;
        this.logger = logger;
    }

    public ConnectorConfiguration getConnectorConfiguration() throws IOException {
        File connectorFile = connectorFilePathProvider.getConnectorFile();
        if (connectorFile == null) {
            logger.info("PerfectoLab connector is not available");
            return null;
        }
        logger.info(String.format("Loading PerfectoLab connector configuration from file %s:", connectorFile.getAbsolutePath()));
        ConnectorConfiguration configuration = new Gson().fromJson(FileUtils.readFileToString(connectorFile), ConnectorConfiguration.class);
        logger.debug(String.format("PerfectLab connector configuration is\n%s", configuration.toString()));
        return configuration;
    }
}
