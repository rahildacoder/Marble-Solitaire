import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the MarbleSolitaireControllerImpl class and its methods.
 */
public class MarbleSolitaireControllerImplTest {

  @Test
  public void testModelInputs() {
    Readable in = new StringReader("4 2 4 4 q");
    Readable in2 = new StringReader("a 4 5 6 5 q");
    Readable in3 = new StringReader("3 x 5 1 5 q");
    Readable in4 = new StringReader("3 2 5 b 3 q");
    Readable in5 = new StringReader("4 3 2 5 c q");
    Readable in6 = new StringReader("5 3 e a m 6 5 q");
    Readable in7 = new StringReader("5\n3\n3\n3\nq");
    Readable in8 = new StringReader("2\n3\na\ne\np\n4\n4\nq");

    StringBuilder log = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    StringBuilder log3 = new StringBuilder();
    StringBuilder log4 = new StringBuilder();
    StringBuilder log5 = new StringBuilder();
    StringBuilder log6 = new StringBuilder();
    StringBuilder log7 = new StringBuilder();
    StringBuilder log8 = new StringBuilder();

    MarbleSolitaireModel mock = new MockMarbleSolitaireModel(log);
    MarbleSolitaireModel mock2 = new MockMarbleSolitaireModel(log2);
    MarbleSolitaireModel mock3 = new MockMarbleSolitaireModel(log3);
    MarbleSolitaireModel mock4 = new MockMarbleSolitaireModel(log4);
    MarbleSolitaireModel mock5 = new MockMarbleSolitaireModel(log5);
    MarbleSolitaireModel mock6 = new MockMarbleSolitaireModel(log6);
    MarbleSolitaireModel mock7 = new MockMarbleSolitaireModel(log7);
    MarbleSolitaireModel mock8 = new MockMarbleSolitaireModel(log8);

    MarbleSolitaireView view = new MarbleSolitaireTextView(mock, new StringBuilder());
    MarbleSolitaireView view2 = new MarbleSolitaireTextView(mock2, new StringBuilder());
    MarbleSolitaireView view3 = new MarbleSolitaireTextView(mock3, new StringBuilder());
    MarbleSolitaireView view4 = new MarbleSolitaireTextView(mock4, new StringBuilder());
    MarbleSolitaireView view5 = new MarbleSolitaireTextView(mock5, new StringBuilder());
    MarbleSolitaireView view6 = new MarbleSolitaireTextView(mock6, new StringBuilder());
    MarbleSolitaireView view7 = new MarbleSolitaireTextView(mock7, new StringBuilder());
    MarbleSolitaireView view8 = new MarbleSolitaireTextView(mock8, new StringBuilder());

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(mock, view, in);
    MarbleSolitaireController c2 = new MarbleSolitaireControllerImpl(mock2, view2, in2);
    MarbleSolitaireController c3 = new MarbleSolitaireControllerImpl(mock3, view3, in3);
    MarbleSolitaireController c4 = new MarbleSolitaireControllerImpl(mock4, view4, in4);
    MarbleSolitaireController c5 = new MarbleSolitaireControllerImpl(mock5, view5, in5);
    MarbleSolitaireController c6 = new MarbleSolitaireControllerImpl(mock6, view6, in6);
    MarbleSolitaireController c7 = new MarbleSolitaireControllerImpl(mock7, view7, in7);
    MarbleSolitaireController c8 = new MarbleSolitaireControllerImpl(mock8, view8, in8);

    c.playGame();
    c2.playGame();
    c3.playGame();
    c4.playGame();
    c5.playGame();
    c6.playGame();
    c7.playGame();
    c8.playGame();

    assertEquals("fromRow = 3, fromCol = 1, toRow = 3, toCol = 3\n", log.toString());
    assertEquals("fromRow = 3, fromCol = 4, toRow = 5, toCol = 4\n", log2.toString());
    assertEquals("fromRow = 2, fromCol = 4, toRow = 0, toCol = 4\n", log3.toString());
    assertEquals("fromRow = 2, fromCol = 1, toRow = 4, toCol = 2\n", log4.toString());
    assertEquals("fromRow = 3, fromCol = 2, toRow = 1, toCol = 4\n", log5.toString());
    assertEquals("fromRow = 4, fromCol = 2, toRow = 5, toCol = 4\n", log6.toString());
    assertEquals("fromRow = 4, fromCol = 2, toRow = 2, toCol = 2\n", log7.toString());
    assertEquals("fromRow = 1, fromCol = 2, toRow = 3, toCol = 3\n", log8.toString());
  }

