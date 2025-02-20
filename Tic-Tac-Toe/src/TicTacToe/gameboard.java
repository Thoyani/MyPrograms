package TicTacToe;

public class gameboard {
    protected String[][] gameBoard;

    public gameboard(int size) {
        if (size <= 1) 
            throw new IllegalArgumentException("Size must be a non-zero Integer greater than 1");
        this.gameBoard = new String[size][size];
        for (int j = 0; j < gameBoard.length; j++) {
            for (int i = 0; i < size; i++) {
                this.gameBoard[i][j] = "   ";
            }
        }
    }

    public void drawLine() {
		for (int i = 0;i < 15;i++) {
            System.out.print("______");
		    try {
			    Thread.sleep(100);
		    } catch (InterruptedException e) {
			    e.printStackTrace();
		    }
        }
		System.out.println();	
	}

    public void displayGameBoard(String gameBoard[][]) {
    	drawLine();
        for (int i = 0; i < (gameBoard.length*4)+1; i++) {
            System.out.print("-");
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println();
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard.length; column++) {
                if (column == 0) {
                    System.out.print("|" + gameBoard[row][column] + "|");
                } else {
                    System.out.print(gameBoard[row][column] + "|");
                    try {
        				Thread.sleep(100);
        			} catch (InterruptedException e) {
        				
        				e.printStackTrace();
        			}
                }
            }
            System.out.println();
            for (int i = 0; i < (gameBoard.length*4)+1; i++) {
                System.out.print("-");
                try {
    				Thread.sleep(10);
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
            }
            System.out.println();
        }
    }

}
