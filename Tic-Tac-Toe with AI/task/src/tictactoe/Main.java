package tictactoe;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean playGame = true;
        while (playGame){
            String command = null;
            String xDifficult = null;
            String oDifficult = null;
            boolean invalidCommand = true;

            while (invalidCommand) {
                System.out.print("Input command: ");
                String input = scanner.nextLine();
                Scanner parseCommand = new Scanner(input);

                try {

                    if (parseCommand.hasNext("start|exit")) {
                        command = parseCommand.next();
                    } else {
                        throw new InputMismatchException("");
                    }

                    switch (command) {
                        case "exit":
                            invalidCommand = false;
                            playGame = false;
                            break;
                        case "start":

                            xDifficult = parseCommand.next("easy|user|medium|hard");
                            oDifficult = parseCommand.next("easy|user|medium|hard");

                            invalidCommand = false;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Bad parameters!");
                }
            }
            if ("start".equals(command)) {
                Game ttGame = new Game();
                Robot xPlayer = RobotFabrics.CreateRobot(ttGame, xDifficult);
                Robot oPlayer = RobotFabrics.CreateRobot(ttGame, oDifficult);

                ttGame.setPlayers(xPlayer, oPlayer);

                ttGame.start();
            }
        }
    }
}
