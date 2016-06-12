package server.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that incapsulates an Printer.
 */
public class Printer {

    public static void printInFile(final String path, final String text) throws IOException {

        BufferedWriter output = null;
        try {
            final File file = new File(path);
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
