package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Triangle Solitaire Model.
 */
public class TriangleSolitaireModel extends AMarbleSolitaireModel {

  /**
   * Constructor which takes in no parameters.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Constructor for the model that takes in the size of one arm of the board.
   * @param armSize size of the arm
   * @throws IllegalArgumentException when the given arm size is negative or even
   */
  public TriangleSolitaireModel(int armSize) throws IllegalArgumentException {
    super(armSize, 0, 0);
  }

  /**
   * Constructor for the model that takes in the empty slot row and column.
   * @param sRow row of the empty slot
   * @param sCol column of the empty slot
   * @throws IllegalArgumentException when the given empty slot row and column is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);

    if (sCol > sRow) {
      throw new IllegalArgumentException("Invalid empty slot position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * Constructor that takes on all parameters.
   * @param armSize size of one arm
   * @param sRow row of the empty slot
   * @param sCol column of the empty slot
   * @throws IllegalArgumentException when the given empty slot row and column is invalid
   */
  public TriangleSolitaireModel(int armSize, int sRow, int sCol) throws IllegalArgumentException {
    super(armSize, sRow, sCol);

    if (sCol > sRow) {
      throw new IllegalArgumentException("Invalid empty slot position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * Creates a board based on given values and returns it.
   * @param armSize the arm thickness.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @return an arraylist of rows of slotStates representing a new Triangle Solitaire game board
   */
  protected List<List<SlotState>> boardMaker(int armSize, int sRow, int sCol) {
    List<List<SlotState>> board = new ArrayList<>();
    for (int i = 0; i < armSize; i++) {
      ArrayList<SlotState> row = new ArrayList<SlotState>();
      for (int j = 0; j < armSize; j++) {
        if (i == sRow && j == sCol) {
          row.add(SlotState.Empty);
        } else if (j < i + 1) {
          row.add(SlotState.Marble);
        } else {
          row.add(SlotState.Invalid);
        }
      }
      board.add(row);
    }

    return board;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move selected");
    }
    else {
      this.slots.get(fromRow).set(fromCol, SlotState.Empty);
      this.slots.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
      this.slots.get(toRow).set(toCol, SlotState.Marble);
    }
  }

  /**
   * Determines whether a move is valid based on dimensions of the positions given.
   * @param fromRow the row of the from position.
   * @param fromCol the column of the from position.
   * @param toRow the row of the to position.
   * @param toCol the column of the to position.
   * @return true if a move is valid.
   */
  @Override
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      SlotState from = this.getSlotAt(fromRow, fromCol);
      SlotState to = this.getSlotAt(toRow, toCol);
    } catch (IllegalArgumentException e) {
      return false;
    }

    return this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            && ((toRow == fromRow + 2 && toCol == fromCol + 2)
            || (toRow == fromRow - 2 && toCol == fromCol - 2)
            || (toCol == fromCol && (toRow == fromRow + 2 || toRow == fromRow - 2))
            || (toRow == fromRow && (toCol == fromCol + 2 || toCol == fromCol - 2)))
            && this.getSlotAt((fromRow + toRow) / 2,
            (fromCol + toCol) / 2) == SlotState.Marble;
  }

  /**
   * Returns the size of the board.
   * @return the longest dimension of the board
   */
  @Override
  public int getBoardSize() {
    return this.armSize;
  }

}
