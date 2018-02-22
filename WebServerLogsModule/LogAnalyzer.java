package WebServerLogsModule;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource();
         for (String line : fr.lines()) {
             System.out.println("Line\t" + line);
             String ip = line.substring(0,line.indexOf(" "));
             line = line.substring(line.indexOf(ip));
             String date = line.substring( line.indexOf("[") + 1, line.indexOf("]") );
             line = line.substring(line.indexOf("\"")+1);
             String request = line.substring(0 , line.indexOf("\"") );
             line = line.substring(request.length() + 2);
             String status = line.substring(0, line.indexOf(" " ));
             String bytes = line.substring(line.indexOf(" " )+1);
             System.out.println("Ip\t" + ip);
             System.out.println("Date\t" + date);
             System.out.println("Request\t" + request);
             System.out.println("Status\t" + status);
             System.out.println("Bytes\t" + bytes);
             System.out.println();
             
             //records.add(new LogEntry("1.2.3.4", new Date(), "example request", 200, 500));
            }
     }
     public void testReadFile() {
            readFile("test.txt");
        }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
