package tictactoe;
import static tictactoe.Indicators.*;
import static tictactoe.GameStatus.*;

public class Game {
  short field;

  Game(String stringFormat){
    int counter = 0;
    short[][] f = new short[3][3];
    for (int i = 2; i >= 0; i--) {
      for (int j = 0; j < 3; j++) {
        f[i][j] = Indicators.getValue(stringFormat.charAt(counter));
        counter++;
      }
    }
    field = 0;

    for (int i = 2; i >= 0; i--) {
      for (int j = 2; j >= 0; j--) {
        field = (short)((field * 3) + f[i][j]);
      }
    }
  }

  public Indicators[][] toArray() {
    short f = this.field;
    Indicators[][] res = new Indicators[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        res[i][j] = Indicators.getCondition((short)(f % 3));
        f /= 3;
      }
    }
    return res;
  }
/*
  public short getfiled() {
    return field;
  }*/
  public Indicators getCellInXY(int x, int y) {
    if ( x > 3 || x < 1 || y > 3 || y < 1) {
      System.exit(1); //todo change to Exception;
    }
    switch ((field / (short) Math.pow(3, (y - 1) * 3 + x - 1)) % 3) {
      case 1:
        return X;
      case 2:
        return O;
    }
    return EMPTY;
  }

  public boolean isEmpty(int x, int y) {
    return getCellInXY(x, y) != EMPTY;
  }

  private void setGame(Indicators[][] arr){
    field = 0;
    for (int i = 2; i >= 0; i--) {
      for (int j = 2; j >= 0; j--) {
        field = (short)((field * 3) + arr[i][j].getValue());
      }
    }
  }

  public void setCell(int x, int y, Indicators indicators) {
    if ( x > 3 || x < 1 || y > 3 || y < 1) {
      System.exit(1); //todo change to Exception;
    }

    Indicators[][] arr = toArray();
    arr[y-1][x-1] = indicators;
    setGame(arr);
  }

  public GameStatus getGameStatus() {
    Indicators[][] f= toArray();
    for (int i = 0; i < 3; i++) {
      if (f[0][i] == f[1][i] && f[1][i] == f[2][i] && f[0][i] != EMPTY) {
        if (f[0][i] == X) {
          return X_WIN;
        } else {
          return O_WIN;
        }
      }
      if (f[i][0] == f[i][1] && f[i][1] == f[i][2] && f[i][0] != EMPTY) {
        if (f[i][0] == X) {
          return X_WIN;
        } else {
          return O_WIN;
        }
      }
    }


    if (((f[1][1] == f[0][0] && f[1][1] == f[2][2])
        || (f[1][1] == f[0][2] && f[1][1] == f[2][0]))
        && f[1][1] != EMPTY) {
      if (f[1][1] == X) {
        return X_WIN;
      } else {
        return O_WIN;
      }
    }


    int xCount = 0;
    int oCount = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        switch (f[i][j]) {
          case X:
            xCount++;
            break;
          case O:
            oCount++;
            break;
        }
      }
    }

    if (xCount + oCount == 9) {
      return DRAW;
    }

    if (xCount == oCount ) {
      return X_TURN;
    }

    return O_TURN;
  }
}
