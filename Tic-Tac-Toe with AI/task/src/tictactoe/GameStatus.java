package tictactoe;

public enum  GameStatus {
  X_TURN("Game not finished"),
  O_TURN("Game not finished"),
  DRAW("Draw"),
  X_WIN("X wins"),
  O_WIN("O wins ");

  private String name;

  GameStatus(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
