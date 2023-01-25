package com.ddigrang.coinrich.discord.command;

import com.ddigrang.coinrich.discord.music.PlayerManager;
import com.ddigrang.coinrich.discord.util.Utilities;
import com.ddigrang.coinrich.discord.youtube.YouTubeSearcher;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class PlayCommand implements Command {


    @Override
    public String getToken() {
        return "뮤직큐";
    }

    @Override
    public void perform(MessageReceivedEvent event, List<String> args) {

        AudioManager audioManager = event.getGuild().getAudioManager();

        AudioChannel channel = event.getMember().getVoiceState().getChannel();

        // Music Channel에 없을 경우 Music Play 불가
        System.out.println("채널이름 = " + event.getMember().getVoiceState().getChannel().getName());
        if (!event.getMember().getVoiceState().getChannel().getName().equals("Music")) {
            event.getChannel().sendMessage("Music 채널에서 이용해 주세요.").queue();
            return;
        }

        if (args.size() == 0) {
            event.getChannel().sendMessage("Music 검색 결과가 없습니다.").queue();
            return;
        }

        String argument = String.join(" ", args);

        // Youtube Music Search
        if (!Utilities.isUrl(argument)) {
            YouTubeSearcher searcher = new YouTubeSearcher();
            String id = searcher.searchFor(argument);
            argument = "https://www.youtube.com/watch?v=" + id;
        }

        audioManager.openAudioConnection(channel);

        PlayerManager manager = PlayerManager.getInstance();

        manager.loadAndPlay(event.getTextChannel(), argument);

    }
}
