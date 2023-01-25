package characters;

import characters.Player;
import main_place.Keyreader;
import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testImageSwapPerUpdate() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        Keyreader kReader = new Keyreader();
        Player player = new Player(mp, kReader);
        assertEquals(10, player.ImageSwapPerUpdate);
    }

    @Test
    void testPlayerMoveSpeed() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        Keyreader kReader = new Keyreader();
        Player player = new Player(mp, kReader);
        assertEquals(0.0625f, player.PlayerMoveSpeed);
    }

    @Test
    void testSetDefault() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        Keyreader kReader = new Keyreader();
        Player player = new Player(mp, kReader);
        player.setdefault();
        assertEquals(12, player.getX());
        assertEquals(12, player.getY());
        assertEquals(6, player.fullHPvalue);
        assertEquals(6, player.currHPvalue);
    }

    @Test
    void testMainPlayer_is_dead() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        Keyreader kReader = new Keyreader();
        Player player = new Player(mp, kReader);
        player.setdefault();
        player.currHPvalue -= 6;
        boolean bool = player.MainPlayer_is_dead();
        assertEquals(true, bool);
        player.currHPvalue += 6;
        bool = player.MainPlayer_is_dead();
        assertEquals(false, bool);
    }



}