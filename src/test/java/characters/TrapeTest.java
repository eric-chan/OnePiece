package characters;

import characters.Trape;
import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TrapeTest {
    @Test
    void testTrapeInitial() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        int x, y;
        x = 1;
        y = 1;
        Trape trape = new Trape(mp, x, y);
        assertEquals(2000, trape.BubbleTime);
        assertEquals(0, trape.lastColTime);
        assertEquals(false, trape.lastCol);
    }
}