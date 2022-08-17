
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
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> ipAddresses = new ArrayList<String>();
         for (LogEntry record : records) {
            String currIpAddress = record.getIpAddress();
            if (!ipAddresses.contains(currIpAddress)) 
                ipAddresses.add(currIpAddress);
         }
         return ipAddresses.size();   
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry record : records) {
             int currStatusCode = record.getStatusCode();
             if (currStatusCode > num)
                System.out.println(record);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry record : records) {
             String currDateStr= record.getAccessTime().toString().substring(4, 10);
             String currIpAddress = record.getIpAddress();
             if (currDateStr.equals(someday) && !uniqueIPs.contains(currIpAddress))
                uniqueIPs.add(currIpAddress);
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry record : records) {
             int currStatusCode = record.getStatusCode();
             String currIpAddress = record.getIpAddress();
             if (currStatusCode >= low && currStatusCode <= high && !uniqueIPs.contains(currIpAddress))
                uniqueIPs.add(currIpAddress);
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry record : records) {
             String currIpAddress = record.getIpAddress();
             if (!counts.containsKey(currIpAddress))
                counts.put(currIpAddress, 1);
             else
                counts.put(currIpAddress, counts.get(currIpAddress) + 1);
         }
         return counts;
     }
     
     public HashMap<String,Integer> countVisitsPerIP(ArrayList<String> ips) {
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (String ip : ips) {
             if (!counts.containsKey(ip))
                counts.put(ip, 1);
             else
                counts.put(ip, counts.get(ip) + 1);
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
         int mostVisit = 0;
         for (int count : counts.values()) {
             if (count > mostVisit)
                mostVisit = count;
         }
         return mostVisit;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
         ArrayList<String> mostVisitIPs = new ArrayList<String>();
         int mostVisits = mostNumberVisitsByIP(counts);
         for (String ip : counts.keySet()) {
             if (counts.get(ip) == mostVisits)
                mostVisitIPs.add(ip);
         }
         return mostVisitIPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for (LogEntry record : records) {
             String ip = record.getIpAddress();
             String date = record.getAccessTime().toString().substring(4, 10);
             if (!map.containsKey(date)) {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 map.put(date, ips);
             }
             else
                 map.get(date).add(ip);
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
         String dayMostVisited = null;
         int mostVisits = 0;
         for (String date : map.keySet()) {
            int currSize = map.get(date).size();
             if (currSize > mostVisits) {
                dayMostVisited = date;
                mostVisits = currSize;
            }
         }
         return dayMostVisited;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
         ArrayList<String> ips = map.get(day);
         HashMap<String,Integer> counts = countVisitsPerIP(ips);
         return iPsMostVisits(counts);
     }
}

