  @Test
  public void testInitialization() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    assertEquals(c, c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireView view = new MarbleSolitaireTextView(null, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(null, view, in);

    assertEquals(c, c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullView() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, null, in);

    assertEquals(c, c);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullInput() {
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, null);

    assertEquals(c, c);
  }

  @Test
  public void testStarterMessageWithZeroParam() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());
  }

  @Test
  public void testTriangleInitialization() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14", log.toString());
  }

  @Test
  public void testEuropeanInitialization() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", log.toString());
  }

  @Test
  public void testStarterMessageWithOneParam() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel(5);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

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
            "        O O O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
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
            "        O O O O O\n" +
            "Score: 104", log.toString());
  }

  @Test
  public void testStarterMessageWithTwoParam() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel(2, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());
  }

  @Test
  public void testStarterMessageWithThreeParam() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel(5, 5, 4);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104", log.toString());
  }

  @Test
  public void testQuitAfterOneMove() {
    Readable in = new StringReader("6 4 4 4 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31", log.toString());
  }

  @Test
  public void testQuitAfterOneMoveWithLetters() {
    Readable in = new StringReader("6 4 x 4 4 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31", log.toString());
  }

  @Test
  public void testQuitAfterLettersAndValidMove() {
    Readable in = new StringReader("a b c d 6 4 4 4 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31", log.toString());
  }

  @Test
  public void testQuitAfterTwoMoves() {
    Readable in = new StringReader("6 4 4 4 3 4 5 4 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 30", log.toString());
  }

  @Test
  public void testInvalidMoveNotEmptyFrom() {
    Readable in = new StringReader("4 4 6 4 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());
  }

  @Test
  public void testInvalidMoveNotEmptyTo() {
    Readable in = new StringReader("4 5 6 5 q");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32", log.toString());
  }

  @Test
  public void testPlayUntilGameOver() {
    Readable in = new StringReader("6 4 4 4 " +
            "3 4 5 4 " +
            "1 4 3 4 " +
            "4 2 4 4 " +
            "4 5 4 3 " +
            "4 7 4 5");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "Game over!\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 26", log.toString());
  }

  @Test
  public void testPlayWithInvalidMovesUntilGameOver() {
    Readable in = new StringReader("6 4 4 4 " +
            "3 5 0 1 " +
            "3 4 5 4 " +
            "3 5 4 4 " +
            "1 4 3 4 " +
            "4 2 4 4 " +
            "4 5 4 3 " +
            "8 9 10 11 " +
            "4 7 4 5");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Invalid move. Play again.\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 30\n" +
                    "Invalid move. Play again.\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 30\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 29\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 28\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ _ O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 27\n" +
                    "Invalid move. Play again.\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ _ O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 27\n" +
                    "Game over!\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ O _ _\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 26", log.toString());
  }

  @Test
  public void testGameOverAfterMultipleInvalidMovesAndLetters() {
    Readable in = new StringReader("6 4 x 4 4 c " +
            "3 5 0 1 " +
            "3 4 5 4 " +
            "3 5 4 4 " +
            "1 4 e 3 4 " +
            "4 2 4 4 " +
            "4 5 4 3 " +
            "8 9 10 a 11 " +
            "4 7 4 5");
    Appendable log = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, log);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, view, in);

    c.playGame();

    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "Score: 32\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "Invalid move. Play again.\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 31\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 30\n" +
                    "Invalid move. Play again.\n" +
                    "    O O O\n" +
                    "    O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 30\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 29\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 28\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ _ O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 27\n" +
                    "Invalid move. Play again.\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ _ O O\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 27\n" +
                    "Game over!\n" +
                    "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ O _ _\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O\n" +
                    "Score: 26", log.toString());
  }
}