//Allows us to use the FileChooser wizard GUI to pick files
import javax.swing.*;
//Needed imports for working w/ IO (input/output)
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

        //This variable will hold the users current working directory (program folder)
        //"user.dir" is shorthand for current working directory (project folder)
        File workingDirectory = new File(System.getProperty("user.dir"));
        //Path is automatically set for user
        //In this case, the file will be stored in the src folder and the name is already chosen
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        //The try block will attempt to write a new txt file
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
            //Prints the error along with additional info related to the error
            e.printStackTrace();
        }
    }
}
