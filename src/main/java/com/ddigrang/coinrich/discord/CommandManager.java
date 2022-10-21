package com.ddigrang.coinrich.discord;

import com.ddigrang.coinrich.discord.command.Command;
import com.ddigrang.coinrich.discord.command.PauseCommand;
import com.ddigrang.coinrich.discord.command.PlayCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class CommandManager {

    private final HashMap<String, Command> commandMap = new HashMap<>();

    public CommandManager() {
        createCommand(new PlayCommand());
        createCommand(new PauseCommand());
    }

    void createCommand(Command command) {
        commandMap.put(command.getToken(), command);
    }

    void performCommand(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().substring(1);

        final String[] tokens = message.split("\\s+");
//        System.out.println("(CommandManager) message = "+message.split("\\s+").toString());
//        System.out.println("(CommandManager) tokens[0] = "+tokens[0]);
        if(commandMap.containsKey(tokens[0])) {
            List<String> args = Arrays.asList(tokens).subList(1, tokens.length);
            commandMap.get(tokens[0]).perform(event, args);
        }
    }
}
