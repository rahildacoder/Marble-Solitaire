import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for the European Solitaire Model.
 */
public class EuropeanSolitaireModelTest {
  private MarbleSolitaireModel g1;
  private MarbleSolitaireModel g2;
  private MarbleSolitaireModel g3;
  private MarbleSolitaireModel g4;

  /**
   * Initial conditions for testing.
   */
  @Before
  public void initialConditions() {
    g1 = new EuropeanSolitaireModel();
    g2 = new EuropeanSolitaireModel(2, 1);
    g3 = new EuropeanSolitaireModel(5);
    g4 = new EuropeanSolitaireModel(5, 0, 4);
  }

  @Test
  public void testInitialization() {
    // fields are private; board size reveals correct armSize
    assertEquals(g1.getBoardSize(), 7);
    assertEquals(g1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(g2.getBoardSize(), 7);
    assertEquals(g2.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(g3.getBoardSize(), 13);
    assertEquals(g3.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(g4.getBoardSize(), 13);
    assertEquals(g4.getSlotAt(0, 4), MarbleSolitaireModelState.SlotState.Empty);

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
      EnglishSolitaireModel error4 = new EnglishSolitaireModel(-5, 3, 4);
      fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, g1.getBoardSize());
    assertEquals(13, g4.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, g1.getSlotAt(-1, 5));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, g1.getSlotAt(70, 5));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, g1.getSlotAt(5, -1));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      assertEquals(MarbleSolitaireModelState.SlotState.Invalid, g1.getSlotAt(5, 99));
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, g1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(5, 3));
  }

  @Test
  public void testMove() {
    try {
      g1.move(7, 3, 5, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(3, 3, 5, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(2, 0, 0, 0);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(0, 0, 2, 0);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(2, 0, 2, 2);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      g1.move(6, 3, 4, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      g1.move(6, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      g1.move(6, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    try {
      g1.move(3, 4, 6, 6);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    // testing down to up move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 3));
    g1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 3));

    // testing up to down move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 3));
    g1.move(2, 3, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 3));

    // testing left to right move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 2));
    g1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 2));

    // testing right to left move
    assertEquals(g1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(g1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(g1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    g1.move(3, 4, 3, 2);
    assertEquals(g1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(g1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(g1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  @Test
  public void testGetScore() {
    assertEquals(36, g1.getScore());
    g1.move(3, 1, 3, 3);
    assertEquals(35, g1.getScore());
  }

  @Test
  public void testIsGameOver() {
    assertFalse(g1.isGameOver());
    g1.move(5, 3, 3, 3);
    assertFalse(g1.isGameOver());
    g1.move(2, 3, 4, 3);
    assertFalse(g1.isGameOver());
    g1.move(0, 3, 2, 3);
    assertFalse(g1.isGameOver());
    g1.move(3, 5, 3, 3);
    assertFalse(g1.isGameOver());
    g1.move(3, 2, 3, 4);
    assertFalse(g1.isGameOver());
    g1.move(3, 0, 3, 2);
    assertFalse(g1.isGameOver());
    g1.move(1, 1, 1, 3);
    assertFalse(g1.isGameOver());
    g1.move(1, 4, 1, 2);
    assertFalse(g1.isGameOver());
    g1.move(5, 1, 5, 3);
    assertFalse(g1.isGameOver());
    g1.move(5, 4, 5, 2);
    assertFalse(g1.isGameOver());
    g1.move(5, 5, 3, 5);
    assertFalse(g1.isGameOver());
    g1.move(3, 5, 3, 3);
    assertFalse(g1.isGameOver());
    g1.move(3, 3, 1, 3);
    assertFalse(g1.isGameOver());
    g1.move(2, 5, 2, 3);
    assertFalse(g1.isGameOver());
    g1.move(2, 3, 0, 3);
    assertFalse(g1.isGameOver());
    g1.move(2, 1, 2, 3);
    assertFalse(g1.isGameOver());
    g1.move(4, 2, 2, 2);
    assertFalse(g1.isGameOver());
    g1.move(6, 2, 4, 2);
    assertFalse(g1.isGameOver());
    g1.move(6, 4, 6, 2);
    assertFalse(g1.isGameOver());
    g1.move(2, 3, 2, 1);
    assertFalse(g1.isGameOver());
    g1.move(2, 0, 2, 2);
    assertFalse(g1.isGameOver());
    g1.move(1, 2, 3, 2);
    assertFalse(g1.isGameOver());
    g1.move(4, 2, 2, 2);
    assertFalse(g1.isGameOver());
    g1.move(4, 4, 4, 2);
    assertFalse(g1.isGameOver());
    g1.move(4, 1, 4, 3);
    assertTrue(g1.isGameOver());
  }

}