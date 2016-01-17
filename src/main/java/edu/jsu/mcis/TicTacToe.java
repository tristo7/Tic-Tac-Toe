package edu.jsu.mcis;

public class TicTacToe {
	private String[][] ticTacToeGameBoardRowByColumn = new String[3][3];
	private boolean isXTurn = true;
	private boolean invalidMarkDetectedFlag = false;
	public enum GameState{TIE, X, O, NOTDONE};
	
	public TicTacToe(){
		for(int row = 0;row<3;row++){
			for(int col = 0;col<3;col++){
				ticTacToeGameBoardRowByColumn[row][col] = "-";
				System.out.println(ticTacToeGameBoardRowByColumn[row][col]);
			}
		}
	}
	
	public String getGameBoard(){
		String board = "";
		for(int row = 0;row<3;row++){
			for(int col = 0;col<3;col++){
				board += ticTacToeGameBoardRowByColumn[row][col];
			}
			board += "\n";
		}
		return board;
	}
	
	public void markLocationByRowAndColumn(int row, int col){
		invalidMarkDetectedFlag = false;
		if(ticTacToeGameBoardRowByColumn[row][col] != "-"){
			invalidMarkDetectedFlag = true;
		} else {
			if(isXTurn){
				ticTacToeGameBoardRowByColumn[row][col] = "X";
				isXTurn = false;
			}else{
				ticTacToeGameBoardRowByColumn[row][col] = "O";
				isXTurn = true;
			}
		}
	}
		
	public GameState getCurrentGameState() {
		GameState result = GameState.NOTDONE;
		
		int dashCounter = 0;
		for(int row = 0;row<3;row++){
			for(int col = 0;col<3;col++){
				if(ticTacToeGameBoardRowByColumn[row][col] == "-"){
					dashCounter++;
				}
			}
		}
		if(dashCounter == 0)
			result = GameState.TIE;
		
		if(	   (ticTacToeGameBoardRowByColumn[0][0] == "X" && ticTacToeGameBoardRowByColumn[2][2] == "X")
			 ||(ticTacToeGameBoardRowByColumn[0][2] == "X" && ticTacToeGameBoardRowByColumn[2][0] == "X")
			 && ticTacToeGameBoardRowByColumn[1][1] == "X"){
				result = GameState.X;
		}else if((ticTacToeGameBoardRowByColumn[0][0] == "O" && ticTacToeGameBoardRowByColumn[2][2] == "O")
			   ||(ticTacToeGameBoardRowByColumn[0][2] == "O" && ticTacToeGameBoardRowByColumn[2][0] == "O")
			   && ticTacToeGameBoardRowByColumn[1][1] == "O"){
				result = GameState.O;
		}
		for(int currentRowOrColumn = 0;currentRowOrColumn<3;currentRowOrColumn++){
			if(ticTacToeGameBoardRowByColumn[currentRowOrColumn][0] == "X" 
			&& ticTacToeGameBoardRowByColumn[currentRowOrColumn][1] == "X" 
			&& ticTacToeGameBoardRowByColumn[currentRowOrColumn][2] == "X"){
				result = GameState.X;
				break;
			}else if(	ticTacToeGameBoardRowByColumn[0][currentRowOrColumn] == "X" 
					 && ticTacToeGameBoardRowByColumn[1][currentRowOrColumn] == "X" 
					 && ticTacToeGameBoardRowByColumn[2][currentRowOrColumn] == "X"){
						result = GameState.X;
						break;
			}else if(	ticTacToeGameBoardRowByColumn[currentRowOrColumn][0] == "O" 
					 && ticTacToeGameBoardRowByColumn[currentRowOrColumn][1] == "O" 
					 && ticTacToeGameBoardRowByColumn[currentRowOrColumn][2] == "O"){
						result = GameState.O;
						break;
			}else if(	ticTacToeGameBoardRowByColumn[0][currentRowOrColumn] == "O" 
					 && ticTacToeGameBoardRowByColumn[1][currentRowOrColumn] == "O" 
					 && ticTacToeGameBoardRowByColumn[2][currentRowOrColumn] == "O"){
						result = GameState.O;
						break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
}
