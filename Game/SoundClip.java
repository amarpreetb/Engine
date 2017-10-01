package com.AMax.Engine.audio;

import com.AMax.game.GameManager;

import javax.sound.sampled.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {

    private Clip clip = null;
    private FloatControl gainControl;

    public SoundClip(String path) {
        try {

            InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedInput = new BufferedInputStream(audioSrc);
            //ais = audio input stream
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedInput);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16, baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(), false);

            //decode audio input stream
            AudioInputStream decodeAis = AudioSystem.getAudioInputStream(decodeFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(decodeAis);

            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void play(){
        if(clip == null){
            return;
        }
            stop();
            clip.setFramePosition(0);
            while (!clip.isRunning()){
                clip.start();
            }
    }
    

    public void stop(){

        if(clip.isRunning()){
            clip.stop();
        }

    }

    public void close(){
        stop();
        clip.drain();
        clip.close();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public void setVolume(float value){
        gainControl.setValue(value);

    }

    public boolean isRunning(){
        return clip.isRunning();
    }


}
