package com.perfectomobile.intellij.shared.systemtools;

import com.perfectomobile.intellij.shared.connector.LogAdapter;
import com.perfectomobile.intellij.shared.systemtools.os.ProcessExecutor;

import java.util.*;

public class SystemToolFacade {
    private final ProcessExecutor processExecutor;
    private final Map<String, SystemToolCall<?>> toolCommands;
    private final LogAdapter logger;
    private List<String> systemToolBaseCommand;

    public SystemToolFacade(LogAdapter logger) {
        this(new ProcessExecutor(logger), logger);
    }

    SystemToolFacade(final ProcessExecutor processExecutor, LogAdapter logger) {
        this.logger = logger;
        this.processExecutor = processExecutor;
        toolCommands = new HashMap<String, SystemToolCall<?>>();
    }

    public void setSystemToolBaseCommand(List<String> systemToolBaseCommand) {
        this.systemToolBaseCommand = systemToolBaseCommand;
    }

    public void addToolCommand(final String commandKey, final SystemToolCall<?> systemToolCall) {
        toolCommands.put(commandKey, systemToolCall);
    }

    public <T extends SystemToolOutputAnalyzer<E>, E> E executeToolCommand(final String commandKey, final T outputAnalyzer,
                                                                           final String... additionalCommandArguments) throws SystemToolCallException {
        final List<String> additionalCommandArgumentsAsList = Arrays.asList(additionalCommandArguments);
        final List<String> fullCommand = getSystemToolCommand(additionalCommandArgumentsAsList);
        final String commandAsString = new SystemToolsHelper().systemToolCommandAsString(fullCommand);

        logger.debug(String.format("About to execute [%s].", commandAsString));

        final SystemToolCall<E> systemCall = getSystemToolCommand(commandKey);
        initializeSystemCall(systemCall, outputAnalyzer);
        final E commandExecutionResult = executeCommand(systemCall, additionalCommandArgumentsAsList);

        logger.debug(String.format("[%s] executed.", commandAsString));

        return commandExecutionResult;
    }

    @SuppressWarnings("unchecked")
    private <T> SystemToolCall<T> getSystemToolCommand(final String commandKey) {
        if (toolCommands.containsKey(commandKey)) {
            return (SystemToolCall<T>) toolCommands.get(commandKey);
        }
        throw new IllegalArgumentException(String.format(
                "There is no system tool command registered as [%s]. Command cannot be retrieved.", commandKey));
    }

    private <T extends SystemToolCall<E>, V extends SystemToolOutputAnalyzer<E>, E> void initializeSystemCall(final T systemCall,
                                                                                                              final V outputAnalyzer) {
        systemCall.setProcessExecutor(processExecutor);
        systemCall.setSystemToolOutputAnalyzer(outputAnalyzer);
    }

    private <T extends SystemToolCall<E>, E> E executeCommand(final T systemCall, final List<String> additionalCommandArguments)
            throws SystemToolCallException {
        return systemCall.callSystemTool(getSystemToolCommand(additionalCommandArguments)).getResult();
    }

    private List<String> getSystemToolCommand(final List<String> additionalCommandParameters) {
        final List<String> command = new ArrayList<String>();
        command.addAll(systemToolBaseCommand);
        command.addAll(additionalCommandParameters);
        return command;
    }
}
