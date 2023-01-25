package main_place;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {

    Clip clip[];
    int SoundNumber = 10;
    String []s= new String[SoundNumber];
    File file ;
    public Sound() {
        clip = new Clip[SoundNumber];
        s[0]="src/main/java/Music/bgm3.wav";
        s[1]="src/main/java/Music/20220330175910.wav";
        s[2]="src/main/java/Music/20220330175910.wav";
        s[3]="src/main/java/Music/20220330175910.wav";
    }

    public int getSoundNumber() {
        return SoundNumber;
    }

    public File getFile() {
        return file;
    }

    public void SetFile (int i)  {
        try{
            file = new File (s[i]);
            AudioInputStream AIS = AudioSystem.getAudioInputStream(file);
            clip[i] = AudioSystem.getClip();
            clip[i].open(AIS);
        }
        catch (Exception e ) {
            System.out.println("Exception thrown by SetFile function, in class Sound");
        }
    }
    public void StartPlay(int i){
        clip[i].start();
    }
    public void SoundLoop(int i){
        clip[i].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void SoundStop(int i){
        clip[i].stop();
    }

    public void PLayMusic(int i){
        SetFile(i);
        StartPlay(i);
        SoundLoop(i);
    }

    public void PLayMusicOnce(int i){
        SetFile(i);
        StartPlay(i);
    }
}
