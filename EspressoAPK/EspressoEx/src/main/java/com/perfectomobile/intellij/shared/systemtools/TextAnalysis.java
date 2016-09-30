package com.perfectomobile.intellij.shared.systemtools;

public interface TextAnalysis<T, E> {
    T analyseText(final E text) throws TextAnalysisException;
}
