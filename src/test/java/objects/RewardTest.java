package objects;

import main_place.MyPanel;
import objects.Reward;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RewardTest {
    @Test
    void testReward() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        int x, y;
        x = 1;
        y = 1;
        String rwdType = "type1";
        Reward rwd = new Reward(mp, x, y, "type1");
        assertEquals("type1", rwd.rwdType);
    }

}