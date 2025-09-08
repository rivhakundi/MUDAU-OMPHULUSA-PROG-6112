import java.util.ArrayList;
import java.util.Scanner;



// Main application class
public class Series {
    private static final Series seriesManager = new Series();
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

        private void captureSeries(String id, String name, String ageRestriction, String episodes) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private SeriesModel searchSeries(String searchId) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean validateAgeRestriction(String ageRestriction) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean updateSeries(String searchId, String name, String ageRestriction, String episodes) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private boolean deleteSeries(String searchId) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private ArrayList<SeriesModel> getSeriesList() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }


