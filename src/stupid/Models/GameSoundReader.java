package stupid.Models;

import com.sun.media.jfxmedia.AudioClip;
import com.sun.media.sound.WaveFileReader;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.*;
import javax.xml.crypto.Data;
import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yuu on 7/16/2016.
 */
public class GameSoundReader {

    private Clip player;
    private AudioInputStream stream;


    public GameSoundReader(String soundRes)
    {
        try
        {
            stream = AudioSystem.getAudioInputStream(new File(soundRes));
            player = AudioSystem.getClip();
            player.open(stream);
        }catch (Exception e){
            System.out.println("Đéo đọc được");
        }

    }


    public void play()
    {
        player.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playOnce() {
        player.loop(0);
    }

    public void stop() {player.stop();}
}
