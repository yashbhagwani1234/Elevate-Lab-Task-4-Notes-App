import java.io.*;
import java.util.*;

public class NotesApp {
    private static final String File_Name = "notes.txt";

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("======= Welcome to Notes App =======");
            System.out.println("1. For write a new note");
            System.out.println("2. For View all Notes.");
            System.out.println("3. For Updating  Notes.");
            System.out.println("4. For deleting any Notes.");
            System.out.println("5. For exit.");
            System.out.println("Enter Your Choice ");
            try {
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    writeNote(sc);
                    break;
                case 2:
                    veiwNote();
                    break;
                case 3:
                    updateNote(sc);
                    break;
                case 4:
                    deleteNote(sc);
                    break;
                case 5:
                    System.out.println("Exiting GoodBye....");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice ! Please Try again ");
            }
        }
            catch(Exception e){
            System.out.println("please Enter Number not any other word or char....");
            return;
          }
        }
        
    }
    private static void writeNote(Scanner sc){
        System.out.print("Enter Your Note :- ");
        String note = sc.nextLine();
        try(FileWriter writer = new FileWriter(File_Name,true)){
            writer.write(note + System.lineSeparator());
            System.out.println("Note Saved Successfully");
        }
        catch(IOException e){
            System.out.println("Error Writing Note :- "+e.getMessage());
        }
    }
    public static List<String> veiwNote(){
        List<String> notes = new ArrayList<>();
        System.out.println("=== your Notes ====");
        try(BufferedReader reader = new BufferedReader(new FileReader(File_Name))){ // file reader opens the file and bufferreader wrap the file to read fast
            String line;
            int index = 1;
            while((line = reader.readLine()) != null){
                System.out.println(index+". "+line);
                notes.add(line);
                index++;
            }
            if(notes.isEmpty()){
                System.out.println("No Notes Found Yet.");
            }
        }catch(FileNotFoundException e){
            System.out.println("No Notes Found . Write Your First Note.");
        }catch(IOException e){
            System.out.println("Error in Reading File : "+e.getMessage());
        }
        return notes;
    }
    private static void updateNote(Scanner sc){
        List<String> notes = veiwNote();
        if(notes.isEmpty()) return;

        System.out.print("Enter the note number you want to update :- ");
        int notnum = sc.nextInt();

        sc.nextLine();

        if(notnum <1 || notnum>notes.size()){
            System.out.println("Not valid not number !");
            return;
        }

        System.out.println("Enter the new content :- ");
        String content = sc.nextLine();
        notes.set(notnum -1,content);

        writeAllNotes(notes);
        System.out.println("Notes Updated SuccessFully.");

    }
    private static void deleteNote(Scanner sc){
        List<String> notes = veiwNote();
        if(notes.isEmpty()) return;

        System.out.print("Enter the note number you want to delete: ");
        int notNum = sc.nextInt();
        sc.nextLine();

        if(notNum < 1 || notNum>notes.size()){
            System.out.println("Invalid Note number !");
            return;
        }
        notes.remove(notNum-1);
        writeAllNotes(notes);
        System.out.println("Note Deleted Successfully !");

    }

    private static void writeAllNotes(List<String> notes){
        try(FileWriter writer = new FileWriter(File_Name,false)){
            for(String n : notes){
                writer.write(n+ System.lineSeparator());
            }
        }catch(IOException e){
            System.out.println("Error Updating file : "+e.getMessage());
        }
    }
}
