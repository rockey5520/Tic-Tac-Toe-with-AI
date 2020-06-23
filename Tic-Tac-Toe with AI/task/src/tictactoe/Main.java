package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String stringFormat = scanner.nextLine();
        GameMap gameMap = new GameMap();
        gameMap.setGame(stringFormat);
        gameMap.showGame();
        int x = 5;
        int y = 5;

        boolean validAnswer = false;
        do {
            System.out.print("Enter the coordinates: ");
            String position = scanner.nextLine();
            try {
                String[] arr = position.split("\\s+");
                x = Integer.parseInt(arr[0]);
                y = Integer.parseInt(arr[1]);

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You should enter 2 numbers!");
                continue;
            }

            if ( x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.print("Coordinates should be from 1 to 3!\n");
                continue;
            }

            if (gameMap.getField().isEmpty(x, y)) {
                System.out.print("This cell is occupied! Choose another one!\n");
                continue;
            }

            validAnswer = true;

        } while (!validAnswer);

        if (!gameMap.makeMoveXY(x, y)) {
            System.out.print("Error occupied when try to make move!");
            System.exit(1);
        }
        gameMap.showGame();
        System.out.println(gameMap.getStatus());
    }
}
