package com.perfectomobile.intellij.shared.systemtools.process;

import com.google.common.collect.Lists;
import com.perfectomobile.intellij.shared.connector.LogAdapter;
import com.perfectomobile.intellij.shared.systemtools.*;
import com.perfectomobile.intellij.shared.systemtools.output.MultilineTextOutputAnalyzer;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WmiCommandLineSystemTool implements ProcessTool.GetParentProcessPidTool {
    private static final String GET_WINDOWS_PARENT_PID_COMMAND = "getWindowsParentPidCommand";
    private static final Pattern PID_PATTERN = Pattern.compile("\\s*(\\d+)\\s*");

    private final SystemToolFacade systemToolFacade;

    WmiCommandLineSystemTool(LogAdapter logger) {
        this(new SystemToolFacade(logger), new SystemToolCallImpl<Long>(logger));
    }

    WmiCommandLineSystemTool(SystemToolFacade systemToolFacade, SystemToolCall<Long> longReturningSystemToolCall) {
        this.systemToolFacade = systemToolFacade;
        systemToolFacade.setSystemToolBaseCommand(getPsBaseCommand());
        systemToolFacade.addToolCommand(GET_WINDOWS_PARENT_PID_COMMAND, longReturningSystemToolCall);

    }

    private List<String> getPsBaseCommand() {
        return Collections.singletonList("wmic");
    }


    @Override
    public long getParentProcessPid(long processPid) throws SystemToolCallException {
        SystemToolOutputAnalyzer<Long> toolOutputAnalyzer = getParentProcessPidTextAnalysis();
        return systemToolFacade.executeToolCommand(GET_WINDOWS_PARENT_PID_COMMAND, toolOutputAnalyzer, "process", "where", String.format("(processid=%d)", processPid), "get", "parentprocessid");
    }

    private SystemToolOutputAnalyzer<Long> getParentProcessPidTextAnalysis() {
        MultilineTextOutputAnalyzer<Long> outputAnalyzer = new MultilineTextOutputAnalyzer<Long>();
        outputAnalyzer.setTextAnalysis(new TextAnalysis<Long, List<String>>() {
            @Override
            public Long analyseText(List<String> text) throws TextAnalysisException {
                for (String line : Lists.reverse(text)) {
                    Matcher matcher = PID_PATTERN.matcher(line);
                    if (matcher.matches()) {
                        return Long.parseLong(matcher.group(1));
                    }
                }
                throw new IllegalStateException("Could not parse process output to find the parent pid. The output was\n" + text.toString());
            }
        });
        return outputAnalyzer;
    }
}
