package com.core;

import front.App;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MyMediaPlayer implements AutoCloseable {

    private boolean released = false;
    private AudioInputStream stream = null;
    private Clip clip = null;
    private FloatControl volumeControl = null;
    private boolean playing = false;
    private File soundtrack1 = new File("src/resources/music/BattleTheme1.wav");
    private File soundtrack2 = new File("src/resources/music/BattleTheme2.wav");
    private String playingTrack = "";

    public AudioInputStream getStream() {
        return stream;
    }

    public void setStream(AudioInputStream stream) {
        this.stream = stream;
    }

    public MyMediaPlayer(File f) {
        try {
            stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(new Listener());
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            released = true;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
            released = false;

            close();
        }
    }

    // true если звук успешно загружен, false если произошла ошибка
    public boolean isReleased() {
        return released;
    }

    // проигрывается ли звук в данный момент
    public boolean isPlaying() {
        return playing;
    }

    // Запуск
	/*
	  breakOld определяет поведение, если звук уже играется
	  Если breakOld==true, звук будет прерван и запущен заново
	  Иначе ничего не произойдёт
	*/
    public void play(boolean breakOld) {
        if (released) {
            if (breakOld) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            } else if (!isPlaying()) {
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            }
        }
    }
    public void nextTrack(){
        if (!playingTrack.equals(soundtrack1.getName())){
            System.out.println(playingTrack.equals(soundtrack1.getName()));
            System.out.println(playingTrack);
            App.mediaPlayer = new MyMediaPlayer(soundtrack1);
            App.mediaPlayer.play();
            App.mediaPlayer.playingTrack = soundtrack1.getName();
        }else{
            System.out.println(playingTrack.equals(soundtrack1.getName()));
            System.out.println(playingTrack);App.mediaPlayer.stopPlaying();
            App.mediaPlayer.stopPlaying();
            App.mediaPlayer = new MyMediaPlayer(soundtrack2);
            App.mediaPlayer.play();
            App.mediaPlayer.playingTrack = soundtrack2.getName();
        }
    }

    public void loopPlaying() {
        Thread thread = new Thread( () ->{
           while (true) {
               while (App.mediaPlayer.isPlaying()) {
                   try {
                       Thread.currentThread().sleep(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               App.mediaPlayer.nextTrack();
           }
        });
        thread.setDaemon(true);
        thread.start();
    }

    // То же самое, что и play(true)
    public void play() {
        play(true);
    }

    // Останавливает воспроизведение
    public void stopPlaying() {
        if (playing) {
            clip.stop();
        }
    }

    // Плавно останавливает воспроизведение
    public void smoothStop() {
        Thread thread = new Thread( () ->
        {for (float i = getVolume(); i > 0.3; i-=0.001f) {
            App.mediaPlayer.setVolume(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (isPlaying()) {
            clip.stop();
        }});
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        if (clip != null)
            clip.close();

        if (stream != null)
            try {
                stream.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
    }

    // Установка громкости
	/*
	  x должен быть в пределах от 0 до 1 (от самого тихого к самому громкому)
	*/
    public void setVolume(float x) {
        if (x < 0) x = 0;
        if (x > 1) x = 1;
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        volumeControl.setValue((max - min) * x + min);
    }

    // Возвращает текущую громкость (число от 0 до 1)
    public float getVolume() {
        float v = volumeControl.getValue();
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        return (v - min) / (max - min);
    }

    // Дожидается окончания проигрывания звука
    public void joinPlay() {
        if (!released) return;
        synchronized (clip) {
            try {
                while (playing)
                    clip.wait();
            } catch (InterruptedException exc) {
            }
        }
    }

    // Статический метод, для удобства
    public static MyMediaPlayer playSound(String path) {
        File f = new File(path);
        MyMediaPlayer snd = new MyMediaPlayer(f);
        snd.play();
        return snd;
    }

    private class Listener implements LineListener {
        public void update(LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                playing = false;
                synchronized (clip) {
                    clip.notify();
                }
            }
        }
    }
}
