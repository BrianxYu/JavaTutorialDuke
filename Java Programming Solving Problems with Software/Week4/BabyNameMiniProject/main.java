import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class main {
    public void totalBirths(FileResource fr) {
        int boys = 0;
        int boyNames = 0;
        int girls = 0;
        int girlNames = 0;
        for (CSVRecord record: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            if (record.get(1).equals("F")) {
                girls += numBorn;
                girlNames++;
            }
            else {
                boys += numBorn;
                boyNames++;
            }
        }
        System.out.println("total births = " + (boys + girls));
        System.out.println("total girls = " + girls);
        System.out.println("total boys = " + boys);
        System.out.println("total girl names = " + girlNames);
        System.out.println("total boy names = " + boyNames);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        for (CSVRecord record: fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name))
                    return rank;
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        int currRank = 0;
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        for (CSVRecord record: fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)) {
                currRank++;
                if (currRank == rank)
                    return record.get(0);
            }
        }        
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int rank = -1;
        int year = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1) {
                if (rank == -1) {
                    rank = currRank;
                    year = currYear;
                }
                else {
                    if (rank > currRank) { // if new rank is better(smaller number), update
                        rank = currRank;
                        year = currYear;
                    }
                }
            }
        }
        if (rank == -1) // if name not exist in all files, return -1
            return -1;
        return year; // otherwise, return year with highest rank
    }
    
    public double getAverageRank(String name, String gender) {
        int count = 0;
        int total = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1) {
                total += currRank;
                count++;
            }
        }
        if (count == 0)
            return -1.0;
        return (double)total / (double)count;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        for (CSVRecord record: fr.getCSVParser(false)) {
            if(record.get(1).equals(gender)) {
                if (record.get(0).equals(name))
                    return total;
                else
                    total += Integer.parseInt(record.get(2));
            }
        }
        return total;
    }
}
