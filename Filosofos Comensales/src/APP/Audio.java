package APP;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Audio {
    private Player player;
    public Audio(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            player = new Player(fileInputStream);
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        new Thread(() -> {
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Detener la reproducción
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproducir en bucle
        }
    }
}
