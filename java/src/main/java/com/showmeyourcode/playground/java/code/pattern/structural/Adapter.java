package com.showmeyourcode.playground.java.code.pattern.structural;

// Existing interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee class with a different interface
class AdvancedMediaPlayer {
    void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }
    void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
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

// The Adapter pattern allows incompatible interfaces to work together. It wraps an existing class with a new interface.
public class Adapter {

    public static void main() {
        MediaPlayer mediaPlayer = new MediaAdapter();
        mediaPlayer.play("mp3", "beyond the horizon.mp3");
        mediaPlayer.play("mp4", "alone.mp4");
        mediaPlayer.play("vlc", "far far away.vlc");
    }
}
