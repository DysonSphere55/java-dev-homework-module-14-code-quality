package goit.hw.game;

import goit.hw.ui.TicTacToeUI;
import goit.hw.ui.TicTacToeUIImpl;

import java.util.Scanner;

import static goit.hw.constants.MessageConstants.ENTER_ANOTHER_MESSAGE;
import static goit.hw.constants.MessageConstants.GREETINGS_MESSAGE;
import static goit.hw.constants.MessageConstants.INVALID_INPUT_MESSAGE;
import static goit.hw.constants.MessageConstants.WINNER_MESSAGE;
import static goit.hw.constants.MessageConstants.LOSER_MESSAGE;
import static goit.hw.constants.MessageConstants.DRAW_MESSAGE;


public class TicTacToe {
    private static final int PLAYER_WON = 1;
    private static final int PLAYER_LOST = 2;
    private static final int PLAYER_DRAW = 3;
    private static final int NEW_GAME = 0;
    private boolean isBoxAvailable;
    private byte endGameCondition;
    private final TicTacToeUI gameUI;

    public TicTacToe() {
        gameUI = new TicTacToeUIImpl();
        isBoxAvailable = false;
        endGameCondition = NEW_GAME;
    }

    public void startGame(Scanner userInput) {
        System.out.println(GREETINGS_MESSAGE);
        while (true) {
            gameUI.printInitialBox();
            if (checkEndGameConditions()) {
                break;
            }
            while (true) {
                if (processUserInput(userInput)) {
                    break;
                }
            }
            if (isPlayerWinner()) {
                endGameCondition = PLAYER_WON;
                continue;
            }
            checkBoxAvailability();
            if (!isBoxAvailable) {
                endGameCondition = PLAYER_DRAW;
                continue;
            }
            makeComputerMove();
            if (isPlayerLoser()) {
                endGameCondition = PLAYER_LOST;
            }
        }
    }

    private void checkBoxAvailability() {
        isBoxAvailable = false;
        for (byte i = 0; i < 9; i++) {
            if (gameUI.getBox()[i] != 'X' && gameUI.getBox()[i] != 'O') {
                isBoxAvailable = true;
                break;
            }
        }
    }

    private void makeComputerMove() {
        while (true) {
            byte randomNumber = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (gameUI.getBox()[randomNumber - 1] != 'X' && gameUI.getBox()[randomNumber - 1] != 'O') {
                gameUI.getBox()[randomNumber - 1] = 'O';
                break;
            }
        }
    }

    private boolean isPlayerLoser() {
        return (gameUI.getBox()[0] == 'O' && gameUI.getBox()[1] == 'O' && gameUI.getBox()[2] == 'O')
                || (gameUI.getBox()[3] == 'O' && gameUI.getBox()[4] == 'O' && gameUI.getBox()[5] == 'O')
                || (gameUI.getBox()[6] == 'O' && gameUI.getBox()[7] == 'O' && gameUI.getBox()[8] == 'O')
                || (gameUI.getBox()[0] == 'O' && gameUI.getBox()[3] == 'O' && gameUI.getBox()[6] == 'O')
                || (gameUI.getBox()[1] == 'O' && gameUI.getBox()[4] == 'O' && gameUI.getBox()[7] == 'O')
                || (gameUI.getBox()[2] == 'O' && gameUI.getBox()[5] == 'O' && gameUI.getBox()[8] == 'O')
                || (gameUI.getBox()[0] == 'O' && gameUI.getBox()[4] == 'O' && gameUI.getBox()[8] == 'O')
                || (gameUI.getBox()[2] == 'O' && gameUI.getBox()[4] == 'O' && gameUI.getBox()[6] == 'O');
    }

    private boolean isPlayerWinner() {
        return (gameUI.getBox()[0] == 'X' && gameUI.getBox()[1] == 'X' && gameUI.getBox()[2] == 'X')
                || (gameUI.getBox()[3] == 'X' && gameUI.getBox()[4] == 'X' && gameUI.getBox()[5] == 'X')
                || (gameUI.getBox()[6] == 'X' && gameUI.getBox()[7] == 'X' && gameUI.getBox()[8] == 'X')
                || (gameUI.getBox()[0] == 'X' && gameUI.getBox()[3] == 'X' && gameUI.getBox()[6] == 'X')
                || (gameUI.getBox()[1] == 'X' && gameUI.getBox()[4] == 'X' && gameUI.getBox()[7] == 'X')
                || (gameUI.getBox()[2] == 'X' && gameUI.getBox()[5] == 'X' && gameUI.getBox()[8] == 'X')
                || (gameUI.getBox()[0] == 'X' && gameUI.getBox()[4] == 'X' && gameUI.getBox()[8] == 'X')
                || (gameUI.getBox()[2] == 'X' && gameUI.getBox()[4] == 'X' && gameUI.getBox()[6] == 'X');
    }

    private boolean processUserInput(Scanner userInput) {
        byte input = userInput.nextByte();
        if (input > 0 && input < 10) {
            if (gameUI.getBox()[input - 1] == 'X' || gameUI.getBox()[input - 1] == 'O') {
                System.out.println(ENTER_ANOTHER_MESSAGE);
            }
            else {
                gameUI.getBox()[input - 1] = 'X';
                return true;
            }
        }
        System.out.println(INVALID_INPUT_MESSAGE);
        return false;
    }

    private boolean checkEndGameConditions() {
        switch (endGameCondition) {
            case PLAYER_WON -> {
                System.out.println(WINNER_MESSAGE);
                return true;
            }
            case PLAYER_LOST -> {
                System.out.println(LOSER_MESSAGE);
                return true;
            }
            case PLAYER_DRAW -> {
                System.out.println(DRAW_MESSAGE);
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
