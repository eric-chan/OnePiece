package main_place;

import main_place.Sound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoundTest {
    @Test
    void testSoundNumber(){
        Sound sd = new Sound();
        assertEquals(10, sd.getSoundNumber());
    }

    @Test
    void testFileInitialization(){
        Sound sd = new Sound();
        boolean testIni = false;
        if(sd.getFile() != null){
            testIni = true;
        }
        assertEquals(false, testIni);
    }
}
