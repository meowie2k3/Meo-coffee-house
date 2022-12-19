package Entity;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SimplyPlayer {

    public static void main(String[] args) {
        new SimplyPlayer();
    }

    public SimplyPlayer() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private Clip clip;
        private JTextField audioFile;
        private JButton play;
        private JButton stop;
        private int lastFrame;

        public TestPane() {
            setLayout(new GridBagLayout());

            audioFile = new JTextField(20);
            play = new JButton(">");
            stop = new JButton("[]");

            JPanel controls = new JPanel();
            controls.add(play);
            controls.add(stop);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(audioFile, gbc);
            gbc.gridy++;
            add(controls, gbc);

            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (clip == null) {
                        try {
                            loadClip(new File(audioFile.getText()));
                            clip.start();
                            clip.addLineListener(new LineListener() {
                                @Override
                                public void update(LineEvent event) {
                                    if (event.getType().equals(Type.START)) {
                                        play.setText("||");
                                    } else if (event.getType().equals(Type.OPEN)) {
                                        System.out.println("Open");
                                    } else if (event.getType().equals(Type.STOP)) {
                                        play.setText(">");
                                    } else if (event.getType().equals(Type.CLOSE)) {
                                        play.setText(">");
                                    }
                                }
                            });
                            play.setText("||");
                        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TestPane.this, "Failed to load audio clip", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                        if (clip.isRunning()) {
                            lastFrame = clip.getFramePosition();
                            clip.stop();
                        } else {
                            if (lastFrame < clip.getFrameLength()) {
                                clip.setFramePosition(lastFrame);
                            } else {
                                clip.setFramePosition(0);
                            }
                            clip.start();
                        }

                    }
                }
            });

            stop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (clip != null) {
                        lastFrame = 0;
                        clip.stop();
                    }
                }
            });
        }

        protected void loadClip(File audioFile) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            this.clip = (Clip) AudioSystem.getLine(info);
            this.clip.open(audioStream);

        }
    }

}