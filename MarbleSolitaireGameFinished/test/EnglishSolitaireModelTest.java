import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the English Solitaire Model.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel model1;
  private EnglishSolitaireModel model2;
  private EnglishSolitaireModel model3;
  private EnglishSolitaireModel model4;

  /**
   * Initial conditions for testing.
   */
  @Before
  public void initialConditions() {
    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(2, 1);
    model3 = new EnglishSolitaireModel(5);
    model4 = new EnglishSolitaireModel(7, 5, 8);
  }

  @Test
  public void testInitialization() {
    // fields are private; board size reveals correct armSize
    assertEquals(model1.getBoardSize(), 7);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(model2.getBoardSize(), 7);
    assertEquals(model2.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(model3.getBoardSize(), 13);
    assertEquals(model3.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(model4.getBoardSize(), 19);
    assertEquals(model4.getSlotAt(5, 8), MarbleSolitaireModelState.SlotState.Empty);

    try {
      EnglishSolitaireModel error1 = new EnglishSolitaireModel(0, 0);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      EnglishSolitaireModel error2 = new EnglishSolitaireModel(4);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      EnglishSolitaireModel error3 = new EnglishSolitaireModel(7, 0, 0);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      EnglishSolitaireModel error4 = new EnglishSolitaireModel(4, 3, 4);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, model1.getBoardSize());
    assertEquals(19, model4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(-1, 5));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(70, 5));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(5, -1));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(5, 99));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(5, 3));
  }

  @Test
  public void testMove() {
    try {
      model1.move(7, 3, 5, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      model1.move(3, 3, 5, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      model1.move(2, 0, 0, 0);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      model1.move(0, 0, 2, 0);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      model1.move(2, 0, 2, 2);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model1.move(6, 3, 4, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model1.move(6, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model1.move(6, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      model3.move(3, 4, 6, 6);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    // testing down to up move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 3));
    model1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 3));

    // testing up to down move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));
    model1.move(2, 3, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));

    // testing left to right move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    model1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));

    // testing right to left move
    assertEquals(model1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    model1.move(3, 4, 3, 2);
    assertEquals(model1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(model1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(model1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  @Test
  public void testGetScore() {
    assertEquals(32, model1.getScore());
    model1.move(3, 1, 3, 3);
    assertEquals(31, model1.getScore());
  }

  @Test
  public void testIsGameOver() {
    assertFalse(model1.isGameOver());
    model1.move(3, 1, 3, 3);
    assertFalse(model1.isGameOver());
    model1.move(3, 4, 3, 2);
    assertFalse(model1.isGameOver());
    model1.move(1, 3, 3, 3);
    assertFalse(model1.isGameOver());
    model1.move(4, 3, 2, 3);
    assertFalse(model1.isGameOver());
    model1.move(6, 3, 4, 3);
    assertFalse(model1.isGameOver());
    model1.move(3, 6, 3, 4);
    assertTrue(model1.isGameOver());
  }
}