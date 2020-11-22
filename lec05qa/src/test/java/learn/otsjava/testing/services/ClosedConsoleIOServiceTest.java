package learn.otsjava.testing.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("ClosedIOService")
class ClosedIOServiceTest {

    private static final String NL = System.lineSeparator();
    private static final String TEXT_TO_PRINT1 = "Hello Goodbye";
    private static final String TEXT_TO_PRINT2 = "Goodbye Hello";

    private PrintStream savedOut;
    private ByteArrayOutputStream buffer;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());
        savedOut = System.out;
        buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        ioService = new ClosedConsoleIOService();
    }

    @AfterEach
    void tearDown() {
        System.setOut(savedOut);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT1 + "\"")
    @Test
    void shouldPrintOnlyFirstCreedLine() throws InterruptedException {
        ioService.out(TEXT_TO_PRINT1);
        Thread.sleep(1000);
        assertThat(buffer.toString()).isEqualTo(TEXT_TO_PRINT1 + NL);
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT2 + "\"")
    @Test
    void shouldPrintOnlySecondCreedLine() {
        ioService.out(TEXT_TO_PRINT2);
        assertThat(buffer.toString()).isEqualTo(TEXT_TO_PRINT2 + NL);
    }
}
