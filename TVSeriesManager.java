import java.util.ArrayList;
import java.util.Scanner;

// SeriesModel class to hold series data
class SeriesModel {
    public String seriesId;
    public String seriesName;
    public String seriesAge;
    public String seriesNumberOfEpisodes;
}

// Series class with all the required methods
class Series {
    private ArrayList<SeriesModel> seriesList;
    
    public Series() {
        seriesList = new ArrayList<>();
    }
    
    public void captureSeries(String id, String name, String age, String episodes) {
        SeriesModel newSeries = new SeriesModel();
        newSeries.seriesId = id;
        newSeries.seriesName = name;
        newSeries.seriesAge = age;
        newSeries.seriesNumberOfEpisodes = episodes;
        seriesList.add(newSeries);
    }
    
    public SeriesModel searchSeries(String id) {
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                return series;
            }
        }
        return null;
    }
    
    public boolean updateSeries(String id, String name, String age, String episodes) {
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                series.seriesName = name;
                series.seriesAge = age;
                series.seriesNumberOfEpisodes = episodes;
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteSeries(String id) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                seriesList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }
    
    public boolean validateAgeRestriction(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

// Main application class
public class TVSeriesManager {
    private static JavaApplication8 seriesManager = new JavaApplication8();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        displayWelcomeScreen();
    }
    
    private static void displayWelcomeScreen() {
        System.out.println("LATEST SERIES - 2025");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        String input = scanner.nextLine();
        
        if (input.equals("1")) {
            displayMainMenu();
        } else {
            System.out.println("Exiting application. Goodbye!");
            System.exit(0);
        }
    }
    
    private static void displayMainMenu() {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series app restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            System.out.print("Your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    captureNewSeries();
                    break;
                case "2":
                    searchForSeries();
                    break;
                case "3":
                    updateSeriesRestriction();
                    break;
                case "4":
                    deleteSeries();
                    break;
                case "5":
                    printSeriesReport();
                    break;
                case "6":
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                System.out.println("Exiting application. Goodbye!");
                System.exit(0);
            }
        }
    }
    
    private static void captureNewSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        
        System.out.print("Enter the series id: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        
        // Validate age restriction
        String ageRestriction;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            ageRestriction = scanner.nextLine();
            
            if (seriesManager.validateAgeRestriction(ageRestriction)) {
                break;
            } else {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            }
        }
        
        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = scanner.nextLine();
        
        seriesManager.captureSeries(id, name, ageRestriction, episodes);
        System.out.println("Series processed successfully!!!");
    }
    
    private static void searchForSeries() {
        System.out.println("\nSEARCH FOR A SERIES");
        System.out.print("Enter the series id to search: ");
        String searchId = scanner.nextLine();
        
        SeriesModel series = seriesManager.searchSeries(searchId);
        
        if (series != null) {
            System.out.println("\nSERIES ID: " + series.seriesId);
            System.out.println("SERIES NAME: " + series.seriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.seriesAge);
            System.out.println("SERIES NUMBER OF EPISODES: " + series.seriesNumberOfEpisodes);
        } else {
            System.out.println("\nSeries with Series Id: " + searchId + " was not found!");
        }
    }
    
    private static void updateSeriesRestriction() {
        System.out.println("\nUPDATE SERIES APP RESTRICTION");
        System.out.print("Enter the series id to update: ");
        String searchId = scanner.nextLine();
        
        SeriesModel existingSeries = seriesManager.searchSeries(searchId);
        if (existingSeries != null) {
            System.out.println("Current series name: " + existingSeries.seriesName);
            System.out.print("Enter the new series name: ");
            String name = scanner.nextLine();
            
            // Validate new age restriction
            String ageRestriction;
            while (true) {
                System.out.print("Enter the new age restriction: ");
                ageRestriction = scanner.nextLine();
                
                if (seriesManager.validateAgeRestriction(ageRestriction)) {
                    break;
                } else {
                    System.out.println("You have entered an incorrect series age!!!");
                    System.out.print("Please re-enter the series age >> ");
                }
            }
            
            System.out.print("Enter the number of episodes: ");
            String episodes = scanner.nextLine();
            
            boolean updated = seriesManager.updateSeries(searchId, name, ageRestriction, episodes);
            if (updated) {
                System.out.println("Series updated successfully!");
            } else {
                System.out.println("Failed to update series.");
            }
        } else {
            System.out.println("Series with Series Id: " + searchId + " was not found!");
        }
    }
    
    private static void deleteSeries() {
        System.out.println("\nDELETE A SERIES");
        System.out.print("Enter the series id to delete: ");
        String searchId = scanner.nextLine();
        
        SeriesModel existingSeries = seriesManager.searchSeries(searchId);
        if (existingSeries != null) {
            System.out.print("Are you sure you want to delete series " + searchId + " from the system? Yes (y) to delete: ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("y")) {
                boolean deleted = seriesManager.deleteSeries(searchId);
                if (deleted) {
                    System.out.println("---");
                    System.out.println("Series with Series Id: " + searchId + " WAS deleted!");
                    System.out.println("---");
                } else {
                    System.out.println("Failed to delete series.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Series with Series Id: " + searchId + " was not found!");
        }
    }
    
    private static void printSeriesReport() {
        System.out.println("\nSERIES REPORT - 2025");
        System.out.println("==========================================");
        
        ArrayList<SeriesModel> seriesList = seriesManager.getSeriesList();
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
        } else {
            for (int i = 0; i < seriesList.size(); i++) {
                SeriesModel series = seriesList.get(i);
                System.out.println("Series " + (i + 1));
                System.out.println();
                System.out.println("SERIES ID: " + series.seriesId);
                System.out.println("SERIES NAME: " + series.seriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.seriesAge);
                System.out.println("NUMBER OF EPISODES: " + series.seriesNumberOfEpisodes);
                System.out.println();
            }
        }
        
        System.out.println("Total series: " + seriesList.size());
        System.out.println("==========================================");
    }
}

