package com.ddigrang.coinrich.discord.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.List;

public interface Command {

    String getToken();

    // JDA 4.4.0 --> 5.0.0 으로 변경하면서
    // GuildMessageReceivedEvent --> MessageReceivedEvent 로 대체
    void perform(MessageReceivedEvent event, List<String> args);
}
