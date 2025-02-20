package TicTacToe;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try(Scanner input = new Scanner(System.in)){
			boolean validInput=false;
			int size = 0;
            System.out.print("\t\t\tTIME TO PLAY\u0002 \nGAME LOADING...\n\n");
            while(!validInput){
                 System.out.print("Please enter the size of the Game Board: ");
                 String sizeAString = input.nextLine();
                 try {

                	 size = Integer.parseInt(sizeAString);
                	 validInput = true;

                 } catch (NumberFormatException e) {
                	 System.out.printf("\nInvalid Input: size must be an Integer and '%s' is not.\n",sizeAString);
                 }
            }
            Playing run = new Playing(size);
            double start = (((double) System.currentTimeMillis()) / 1000) / 60;
            run.startPlaying();
            double end = (((double) System.currentTimeMillis()) / 1000) / 60;
            System.out.printf("\nPlaying Time : %.2f minutes", (end - start));

        } catch (IllegalCallerException error) {
            System.out.println(error.getMessage() + " Invalid input");
        }
    }
}
