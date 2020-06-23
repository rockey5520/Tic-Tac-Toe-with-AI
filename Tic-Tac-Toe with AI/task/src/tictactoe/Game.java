package tictactoe;
import static tictactoe.Indicators.*;

public class Game {
  private GameMap field;
  private Indicators turn;
  private Robot xPlayer = null;
  private Robot oPlayer = null;

  public void setPlayers(Robot xPlayer, Robot oPlayer) {
    this.xPlayer= xPlayer;
    this.oPlayer = oPlayer;
  }

  Game() {
    field = new GameMap("_________");
    turn = X;
  }

  public void start() {
    //Ready to start?
    if (xPlayer == null || oPlayer == null) {
      System.out.print("Players are not initialized!");
      System.exit(1);
    }

    //main Loop
    boolean playGame = true;
    Coordinates coordinates = null;
    GameStatus status = GameStatus.X_TURN;

    while (playGame) {

      showfield();
      switch (turn) {
        case X:
          xPlayer.makeMove();
          break;
        case O:
          oPlayer.makeMove();
          break;
      }
            /*switch (turn) {
                case X:
                    coordinates = xPlayer.getMove();
                    break;
                case O:
                    coordinates = oPlayer.getMove();
                    break;
            }
            if (!makeMove(coordinates)) {
                System.out.print("Error occupied when try to make move!");
                System.exit(1);
            }*/

      status = getStatus();
      switch (status) {
        case X_WIN:
        case O_WIN:
        case DRAW:
          playGame = false;
          break;
      }
    }

    showfield();
    System.out.println(status);
  }

  public void showfield() {
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

  public void setField(String stringFormat) {
    field = new GameMap(stringFormat);
  }

  public GameMap getField() {
    return field;
  }

  public GameStatus getStatus() {
    return field.getGameStatus();
  }

  public boolean makeMove(Coordinates coordinates) {
    if (coordinates.isValid() && field.isCellEmpty(coordinates)) {
      field.setCell(coordinates, turn);
      nextTurn();
      return true;
    }
    return false;
  }

  private void nextTurn() {
    if (turn == X) {
      turn = O;
    } else {
      turn = X;
    }
  }

  public Indicators getCurrentTurn() {
    return turn == X? X : O; // protect turn field.
  }
}
