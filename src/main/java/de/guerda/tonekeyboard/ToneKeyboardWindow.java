/**
 * 
 */
package de.guerda.tonekeyboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

/**
 * @author philip
 * 
 */
public class ToneKeyboardWindow extends JFrame {

  /**
   * Default serial version UID.
   */
  private static final long serialVersionUID = 1L;
  private Logger logger;
  private Keyboard keyboard;
  private SoundController soundController;

  /**
   * Creates and returns a logger for this class.
   * 
   * @return a logger for this class
   */
  private Logger getLogger() {
    if (logger == null) {
      logger = Logger.getLogger(getClass());
    }
    return logger;
  }

  public ToneKeyboardWindow() {
    super();
    initializeComponents();

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
      getLogger().debug("Could not set look and feel", e);
    } catch (InstantiationException e) {
      getLogger().debug("Could not set look and feel", e);
    } catch (IllegalAccessException e) {
      getLogger().debug("Could not set look and feel", e);
    } catch (UnsupportedLookAndFeelException e) {
      getLogger().debug("Could not set look and feel", e);
    }

    setVisible(true);
    setBounds(1000, 100, 800, 700);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void initializeComponents() {
    keyboard = new Keyboard();
    setLayout(new MigLayout());
    getContentPane().add(keyboard);

    JButton tmpJButton = new JButton("Play");
    tmpJButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent aEvent) {
        handlePlayButtonClick(aEvent);
      }
    });

    getContentPane().add(tmpJButton);
  }

  private void handlePlayButtonClick(ActionEvent aEvent) {
    List<Integer> tmpValue = keyboard.getValue();
    for (Integer tmpInteger : tmpValue) {
      getSoundController().play(Note.A4, 100);
    }
    

  }

  private SoundController getSoundController() {
    return soundController;
  }

  public void setSoundController(SoundController aSoundController) {
    soundController = aSoundController;
  }
}
