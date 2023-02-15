package cs3500.marblesolitaire.view;

import java.io.IOException;
import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract class representing a Marble Solitaire Text View.
 */
abstract class AMarbleSolitaireTextView implements MarbleSolitaireView {
  final MarbleSolitaireModelState game;
  final Appendable out;

  /**
   * Constructor which initializes the state of the game.
   *
   * @param game the MarbleSolitaireModelState that represents the current model.
   * @throws IllegalArgumentException if the given MarbleSolitaireModelState is null.
   */
  public AMarbleSolitaireTextView(MarbleSolitaireModelState game) throws IllegalArgumentException {
    if (game == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.game = game;
    this.out = System.out;
  }

  /**
   * Constructor which initializes the state of the game.
   *
   * @param game the MarbleSolitaireModelState that represents the current model.
   * @param out the location of the view.
   * @throws IllegalArgumentException if either argument is null.
   */
  public AMarbleSolitaireTextView(MarbleSolitaireModelState game, Appendable out)
          throws IllegalArgumentException {
    if (game == null || out == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.out = out;
    this.game = game;
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   * @return the game state as a string
   */
  @Override
  public String toString() {
    String view = "";
    for (int i = 0; i < this.game.getBoardSize(); i++) {
      boolean lastSlot = false;
      for (int j = 0; j < this.game.getBoardSize(); j++) {
        MarbleSolitaireModelState.SlotState currentSlot = game.getSlotAt(i, j);
        MarbleSolitaireModelState.SlotState nextSlot;
        try {
          nextSlot = game.getSlotAt(i, j + 1);
        } catch (IllegalArgumentException e) {
          nextSlot = MarbleSolitaireModelState.SlotState.Invalid;
        }
        if (j == 0) {
          if (currentSlot == MarbleSolitaireModelState.SlotState.Invalid) {
            view += " ";
          }
          else if (currentSlot == MarbleSolitaireModelState.SlotState.Empty) {
            view += "_";
          }
          else if (currentSlot == MarbleSolitaireModelState.SlotState.Marble) {
            view += "O";
          }
        }
        else {
          if (currentSlot == MarbleSolitaireModelState.SlotState.Invalid && !lastSlot) {
            view += "  ";
          }
          else if (currentSlot == MarbleSolitaireModelState.SlotState.Empty && !lastSlot) {
            view += " _";
          }
          else if (currentSlot == MarbleSolitaireModelState.SlotState.Marble && !lastSlot) {
            view += " O";
          }
        }
        if (currentSlot != MarbleSolitaireModelState.SlotState.Invalid
                && nextSlot == MarbleSolitaireModelState.SlotState.Invalid) {
          lastSlot = true;
          if (i != game.getBoardSize() - 1) {
            view += "\n";
          }
        }
      }
    }

    return view;
  }

  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    Objects.requireNonNull(message);
    this.out.append(message);
  }

}
