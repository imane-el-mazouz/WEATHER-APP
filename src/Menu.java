import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public static void MenuPrincipal() throws SQLException {
        int choiceofMain;

        do {
            System.out.println("PRINCIPAL MENU ");
            System.out.println("1 -MENU OF CITY");
            System.out.println("2 -MENU OF CITY HISTORY");
            System.out.println("EXIT ..");
            System.out.print("ENTER YOUR CHOICE ");
            choiceofMain = new Scanner(System.in).nextInt();
            switch (choiceofMain) {
                case 1:
                    cityMenu();
                    break;
                case 2:
                    cityHistoryMenu();
                    break;
                case 3:
                    System.out.println("exit ");
                    break;
                default:
                    System.out.println("invalid choice ");
                    break;
            }
        } while (choiceofMain != 3);
    }

    public static void cityMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int choice, cityId, currentTemperature, currentHumidity, currentWindSpeed;
        String cityName;

        do {
            System.out.println("Menu of the City");
            System.out.println("1. Add City ");
            System.out.println("2. Update City ");
            System.out.println("3. Delete City  ");
            System.out.println("4. Read All Cities");
            System.out.println("5. get city by name");
            System.out.println("6. Quit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("ID : ");
                    cityId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("NAME : ");
                    cityName = scanner.nextLine();
                    System.out.print("Current Temperature : ");
                    currentTemperature = scanner.nextInt();
                    System.out.print("Current Humidity : ");
                    currentHumidity = scanner.nextInt();
                    System.out.print("Current WindSpeed : ");
                    currentWindSpeed = scanner.nextInt();
                    System.out.println("city added successfully ");
                    DatabaseManager.addCity(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
                    break;
                case 2:
                    System.out.print("Enter City ID to update: ");
                    cityId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("NAME : ");
                    cityName = scanner.nextLine();
                    System.out.print("Current Temperature : ");
                    currentTemperature = scanner.nextInt();
                    System.out.print("Current Humidity : ");
                    currentHumidity = scanner.nextInt();
                    System.out.print("Current WindSpeed : ");
                    currentWindSpeed = scanner.nextInt();
                    DatabaseManager.updateCity(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
                    break;
                case 3:
                    System.out.print("Enter ID of the city to delete: ");
                    cityId = scanner.nextInt();
                    DatabaseManager.deleteCity(cityId);
                    break;
                case 4:
                    System.out.println("All Cities:");
                    for (City city : DatabaseManager.getAllcities()) {
                        System.out.println(city);
                    }
                    break;
                case 5:
                    System.out.print("Enter the name of the city to search: ");
                    String cityNameToSearch = new Scanner(System.in).nextLine();
                    City selectedCity = DatabaseManager.getCityByName(cityNameToSearch);

                    if (selectedCity != null) {
                        System.out.println("Selected City:");
                        System.out.println("ID: " + selectedCity.getCityId());
                        System.out.println("Name: " + selectedCity.getCityName());
                        System.out.println("Temperature: " + selectedCity.getCurrentTemperature());
                        System.out.println("Humidity: " + selectedCity.getCurrentHumidity());
                        System.out.println("Wind Speed: " + selectedCity.getCurrentWindSpeed());
                    } else {
                        System.out.println("City not exists.");
                    }
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }
        } while (choice != 6);
        scanner.close();
    }

    public static void cityHistoryMenu() throws SQLException {
        int option, historicalDataId, cityId, temperature;
        String eventDateInput;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate eventDate;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Menu of the CityHistory");
            System.out.println("1. Add City History");
            System.out.println("2. Read All the History of the City");
            System.out.println("3. Update City History");
            System.out.println("4. Delete City History");
            System.out.println("5. get city by ID");
            System.out.println("6. Exit");
            System.out.println("Enter your option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("DataID : ");
                    historicalDataId = scanner.nextInt();
                    System.out.print("Temperature : ");
                    temperature = scanner.nextInt();
                    System.out.println("Enter event date (DD/MM/YYYY): ");
                    eventDateInput = scanner.next();
                    eventDate = LocalDate.parse(eventDateInput, formatter);
                    System.out.println("City Id : ");
                    cityId = scanner.nextInt();
                    DatabaseManager.addCityHistory(new CityHistory(historicalDataId, cityId, eventDate.toString(), temperature));
                    break;
                case 2:
                    System.out.println("All City Histories:");
                    for (CityHistory cth : DatabaseManager.getAllHistorycities()) {
                        System.out.println(cth);
                    }
                    break;

                case 3:
                    System.out.print("Enter Historical Data ID to update: ");
                    historicalDataId = scanner.nextInt();
                    System.out.print("Temperature : ");
                    temperature = scanner.nextInt();
                    System.out.println("Enter event date (DD/MM/YYYY): ");
                    eventDateInput = scanner.next();
                    eventDate = LocalDate.parse(eventDateInput, formatter);
                    System.out.print("City Id : ");
                    cityId = scanner.nextInt();
                    DatabaseManager.updateCityHistory(new CityHistory(historicalDataId, cityId, eventDate.toString(), temperature));
                    break;
                case 4:
                    System.out.print("Enter Historical Data ID to delete: ");
                    historicalDataId = scanner.nextInt();
                    DatabaseManager.deleteCityHistory(historicalDataId);
                    break;

                case 5:
                    System.out.println("Enter the id of the city history to search: ");
                    int cityidToSearch = new Scanner(System.in).nextInt();
                    DatabaseManager.getCityHistoryById(cityidToSearch);

//                    if (selectedCity != null) {
//                        for (CityHistory cth : DatabaseManager.getAllHistoryCities()) {
//                            System.out.println(cth);
//                        }
//                    } else {
//                        System.out.println("City history not found.");
//                    }
                    break;
                case 6:
                    System.out.println("Exit");
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 5);
        scanner.close();
    }
}











