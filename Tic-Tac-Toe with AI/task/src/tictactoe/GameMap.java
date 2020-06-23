package tictactoe;

import static tictactoe.Indicators.*;

public class GameMap {
  private Game field;
  GameMap() {
    field = new Game("_________");
  }


  public void showGame() {
    Indicators[][] arr = field.toArray();
    System.out.print("---------\n");
    for (int i = 2; i > -1 ; i--) {
      System.out.print("| ");
      for (int j = 0; j < 3; j++) {
        System.out.print(arr[i][j] == EMPTY ? "  " : arr[i][j]+  " ");
      }
      System.out.print("|\n");
    }
    System.out.print("---------\n");
  }

  public void setGame(String stringFormat) {
    field = new Game(stringFormat);
  }

  public Game getField() {
    return field;
  }

  public boolean makeMoveXY(int x, int y) {
    if (field.isEmpty(x, y)) {
      return false;
    }
    Indicators indicators;
    GameStatus status = field.getGameStatus();
    switch (status) {
      case X_TURN:
        indicators = X;
        break;
      case O_TURN:
        indicators = O;
        break;
      default:
        return false;
    }

    field.setCell(x, y, indicators);
    return true;
  }

  public GameStatus getStatus() {
    return field.getGameStatus();
  }
}
