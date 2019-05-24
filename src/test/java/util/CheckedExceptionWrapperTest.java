package util;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.UncheckedIOException;

public class CheckedExceptionWrapperTest {

    @Test
    public void testSupplier() {
        String string = CheckedExceptionWrapper.wrap(() -> {
            return "foobar";
        });
        assertEquals("foobar", string);
    }

    @Test
    public void testSupplierException() {
        try {
            CheckedExceptionWrapper.wrap(() -> {
                throw new Exception("Exception");
            });
        } catch (RuntimeException e) {
            assertEquals(Exception.class, e.getCause().getClass());
            assertEquals("Exception", e.getCause().getMessage());
            return;
        }
        fail();
    }

    @Test
    public void testSupplierIOException() {
        try {
            CheckedExceptionWrapper.wrap(() -> {
                throw new IOException("IOException");
            });
        } catch (UncheckedIOException e) {
            assertEquals(IOException.class, e.getCause().getClass());
            assertEquals("IOException", e.getCause().getMessage());
            return;
        }
        fail();
    }

    @Test
    public void testSupplierRuntimeException() {
        try {
            CheckedExceptionWrapper.wrap(() -> {
                throw new RuntimeException("RuntimeException");
            });
        } catch (RuntimeException e) {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals("RuntimeException", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    public void testRunnerException() {
        try {
            CheckedExceptionWrapper.wrap(() -> {
                if (true) {
                    throw new Exception("Exception");
                }
            });
        } catch (RuntimeException e) {
            assertEquals(Exception.class, e.getCause().getClass());
            assertEquals("Exception", e.getCause().getMessage());
            return;
        }
        fail();
    }

    @Test
    public void testRunnerIOException() {
        try {
            CheckedExceptionWrapper.wrap(() -> {
                if (true) {
                    throw new IOException("IOException");
                }
            });
        } catch (UncheckedIOException e) {
            assertEquals(IOException.class, e.getCause().getClass());
            assertEquals("IOException", e.getCause().getMessage());
            return;
        }
        fail();
    }

    @Test
    public void testRunnerRuntimeException() {
        try {
            CheckedExceptionWrapper.wrap(() -> {
                if (true) {
                    throw new RuntimeException("RuntimeException");
                }
            });
        } catch (RuntimeException e) {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals("RuntimeException", e.getMessage());
            return;
        }
        fail();
    }
}
