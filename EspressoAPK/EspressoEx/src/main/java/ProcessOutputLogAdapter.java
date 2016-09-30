import com.perfectomobile.intellij.shared.connector.LogAdapter;

import java.io.PrintStream;

public class ProcessOutputLogAdapter implements LogAdapter {
    private final PrintStream errorStream;
    private final PrintStream warnStream;
    private final PrintStream infoStream;
    private final PrintStream debugStream;

    public ProcessOutputLogAdapter(PrintStream errorStream, PrintStream warnStream, PrintStream infoStream, PrintStream debugStream) {
        this.errorStream = errorStream;
        this.warnStream = warnStream;
        this.infoStream = infoStream;
        this.debugStream = debugStream;
    }


    @Override
    public void error(String message) {
        errorStream.println(message);
    }

    @Override
    public void error(String message, Throwable cause) {
        errorStream.println(message);
        cause.printStackTrace(errorStream);
    }

    @Override
    public void warn(String message) {
        warnStream.println(message);
    }

    @Override
    public void warn(String message, Throwable cause) {
        warnStream.println(message);
        cause.printStackTrace(warnStream);
    }

    @Override
    public void debug(String message) {
        debugStream.println(message);
    }

    @Override
    public void debug(String message, Throwable cause) {
        debugStream.println(message);
        cause.printStackTrace(debugStream);
    }

    @Override
    public void info(String message) {
        infoStream.println(message);
    }

    @Override
    public void info(String message, Throwable cause) {
        infoStream.println(message);
        cause.printStackTrace(infoStream);
    }
}
