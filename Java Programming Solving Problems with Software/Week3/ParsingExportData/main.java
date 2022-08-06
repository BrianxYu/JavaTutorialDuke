import edu.duke.*;
import org.apache.commons.csv.*;

public class main {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("\nHere is the information for Nauru");
        System.out.println(countryInfo(parser, "Nauru"));
        
        System.out.println("\nThe following countries export cotton and flowers:");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        System.out.println("\nThe following countries export cocoa:");
        parser = fr.getCSVParser();
        listExportersOneProducts(parser, "cocoa");
        
        System.out.println("\nThe following countries have values above a trillion:");
        parser = fr.getCSVParser();
        numberOfExporters(parser, "$999,999,999,999");
        
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record: parser) {
            String currCountry = record.get("Country");
            if (currCountry.equals(country)) {
                return currCountry + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersOneProducts(CSVParser parser, String exportItem) {
        int counter = 0;
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                System.out.println(record.get("Country"));
                counter++;
            }
        }
        System.out.println("Number of country exporting " + exportItem + ": " + counter);
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        int counter = 0;
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
                counter++;
            }
        }
        System.out.println("Number of country exporting " + exportItem1 + " and " + exportItem2 + ": " + counter);
    }
    
    public void numberOfExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
