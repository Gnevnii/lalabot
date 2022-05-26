package ru.gnev.conciergebot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gnev.conciergebot.command.CommandContainer;
import ru.gnev.conciergebot.command.CommandName;
import ru.gnev.conciergebot.service.SendMessageServiceImpl;

@Component
public class ConciergeBot extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public ConciergeBot() {
        this.commandContainer = new CommandContainer(new SendMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}