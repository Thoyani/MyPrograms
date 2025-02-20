package TicTacToe;
import java.io.IOException;
import java.util.Scanner;

public class Playing extends gameboard {
	
	 protected String Osymbol=" O ",Xsymbol= " X ";
	 protected boolean gameOn;
	 Scanner input = new Scanner (System.in);
	 public Playing(int size) {
	        super(size);
	        this.gameOn = true;
	        
	    }
	
		public void startPlaying() {
			    displayGameBoard(gameBoard);
		        while (gameOn) {
		        	try {
		        	    player(Osymbol);
		        	    player(Xsymbol);
		        	}catch(IOException error) {
		        		System.out.println(error.getMessage());
		        	}
		        }
		 }
		
		public void player(String playerSymbol)throws IOException{
			    if(gameOn) {
			        int row,column;
	                int[]player = validInput(playerSymbol);
	                row = player[0];
	                column = player[1];
	            
	                    if ((row <= 0 || row > gameBoard.length) || (column <= 0 || column > gameBoard.length) || !"   ".equals(gameBoard[row - 1][column - 1])) {
	                        
	                        while (true) {
	                            validatingMove(row,column,playerSymbol);
	                            displayGameBoard(gameBoard);
	                            break;
	                        }

	                        if (noPossibleMove(gameBoard)) {
	                            System.out.print("Game Over\nIt's a Draw!");
	                            gameOn = false;
	                        }

	                    }else{

	                        gameBoard[row - 1][column - 1] = playerSymbol;
	                        displayGameBoard(gameBoard);
	                      
							if (noPossibleMove(gameBoard)) {
	                            System.out.print("Game Over\nIt's a Draw!");
	                            gameOn = false;
	                        }
	                    }

	                    if (winChecker(gameBoard,playerSymbol)) {
	                    	System.out.printf("\nGame Over\nCongratulations\u0002 %splayer!",playerSymbol);
	                    	gameOn = false;
	                    }
			    }
		 }

		public int[] validInput(String symbol){
		            int[] playerMove = new int[2];
		            boolean validInput = false;
		            int row = 0;
		            int column = 0;

		            while (!validInput) {
		                
		                System.out.println("\nNB: There must be a space in between!");
		                drawLine();
		                System.out.printf("\n%sPlayer please enter your row and column : ",symbol);
		                String rowAString = input.next();
		                String columnAString = input.next();
		                
		                try {

		                    row = Integer.parseInt(rowAString);
		                    column = Integer.parseInt(columnAString);
		                    validInput = true;
		                } catch (NumberFormatException e) {
		                     System.out.printf("\nInvalid Input: Row and Column must be Integers and one or both (%s,%s) is/are not.\n",rowAString,columnAString);
		                }
		           }
		           playerMove[0]= row;
		           playerMove[1]= column; 
		            
		           return playerMove;
		    }
		
		

		public void validatingMove(int row, int column, String symbol) {
		  
		        while (((row <= 0 || row > gameBoard.length) || (column <= 0 || column > gameBoard.length)) || !"   ".equals(gameBoard[row - 1][column - 1])) {

		            System.out.printf("Move entered outside Grid or already Occupied.\nNext time enter a number between 1 and %d\n", gameBoard.length);
		            int[]playerMove=validInput(symbol);
		            row = playerMove[0];
		            column = playerMove[1];
		           }
		         
		        gameBoard[row - 1][column - 1] = symbol;
		    }

		public static boolean winChecker(String[][] gameBoard, String playerSymbol) {
		        int columnCount = 0, LRcross_count = 0, RLcross_count = 0, rowCount = 0;
		        String col = "", prevColIndex = "";
		        boolean won=false;
		        for (int row = 0; row < gameBoard.length; row++) {
		            for (int column = 0; column < gameBoard.length;
		            		column++) {

		                if (gameBoard[row][column].equals(playerSymbol) && column == row) {
		                    LRcross_count++;
		                }
		                if (gameBoard[row][column].equals(playerSymbol)) {
		                    col += column;
		                    columnCount++;
		                }
		                if (gameBoard[row][column].equals(playerSymbol) && (row == gameBoard.length - column - 1 && column == gameBoard.length - row - 1)) {
		                    RLcross_count++;
		                }
		            }
		            if (columnCount == gameBoard.length) {
		                won = true;
		                break;
		            }
		            if ((col.contains(prevColIndex) || prevColIndex.equals("")) && !(col.equals(""))) {
		                prevColIndex = col;
		                rowCount++;
		            }
		            columnCount = 0;
		            col = "";
		        }

		        if (rowCount == gameBoard.length) {
		            won = true;
		        } else if (LRcross_count == gameBoard.length) {
		            won = true;
		        } else if (RLcross_count == gameBoard.length) {
		            won = true;
		        } else {
		            won = (columnCount == gameBoard.length);
		        }
		        return won;
		    }

		public boolean noPossibleMove(String[][] gameBoard) {
		        int count = 0;
		        for (int row = 0; row < gameBoard.length; row++) 
		            for (int column = 0; column < gameBoard.length; column++) 
		                if (gameBoard[row][column].equals("   ")) 
		                    count++;
		        return count == 0;
		    }
		
}
