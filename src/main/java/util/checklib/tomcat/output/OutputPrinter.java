package util.checklib.tomcat.output;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class OutputPrinter {

   private String filename;
   private StringBuilder sb;

    public OutputPrinter() {
        this.filename = null;
    }

    public OutputPrinter(String filename) {
        this.filename = filename;
        sb = new StringBuilder();

    }

   public void writeln(String towrite) throws IOException {
       writeSelection(towrite);
   }

    public void emptyLine() throws IOException {
        writeln("");
    }

    private void writeSelection(String towrite) throws IOException {
        if (filename!=null) {
           sb.append(towrite).append("\n");
        }  else {
            System.out.println(towrite);
        }
    }


    public void flushToFile() throws IOException {
        FileUtils.writeStringToFile(new File(filename), sb.toString());
    }

}
