package tictactoe;

public enum Indicators {
  EMPTY('_', (short) 0),
  X('X', (short) 1),
  O('O', (short) 2);

  private char name;
  private short value;

  Indicators(char name, short value){
    this.value = value;
    this.name = name;
  }

  public short getValue() {
    return value;
  }

  public char getName() {
    return name;
  }

  public static short getValue(char name) {
    for (Indicators condition : Indicators.values()) {
      if (condition.getName() == name) {
        return condition.getValue();
      }
    }
    return 0;
  }

  public static char getName(short value) {
    for (Indicators condition : Indicators.values()) {
      if (condition.getValue() == value) {
        return condition.getName();
      }
    }
    return 0;
  }

  public static Indicators getCondition(short value) {
    for (Indicators condition : Indicators.values()) {
      if (condition.getValue() == value) {
        return condition;
      }
    }
    return EMPTY;
  }

  @Override
  public String toString() {
    return String.valueOf(name);
  }
}
