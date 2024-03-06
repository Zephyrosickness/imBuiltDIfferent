//Allows us to use the FileChooser wizard GUI to pick files
import javax.swing.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class  WritingFiles {
    //need to add the "throws IOException" after typical main phrase
    public static void main(String[] args) throws IOException   {

        //Sample data that is being added to an ArrayList named recs
        ArrayList <String> recs = new ArrayList<String>();
        recs.add("Sample data Line 1");
        recs.add("Sample data Line 2");
        recs.add("Sample data Line 3");

        //Creates a JFileChooser object that will open the JFileChooser Wizard GUI
        //Allows user to search for files that they want read by the program
        //Must easier for the user than typing in a file directory
        JFileChooser chooser = new JFileChooser();

        File selectedFile; //file the user ends up choosing to open will be stored here
        String rec = ""; //records each line that is read in the file

        //This variable will hold the users current working directory (program folder)
        //"user.dir" is shorthand for current working directory (project folder)
        File workingDirectory = new File(System.getProperty("user.dir"));
        //Path is automatically set for user
        //In this case, the file will be stored in the src folder and the name is already chosen
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        //The try block will prompt the user to open a file
        //If an error occurs in this block, the catch block will handle the IO Exception
        try {
            //OutputStream is needed in order to create our Buffered Writer
            //OutputStream allows bytes of data to be written to a file
            //BufferedWriter is our actual writing tool that will be used to write characters to file
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            //Actually writing data from recs to new file
            for (String r : recs) {
                //r is the String being written
                //0 is the index of the String the writer starts writing at
                //r.length is the index of the String the writer stops writing at
                writer.write(r, 0, r.length());
                //need new line added before we write more data - ensures next bit of data is put on own line
                writer.newLine();
            }
            writer.close(); //closes file and clears buffer
            System.out.println("Data file written!");
        }
        //This catch block is hit for a variety of IO Exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
