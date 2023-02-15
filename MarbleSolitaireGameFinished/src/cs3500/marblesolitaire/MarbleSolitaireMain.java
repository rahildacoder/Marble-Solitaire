package cs3500.marblesolitaire;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Main class for Marble Solitaire to run the game.
 */
public class MarbleSolitaireMain {
  /**
   * Creates a default model, view and controller for the marble solitaire game and runs the game.
   * @param args by convention.
   */
  public static void main(String []args) throws IOException {
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    MarbleSolitaireController controller;

    int sRow = 0;
    int sCol = 0;
    int armSize = 0;

    try {
      if (args[0].equals("european") || args[0].equals("english")) {
        sRow = 3;
        sCol = 3;
        armSize = 3;
      } else if (args[0].equals("triangular")) {
        armSize = 5;
      }

      if (args[1].equals("-size")) {
        armSize = Integer.parseInt(args[2]);
        if (!args[0].equals("triangular")) {
          sRow = (3 * armSize - 2) / 2;
          sCol = (3 * armSize - 2) / 2;
        }
      } else if (args[1].equals("-hole")) {
        sRow = Integer.parseInt(args[2]);
        sCol = Integer.parseInt(args[3]);
      }

      if (args[4].equals("-size")) {
        armSize = Integer.parseInt(args[5]);
      } else if (args[3].equals("-hole")) {
        sRow = Integer.parseInt(args[4]);
        sCol = Integer.parseInt(args[5]);
      }

    }
    catch (IndexOutOfBoundsException e) {
      // do nothing
    }

    switch (args[0]) {
      case "european":
        model = new EuropeanSolitaireModel(armSize, sRow, sCol);
        view = new MarbleSolitaireTextView(model, new PrintStream(System.out));
        controller = new MarbleSolitaireControllerImpl(model, view,
                new InputStreamReader(System.in));
        controller.playGame();
        break;
      case "english":
        model = new EnglishSolitaireModel(armSize, sRow, sCol);
        view = new MarbleSolitaireTextView(model, new PrintStream(System.out));
        controller = new MarbleSolitaireControllerImpl(model, view,
                new InputStreamReader(System.in));
        controller.playGame();
        break;
      case "triangular":
        model = new TriangleSolitaireModel(armSize, sRow, sCol);
        view = new TriangleSolitaireTextView(model, new PrintStream(System.out));
        controller = new MarbleSolitaireControllerImpl(model, view,
                new InputStreamReader(System.in));
        controller.playGame();
        break;
      default:
        break;
    }
  }
}
