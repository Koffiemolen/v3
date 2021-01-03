package sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFactory {

    private Clip clip;

    public SoundFactory() {
    }

    public void alienBomb() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/alienBeam.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void explosion() {
        try {
            AudioInputStream explosionInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/damageSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(explosionInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alienBoss() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/bossSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alienBonus() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/bonusSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void projectile() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/bulletSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void death() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/deathSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hitmarker() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/hitmarkerSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void levelUp() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/levelUpSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shield() {
        try {
            AudioInputStream laserInpuStream = AudioSystem.getAudioInputStream(new File("audio/sounds/shieldSound.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(laserInpuStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
