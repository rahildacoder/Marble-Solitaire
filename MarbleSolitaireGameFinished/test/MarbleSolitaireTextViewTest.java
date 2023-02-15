import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Text View of Marble Solitaire.
 */
public class MarbleSolitaireTextViewTest {
  private MarbleSolitaireTextView v1;
  private MarbleSolitaireTextView v2;
  private MarbleSolitaireTextView v3;
  private MarbleSolitaireTextView v4;
  private MarbleSolitaireTextView v5;


  @Before
  public void initialConditions() {
    EnglishSolitaireModel model1 = new EnglishSolitaireModel();
    EnglishSolitaireModel model2 = new EnglishSolitaireModel(5);
    EnglishSolitaireModel model3 = new EnglishSolitaireModel(4, 3);
    EnglishSolitaireModel model4 = new EnglishSolitaireModel(5, 4, 2);
    EnglishSolitaireModel model5 = new EnglishSolitaireModel();
    model5.move(5, 3, 3, 3);

    v1 = new MarbleSolitaireTextView(model1);
    v2 = new MarbleSolitaireTextView(model2);
    v3 = new MarbleSolitaireTextView(model3);
    v4 = new MarbleSolitaireTextView(model4);
    v5 = new MarbleSolitaireTextView(model5);
  }

  @Test
  public void testToString() {
    try {
      MarbleSolitaireTextView v5 = new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", v1.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", v2.toString());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O", v3.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O _ O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", v4.toString());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O", v5.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, out);
    view.renderBoard();

    assertEquals(view.toString(), out.toString());
  }

  @Test
  public void testRenderMessage() throws IOException {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable out = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, out);
    view.renderMessage("( ͡° ͜ʖ ͡°)");

    assertEquals("( ͡° ͜ʖ ͡°)", out.toString());
  }

}