
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int numUniqueAddress = la.countUniqueIPs();
        System.out.println("There are " + numUniqueAddress + " unique IP addresses");
    }
    
    public void testAllHigherThanNum () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String accessDate = "Sep 24";
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(accessDate);
        System.out.println("There were " + uniqueIPs.size() + " unique IP addresses accessed on " + accessDate);
        for (String ips : uniqueIPs)
            System.out.println("\t" + ips);
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int numUniqueAddressInRange = la.countUniqueIPsInRange(400, 499);
        System.out.println("There are " + numUniqueAddressInRange + " unique IP addresses within range");
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int mostVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Maximum number of visits to this website by a single IP address is " + mostVisits);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ips = la.iPsMostVisits(counts);
        System.out.println("The following IP addresses visited the most:");
        for (String ip: ips) {
            System.out.println("\t" + ip);
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String dateMostFreq = la.dayWithMostIPVisits(la.iPsForDays());
        System.out.println(dateMostFreq + " has most frequent visits");
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String day = "Sep 30";
        ArrayList<String> ids = la.iPsWithMostVisitsOnDay(la.iPsForDays(), day);
        System.out.println("On " + day + ", the most frequently visited IP addresses are:");
        for (String id: ids)
            System.out.println("\t" + id);
    }
}


















