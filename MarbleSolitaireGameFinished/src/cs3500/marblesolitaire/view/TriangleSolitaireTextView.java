package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Class representing a Triangle Solitaire Text View.
 */
public class TriangleSolitaireTextView extends AMarbleSolitaireTextView {
  /**
   * Constructor which initializes the state of the Triangle Solitaire Game.
   * @param game the MarbleSolitaireModelState that represents the current model.
   * @throws IllegalArgumentException if the given MarbleSolitaireModelState is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    super(game);
  }

  /**
   * Constructor that takes in a MarbleSolitaireModelState and an Appendable.
   * @param game the MarbleSolitaireModelState that represents the current model.
   * @param out the location of the view.
   * @throws IllegalArgumentException if either argument is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState game, Appendable out)
          throws IllegalArgumentException {
    super(game, out);
  }

  /**
   * Returns the string representation of the model's state.
   * @return the representation of the board as a string
   */
  @Override
  public String toString() {
    String view = "";
    int boardSize = this.game.getBoardSize();

    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize - i - 1; j++) {
        view = view + " ";
      }
      for (int j = 0; j < boardSize; j++) {
        if (this.game.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Marble)) {
          view = view + "O ";
        }
        if (this.game.getSlotAt(i, j).equals(MarbleSolitaireModelState.SlotState.Empty)) {
          view = view + "_ ";
        }
      }
      view = view.substring(0, view.length() - 1);
      if (i < boardSize - 1) {
        view = view + "\n";
      }
    }
    return view;

  }
}
