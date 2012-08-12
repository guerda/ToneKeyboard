package de.guerda.tonekeyboard;

enum Note {

  REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5, A5$, B5, C5, C5$, D5, D5$, E5, F5, F5$, G5, G5$;
  public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
  public static final int SECONDS = 2;
  private final byte[] sin = new byte[SECONDS * SAMPLE_RATE];

  Note() {
    int n = this.ordinal();
    if (n > 0) {
      double exp = ((double) n - 1) / 12d;
      double f = 440d * Math.pow(2d, exp);
      for (int i = 0; i < sin.length; i++) {
        double period = SAMPLE_RATE / f;
        double angle = 2.0 * Math.PI * i / period;
        sin[i] = (byte) (Math.sin(angle) * 127f);
      }
    }
  }

  public byte[] data() {
    return sin;
  }
}