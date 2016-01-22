package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TicTacToeTest {
	private TicTacToe t;
	
	@Before
	public void setup(){
		t = new TicTacToe();
	}
	
	@Test
	public void testGetGameBoard(){
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(2,0);
		t.markLocationByRowAndColumn(2,2);
		assertEquals("X-O\nOX-\nX-O\n",t.getGameBoard());
	}
	
	@Test
	public void testXGoesFirst(){
		assertEquals("X",t.getWhoseTurnItIs());
	}
	
	@Test
	public void testOGoesSecond(){
		t.markLocationByRowAndColumn(0,0);
		assertEquals("O",t.getWhoseTurnItIs());
	}
	
	@Test
	public void testMarkLocationByRowAndColumn(){
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(0,1);
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(2,0);
		t.markLocationByRowAndColumn(1,2);
		t.markLocationByRowAndColumn(2,2);
		t.markLocationByRowAndColumn(2,1);
		assertEquals("XOX\nXOX\nOXO\n",t.getGameBoard());
	}
	
	@Test
	public void testInitialBoardIsEmpty() {

		for(int row = 0;row<3;row++){
			for(int col = 0;col<3;col++){
				assertEquals("---\n---\n---\n",t.getGameBoard());
			}
		}
	}
	
	@Test
	public void testMarkXInUpperRightCorner() {
		t.markLocationByRowAndColumn(0,2);
		assertEquals("--X\n---\n---\n",t.getGameBoard());
	}
	
	@Test
	public void testMarkOInBottomLeftCorner() {
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(2,0);
		assertEquals("X--\n---\nO--\n",t.getGameBoard());
	}
	
	@Test
	public void testUnableToMarkOverExistingMark() {
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(0,0);
		assertEquals("X--\n---\n---\n",t.getGameBoard());
	}
	
	@Test
	public void testGameIsNotOverAfterTheFirstMark() {
		t.markLocationByRowAndColumn(0,0);
		assertEquals(TicTacToe.GameState.NOTDONE, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsWonByXHorizontallyAcrossTopRow() {
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(0,1);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(0,2);
		assertEquals(TicTacToe.GameState.X, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsWonByOHorizontallyAcrossBottomRow() {
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(2,0);
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(2,1);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(2,2);
		assertEquals(TicTacToe.GameState.O, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsWonByXVerticallyLeftColumn(){
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(2,2);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(2,1);
		t.markLocationByRowAndColumn(2,0);
		assertEquals(TicTacToe.GameState.X, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsWonByOVerticallyRightColumn(){
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(1,2);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(2,2);
		assertEquals(TicTacToe.GameState.O, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsWonByXDiagonallyRightToLeft() {
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(0,1);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(2,0);
		assertEquals(TicTacToe.GameState.X, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsWonByODiagonallyLeftToRight() {
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(0,1);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(2,2);
		assertEquals(TicTacToe.GameState.O, t.getCurrentGameState());
	}
	
	@Test
	public void testGameIsOverByTieIfAllLocationsAreFilled() {
		t.markLocationByRowAndColumn(0,0);
		t.markLocationByRowAndColumn(0,1);
		t.markLocationByRowAndColumn(0,2);
		t.markLocationByRowAndColumn(1,1);
		t.markLocationByRowAndColumn(1,0);
		t.markLocationByRowAndColumn(2,0);
		t.markLocationByRowAndColumn(1,2);
		t.markLocationByRowAndColumn(2,2);
		t.markLocationByRowAndColumn(2,1);
		assertEquals(TicTacToe.GameState.TIE, t.getCurrentGameState());
	}	

	@Test
	public void testMarkOutsideOfIndexKeepsTurn(){
		t.markLocationByRowAndColumn(4,4);
		t.markLocationByRowAndColumn(-2,-3);
		assertEquals("X",t.getWhoseTurnItIs());
	}
	
	@Test
	public void testGetMarkByRowAndColumn(){
		t.markLocationByRowAndColumn(0,0);
		assertEquals("X",t.getMarkByRowAndColumn(0,0));
		t.markLocationByRowAndColumn(1,1);
		assertEquals("O",t.getMarkByRowAndColumn(1,1));
		t.markLocationByRowAndColumn(2,2);
		assertEquals("X",t.getMarkByRowAndColumn(2,2));
		assertEquals("-",t.getMarkByRowAndColumn(0,2));
		
	}
}

	