// Unit test class (commented out for normal execution)
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeriesTest {
    private Series seriesManager;
    
    @BeforeEach
    public void setUp() {
        seriesManager = new Series();
        // Add some test data
        seriesManager.captureSeries("101", "Extreme Sports", "12", "10");
        seriesManager.captureSeries("102", "Home Cooking", "10", "15");
    }
    
    @Test
    public void TestSearchSeries() {
        SeriesModel result = seriesManager.searchSeries("101");
        assertNotNull(result);
        assertEquals("101", result.seriesId);
        assertEquals("Extreme Sports", result.seriesName);
        assertEquals("12", result.seriesAge);
        assertEquals("10", result.seriesNumberOfEpisodes);
    }
    
    @Test
    public void TestSearchSeries_SeriesNotFound() {
        SeriesModel result = seriesManager.searchSeries("999");
        assertNull(result);
    }
    
    @Test
    public void TestUpdateSeries() {
        boolean result = seriesManager.updateSeries("101", "Extreme Sports 2025", "14", "12");
        assertTrue(result);
        
        SeriesModel updatedSeries = seriesManager.searchSeries("101");
        assertNotNull(updatedSeries);
        assertEquals("Extreme Sports 2025", updatedSeries.seriesName);
        assertEquals("14", updatedSeries.seriesAge);
        assertEquals("12", updatedSeries.seriesNumberOfEpisodes);
    }
    
    @Test
    public void TestDeleteSeries() {
        boolean result = seriesManager.deleteSeries("101");
        assertTrue(result);
        
        SeriesModel deletedSeries = seriesManager.searchSeries("101");
        assertNull(deletedSeries);
    }
    
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        boolean result = seriesManager.deleteSeries("999");
        assertFalse(result);
    }
    
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        assertTrue(seriesManager.validateAgeRestriction("5"));
        assertTrue(seriesManager.validateAgeRestriction("12"));
        assertTrue(seriesManager.validateAgeRestriction("18"));
    }
    
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        assertFalse(seriesManager.validateAgeRestriction("1"));
        assertFalse(seriesManager.validateAgeRestriction("19"));
        assertFalse(seriesManager.validateAgeRestriction("0"));
        assertFalse(seriesManager.validateAgeRestriction("twenty"));
        assertFalse(seriesManager.validateAgeRestriction(""));
    }
}
*/