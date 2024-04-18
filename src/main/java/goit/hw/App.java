package goit.hw;

import goit.hw.game.TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner userInput = new Scanner(System.in)) {
            TicTacToe game = new TicTacToe();
            game.startGame(userInput);
        } catch (InputMismatchException ime) {
            System.out.println("Please enter only number 1-9!");
        }
    }
}