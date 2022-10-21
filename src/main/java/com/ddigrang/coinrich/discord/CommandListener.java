package com.ddigrang.coinrich.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandListener extends ListenerAdapter {

    private final CommandManager manager;
    private static final String COMMAND_PREFIX = "!";

    @Autowired
    public CommandListener(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Bot's Chat ignore
        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();
        if (message.startsWith(COMMAND_PREFIX))
            manager.performCommand(event);
    }
}
