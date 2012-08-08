/**
 * 
 */
package de.guerda.tonekeyboard;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
    Keyboard tmpKeyboard = new Keyboard();
    add(tmpKeyboard);
  }

}
