package util;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * wrap checked exception as RuntimeException
 * 
 */
public class CheckedExceptionWrapper {

    /**
     * This method wrap a checked exception as RuntimeException. code block has return value.
     * 
     * @param <R>      return type
     * @param supplier wrapped code
     * @return return value
     */
    public static <R> R wrap(final ThrowableSupplier<R> supplier) {
        try {
            return supplier.get();
        } catch (final RuntimeException cause) {
            throw cause;
        } catch (final IOException cause) {
            throw new UncheckedIOException(cause);
        } catch (final Throwable cause) {
            throw new RuntimeException(cause);
        }
    }

    /**
     * This method wrap a checked exception as RuntimeException. code block don't have return value.
     * 
     * @param runner wrapped code
     */
    public static void wrap(final ThrowableRunner runner) {
        try {
            runner.run();
        } catch (final RuntimeException cause) {
            throw cause;
        } catch (final IOException cause) {
            throw new UncheckedIOException(cause);
        } catch (final Throwable cause) {
            throw new RuntimeException(cause);
        }
    }

    @FunctionalInterface
    public static interface ThrowableSupplier<R> {
        R get() throws Throwable;
    }

    @FunctionalInterface
    public static interface ThrowableRunner {
        void run() throws Throwable;
    }
}

