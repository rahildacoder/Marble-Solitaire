package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw04.AMarbleSolitaireModel;

/**
 * Class representing an English Solitaire Model.
 */
public class EnglishSolitaireModel extends AMarbleSolitaireModel {

  /**
   * Constructor which takes in no parameters.
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * Constructor which takes in a value for arm thickness.
   * @param armSize the arm thickness.
   * @throws IllegalArgumentException when the given arm thickness is either even or negative.
   */
  public EnglishSolitaireModel(int armSize) throws IllegalArgumentException {
    super(armSize);

    if (armSize % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness (must be an odd number)");
    }
  }

  /**
   * Constructor which takes in the empty slot row and column.
   * @param sRow row of the empty slot.
   * @param sCol column of the empty slot.
   * @throws IllegalArgumentException when the given values are invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
  }

  /**
   * Constructor which takes in values for arm thickness and the empty slot row and column.
   * @param armSize the arm thickness.
   * @param sRow row of the empty slot.
   * @param sCol column of the empty slot.
   * @throws IllegalArgumentException when the given values are invalid.
   */
  public EnglishSolitaireModel(int armSize, int sRow, int sCol) throws IllegalArgumentException {
    super(armSize, sRow, sCol);
  }

  /**
   * Creates a board based on given values and returns it.
   * @param armSize the arm thickness.
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @return an arraylist of rows of slotStates representing a new English Solitaire game board
   */
  protected List<List<SlotState>> boardMaker(int armSize, int sRow, int sCol) {
    int boardSize = (3 * armSize) - 2;
    List<List<SlotState>> board = new ArrayList<>();

    for (int i = 0; i < boardSize; i++) {
      ArrayList<SlotState> row = new ArrayList<SlotState>();
      for (int j = 0; j < boardSize; j++) {
        boolean upLeft = j < armSize - 1 && i < armSize - 1;
        boolean upRight = j > (2 * armSize - 2) && i < armSize - 1;
        boolean downLeft = j < armSize - 1 && i > (2 * armSize - 2);
        boolean downRight = j > (2 * armSize - 2) && i > (2 * armSize - 2);
        if (upLeft || downRight || downLeft || upRight) {
          row.add(SlotState.Invalid);
        } else if (i == sRow && j == sCol) {
          row.add(SlotState.Empty);
        } else {
          row.add(SlotState.Marble);
        }
      }
      board.add(row);
    }
    return board;
  }

}