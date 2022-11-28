package com.github.smirnovanton90.jrtb.command;

import com.github.smirnovanton90.jrtb.service.SendBotMessageService;
import com.github.smirnovanton90.jrtb.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegramUserService);
    }

    @ParameterizedTest (name = "{index} - {0} - команда верная!")
    @EnumSource(CommandName.class)
    @DisplayName("Проверка корректную отработку всех команд")
    public void shouldGetAllTheExistingCommands(CommandName commandName) {

        Command command = commandContainer.retrieveCommand(commandName.getCommandName());
        Assertions.assertNotEquals(UnknownCommand.class, command.getClass());

    }

    @ParameterizedTest (name = "{index} - {0} - такая команда отсутствует!")
    @DisplayName("Проверка корректную отработку неразобранной команды")
    @ValueSource(strings = {"/fgjhdfgdfg", "fgjhdfgdfg", "//fgjhdfgdfg", "123", "/123"})
    public void shouldReturnUnknownCommand(String inputCommand) {

        Command command = commandContainer.retrieveCommand(inputCommand);
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}