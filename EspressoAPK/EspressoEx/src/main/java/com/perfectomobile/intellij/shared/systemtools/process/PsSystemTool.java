package com.perfectomobile.intellij.shared.systemtools.process;


import com.perfectomobile.intellij.shared.connector.LogAdapter;
import com.perfectomobile.intellij.shared.systemtools.*;
import com.perfectomobile.intellij.shared.systemtools.output.MultilineTextOutputAnalyzer;

import java.util.Collections;
import java.util.List;

public class PsSystemTool implements ProcessTool.GetParentProcessPidTool {
    private static final String GET_MAC_PARENT_PID_COMMAND = "getMacParentPidCommand";

    private final SystemToolFacade systemToolFacade;

    PsSystemTool(LogAdapter logger) {
        this(new SystemToolFacade(logger), new SystemToolCallImpl<Long>(logger));
    }

    PsSystemTool(SystemToolFacade systemToolFacade, SystemToolCall<Long> longReturningSystemToolCall) {
        this.systemToolFacade = systemToolFacade;
        systemToolFacade.setSystemToolBaseCommand(getPsBaseCommand());
        systemToolFacade.addToolCommand(GET_MAC_PARENT_PID_COMMAND, longReturningSystemToolCall);

    }

    private List<String> getPsBaseCommand() {
        return Collections.singletonList("ps");
    }


    @Override
    public long getParentProcessPid(long processPid) throws SystemToolCallException {
        SystemToolOutputAnalyzer<Long> toolOutputAnalyzer = getParentProcessPidTextAnalysis();
        return systemToolFacade.executeToolCommand(GET_MAC_PARENT_PID_COMMAND, toolOutputAnalyzer, "-o", "ppid=", "-p", Long.toString(processPid));
    }

    private SystemToolOutputAnalyzer<Long> getParentProcessPidTextAnalysis() {
        MultilineTextOutputAnalyzer<Long> outputAnalyzer = new MultilineTextOutputAnalyzer<Long>();
        outputAnalyzer.setTextAnalysis(new TextAnalysis<Long, List<String>>() {
            @Override
            public Long analyseText(List<String> text) throws TextAnalysisException {
                return Long.parseLong(text.get(0).trim());
            }
        });
        return outputAnalyzer;
    }
}
