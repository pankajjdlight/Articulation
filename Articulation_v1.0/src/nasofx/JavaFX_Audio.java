/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class JavaFX_Audio extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Play 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                playSound();
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("java-buddy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void playSound(){
        AudioInputStream audio = null;
        try {
            String clipPath = "/Users/DoD/Desktop/Speech_files/Tarun_s4.wav";
            audio = AudioSystem.getAudioInputStream(new File(clipPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(JavaFX_Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaFX_Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
                Logger.getLogger(JavaFX_Audio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audio.close();
            } catch (IOException ex) {
                Logger.getLogger(JavaFX_Audio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
