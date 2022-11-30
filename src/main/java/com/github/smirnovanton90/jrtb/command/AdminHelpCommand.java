package com.github.smirnovanton90.jrtb.command;

import com.github.smirnovanton90.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.smirnovanton90.jrtb.command.CommandName.STAT;
import static java.lang.String.format;

/**
 * Admin Help {@link Command}.
 */
public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = format("""
                    ✨<b>Доступные команды админа</b>✨

                    <b>Получить статистику</b>
                    %s - статистика бота
                    """,
            STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), ADMIN_HELP_MESSAGE);
    }
}
