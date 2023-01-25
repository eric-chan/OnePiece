package characters;

import characters.DynamicObject;
import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DynamicObjectTest {
    @Test
    void testWalkNumber() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        DynamicObject dObj = new DynamicObject(mp);
        assertEquals(1, dObj.walkNumber);
    }

    @Test
    void testWalkCounter() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        DynamicObject dObj = new DynamicObject(mp);
        assertEquals(0, dObj.walkcounter);
    }

    @Test
    void testPoint() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        DynamicObject dObj = new DynamicObject(mp);
        assertEquals(0, dObj.point);
    }

    @Test
    void testSpeedXandY() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        DynamicObject dObj = new DynamicObject(mp);
        assertEquals(0, dObj.getSpeedX());
        assertEquals(0, dObj.getSpeedY());
    }

    @Test
    void setSpeedX() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        DynamicObject dObj = new DynamicObject(mp);
        dObj.setSpeedX(2);
        assertEquals(2, dObj.getSpeedX());
    }

    @Test
    void testgetDirection() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        DynamicObject dObj = new DynamicObject(mp, 1,2);
        DynamicObject dObj1 = new DynamicObject(mp, 2,1);

    }
}
