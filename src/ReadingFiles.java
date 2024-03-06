//Allows us to use the FileChooser wizard GUI to pick files
import javax.swing.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class  ReadingFiles {
    //need to add the "throws IOException" after typical main phrase
    public static void main(String[] args) throws IOException   {

        //Creates a JFileChooser object that will open the JFileChooser Wizard GUI
        //Allows user to search for files that they want read by the program
        //Must easier for the user than typing in a file directory
        JFileChooser chooser = new JFileChooser();

        File selectedFile; //file the user ends up choosing to open will be stored here
        String rec = ""; //records each line that is read in the file

        //The try block will prompt the user to open a file
        //If an error occurs in this block, the catch block will handle the IO Exception
        try {
            //This variable will hold the users current working directory (program folder)
            //"user.dir" is shorthand for current working directory (project folder)
            File workingDirectory = new File(System.getProperty("user.dir"));

            //This will make the JFileChooser GUI default to look in the workingDirectory first
            //User can still navigate out of this folder if desired
            chooser.setCurrentDirectory(workingDirectory);

            //Checks to see if the user picks a file in the file chooser wizard
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //Stores user selected file
                selectedFile = chooser.getSelectedFile();
                //Holds the path to the selected file
                Path file = selectedFile.toPath();

                //InputStream is needed in order to create our Buffered Reader
                //InputStream allows bytes of data to be read from a file
                //BufferedReader is our actual "reader" tool that will be used to read characters from file
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //Moving through file and reading
                //Starts at line 0 and moves line by line through the file
                //Rec holds what the reader finds on the line
                int line = 0;
                System.out.println("File Path: " + file);
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    //Prints the line # and the contents of the line
                    System.out.printf("\nLine%4d: %-60s ", line, rec);
                }
                reader.close(); // must close the file to seal it and clear buffer
                System.out.println("\n\nData file read!"); //Success message
            } else {
                System.out.println("File not selected. Please restart program.");
                System.exit(0);
            }
        }
        //This catch block is hit when the user file the user attempts to open a file that can not be found
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            //Prints the error along with additional info related to the error
            e.printStackTrace();
        }
        //This catch block is hit for all other IO Exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
