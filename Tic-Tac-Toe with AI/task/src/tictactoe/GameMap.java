package tictactoe;

import static tictactoe.GameStatus.*;
import static tictactoe.Indicators.*;

import java.util.ArrayList;

public class GameMap {
  //String field; // like "XO___XO"
  short field; // 3^9 = 19683 so short will be enough;
  //lets take ternary system 0 = '_', 1=X ,2 = O

  GameMap (String stringFormat){
    /*
     *  not logic string format
     *  [13(s0) 23(s1) 33(s2)]
     *  [12(s3) 22(s4) 32(s5)]
     *  [11(s6) 21(s7) 31(s8)]
     *  */
    int counter = 0;
    short[][] f = new short[3][3];
    for (int i = 2; i >= 0; i--) {
      for (int j = 0; j < 3; j++) {
        f[i][j] = Indicators.getValue(stringFormat.charAt(counter));
//                System.out.printf("i=%d j=%d count=%d\n",i,j,counter);
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

  public GameMap(short field) {
    this.field = field;
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

  public short getfiled() {
    return field;
  }

  /*
   * x = 1..3, y = 1..3;
   */

  public Indicators getCell(Coordinates coordinates) {
    final int x = coordinates.x;
    final int y = coordinates.y;

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

  public boolean isCellEmpty(Coordinates coordinates) {
    return getCell(coordinates) == EMPTY;
  }

  private void setField(Indicators[][] arr){
    field = 0;
    for (int i = 2; i >= 0; i--) {
      for (int j = 2; j >= 0; j--) {
        field = (short)((field * 3) + arr[i][j].getValue());
      }
    }
  }

  public void setCell(Coordinates coordinates, Indicators condition) {
    final int x = coordinates.x;
    final int y = coordinates.y;
    if ( x > 3 || x < 1 || y > 3 || y < 1) {
      System.exit(1); //todo change to Exception;
    }
    //todo try to remake without array using
    Indicators[][] arr = toArray();
    arr[y-1][x-1] = condition;
    setField(arr);
  }

  /*
   * X - X is winner
   * O - O is winner
   * EMPTY - have't winner
   * */
  public Indicators getWinner() {
    Indicators[][] f= toArray();
    // check for wins

    for (int i = 0; i < 3; i++) {
      //columns
      if (f[0][i] == f[1][i] && f[1][i] == f[2][i] && f[0][i] != EMPTY) {
        if (f[0][i] == X) {
          return X;
        } else {
          return O;
        }
      }
      //rows
      if (f[i][0] == f[i][1] && f[i][1] == f[i][2] && f[i][0] != EMPTY) {
        if (f[i][0] == X) {
          return X;
        } else {
          return O;
        }
      }
    }

    //diagonals
    if (((f[1][1] == f[0][0] && f[1][1] == f[2][2])
        || (f[1][1] == f[0][2] && f[1][1] == f[2][0]))
        && f[1][1] != EMPTY) {
      if (f[1][1] == X) {
        return X;
      } else {
        return O;
      }
    }
    return EMPTY;
  }

  public GameStatus getGameStatus() {

    Indicators winner = getWinner();

    switch (winner) {
      case X:
        return X_WIN;
      case O:
        return O_WIN;
    }

    Indicators[][] f= toArray();
    // check for finished and draw.
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
    // may be not representative when creating test fields
    if (xCount == oCount ) {
      return X_TURN;
    }

    return O_TURN;
  }

  public ArrayList<Coordinates> getEmpties() {
    ArrayList<Coordinates> res = new ArrayList<>();
    Indicators[][] array = toArray();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (array[i][j] == EMPTY) {
          res.add(new Coordinates(j + 1, i + 1));
        }
      }
    }
    return res;
  }

  public boolean haveEmpties() {
    short f = this.field;
    for (int i = 0; i < 9; i++) {
      if (f % 3 == EMPTY.getValue()) {
        return true;
      }
      f /= 3;
    }
    return false;
  }
}
