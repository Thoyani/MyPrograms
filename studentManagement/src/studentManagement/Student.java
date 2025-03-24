package studentManagement;
import java.io.*;
import java.util.Scanner;

public class Student  extends User {
        public Student(String fileName,Scanner inputScanner){super(fileName,inputScanner);
            try{
                    
                    while(loggedIn){
                    	displayRegisteredModules();
                        System.out.println("\n1.Check Marks\n2.Get enrolled for a Module\n3.Exit\nEnter : ");
            
                        int choice = inputScanner.nextInt();
                        homeSelection(choice,inputScanner); 
                    }
                }catch(Exception e){
                        System.out.println("Error Occurred!");
                        e.printStackTrace();
                        }
        }
        public void homeSelection(int choice,Scanner inputScanner)throws IOException {
        	switch(choice){
                case 1:
                    while(true){ 
                        System.out.print("\nEnter Module Number:");
                        int moduleNumber = inputScanner.nextInt();
                        String moduleName = getModule(moduleNumber,name+"_Reg"+".txt");
                        System.out.print("\nEnter Test Number:");
                        int testNumber = inputScanner.nextInt();
                        checkMarks(moduleName,testNumber);
                        System.out.print("\nContinue Checking Marks [Y/N]:");
                        inputScanner.nextLine();//Clearing Scanner.
                        String answer = inputScanner.nextLine();
                        if(answer.equalsIgnoreCase("N"))
                            break;
                    
                    }break;
                case 3:
                	loggedIn = false;
                    System.out.print("\nExiting...");
                    User.drawLine();
                    break;
                                 
                case 2 :
            	    Admin.displayModules();
            	    while (true) {
                        System.out.print("\nEnter Module Number:");
                        int moduleNumber = inputScanner.nextInt();
                                         inputScanner.nextLine();
                        moduleRegistration(moduleNumber);
                        System.out.print("\nContinue Module registering [Y/N]: ");
                        String answer = inputScanner.nextLine();
                        if(answer.equalsIgnoreCase("N"))
                            break;
                   }break;
               default :
            	   System.out.print("Invalid selection!");
            	   return;
               }
        }
        
        public void moduleRegistration(int moduleNumber)throws IOException{
            String moduleName = getModule(moduleNumber,"Modules.txt");
            if(moduleName!=""){
               File moduleFilename = new File(moduleName+".txt");
               try(BufferedWriter moduleFile = new BufferedWriter(new FileWriter(moduleFilename, true))){
                  if(!userExist(userID,moduleName)){
                      moduleFile.write( userID +";"+name+"\n" );
                      System.out.print("\nModule registration Successfully!!");
                  }else{
                      System.out.println("You have already been enrolled for "+moduleName);
                  }
              }
           }else{
              System.out.println("InvalidModule No. : module not found!");
           }
       }

        public void displayRegisteredModules()throws IOException{

            System.out.println("\n-HOME-\n\t\t=Registered Modules=\n\nModule\t\tDescription\n");
            BufferedReader readModuleFile = new BufferedReader(new FileReader("Modules.txt"));
            File registeredModuleFile = new File(name+"_Reg"+".txt");
            try(BufferedWriter registeredModule = new BufferedWriter(new FileWriter(registeredModuleFile));
                Scanner fileScanner = new Scanner(readModuleFile)){
            String moduleName,moduleDescription;
            boolean modulesToDisplay=false;
            int moduleCount=1;
            while (fileScanner.hasNext()) {
                String line[] = (fileScanner.nextLine()).split(";");
                moduleName = line[0];
                moduleDescription = line[1];
                if(userExist(userID,moduleName)){
                    modulesToDisplay=true;
                    System.out.printf("%d %s\t\t%s\n",moduleCount,moduleName,moduleDescription);
//                    if(!Admin.moduleExist(moduleName)){
                       registeredModule.write(moduleName+";"+moduleDescription+"\n" );
//                    }
                    moduleCount++;
                }
            }
            if(!modulesToDisplay){
                System.out.println("\n\t\tNo modules registered yet.");
            }
            }
        }
        public String getModule(int moduleNumber,String fileName)throws IOException{
             String module = "";
            try(BufferedReader readModuleFile = new BufferedReader(new FileReader(fileName));Scanner fileScanner = new Scanner(readModuleFile))
            {
                int lineCount = 0;
                while (fileScanner.hasNext()) {
                    String line[] = fileScanner.nextLine().split(";");
                    lineCount++;
                    if(lineCount==moduleNumber){
                        module = line[0];
                        break;
                    }
                }
            }
            return module;
        }
    
        public void checkMarks(String moduleName,int TestNumber) throws IOException{

                File moduleTestFile = new File(moduleName+"Test"+TestNumber+".txt");
                try(BufferedReader readTestFile = new BufferedReader(new FileReader(moduleTestFile));Scanner fileScanner = new Scanner(readTestFile))
                {
                    String studentId,mark;
                    System.out.printf("Name: %s\nStudent Id : %s\nModule: %s\nTest %d Mark: ",name,userID,moduleName,TestNumber);
                    boolean noMarkFound =true;
                    while(fileScanner.hasNextLine()){
                        String line[] = (fileScanner.nextLine()).split(";");
                        studentId = line[0];
                        mark = line[1];
                        if(studentId.equals(userID)){
                            System.out.printf("%s%\n",mark);
                            noMarkFound = false;
                            break;
                        }
                    }
                    System.out.print( (noMarkFound)? "No mark captured yet" : "\nDone");
                }
        }
        
}
    
    
    


