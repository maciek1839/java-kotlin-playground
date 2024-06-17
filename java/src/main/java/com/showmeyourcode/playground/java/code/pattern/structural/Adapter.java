package com.showmeyourcode.playground.java.code.pattern.structural;

import lombok.extern.slf4j.Slf4j;

// The Adapter pattern allows incompatible interfaces to work together. It wraps an existing class with a new interface.
public class Adapter {

    private Adapter() {
    }

    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new MediaAdapter();
        mediaPlayer.play("mp3", "beyond the horizon.mp3");
        mediaPlayer.play("mp4", "alone.mp4");
        mediaPlayer.play("vlc", "far far away.vlc");
    }
}

// Existing interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee class with a different interface
@Slf4j
class AdvancedMediaPlayer {
    void playVlc(String fileName) {
        log.info("Playing vlc file. Name: {}", fileName);
    }
    void playMp4(String fileName) {
        log.info("Playing mp4 file. Name: {}", fileName);
    }
}

// Adapter class implementing the target interface and using the adaptee class
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer = new AdvancedMediaPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if(audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
