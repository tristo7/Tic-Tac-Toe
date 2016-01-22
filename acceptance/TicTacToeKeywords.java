import edu.jsu.mcis.*;

public class TicTacToeKeywords {
	private TicTacToe t;
	
	public void startNewGame() {
		t = new TicTacToe();
	}
	
	public void markLocation(int row, int col) {
		t.markLocationByRowAndColumn(row,col);
	}
	
	public String getMark(int row, int col) {
		return t.getMarkByRowAndColumn(row,col);
	}
    
	public String getWinner() {
		TicTacToe.GameState gs = t.getCurrentGameState();
		switch(gs){
			case X:
				return "X";
			case O:
				return "O";
			case TIE:
				return "TIE";
		}
		return "";
	}
}
