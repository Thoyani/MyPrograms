package studentManagement;
import java.util.Scanner;

public class studentManagementSystem {
    @SuppressWarnings("unused")
	public static void main(String[] args) {
         try(Scanner inputScanner = new Scanner(System.in)){
        User.drawLine();
        System.out.print("\n1.STUDENT\n2.ADMIN\nEnter choice no. : ");
        
        String userType = inputScanner.nextLine();
        User.drawLine();

        while(!userType.equals("1") && !userType.equals("2")){
            System.out.print("\nPlease enter choice number[1 or 2] in the list above : ");
            userType = inputScanner.nextLine();
        }
        
        String fileName = (userType.equals("1"))? "Students" : "Admins";
        //inputScanner.close();
        if(fileName.equals("Students")){
              Student newStudent = new Student(fileName,inputScanner);
        }else{
              Admin newAdmin = new Admin(fileName,inputScanner);
        }
         }catch (Exception e){
            e.printStackTrace();}
         }
}