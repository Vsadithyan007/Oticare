package com.example.oti_care;

import android.net.Uri;

public class MusicList {
    private String artist;
    private String duration;
    private boolean isPlaying;
    private Uri musicFile;
    private String title;

    public MusicList(String title2, String artist2, String duration2, boolean isPlaying2, Uri musicFile2) {
        this.title = title2;
        this.artist = artist2;
        this.duration = duration2;
        this.isPlaying = isPlaying2;
        this.musicFile = musicFile2;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getDuration() {
        return this.duration;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public Uri getMusicFile() {
        return this.musicFile;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }
}
