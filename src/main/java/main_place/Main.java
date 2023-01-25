package main_place;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        JFrame game_frame = new JFrame();
        game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //frame settings
        game_frame.setTitle("One piece");
        game_frame.getContentPane().setLayout(new BorderLayout());

        //panel settings
        MyPanel panel = new MyPanel(game_frame);

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int panelWidth = 16 / 2 * 2 * panel.cameraCtl.getPixelPerTile();
        int panelHeight = 10 / 2 * 2 * panel.cameraCtl.getPixelPerTile();

        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        game_frame.setLocation((screenWidth - panelWidth) / 2,(screenHeight - panelHeight) / 2);
        //game_frame.setResizable(false);
        game_frame.add(panel);
        game_frame.pack();
        game_frame.setVisible(true);

        panel.start_thread();
    }
}
