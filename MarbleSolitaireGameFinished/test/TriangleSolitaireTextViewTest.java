import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the text view of triangle solitaire.
 */
public class TriangleSolitaireTextViewTest {

  private TriangleSolitaireModel g1;
  private MarbleSolitaireView v1;
  private MarbleSolitaireView v2;
  private MarbleSolitaireView v3;
  private MarbleSolitaireView v4;

  @Before
  public void init() {
    g1 = new TriangleSolitaireModel();
    v1 = new TriangleSolitaireTextView(g1);

    TriangleSolitaireModel g2 = new TriangleSolitaireModel(2, 1);
    v2 = new TriangleSolitaireTextView(g2);

    TriangleSolitaireModel g3 = new TriangleSolitaireModel(3);
    v3 = new TriangleSolitaireTextView(g3);

    TriangleSolitaireModel g4 = new TriangleSolitaireModel(3, 2, 2);
    v4 = new TriangleSolitaireTextView(g4);
  }

  @Test
  public void testInitialization() {
    try {
      MarbleSolitaireView vError = new MarbleSolitaireTextView(null);
      fail();
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", v1.toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O _ O\n" +
            " O O O O\n" +
            "O O O O O", v2.toString());
    assertEquals("  _\n" +
            " O O\n" +
            "O O O", v3.toString());
    assertEquals("  O\n" +
            " O O\n" +
            "O O _", v4.toString());

  }

  @Test
  public void testToString() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", v1.toString());
    g1.move(2, 2, 0, 0);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", v1.toString());

  }

  @Test
  public void testRenderBoard() throws IOException {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, out);
    view.renderBoard();

    assertEquals(view.toString(), out.toString());
  }

  @Test
  public void testRenderMessage() throws IOException {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, out);
    view.renderMessage("( ͡° ͜ʖ ͡°)");

    assertEquals("( ͡° ͜ʖ ͡°)", out.toString());
  }

}