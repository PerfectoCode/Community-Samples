package com.perfectomobile.intellij.shared.systemtools;


import com.perfectomobile.intellij.shared.systemtools.os.ProcessExecutor;

import java.util.List;

public interface SystemToolCall<T> {
    void setProcessExecutor(final ProcessExecutor processExecutor);

    void setSystemToolOutputAnalyzer(final SystemToolOutputAnalyzer<T> outputHandler);

    SystemToolCallResult<T> callSystemTool(final List<String> systemToolInvocationCommands) throws SystemToolCallException;
}
