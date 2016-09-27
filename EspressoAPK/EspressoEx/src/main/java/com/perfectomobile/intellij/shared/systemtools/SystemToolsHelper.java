package com.perfectomobile.intellij.shared.systemtools;

import java.util.List;

public class SystemToolsHelper {
    public String systemToolCommandAsString(final List<String> systemToolCommands) {
        final StringBuilder builder = new StringBuilder();
        for (final String command : systemToolCommands) {
            builder.append(command).append(" ");
        }
        return builder.toString();
    }
}
