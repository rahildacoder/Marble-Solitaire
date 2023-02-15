package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a European Solitaire Model.
 */
public class EuropeanSolitaireModel extends AMarbleSolitaireModel {

  /**
   * Constructor which takes in no parameters.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Constructor which takes in a value for arm thickness.
   * @param armSize the arm thickness.
   * @throws IllegalArgumentException when the given arm thickness is either even or negative.
   */
  public EuropeanSolitaireModel(int armSize) throws IllegalArgumentException {
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
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
  }

  /**
   * Constructor which takes in values for arm thickness and the empty slot row and column.
   * @param armSize the arm thickness.
   * @param sRow row of the empty slot.
   * @param sCol column of the empty slot.
   * @throws IllegalArgumentException when the given values are invalid.
   */
  public EuropeanSolitaireModel(int armSize, int sRow, int sCol) throws IllegalArgumentException {
    super(armSize, sRow, sCol);
  }

  @Override
  protected List<List<SlotState>> boardMaker(int armSize, int sRow, int sCol) {
    int boardSize = (3 * armSize) - 2;
    List<List<SlotState>> board = new ArrayList<>();

    for (int i = 0; i < boardSize; i++) {
      ArrayList<SlotState> row = new ArrayList<SlotState>();
      for (int j = 0; j < boardSize; j++) {
        boolean upLeft = (i > (boardSize + (armSize + 2 * j)) / 2 - 1) && j < armSize - 1;
        boolean upRight = i > armSize * 2 - 2
                && j > (boardSize / 2) + (armSize / 2 + (boardSize - i - 1));
        boolean downLeft = i < armSize - 1 && j < (boardSize - (armSize + 2 * i)) / 2;
        boolean downRight = i < armSize - 1 && j > (boardSize / 2) + ((armSize + 2 * i) / 2);
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
