package objects;

import main_place.MyPanel;
import objects.StaticEnvironmentalObject;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StaticEnvironmentalObjectTest {
    @Test
    void testInitialization() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        StaticEnvironmentalObject staticEObj = new StaticEnvironmentalObject(mp);
        String displayType = staticEObj.getDisplayType();
        assertEquals("None", displayType);
        assertEquals(1, staticEObj.getSizeX());
        assertEquals(1, staticEObj.getSizeY());
    }

    @Test
    void testInitialization1() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        StaticEnvironmentalObject staticEObj = new StaticEnvironmentalObject(mp, 1.1f, 2.2f, 1, 2, "One");
        String displayType = staticEObj.getDisplayType();
        assertEquals("One", staticEObj.getDisplayType());
        assertEquals(1, staticEObj.getSizeX());
        assertEquals(2, staticEObj.getSizeY());
    }

}