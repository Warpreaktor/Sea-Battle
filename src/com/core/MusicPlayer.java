package com.core;

import front.App;

import java.io.File;

public class MusicPlayer {
    private String soundtrack0 = "src/resources/music/SeaBattle_Main_Theme.wav";
    private String soundtrack1 = "src/resources/music/BattleTheme1.wav";
    private String soundtrack2 = "src/resources/music/BattleTheme2.wav";
    private String[] musicTracks;
    private String playingTrackName = "";
    private MusicTrack playingTrack;
    private Thread thread;
    private boolean loopPlaying = false;

    public MusicTrack getPlayingTrack() {
        return playingTrack;
    }

    public String[] getMusicTracks() {
        return musicTracks;
    }

    public MusicPlayer(){
        musicTracks = new String[]{soundtrack0, soundtrack1, soundtrack2};
    }
    public void play(String filePath){
        playingTrack = new MusicTrack(new File(filePath));
        playingTrack.play();
    }
    public void stopLoop(){
        loopPlaying = false;
    }
    public void loopPlaying() {
        thread = new Thread(() -> {
            loopPlaying = true;
            while (loopPlaying) {
                if (!playingTrack.isPlaying()) {
                    App.musicPlayer.nextTrack();
                }
            }
            playingTrack.stopPlaying();
        });
        thread.setDaemon(true);
        thread.start();
    }
    public void nextTrack() {
        if (!playingTrack.equals(soundtrack1)) {
            play(soundtrack1);
        } else {
            play(soundtrack2);
        }
    }
    public Thread getThread() {
        return thread;
    }
    public void smoothStop() {
        Thread thread = new Thread(() ->
        {
            for (float i = playingTrack.getVolume(); i > 0.4; i -= 0.001f) {
                playingTrack.setVolume(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (playingTrack.isPlaying()) {
                playingTrack.stopPlaying();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
