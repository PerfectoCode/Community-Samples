package com.perfectomobile.intellij.shared.systemtools;

import java.io.IOException;
import java.io.InputStream;

public interface SystemToolOutputAnalyzer<T> {
    void analyzeSystemToolOutput(final InputStream in) throws IOException, TextAnalysisException;

    T reportAnalysisResult();
}
