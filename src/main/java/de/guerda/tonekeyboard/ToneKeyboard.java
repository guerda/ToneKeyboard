package de.guerda.tonekeyboard;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ToneKeyboard {
  private SoundController soundController;

  public static void main(String[] args) {
    ToneKeyboard tmpToneKeyboard = new ToneKeyboard();
    tmpToneKeyboard.run();
  }

  private void run() {
    soundController = new SoundController();
    soundController.init();
    ToneKeyboardWindow tmpWindow = new ToneKeyboardWindow();
    tmpWindow.addWindowListener(new WindowListener() {

      @Override
      public void windowOpened(WindowEvent aArg0) {
      }

      @Override
      public void windowIconified(WindowEvent aArg0) {
      }

      @Override
      public void windowDeiconified(WindowEvent aArg0) {
      }

      @Override
      public void windowDeactivated(WindowEvent aArg0) {
      }

      @Override
      public void windowClosing(WindowEvent aArg0) {
      }

      @Override
      public void windowClosed(WindowEvent aEvent) {
        handleWindowClosed(aEvent);
      }

      @Override
      public void windowActivated(WindowEvent aArg0) {
      }
    });
    tmpWindow.setSoundController(soundController);

  }

  private void handleWindowClosed(WindowEvent aEvent) {
    soundController.shutdown();
  }

}
