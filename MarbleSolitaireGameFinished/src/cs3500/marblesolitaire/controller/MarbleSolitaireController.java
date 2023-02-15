package cs3500.marblesolitaire.controller;

/**
 * Interface representing a controller for a marble solitaire game which allows a user to
 * input values to move marbles on the board and transmit the board view to the designated output
 * location.
 */
public interface MarbleSolitaireController {
  /**
   * Plays a game of marble solitaire.
   * @throws IllegalStateException if the controller can not successfully read input or transmit
   *                               output.
   */
  void playGame() throws IllegalStateException;
}
