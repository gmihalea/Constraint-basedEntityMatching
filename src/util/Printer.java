package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class that incapsulates an Printer.
 */
public class Printer {

    public static void printInFile(final String text) throws IOException {

        BufferedWriter output = null;
        try {
            final File file = new File(Constants.OUTPUT_FILE_PATH);
            output = new BufferedWriter(new FileWriter(file, true));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }
}
