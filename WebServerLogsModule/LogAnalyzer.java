package WebServerLogsModule;

/**
 * A Java class to analyze Apache Webserver logs.
 * 
 * @author Davide Nastri
 * @version 2/22/2018
 */
import java.util.*;
import edu.duke.*;
import java.text.*; 

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD", Locale.US);
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    public void readFile(String filename) {
    FileResource f = new FileResource(filename);
    for (String line: f.lines()) {
        LogEntry log = WebLogParser.parseEntry(line);
        records.add(log);
    }
    }
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(statusCode);
            }
        }
    }
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPVisits = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            String accessTime = le.getAccessTime().toString();
            accessTime = accessTime.substring(4, 10);
            //System.out.println("Specified date is " + someday);
            //System.out.println("Date on log is " + accessTime);
            if (someday.equals(accessTime)){
                if (!uniqueIPVisits.contains(ipAddress)) {
                    uniqueIPVisits.add(ipAddress);
                }
            }
        }
        return uniqueIPVisits;
    }
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        for (LogEntry le: records) {
            String ipAddress = le.getIpAddress();
            if(le.getStatusCode() >= low && le.getStatusCode()<= high) {
                if (!uniqueIPsInRange.contains(ipAddress)) {
                    uniqueIPsInRange.add(ipAddress);
                }
            }
        }
        return uniqueIPsInRange.size();
    }
    public static Date parseDate(String dateStr) {
        ParsePosition pp = new ParsePosition(0);
        return  dateFormat.parse(dateStr, pp);
    }
    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int mostNumberVisits = 0;
        for (String ip : counts.keySet() ) {
            if (counts.get(ip) > mostNumberVisits) {
                mostNumberVisits = counts.get(ip);
            }
        }
        return mostNumberVisits;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> ipWithMaxVisits = new ArrayList<String>();
        int maxVisitNumber = mostNumberVisitsByIP(counts);
        int currentIpVisitNumber;
        for (String ip : counts.keySet()) {
            currentIpVisitNumber = counts.get(ip);
            if (currentIpVisitNumber == maxVisitNumber) {
                ipWithMaxVisits.add(ip);
            }
        }
        return ipWithMaxVisits;
    }    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
            if (!ipForDays.containsKey(date)){
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(ip);
                ipForDays.put(date, ips);
            } else {
                ipForDays.get(date).add(ip);
            }
            
        }    
        return ipForDays;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipForDays) {
        String dayWithMostVisits = "";
        int maxVisits = 0;
        int currentDayVisits;
        for (String day : ipForDays.keySet()) {
            currentDayVisits = ipForDays.get(day).size();
            if (maxVisits < currentDayVisits) {
                maxVisits = currentDayVisits;
                dayWithMostVisits = day;
            }
        }
        return dayWithMostVisits;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsPerDays, String day) {
        ArrayList<String> ip = new ArrayList<String>();
        HashMap<String, Integer> ips = new HashMap<String, Integer>();
        ArrayList<String> ipsWithMostsVisitsOnDay = new ArrayList<String>();
        for (String time: ipsPerDays.keySet()) {
            if (time.equals(day)) {
                ip = ipsPerDays.get(time);
            }
        }
        for (int k = 0; k < ip.size(); k++) {
            if (!ips.containsKey(ip.get(k))) {
                ips.put(ip.get(k), 1);
            } else {
                ips.put(ip.get(k), ips.get(ip.get(k))+1);
            }
        }
        ipsWithMostsVisitsOnDay = iPsMostVisits(ips);
        return ipsWithMostsVisitsOnDay;
    }
}
