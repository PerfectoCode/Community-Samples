package com.perfectomobile.intellij.shared.connector;

import java.io.File;
import java.io.IOException;

public interface ConnectorFileProvider {
    /**
     * Gets the connector file, null if the file does not exist
     */
    File getConnectorFile() throws IOException;
}
