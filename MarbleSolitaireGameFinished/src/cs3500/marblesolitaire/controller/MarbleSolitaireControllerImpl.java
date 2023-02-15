package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class representing an implementation of a game controller that takes in input from a user
 * and transmits it to the model and view.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  final Readable in;

  /**
   * Constructor for the controller which takes in a model, view and readable.
   * @param model a game of marble solitaire.
   * @param view the text view of the game.
   * @param in the location that the input is taken from.
   * @throws IllegalArgumentException if any value is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Invalid Arguments.");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.in);
    ArrayList<Integer> positions = new ArrayList<>();
    try {
      this.showGame();
      while (!model.isGameOver()) {
        try {
          if (scan.hasNextInt()) {
            int position = scan.nextInt();
            positions.add(position);
          }
          else {
            String input = scan.next();
            if (input.equalsIgnoreCase("q")) {
              view.renderMessage("Game quit!" + System.lineSeparator());
              view.renderMessage("State of game when quit:" + System.lineSeparator());
              view.renderBoard();
              view.renderMessage(System.lineSeparator());
              view.renderMessage("Score: " + model.getScore());
              return;
            }
          }
          if (positions.size() == 4) {
            int fromRow = positions.get(0);
            int fromCol = positions.get(1);
            int toRow = positions.get(2);
            int toCol = positions.get(3);
            try {
              model.move(fromRow - 1,fromCol - 1, toRow - 1, toCol - 1);
              this.showGame();
            }
            catch (IllegalArgumentException e) {
              view.renderMessage("Invalid move. Play again." + System.lineSeparator());
              this.showGame();
            }
            positions = new ArrayList<>();
          }
        }
        catch (InputMismatchException | NumberFormatException e) {
          view.renderMessage("Re-enter value" + System.lineSeparator());
        }
        catch (NoSuchElementException e) {
          throw new IllegalStateException("Insufficient values");
        }
      }
      if (model.isGameOver()) {
        view.renderMessage("Game over!" + System.lineSeparator());
        this.showGame();
      }
    } catch (IOException e) {
      throw new IllegalStateException("Error reading input");
    }
  }

  private void showGame() throws IOException {
    view.renderBoard();
    view.renderMessage(System.lineSeparator());
    view.renderMessage("Score: " + model.getScore() + System.lineSeparator());
  }
}
