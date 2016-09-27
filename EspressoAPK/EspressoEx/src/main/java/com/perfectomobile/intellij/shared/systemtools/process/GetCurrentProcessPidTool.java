package com.perfectomobile.intellij.shared.systemtools.process;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCurrentProcessPidTool {
    private static final Pattern RT_MXBEAN_NAME_PATTERN = Pattern.compile("(\\d+)@.+");
    private final RuntimeMXBean runtimeMXBean;

    GetCurrentProcessPidTool() {
        this(ManagementFactory.getRuntimeMXBean());
    }

    public GetCurrentProcessPidTool(RuntimeMXBean runtimeMXBean) {
        this.runtimeMXBean = runtimeMXBean;
    }


    public long getCurrentProcessPid() {
        String rtMxBeanName = runtimeMXBean.getName();
        Matcher rtMxBeanNameMatcher = RT_MXBEAN_NAME_PATTERN.matcher(rtMxBeanName);
        if (!rtMxBeanNameMatcher.matches()) {
            throw new IllegalStateException(String.format("Cannot determine current JVM PID, RuntimeMXBean name does not match pattern [%s]", RT_MXBEAN_NAME_PATTERN.toString()));
        }

        return Long.parseLong(rtMxBeanNameMatcher.group(1));
    }
}
