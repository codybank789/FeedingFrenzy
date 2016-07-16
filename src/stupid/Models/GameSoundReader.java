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
    private File inputFile;
    private DataLine.Info dataline;
    private AudioFormat format;
    private AudioInputStream stream;


    public GameSoundReader(String soundRes)
    {
        try
        {
            inputFile = new File(soundRes);
            stream = AudioSystem.getAudioInputStream(this.getClass().getResource(soundRes));
            player = AudioSystem.getClip();
            player.open(stream);
        }catch (Exception e){
            System.out.println("Đéo đọc được");
        }

    }


    public void playSound()
    {
        player.start();
    }
}
