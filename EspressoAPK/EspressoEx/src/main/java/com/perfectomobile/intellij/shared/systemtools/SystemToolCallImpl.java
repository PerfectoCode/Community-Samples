package com.perfectomobile.intellij.shared.systemtools;

import com.perfectomobile.intellij.shared.connector.LogAdapter;
import com.perfectomobile.intellij.shared.systemtools.os.ProcessExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SystemToolCallImpl<T> implements SystemToolCall<T> {
    private final LogAdapter logger;
    private SystemToolOutputAnalyzer<T> outputAnalyzer;
    protected ProcessExecutor processExecutor;

    public SystemToolCallImpl(LogAdapter logger) {
        this.logger = logger;
    }

    @Override
    public void setProcessExecutor(final ProcessExecutor processExecutor) {
        this.processExecutor = processExecutor;
    }

    @Override
    public void setSystemToolOutputAnalyzer(final SystemToolOutputAnalyzer<T> outputAnalyzer) {
        this.outputAnalyzer = outputAnalyzer;
    }

    @Override
    public SystemToolCallResult<T> callSystemTool(final List<String> systemToolInvocationCommands) throws SystemToolCallException {
        Process process;
        try {
            process = startOsProcess(systemToolInvocationCommands);
        } catch (final IOException e) {
            throw new SystemToolCallException(systemToolInvocationCommands, e);
        }
        InputStream in = null;
        try {
            in = getProcessOutput(process);
            if (outputAnalyzer != null) {
                outputAnalyzer.analyzeSystemToolOutput(in);
            }
        } catch (Exception e) {
            throw new SystemToolCallException(systemToolInvocationCommands, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    handleStreamNotClosed(systemToolInvocationCommands, e);
                }
            }
        }
        return new SystemToolCallResult<T>(process, outputAnalyzer);
    }

    protected Process startOsProcess(final List<String> systemToolInvocationCommands) throws IOException {
        return processExecutor.syncExecOsProcess(systemToolInvocationCommands);
    }

    protected InputStream getProcessOutput(final Process process) {
        return process.getInputStream();
    }

    private void handleStreamNotClosed(final List<String> systemToolInvocationCommands, IOException e) {
        logger.warn(String.format(
                "Could not close input stream for used system tool executed with command [%s]. This may indicate leak of system resources. Refer to the logged exception for more details.",
                new SystemToolsHelper().systemToolCommandAsString(systemToolInvocationCommands)), e);
    }
}
