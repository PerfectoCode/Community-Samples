package com.perfectomobile.intellij.shared.systemtools.os;

import org.apache.commons.lang3.SystemUtils;

public class OSTypeHelper {
    private static final OSTypeHelper _instance = new OSTypeHelper();

    public static OSTypeHelper instance() {
        return _instance;
    }

    public boolean isMacOS() {
        return SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX;
    }

    public boolean isWinOS() {
        return SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_WINDOWS_8;
    }

    public String getOSName() {
        return SystemUtils.OS_NAME;
    }
}
