package com.perfectomobile.intellij.shared.systemtools;

public class SystemToolCallResult<T> {
    private final SystemToolOutputAnalyzer<T> outputAnalyzer;
    private final Process systemToolProcess;

    public SystemToolCallResult(final Process systemToolProcess, final SystemToolOutputAnalyzer<T> outputAnalyzer) {
        this.systemToolProcess = systemToolProcess;
        this.outputAnalyzer = outputAnalyzer;
    }

    public int getExitCode() {
        return this.systemToolProcess.exitValue();
    }

    public T getResult() {
        if (outputAnalyzer == null) {
            throw new UnsupportedOperationException("Getting the result of the tool call is only possible if the output analyzer is set");
        }
        return outputAnalyzer.reportAnalysisResult();
    }

    public Process getSystemToolProcess() {
        return systemToolProcess;
    }
}
