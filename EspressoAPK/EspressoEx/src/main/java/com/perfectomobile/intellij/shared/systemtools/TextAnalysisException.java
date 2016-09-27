package com.perfectomobile.intellij.shared.systemtools;

public class TextAnalysisException extends Exception {
    private static final long serialVersionUID = 2422187578358975258L;

    public TextAnalysisException(Throwable cause) {
        super(cause);
    }

    public TextAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextAnalysisException(String message) {
        super(message);
    }
}
