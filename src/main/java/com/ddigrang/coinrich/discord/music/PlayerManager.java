package com.ddigrang.coinrich.discord.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;

    private PlayerManager() {
        this.musicManagers = new HashMap<>();

        this.playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }

    public synchronized GuildMusicManager getGuildMusicManager(Guild guild) {
        long guilgId = guild.getIdLong();
        GuildMusicManager musicManager = musicManagers.get(guilgId);

        if(musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guilgId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }

    public synchronized GuildMusicManager getGuildMusicManager(long guildId) {
        return musicManagers.get(guildId);
    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("adding to queue "+track.getInfo().title).queue();
                play(musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if(firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                channel.sendMessage("adding to queue "+firstTrack.getInfo().title + " (first track of playlist " +playlist.getName() + ")").queue();

                play(musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("can't find anything by " +trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("couldn't play: "+exception.getMessage()).queue();
            }
        });
    }

    private void play(GuildMusicManager musicManager, AudioTrack track) {
        musicManager.scheduler.queue(track);
    }

    public void skipTrack(TextChannel channel) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        AudioTrack track = musicManager.scheduler.getNextTrack();
        musicManager.scheduler.nextTrack();

        if(track == null) {
            channel.sendMessage("okay, skipping.").queue();
            return;
        }
        channel.sendMessage("skipped, now playing "+track.getInfo().title).queue();
    }

    public void pauseTrack(TextChannel channel) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        musicManager.scheduler.pauseTrack();

        channel.sendMessage("음악 일시정지").queue();
    }

    public void resumeTrack(TextChannel channel) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        musicManager.scheduler.resumeTrack();

        channel.sendMessage("okay, paused.").queue();
    }

    public void clearTracks(TextChannel channel) {
        GuildMusicManager musicManager = getGuildMusicManager(channel.getGuild());
        musicManager.scheduler.clearTracks();
        musicManager.scheduler.nextTrack();
    }

    public static synchronized  PlayerManager getInstance() {
        if(INSTANCE == null)
            INSTANCE = new PlayerManager();

        return INSTANCE;
    }
}