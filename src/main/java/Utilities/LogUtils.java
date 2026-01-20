package Utilities;


import org.apache.logging.log4j.LogManager;

public class LogUtils {

    private static final String logs_path = "test-outputs/logs";

    /*
    Why getStackTrace()[2] is used:
    Thread.currentThread().getStackTrace() returns an array of stack frames where:

    index 0 → Thread.getStackTrace() (the JVM call)

    index 1 → the current method (trace())

    index 2 → the actual caller of trace()
     */

    //This method logs a TRACE-level message using a logger named after the calling method
    public static void trace(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .trace(message);
    }

    //This method logs a DEBUG-level message using a logger named after the calling method
    public static void debug(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(message);
    }

    //This method logs a INFO-level message using a logger named after the calling method
    public static void info(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(message);
    }

    //This method logs a WARN-level message using a logger named after the calling method
    public static void warn(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(message);
    }

    //This method logs a ERROR-level message using a logger named after the calling method
    public static void error(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(message);
    }

    //This method logs a FATAL-level message using a logger named after the calling method
    public static void fatal(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
    }


}
