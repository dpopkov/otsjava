package learn.otsjava.testing.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("MessageBuilder должен")
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

    @DisplayName("используя заданный шаблон генерировать из текста и подписи корректное сообщение")
    @Test
    void shouldBuildCorrectMessageUsingGivenTemplateForGivenTextAndSign() {
        given(provider.getMessageTemplate(TEMPLATE_NAME)).willReturn(TEMPLATE_TEXT);
        String actualMessage = builder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, SIGN);
        assertEquals(String.format(TEMPLATE_TEXT, MESSAGE_TEXT, SIGN), actualMessage);
    }

    @DisplayName("получать шаблон у заданного провайдера при построении сообщения")
    @Test
    void shouldRequestTemplateWhenBuildingMessage() {
        given(provider.getMessageTemplate(TEMPLATE_NAME)).willReturn(TEMPLATE_TEXT);
        builder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, SIGN);
        verify(provider, atLeastOnce()).getMessageTemplate(TEMPLATE_NAME);
    }

    @DisplayName("выбрасывать исключение если провайдер не предоставляет шаблон")
    @Test
    void shouldThrowExceptionWhenTemplateNotFound() {
        given(provider.getMessageTemplate(TEMPLATE_NAME)).willReturn("");
        assertThrows(TemplateNotFoundException.class,
                () -> builder.buildMessage(TEMPLATE_NAME, MESSAGE_TEXT, SIGN));
    }
}
