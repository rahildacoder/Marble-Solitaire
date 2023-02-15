package cs3500.marblesolitaire.model.hw04;

import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Class representing an abstract marble solitaire model.
 */
public abstract class AMarbleSolitaireModel implements MarbleSolitaireModel {
  final int armSize;

  private final int sRow;

  private final int sCol;

  final List<List<SlotState>> slots;

  /**
   * Constructor which takes in no parameters.
   */
  public AMarbleSolitaireModel() {
    this.armSize = 3;
    this.sRow = 3;
    this.sCol = 3;
    this.slots = this.boardMaker(this.armSize, this.sRow, this.sCol);
  }

  /**
   * Constructor which takes in a value for arm thickness.
   * @param armSize the arm thickness.
   * @throws IllegalArgumentException when the given arm thickness is negative.
   */
  public AMarbleSolitaireModel(int armSize) throws IllegalArgumentException {
    if (armSize <= 0) {
      throw new IllegalArgumentException("Invalid arm thickness (must be an odd number)");
    }

    this.armSize = armSize;
    this.sRow = (3 * armSize - 2) / 2;
    this.sCol = (3 * armSize - 2) / 2;
    this.slots = this.boardMaker(this.armSize, this.sRow, this.sCol);
  }

  /**
   * Constructor which takes in the empty slot row and column.
   * @param sRow row of the empty slot.
   * @param sCol column of the empty slot.
   * @throws IllegalArgumentException when the given values are invalid.
   */
  public AMarbleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armSize = 3;
    this.sRow = sRow;
    this.sCol = sCol;
    this.slots = this.boardMaker(this.armSize, this.sRow, this.sCol);

    if (this.getSlotAt(sRow, sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty slot position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * Constructor which takes in values for arm thickness and the empty slot row and column.
   * @param armSize the arm thickness.
   * @param sRow row of the empty slot.
   * @param sCol column of the empty slot.
   * @throws IllegalArgumentException when the given values are invalid.
   */
  public AMarbleSolitaireModel(int armSize, int sRow, int sCol) throws IllegalArgumentException {
    if (armSize < 1) {
      throw new IllegalArgumentException("Invalid arm thickness (must be an odd number)");
    }

    this.armSize = armSize;
    this.sRow = sRow;
    this.sCol = sCol;
    this.slots = this.boardMaker(this.armSize, this.sRow, this.sCol);

    if (this.getSlotAt(sRow, sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
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
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      SlotState from = this.getSlotAt(fromRow, fromCol);
      SlotState to = this.getSlotAt(toRow, toCol);
    } catch (IllegalArgumentException e) {
      return false;
    }

    return this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            && (((toRow - fromRow == 2 || fromRow - toRow == 2) && toCol == fromCol)
            || ((toCol - fromCol == 2 || fromCol - toCol == 2) && toRow == fromRow))
            && this.getSlotAt((fromRow + toRow) / 2,
            (fromCol + toCol) / 2) == SlotState.Marble;
  }

  /**
   * Creates a board based on given values and returns it.
   * @param armSize the arm thickness.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @return an arraylist of rows of slotStates representing a new English Solitaire game board
   */
  protected abstract List<List<SlotState>> boardMaker(int armSize, int sRow, int sCol);

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (!validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move selected");
    }
    else {
      this.slots.get(fromRow).set(fromCol, SlotState.Empty);
      this.slots.get(toRow).set(toCol, SlotState.Marble);
      this.slots.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
    }
  }

  @Override
  public int getBoardSize() {
    return 3 * this.armSize - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > this.getBoardSize() - 1 ||  col < 0 || col > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("The given row or column is beyond the board dimensions");
    }
    return this.slots.get(row).get(col);
  }

  @Override
  public int getScore() {
    int count = 0;

    for (List<SlotState> row : slots) {
      for (SlotState state : row) {
        if (state == SlotState.Marble) {
          count++;
        }
      }
    }

    return count;
  }

  @Override
  public boolean isGameOver() {
    for (int fromCol = 0; fromCol < this.getBoardSize(); fromCol++) {
      for (int fromRow = 0; fromRow < this.getBoardSize(); fromRow++) {
        for (int toCol = 0; toCol < this.getBoardSize(); toCol++) {
          for (int toRow = 0; toRow < this.getBoardSize(); toRow++) {
            if (validMove(fromRow, fromCol, toRow, toCol)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }


}
