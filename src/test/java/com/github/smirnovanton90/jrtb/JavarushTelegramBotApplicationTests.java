package com.github.smirnovanton90.jrtb;

import com.github.smirnovanton90.jrtb.bot.JavarushTelegramBot;
import com.github.smirnovanton90.jrtb.service.SendBotMessageService;
import com.github.smirnovanton90.jrtb.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
class JavarushTelegramBotApplicationTests {

	private SendBotMessageService sendBotMessageService;
	private JavarushTelegramBot javarushBot;

	@BeforeEach
	public void init() {
		javarushBot = Mockito.mock(JavarushTelegramBot.class);
		sendBotMessageService = new SendBotMessageServiceImpl(javarushBot);
	}
	@Test
	public void shouldProperlySendMessage() throws TelegramApiException {
		//given
		Long chatId = 1234567890L;
		String message = "test_message";

		SendMessage sendMessage = new SendMessage();
		sendMessage.setText(message);
		sendMessage.setChatId(chatId);
		sendMessage.enableHtml(true);

		//when
		sendBotMessageService.sendMessage(chatId, message);

		//then
		Mockito.verify(javarushBot).execute(sendMessage);
	}

}
