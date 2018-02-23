package WebServerLogsModule;
/**
 * This is the Tester for LogEntry class.
 * 
 * @author Davide Nastri
 * @version 2/21/2018
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
        
    }
    
    public void testLogAnalyzer() {
        
    	 LogAnalyzer la = new LogAnalyzer();
    	 la.readFile("WebServerLogsModule/weblog3-short_log");
    	 la.printAll();
    	 int uniqueIPs = la.countUniqueIPs();
    	 System.out.println("There are " + uniqueIPs + " unique IPs");
    	 System.out.println("Unique visitors on Sep 14:");
    	 System.out.println(la.uniqueIPVisitsOnDay("Sep 14").size());
    	 System.out.println("Unique visitors on Sep 30:");    	 
    	 System.out.println(la.uniqueIPVisitsOnDay("Sep 30").size());
    	 System.out.println("Unique ips in range 200,299:");
    	 System.out.println(la.countUniqueIPsInRange(200,299));
    	 System.out.println("Unique ips in range 300,399:");
    	 System.out.println(la.countUniqueIPsInRange(300,399));
    	 System.out.println("printAllHigherThanNum using 400");
    	 la.printAllHigherThanNum(400);
    	 System.out.println("Unique visitors on Mar 24:");    	 
    	 System.out.println(la.uniqueIPVisitsOnDay("Mar 24").size());
    	 System.out.println("countVisitsPerIP:");
    	 HashMap<String,Integer> counts = la.countVisitsPerIP();
    	 System.out.println(counts);
 	 System.out.println("mostNumbervisitsByIP:");
    	 System.out.println(la.mostNumberVisitsByIP(counts));
 	 System.out.println("iPsMostVisits:");
    	 System.out.println(la.iPsMostVisits(counts));  
 	 System.out.println("iPsForDays:");
    	 System.out.println(la.iPsForDays());  
    	 
    }

}
