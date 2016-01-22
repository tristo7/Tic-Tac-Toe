package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToe {
	private String[][] ticTacToeGameBoardRowByColumn = new String[3][3];
	private boolean isXTurn = true;
	private static boolean invalidMarkDetectedFlag = false;
	
	public enum GameState{
		TIE, 
		X, 
		O, 
		NOTDONE};
	
	public TicTacToe(){
		for(int row = 0;row<3;row++){
			for(int col = 0;col<3;col++){
				ticTacToeGameBoardRowByColumn[row][col] = "-";
			}
		}
	}
	
	public String getWhoseTurnItIs(){
		if(isXTurn)
			return "X";
		else
			return "O";
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
	
	public String getMarkByRowAndColumn(int row, int col){
		return ticTacToeGameBoardRowByColumn[row][col];
	}
	
	public void markLocationByRowAndColumn(int row, int col){
		invalidMarkDetectedFlag = false;
		if(row >2 || row <0 || col >2 || col <0)
			invalidMarkDetectedFlag = true;
		else{
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
		
		if(	   ((ticTacToeGameBoardRowByColumn[0][0] == "X" && ticTacToeGameBoardRowByColumn[2][2] == "X")
			 ||(ticTacToeGameBoardRowByColumn[0][2] == "X" && ticTacToeGameBoardRowByColumn[2][0] == "X"))
			 && ticTacToeGameBoardRowByColumn[1][1] == "X"){
				result = GameState.X;
		}else if(((ticTacToeGameBoardRowByColumn[0][0] == "O" && ticTacToeGameBoardRowByColumn[2][2] == "O")
			   ||(ticTacToeGameBoardRowByColumn[0][2] == "O" && ticTacToeGameBoardRowByColumn[2][0] == "O"))
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
	
	public static void main(String[] args){
			TicTacToe game = new TicTacToe();
			Scanner input = new Scanner(System.in);
			int chosenRow, chosenColumn, turnNum;
			turnNum = 1;
			boolean isFirstOrSecondTurn = true;
		do{
			System.out.print("\033[H\033[2J"); //Clear the console
			System.out.println(game.getGameBoard()+"\n");
			System.out.println(game.getWhoseTurnItIs()+"'s turn. Please enter a row and column.\n");
			if(isFirstOrSecondTurn){
				System.out.println("Example: \"1 2\" would mark a(n) "+game.getWhoseTurnItIs()+" in the first row second column.\n");
				turnNum++;
				if(turnNum>2)
					isFirstOrSecondTurn = false;
			}
			chosenRow = input.nextInt();
			chosenColumn = input.nextInt();
			game.markLocationByRowAndColumn(chosenRow-1,chosenColumn-1);
			System.out.println();
			
			
			while(invalidMarkDetectedFlag){
				System.out.println("That location is already occupied. Please choose another.");
				chosenRow = input.nextInt();
				chosenColumn = input.nextInt();
				game.markLocationByRowAndColumn(chosenRow-1,chosenColumn-1);
			}
			
		}while(game.getCurrentGameState() == GameState.NOTDONE);
		System.out.print("\033[H\033[2J"); //Clear the console
		System.out.println(game.getGameBoard()+"\n");
		
		
		switch(game.getCurrentGameState()){
			case X:
				System.out.println("X won!");
				break;
			case O:
				System.out.println("O won!");
				break;
			case TIE:
				System.out.println("Tie!");
				break;
		}	
	}
}
