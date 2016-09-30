package com.perfectomobile.intellij.shared.systemtools.output;

import com.perfectomobile.intellij.shared.systemtools.SystemToolOutputAnalyzer;
import com.perfectomobile.intellij.shared.systemtools.TextAnalysis;
import com.perfectomobile.intellij.shared.systemtools.TextAnalysisException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MultilineTextOutputAnalyzer<T> implements SystemToolOutputAnalyzer<T> {
    private TextAnalysis<T, List<String>> textAnalysis;
    private T result;

    public MultilineTextOutputAnalyzer() {
    }

    public void setTextAnalysis(final TextAnalysis<T, List<String>> textAnalysis) {
        this.textAnalysis = textAnalysis;
    }

    @Override
    public void analyzeSystemToolOutput(final InputStream in) throws IOException, TextAnalysisException {
        @SuppressWarnings("unchecked")
        final List<String> lines = new ArrayList<String>(IOUtils.readLines(in));
        result = textAnalysis.analyseText(lines);
    }

    @Override
    public T reportAnalysisResult() {
        return result;
    }
}
