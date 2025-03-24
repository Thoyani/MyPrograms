package studentManagement;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

abstract class User {
    protected boolean loggedIn = false;
    protected String userID;
    private String password;
    protected String name;
    private String userFileName;

    public User(String fileName,Scanner inputScanner){
        this.userFileName=fileName;
        try{ 
            
            System.out.print("Do you want to :\n\n1.Login\n2.Register\nEnter your choice here: ");
            String choice = inputScanner.nextLine();

            while((!choice.equals("1") && !choice.equals("2"))){ 
                System.out.println("Invalid choice! Please try again.\nEnter your choice here:");
                choice = inputScanner.nextLine();
            }
            if(choice.equals("1")){
                loginPortal(inputScanner);
            }else{
                registrationPortal(inputScanner);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean userExist(String userId,String fileName)throws IOException {
        File file = new File(fileName+".txt");
        boolean userIDExist = false;
        try(BufferedReader user = new BufferedReader(new FileReader(file));
        Scanner getUser = new Scanner(user)){
   
        while(getUser.hasNext()) {
            String line[] = getUser.nextLine().split(";");
            if(line[0].equals(userId)){
                userIDExist=true;
                break;
            }
        }
        }
        return userIDExist;
    }
    
    public void setFields(String userId)throws IOException{
    
        try(BufferedReader user = new BufferedReader(new FileReader(userFileName+".txt"));Scanner getUser = new Scanner(user);){
        
        while(getUser.hasNext()) {
            String line[] = (getUser.nextLine()).split(";");
            String userIdFound = line[0];
            if(userIdFound.equals(userId)){
                this.userID = userIdFound;
                this.name = line[1];
                this.password=line[2];
                break;
            }
        }
    }
    }
    
    public  void loginPortal(Scanner inputScanner)throws IOException{
        System.out.print("Enter your user ID: ");
        String userID = inputScanner.nextLine();
        if(userExist(userID,userFileName)){
            setFields(userID);
            int i = 1;
            while(i <= 3){
                System.out.print("Enter your password: ");
                String userPassword = inputScanner.nextLine();
                if(userPassword.equals(password)){
                        System.out.println("Login Successful!\n");
                        User.drawLine();

                        System.out.printf("\nWelcome %s\n",name);
                        loggedIn = true;
                        break;
                }
                if (i==3){
                    System.out.println("Login failed!\nYou have entered wrong password 3 times.");
                    break;
                }else{
                    System.out.printf("You have %d attempt/s left\n",3-i);
                }
            i++;
            }
        }else{
            System.out.println("User Id "+userID+" does not exist.\nConsider registering[N/Y]: ");
            String choice = inputScanner.nextLine();
            if(choice.equalsIgnoreCase("N")){
                System.out.println("Exiting...");
                User.drawLine();

                System.exit(0);
            }else{
                    registrationPortal(inputScanner);
                }
            }

    }
//    public void modulesToTake(){

//  }

    public  void registrationPortal(Scanner inputScanner)throws IOException{
            User.drawLine();
            System.out.print("\n\t\t\t\t\t>> REGISTRATION <<");
            User.drawLine();
            userRegistration(inputScanner);
    }

     public void userRegistration(Scanner inputScanner)throws IOException{
        File filepath = new File(userFileName+".txt");
        try(BufferedWriter addUser = new BufferedWriter(new FileWriter(filepath,true))){
            Random rand = new Random();
            long newUserId = 224000000+rand.nextLong((20000-10000)+1) + 10000;
            System.out.print("\nEnter your name :");
            String Username = inputScanner.nextLine();
            
            String userId = getUserIdByName(Username);
            String answer="N";
            if(!userId.equals("")) {
            	System.out.printf("%s <-- Is this you? \nAnswer with (N/Y): ",userId);
                answer = inputScanner.nextLine();
            }
            
            

            while((!answer.equals("N") && !answer.equals("Y"))){ 
                System.out.println("Please try again.\nAnswer with (N/Y): ");
                answer = inputScanner.nextLine();
            }

            if(answer.equals("Y"))
            {
                System.out.print("\nPlease Login to continue.");
                loginPortal(inputScanner);
            }
            else{

                String passWord = getValidPassword(inputScanner);
                System.out.println("\nThis is your User Id : "+ newUserId);
                addUser.write( newUserId +";"+ Username +";"+ passWord+"\n" );
                addUser.close();
                System.out.println("Registration Successful.\n\nPlease Login to continue.\n");
                loginPortal(inputScanner);
            }
        }
    }

    public static String getValidPassword(Scanner inputScanner){
           inputScanner.nextLine();
        //Robust and complexity of the password to be added later.
        //To be continued...
            String password="";
            while(password.length() != 5){
                System.out.print("\n! Password must be 5 characters long.\nEnter your password: ");
                password = inputScanner.nextLine();
                }
                return password;
     }
      public static void drawLine(){
      System.out.println();
        for (int i = 0; i < 15; i++) {
            System.out.print("_____");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        System.out.println();

      }
    public String getUserIdByName(String userName)throws IOException{
         String userId = "";
         try(BufferedReader userFile = new BufferedReader(new FileReader(userFileName+".txt"))){
             String line;
             while((line = userFile.readLine()) != null){
                String[] user = line.split(";");
                 if(user[1].equals(userName)){
                     userId = user[0];
                    break;
                     }
                 }
            }
            return userId;
     }
}

    

