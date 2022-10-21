package com.ddigrang.coinrich.discord.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class TextClearCommand implements Command {
    @Override
    public String getToken() {
        return "청소";
    }

    @Override
    public void perform(MessageReceivedEvent event, List<String> args) {

    }
}
