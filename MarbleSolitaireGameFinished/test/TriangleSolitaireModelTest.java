import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for the Triangle Solitaire Model.
 */
public class TriangleSolitaireModelTest {
  MarbleSolitaireModel g1;
  MarbleSolitaireModel g2;
  MarbleSolitaireModel g3;
  MarbleSolitaireModel g4;

  /**
   * Initial conditions for testing.
   */
  @Before
  public void initialConditions() {
    g1 = new TriangleSolitaireModel();
    g2 = new TriangleSolitaireModel(1, 1);
    g3 = new TriangleSolitaireModel(3);
    g4 = new TriangleSolitaireModel(3, 2, 1);
  }

  @Test
  public void testInitialization() {
    assertEquals(g1.getBoardSize(), 5);
    assertEquals(g1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(g2.getBoardSize(), 5);
    assertEquals(g2.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(g3.getBoardSize(), 3);
    assertEquals(g3.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);

    assertEquals(g4.getBoardSize(), 3);
    assertEquals(g4.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Empty);

    try {
      MarbleSolitaireModel error1 = new TriangleSolitaireModel(-1);
      fail();
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      MarbleSolitaireModel error2 = new TriangleSolitaireModel(0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      MarbleSolitaireModel error3 = new TriangleSolitaireModel(-2, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      MarbleSolitaireModel error4 = new TriangleSolitaireModel(5, 0, 1);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, g1.getBoardSize());
    assertEquals(3, g4.getBoardSize());
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
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, g1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 3));
  }

  @Test
  public void testMove() {
    try {
      g1.move(3, 2, 1, 2);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(0, 2, 0, 0);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(0, 0, 3, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(3, 3, 1, 1);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(5, 3, 2, 3);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(3, 1, 5, 2);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      g1.move(2, 2, 0, 0);
      g1.move(3, 3, 1, 1);
    } catch (IllegalArgumentException e) {
      // do nothing
    }

    // testing diagonal up right move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(2, 1));
    g1.move(3, 1, 1, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(2, 1));

    // testing diagonal up left move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 2));
    g1.move(4, 3, 2, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 2));

    // testing left to right move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 2));
    g1.move(4, 1, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 2));

    // testing right to left move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 3));
    g1.move(4, 4, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(4, 3));

    // testing diagonal down left move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(2, 1));
    g1.move(1, 1, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g1.getSlotAt(2, 1));

    g3.move(2, 0, 0, 0);
    g3.move(2, 2, 2, 0);

    // testing diagonal down right move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g3.getSlotAt(1, 1));
    g3.move(0, 0, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, g3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, g3.getSlotAt(1, 1));
  }

  @Test
  public void testGetScore() {
    assertEquals(14, g1.getScore());
    assertEquals(5, g3.getScore());
  }

  @Test
  public void testIsGameOver() {
    assertFalse(g3.isGameOver());
    g3.move(2, 0, 0, 0);
    assertFalse(g3.isGameOver());
    g3.move(2, 2, 2, 0);
    assertFalse(g3.isGameOver());
    g3.move(0, 0, 2, 2);
    assertTrue(g3.isGameOver());
  }
}