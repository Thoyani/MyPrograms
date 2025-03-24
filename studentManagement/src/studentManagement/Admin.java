package studentManagement;
import java.io.*;
import java.util.Scanner;

class Admin extends User {
	
        public Admin(String fileName,Scanner inputScanner){ 
            super(fileName,inputScanner);
            try{
                
                System.out.print("\n1.Add module \n2.Record Marks\n3.Check Students' Marks\n ");
                while(loggedIn){      
                     System.out.print("\nEnter your choice: ");         
                     String choice = inputScanner.nextLine();
                     while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3")){
                           System.out.print("\nPlease enter choice number[1 , 2 or 3] in the list above : ");
                           choice = inputScanner.nextLine();
                      }
                      switch (choice) {
                           case "1":
                               addModules(inputScanner);
                               break;
                               
                           case "2":
                        	   addTestMark(inputScanner);
                        //System.out.print("\nWould you like to exit [Y/N]? ");
                        //String  = inputScanner.nextLine();
                        //if(cy.equalsIgnoreCase("N")){
                            //loggedIn=false;
                        	   break;
                        	   
                           case "3":
                        	   System.out.print("\nEnter Module name: ");
                        	   String moduleName = inputScanner.nextLine();
                        	   System.out.print("\nEnter Test number : ");
                        	   int testNumber = inputScanner.nextInt();
                        	   checkMarks(moduleName,testNumber);
                        	   inputScanner.nextLine();
                        	   break;
                           default:
                        	   System.out.print("\nInvalidSelection!!");return;
                        	   
                      	}
                
                
                // System.out.print("\nEnter Module Name : ");
                // String moduleName = inputScanner.nextLine();
                // System.out.print("Enter Module Test Number: ");
                // testNumber = inputScanner.nextInt();
                // System.out.print("Enter Student ID: ");
                // inputScanner.nextLine();
                // String studentId = inputScanner.nextLine();
                // System.out.print("Enter Student Name: ");
                // String studentName = inputScanner.nextLine();
                //addTestMark(inputScanner);
                 
            }
            }catch (Exception e){
            e.printStackTrace();
            }
        }
    
        public void addModules(Scanner inputScanner) throws IOException {
                try(BufferedWriter addModule = new BufferedWriter(new FileWriter("Modules.txt",true))){
                    String choice;
                    while(true){ 
                        System.out.print("\nEnter module name : ");
                        String moduleName = inputScanner.nextLine();
                        if(!moduleExist(moduleName)){
                            System.out.print("Enter module Description : ");
                            String moduleDescription = inputScanner.nextLine();
                            addModule.write(moduleName+";"+moduleDescription+"\n");
                            System.out.print("\nModule added successfully.\nProceed adding modules?[Y/N]: ");
                            //Thread.sleep(3000);//waiting for 3SEC 
                            choice = inputScanner.nextLine();
                            //Still  handle userExceptions
                            if(choice.equalsIgnoreCase("N")){
                                  System.out.println(" Module addition Completed!");
                                  break;
                            }
                        }else{
                            System.out.printf("Module %s already available.",moduleName);
                        }
                    }
                }
        }
        public void addTestMark(Scanner inputScanner) throws IOException {

            System.out.print("\nEnter Module Name : ");
            String moduleName = inputScanner.nextLine();

            System.out.print("Enter Module Test Number: ");
            int testNumber = inputScanner.nextInt();
            inputScanner.nextLine();

            System.out.print("Enter Student ID: ");
            String studentId = inputScanner.nextLine();
            if(isRegistered(studentId,moduleName)){
                System.out.print("Enter Student Name: ");
                String studentName = inputScanner.nextLine();

                String moduleFilename = moduleName+"Test"+testNumber;
                File moduleTestFile = new File(moduleFilename+".txt");

                try (BufferedWriter moduleDirectory = new BufferedWriter(new FileWriter(moduleTestFile,true));)
                {
                    if(!userExist(studentId,moduleFilename)){
                        System.out.print("Enter Mark[%] : ");
                        String studentMark= inputScanner.nextLine();
                        moduleDirectory.write(studentId+";"+studentName+";"+studentMark+"\n");
                    
                        //Thread.sleep(2000);//waiting for 2sec
                        System.out.println("Student mark added successfully.");
                    }else{
                        System.out.printf("Test %s Mark of %s already captured\n",testNumber,name);
                    }
                }
            }else{
            
                 System.out.printf("%s Is not registered for %s \n",studentId,moduleName);
            }
        }
        public boolean isRegistered(String userId,String moduleName)throws IOException{
            boolean  isRegistered=false;
            if(!userExist(userId,moduleName)){
                return isRegistered;
            }else{
                isRegistered=true;
                return isRegistered;
            }
        }
    
        public static boolean moduleExist(String moduleName)throws IOException{
            boolean moduleFound = false;
            try(BufferedReader moduleDirectory = new BufferedReader(new FileReader("Modules.txt"))) {
                String line;
                while((line = moduleDirectory.readLine()) != null){
                    if(line.contains(moduleName)){
                        moduleFound = true;
                        break;
                    }
                }
            }
            return moduleFound;
        }
        public  void checkMarks(String moduleName,int TestNumber)throws IOException{
                File moduleFile = new File(moduleName+"Test"+TestNumber+".txt");
                try (BufferedReader readModuleFile = new BufferedReader(new FileReader(moduleFile));Scanner fileScanner = new Scanner(readModuleFile))
                {
                    @SuppressWarnings("unused")
					String name,studentId,mark;
                    System.out.printf("\nModule: %s\nTest %d Marks\n",moduleName,TestNumber);
                    while(fileScanner.hasNextLine()){
                        String line[] = (fileScanner.nextLine()).split(";");
                        name = line[1];
                        studentId = line[0];
                        mark = line[2];
                        if(line[0]!=null){
                                System.out.printf("Name: %s\nStudent Id: %s \nMark : %s%s\n",line[1],line[0],line[2],"%");
                                
                        }else{
                                System.out.println("No Students' Marks for "+moduleName);
                        }
                    }
                }
            System.out.printf("Marks Checked Successfully!");
        }
        
        public static void displayModules() throws IOException {
            BufferedReader readModuleFile = new BufferedReader(new FileReader("Modules.txt"));
            try (Scanner fileScanner = new Scanner(readModuleFile))
            {
                System.out.printf("\n\t\tAvailable Modules\n\t\t-----------------\n\nModule Name\t\tDescription\n");
                int lineCount = 0;
                while(fileScanner.hasNext()){
                    String line[] = ((fileScanner.nextLine()).split(";"));
                    lineCount++;
                    System.out.printf("%d. %s\t\t%s\n",lineCount,line[0],line[1]);
                }
                System.out.printf("NOTE: Provided modules were added by Admin ,If ever the module you wish to register for is not available\nAdmin will make it available shortly.");
                User.drawLine();
            }
        }
}
