package characters;

import characters.Monster;
import characters.Player;
import main_place.MyPanel;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    @Test
    void testRandomSpeedXandYInitial() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        Player player = new Player(mp, mp.keyreader);
        int x, y;
        x = 1;
        y = 1;
        Monster monster = new Monster(mp, x, y, player);
        assertEquals(0.0f, monster.getRandomSpeedX());
        assertEquals(0.0f, monster.getRandomSpeedY());
    }

    @Test
    void testLastRandomMovementUpdatesTS() throws UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame();
        MyPanel mp = new MyPanel(frame);
        Player player = new Player(mp, mp.keyreader);
        int x, y;
        x = 1;
        y = 1;
        Monster monster = new Monster(mp, x, y, player);
        assertEquals(0.0f, monster.getLastRandomMovementUpdateTS());
    }
}
