package uk.ac.city.adbt065;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * A control panel for several options such as pausing and resuming the game
 */

public class manualControlPanel extends JFrame implements ActionListener {
    private final JComboBox<String> comboBox;
    private final View view;
    public manualControlPanel(View view){
        this.view = view;
        String[] gameSettings = {"Pause", "Resume", "Quit", "Go to next level", "Go back a level", "Load level 1", "Load level 2", "Load level 3", "Load level 4"};
        comboBox = new JComboBox<>(gameSettings);
        comboBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File f;
        if(e.getSource() == comboBox){
            if(comboBox.getSelectedItem()=="Pause"){
                view.getGl().setPause(true);
            } else if(comboBox.getSelectedItem()=="Resume"){
                view.getGl().setPause(false);
            } else if(comboBox.getSelectedItem()=="Quit"){
                System.exit(0);
            } else if(comboBox.getSelectedItem()=="Go to next level"){
                view.getGl().getGame().goToNextLevel();
            } else if(comboBox.getSelectedItem()=="Go back a level"){
                view.getGl().getGame().goBackLevel();
            } else if(comboBox.getSelectedItem()=="Load level 1"){
                gameLevel gl;
                try {
                    f = new File("data/level1.txt");
                    if(f.isFile()){
                        gl = gameSaverLoader.load("data/level1.txt", view.getGl().getGame());
                        view.getGl().getGame().setLevel(gl);
                    } else{
                        JOptionPane.showMessageDialog(view.getGl().getGame().getFrame(), "Level 1 save file does not exist");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(comboBox.getSelectedItem()=="Load level 2"){
                gameLevel gl;
                try {
                    f = new File("data/level2.txt");
                    if(f.isFile()){
                        gl = gameSaverLoader.load("data/level2.txt", view.getGl().getGame());
                        view.getGl().getGame().setLevel(gl);
                    } else{
                        JOptionPane.showMessageDialog(view.getGl().getGame().getFrame(), "Level 2 save file does not exist");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(comboBox.getSelectedItem()=="Load level 3"){
                gameLevel gl;
                try {
                    f = new File("data/level3.txt");
                    if(f.isFile()){
                        gl = gameSaverLoader.load("data/level3.txt", view.getGl().getGame());
                        view.getGl().getGame().setLevel(gl);
                    } else{
                        JOptionPane.showMessageDialog(view.getGl().getGame().getFrame(), "Level 3 save file does not exist");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if(comboBox.getSelectedItem()=="Load level 4"){
                gameLevel gl;
                try {
                    f = new File("data/level4.txt");
                    if(f.isFile()){
                        gl = gameSaverLoader.load("data/level4.txt", view.getGl().getGame());
                        view.getGl().getGame().setLevel(gl);
                    } else{
                        JOptionPane.showMessageDialog(view.getGl().getGame().getFrame(), "Level 4 save file does not exist");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
