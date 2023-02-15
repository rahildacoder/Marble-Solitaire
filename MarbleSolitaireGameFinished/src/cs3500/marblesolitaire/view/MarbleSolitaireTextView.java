package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a class that displays a board of a marble solitaire game.
 */
public class MarbleSolitaireTextView extends AMarbleSolitaireTextView {

  /**
   * Constructor which initializes the state of the game.
   *
   * @param game the MarbleSolitaireModelState that represents the current model.
   * @throws IllegalArgumentException if the given MarbleSolitaireModelState is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    super(game);
  }


  /**
   * Constructor which initializes the state of the game.
   *
   * @param game the MarbleSolitaireModelState that represents the current model.
   * @param out the location that the view is to be displayed.
   * @throws IllegalArgumentException if the given MarbleSolitaireModelState is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable out)
          throws IllegalArgumentException {
    super(game, out);
  }
}

