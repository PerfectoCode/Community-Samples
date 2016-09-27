package com.perfectomobile.intellij.shared.systemtools;

import java.util.List;

public class SystemToolCallException extends Exception {
    private static final long serialVersionUID = 1L;

    public SystemToolCallException(final List<String> systemToolInvocationCommands, final Throwable cause) {
        super(String.format("Execution failed for calling system tool with command [%s].%s",
                getSystemToolInvocationCommandsAsString(systemToolInvocationCommands), cause == null ? ""
                        : " More details in the nested exception."), cause);
    }

    public SystemToolCallException(final List<String> systemToolInvocationCommands, final int exitCode) {
        super(String.format("Execution failed for calling system tool with command [%s] with exit code [%d]",
                getSystemToolInvocationCommandsAsString(systemToolInvocationCommands), exitCode));
    }

    public SystemToolCallException(List<String> systemToolInvocationCommands, int exitCode, String errorOutput) {
        super(String.format("Execution failed for calling system tool with command [%s] with exit code [%d]. The error output was\n%s",
                getSystemToolInvocationCommandsAsString(systemToolInvocationCommands), exitCode, errorOutput));
    }

    private static String getSystemToolInvocationCommandsAsString(final List<String> systemToolInvocationCommands) {
        final StringBuilder builder = new StringBuilder();
        for (final String command : systemToolInvocationCommands) {
            builder.append(command).append(" ");
        }
        return builder.toString();
    }
}
