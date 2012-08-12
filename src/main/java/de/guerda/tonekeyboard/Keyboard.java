/**
 * 
 */
package de.guerda.tonekeyboard;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

/**
 * @author philip
 * 
 */
public class Keyboard extends JPanel {

  /**
   * Default serial version UID.
   */
  private static final long serialVersionUID = 1L;

  private Logger logger;

  private final HashMap<Integer, JToggleButton> buttonMap;

  //@formatter:off
  private final String[] letters = new String[] {
      "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
      "Q", "W", "E", "R", "T", "Z", "U", "I", "O", "P", 
      "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ö", 
      "Y", "X", "C", "V", "B", "N", "M", ",", ".", "-"};
  
  private final int[] keycodes = new int[] {
      49,  50,  51,  52,  53,  54,  55,  56,  57,  48, 
      81,  87,  69,  82,  84,  90,  85,  73,  79,  80,
      65,  83,  68,  70,  71,  72,  74,  75,  76,  0,
      89,  88,  67,  86,  66,  78,  77,  44,  46,  45
  };
  //@formatter:on

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

  public Keyboard() {
    buttonMap = new HashMap<Integer, JToggleButton>();

    MigLayout tmpMigLayout = new MigLayout();
    setLayout(tmpMigLayout);

    // Initialize all key buttons
    for (int i = 0; i < letters.length; i++) {
      JToggleButton tmpButton = new JToggleButton();
      tmpButton.setText(letters[i]);
      tmpButton.setActionCommand("" + i);
      buttonMap.put(keycodes[i], tmpButton);
      tmpButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent aE) {
          handleButtonClick(aE);
        }

      });
      String tmpLayoutString = "";
      if (i % 10 == 9) {
        tmpLayoutString = "wrap";
      }
      add(tmpButton, tmpLayoutString);
    }

    // Create action listener to update the button settings
    addKeyListener(new KeyListener() {

      public void keyTyped(KeyEvent aE) {
        // Nothing to do
      }

      public void keyReleased(KeyEvent aE) {
        // Nothing to do
      }

      public void keyPressed(KeyEvent aE) {
        handleKeyPressed(aE);
      }

    });

    setFocusable(true);
    requestFocus();
  }

  /**
   * @param aE
   */
  public void handleKeyPressed(KeyEvent aE) {
    int tmpKeyCode = aE.getKeyCode();
    JToggleButton tmpButton = buttonMap.get(tmpKeyCode);
    if (tmpButton != null) {
      tmpButton.doClick();
    }
  }

  private void handleButtonClick(ActionEvent aE) {
    getLogger().debug("Button pressed: " + ((JToggleButton) aE.getSource()).getText());
  }

  public List<Integer> getValue() {
    ArrayList<Integer> tmpResult = new ArrayList<Integer>();
    Component[] tmpComponents = getComponents();
    for (Component tmpComponent : tmpComponents) {
      if (tmpComponent instanceof JToggleButton) {
        JToggleButton tmpJToggleButton = (JToggleButton) tmpComponent;
        if (tmpJToggleButton.isSelected()) {
          int tmpCode = Integer.parseInt(tmpJToggleButton.getActionCommand());
          tmpResult.add(tmpCode);
        }
      }
    }
    return tmpResult;
  }
}
