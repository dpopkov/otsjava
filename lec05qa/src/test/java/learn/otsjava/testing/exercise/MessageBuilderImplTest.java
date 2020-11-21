package learn.otsjava.testing.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageBuilderImplTest {
    private static final String TEMPLATE_NAME = "template";
    private static final String TEMPLATE_TEXT = "Hi!\n %s \n Yours sincerely, %s";
    private static final String MESSAGE_TEXT = "How are you doing?";
    private static final String SIGN = "Bobby";

    private MessageTemplateProvider provider;
    private MessageBuilder builder;

    @BeforeEach
    void setUp() {
        provider = mock(MessageTemplateProvider.class);
        builder = new MessageBuilderImpl(provider);
    }

    @Test
    void buildMessageTest() {
        when(provider.getMessageTemplate(TEMPLATE_NAME)).thenReturn(TEMPLATE_TEXT);
        String actualMessage = builder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, SIGN);
        assertEquals(String.format(TEMPLATE_TEXT, MESSAGE_TEXT, SIGN), actualMessage);
    }

    @Test
    void whenBuildMessageThenUsesTemplate() {
        when(provider.getMessageTemplate(TEMPLATE_NAME)).thenReturn(TEMPLATE_TEXT);
        builder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, SIGN);
        verify(provider, atLeastOnce()).getMessageTemplate(TEMPLATE_NAME);
    }

    @Test
    void whenTemplateNotFoundThenException() {
        when(provider.getMessageTemplate(TEMPLATE_NAME)).thenReturn("");
        assertThrows(TemplateNotFoundException.class,
                () -> builder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, SIGN));
    }
}
