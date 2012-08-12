/**
 * 
 */
package de.guerda.tonekeyboard;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * @author philip
 * 
 */
public class SoundController {

  private SourceDataLine line;

  private void makeSomeSounds() throws LineUnavailableException {
    System.out.println("Playing sound");
    final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
    SourceDataLine line = AudioSystem.getSourceDataLine(af);
    line.open(af, Note.SAMPLE_RATE);
    line.start();
    for (Note n : Note.values()) {
      play(n, 500);
      play(Note.REST, 10);
    }
    line.drain();
    line.close();

    System.out.println("Done");
  }

  public void play(Note note, int ms) {
    ms = Math.min(ms, Note.SECONDS * 1000);
    int length = Note.SAMPLE_RATE * ms / 1000;
    line.write(note.data(), 0, length);
  }

  public static void main(String[] args) throws Exception {
    SoundController tmpController = new SoundController();
    tmpController.init();

    tmpController.makeSomeSounds();
    
    tmpController.shutdown();
  }

  public void init() {
    final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
    try {
      line = AudioSystem.getSourceDataLine(af);
      line.open(af, Note.SAMPLE_RATE);
      line.start();
    } catch (LineUnavailableException e) {
      throw new IllegalStateException("", e);
    }
  }

  public void shutdown() {
    line.drain();
    line.close();

  }
}
