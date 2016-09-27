package com.perfectomobile.intellij.shared.systemtools.process;

import com.google.common.base.Supplier;
import com.perfectomobile.intellij.shared.connector.LogAdapter;
import com.perfectomobile.intellij.shared.systemtools.SystemToolCallException;
import com.perfectomobile.intellij.shared.systemtools.os.OSTypeHelper;

public class ProcessTool {
    interface GetParentProcessPidTool {
        long getParentProcessPid(long processPid) throws SystemToolCallException;
    }

    private final GetParentProcessPidTool getParentProcessPidTool;
    private final GetCurrentProcessPidTool getCurrentProcessPidTool;

    public ProcessTool(final LogAdapter logger) {
        this(new GetCurrentProcessPidTool(), new Supplier<GetParentProcessPidTool>() {
            @Override
            public GetParentProcessPidTool get() {
                OSTypeHelper osTypeHelper = OSTypeHelper.instance();
                if (osTypeHelper.isWinOS()) {
                    return new WmiCommandLineSystemTool(logger);
                }
                if (osTypeHelper.isMacOS()) {
                    return new PsSystemTool(logger);
                }
                throw new IllegalStateException(String.format("Unsupported operating ssystem: %s", osTypeHelper.getOSName()));
            }
        });
    }

    ProcessTool(GetCurrentProcessPidTool getCurrentProcessPidTool, Supplier<GetParentProcessPidTool> getParentProcessPidToolSupplier) {
        this.getCurrentProcessPidTool = getCurrentProcessPidTool;
        this.getParentProcessPidTool = getParentProcessPidToolSupplier.get();
    }

    public long getCurrentProcessPid() {
        return getCurrentProcessPidTool.getCurrentProcessPid();
    }

    public long getParentProcessPid(long processPid) throws SystemToolCallException {
        return getParentProcessPidTool.getParentProcessPid(processPid);
    }
}
