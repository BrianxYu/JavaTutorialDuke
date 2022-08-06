import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Main {
    
    public CSVRecord coldestBetweenTheTwo(CSVRecord record1, CSVRecord record2) {
        double temp1 = Double.parseDouble(record1.get("TemperatureF"));
        if (temp1 == -9999) // this is to ignore bogus -9999 temperature
            return record2;
        
        double temp2 = Double.parseDouble(record2.get("TemperatureF"));
        if (temp2 == -9999) // this is to ignore bogus -9999 temperature
            return record1;
        
        if (temp1 > temp2)
            return record2;
        return record1;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        for (CSVRecord record : parser) {
            if (coldestRecord == null)
                coldestRecord = record;
            else
                coldestRecord = coldestBetweenTheTwo(coldestRecord, record);
        }
        return coldestRecord;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2012/weather-2012-01-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println(coldest.get("TemperatureF") + " " + coldest.get("TimeEST"));
    }
    
    public File coldestBetweenTheTwo(File file1, File file2) {
        FileResource fr1 = new FileResource(file1);
        FileResource fr2 = new FileResource(file2);
        CSVRecord record1 = coldestHourInFile(fr1.getCSVParser());
        CSVRecord record2 = coldestHourInFile(fr2.getCSVParser());
        
        double temp1 = Double.parseDouble(record1.get("TemperatureF"));      
        double temp2 = Double.parseDouble(record2.get("TemperatureF"));
        
        if (temp1 > temp2)
            return file2;
        return file1;
    }
    
    public File fileWithColdestTemperature() {
        File coldest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            if (coldest == null)
                coldest = f;
            else
                coldest = coldestBetweenTheTwo(coldest, f);
        }
        return coldest;
    }
    
    public void testFileWithColdestTemperature() {
        File coldestFile = fileWithColdestTemperature();
        String coldestDay = coldestFile.getName();
        System.out.println("Coldest day was in file " + coldestDay);
        
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + record.get("TemperatureF"));
        
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for (CSVRecord r: parser) 
            System.out.println(r.get("DateUTC") + ": " + r.get("TemperatureF"));
    }
    
    public CSVRecord lowerHumidityBetweenTheTwo(CSVRecord record1, CSVRecord record2) {
        String humidityString1 = record1.get("Humidity");
        if (humidityString1.equals("N/A")) // this is to ignore unknown N/A humidity value
            return record2;
        int humidity1 = Integer.parseInt(humidityString1);
        
        String humidityString2 = record2.get("Humidity");
        if (humidityString2.equals("N/A")) // this is to ignore unknown N/A humidity value
            return record1;
        int humidity2 = Integer.parseInt(humidityString2);
        
        if (humidity1 > humidity2)
            return record2;
        return record1;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;
        for (CSVRecord record : parser) {
            if (lowestHumidityRecord == null)
                lowestHumidityRecord = record;
            else
                lowestHumidityRecord = lowerHumidityBetweenTheTwo(lowestHumidityRecord, record);
        }
        return lowestHumidityRecord;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currlowest = lowestHumidityInFile(fr.getCSVParser());
            if (lowest == null)
                lowest = currlowest;
            else
                lowest = lowerHumidityBetweenTheTwo(lowest, currlowest);
        }
        return lowest;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        double totalTemp = 0;
        int numMeasure = 0;
        for (CSVRecord record : parser) {
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp != -9999) {
                totalTemp += currTemp;
                numMeasure++;
            }
        }
        return totalTemp / (double)numMeasure;
    }    
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0;
        int numMeasure = 0;
        for (CSVRecord record : parser) {
            String currHumidityString = record.get("Humidity");
            if (!currHumidityString.equals("N/A")) {
                int currHumidity = Integer.parseInt(currHumidityString);
                if (currHumidity >= value) {
                    double currTemp = Double.parseDouble(record.get("TemperatureF"));
                    if (currTemp != -9999) {
                        totalTemp += currTemp;
                        numMeasure++;
                    }
                }
            }
        }
        if (numMeasure > 0)
            return totalTemp / (double)numMeasure;
        return 0;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (avgTemp != 0) 
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        else 
            System.out.println("No temperatures with that humidity");
    }
}
