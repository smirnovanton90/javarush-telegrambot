package com.github.smirnovanton90.jrtb.command;

import com.github.smirnovanton90.jrtb.javarushclient.JavaRushGroupClient;
import com.github.smirnovanton90.jrtb.javarushclient.GroupDiscussionInfo;
import com.github.smirnovanton90.jrtb.javarushclient.GroupRequestArgs;
import com.github.smirnovanton90.jrtb.repository.entity.GroupSub;
import com.github.smirnovanton90.jrtb.service.GroupSubService;
import com.github.smirnovanton90.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.smirnovanton90.jrtb.command.CommandName.ADD_GROUP_SUB;
import static com.github.smirnovanton90.jrtb.command.CommandUtils.getChatId;
import static com.github.smirnovanton90.jrtb.command.CommandUtils.getMessage;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Add Group subscription {@link Command}.
 */
public class AddGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JavaRushGroupClient javaRushGroupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService, JavaRushGroupClient javaRushGroupClient,
                              GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.javaRushGroupClient = javaRushGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        Long chatId = getChatId(update);
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = javaRushGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Подписал на группу " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(Long chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(Long chatId) {
        String groupIds = javaRushGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = """
                Чтобы подписаться на группу - передай команду /addGroupSub вместе с ID группы из списка ниже.\s
                Например: /addGroupSub 16.\s

                Я подготовил список всех групп - выбирай, какую хочешь :)\s

                Имя группы - ID группы\s

                %s""";

        sendBotMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}