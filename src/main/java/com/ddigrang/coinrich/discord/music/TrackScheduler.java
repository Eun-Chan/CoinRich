package com.ddigrang.coinrich.discord.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrackScheduler extends AudioEventAdapter {

    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private AudioTrack currentTrack;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack track) {
        if(!player.startTrack(track, true))
            queue.offer(track);
        else
            currentTrack = track;
    }

    public AudioTrack getNextTrack() {
        return queue.peek();
    }

    public void nextTrack() {
        AudioTrack track = queue.poll();
        currentTrack = track;
        player.startTrack(track, false);
    }

    public void pauseTrack() {
        player.setPaused(true);
    }

    public void resumeTrack() {
        player.setPaused(false);
    }

    public void clearTracks() {
        queue.clear();
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext)
            nextTrack();
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    public AudioTrack getCurrentTrack() {
        return currentTrack;
    }
}
