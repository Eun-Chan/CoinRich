package com.ddigrang.coinrich.discord.command;

import com.ddigrang.coinrich.discord.music.PlayerManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class PauseCommand implements Command{
    @Override
    public String getToken() {
        return "일시정지";
    }

    @Override
    public void perform(MessageReceivedEvent event, List<String> args) {
        PlayerManager manager = PlayerManager.getInstance();
        manager.pauseTrack(event.getTextChannel());
    }
}
