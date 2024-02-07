import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src\\test.txt");
        scan = new Scanner(target);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            System.out.println(line);
        }
    }
}
