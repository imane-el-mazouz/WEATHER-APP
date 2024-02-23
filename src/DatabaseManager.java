import java.sql.*;
import java.time.LocalDate;
import java.util.*;
public class DatabaseManager {
     private static final String URL = "jdbc:mysql://localhost/meteo";
     private static final String USERNAME = "imane";
     private static final String PASSWORD = "1234";


     public static Connection getConnection() throws SQLException {
          return DriverManager.getConnection(URL, USERNAME, PASSWORD);
     }

     public static List<City> getAllcities() throws SQLException {
          List<City> cities = new ArrayList<>();
          String sql = "SELECT * FROM City";
          Connection connection = getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
               City city = new City();
               city.setCityId(resultSet.getInt("cityId"));
               city.setCityName(resultSet.getString("cityName"));
               city.setCurrentTemperature(resultSet.getInt("currentTemperature"));
               city.setCurrentHumidity(resultSet.getInt("currentHumidity"));
               city.setCurrentWindSpeed(resultSet.getInt("currentWindSpeed"));
               cities.add(city);
          }
          connection.close();
          statement.close();
          resultSet.close();
          return cities;
     }

     public static List<CityHistory> getAllHistorycities() throws SQLException {
          List<CityHistory> cityHistories = new ArrayList<>();
          String sql = "SELECT * FROM CityHistory";
          Connection connection = getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
               int historicalDataId = resultSet.getInt("historicalDataId");
               int cityId = resultSet.getInt("cityId");
               String eventDate = resultSet.getString("eventDate");
               int temperature = resultSet.getInt("temperature");
               cityHistories.add(new CityHistory(historicalDataId, cityId, eventDate, temperature));
          }
          connection.close();
          statement.close();
          resultSet.close();
          return cityHistories;
     }



     public static void addCity(City city) throws SQLException {
          String sql = "INSERT INTO City(cityName, cityId, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
          try {
               Connection connection = getConnection();
               PreparedStatement statement = connection.prepareStatement(sql);
               statement.setString(1, city.getCityName());
               statement.setInt(2, city.getCityId());
               statement.setInt(3, city.getCurrentTemperature());
               statement.setInt(4, city.getCurrentHumidity());
               statement.setInt(5, city.getCurrentWindSpeed());
               statement.executeUpdate();
               connection.close();
               statement.close();
               System.out.println("The city is added successfully!");
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }


     public ResultSet readAllCities() throws SQLException {
          Connection connection = getConnection();
          String query = "SELECT * FROM city ";
          PreparedStatement statement = connection.prepareStatement(query);
          return statement.executeQuery();
     }

     public static void updateCity(City city) throws SQLException {
          String sql = "UPDATE City SET cityName = ? WHERE cityId = ?";
          Connection connection = getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1, city.getCityName());
          statement.setInt(2, city.getCityId());
          statement.executeUpdate();
          connection.close();
          statement.close();
          System.out.println("the city is updated successfully!");
     }

     public static void deleteCity(int cityId) throws SQLException {
          String sql = "DELETE FROM City WHERE cityId = ?";
          Connection connection = getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, cityId);
          statement.executeUpdate();
          connection.close();
          statement.close();
          System.out.println("City deleted successfully!");
     }
     public static City getCityByName(String cityName) throws SQLException {
          String sql = "SELECT * FROM City WHERE cityName = ?";
          Connection connection = getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setString(1, cityName);
          ResultSet resultSet = statement.executeQuery();
          City city = null;
          if (resultSet.next()) {
               int id = resultSet.getInt("cityId");
               String name = resultSet.getString("cityName");
               int temp = resultSet.getInt("currentTemperature");
               int hm = resultSet.getInt("currentHumidity");
               int wind = resultSet.getInt("currentWindSpeed");
               city = new City();
          }

          connection.close();
          return city;
     }
     public ResultSet readAllHistoryForCity(int cityId) throws SQLException {
          Connection connection = getConnection();
          String query = "SELECT * FROM cityhistory WHERE cityId = ?";
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, cityId);
          return statement.executeQuery();
     }

     public static void addCityHistory(CityHistory cityHistory) throws SQLException {
          Connection connection = getConnection();
          String query = "INSERT INTO cityhistory (historicalDataId, cityId, eventDate, temperature) VALUES (?, ?, ?, ?)";
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, cityHistory.getHistoricalDataId());
          statement.setInt(2, cityHistory.getCityId());
          statement.setString(3, cityHistory.getEventDate());
          statement.setInt(4, cityHistory.getTemperature());
          statement.executeUpdate();
          statement.close();
          connection.close();
     }

     public static void updateCityHistory(CityHistory cityHistory) throws SQLException {
          Connection connection = getConnection();
          String query = "UPDATE cityhistory SET eventDate = ?, temperature = ? WHERE historicalDataId = ?";
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, cityHistory.getEventDate());
          statement.setInt(2, cityHistory.getTemperature());
          statement.setInt(3, cityHistory.getHistoricalDataId());
          statement.executeUpdate();
          statement.close();
          connection.close();
     }

     public static void deleteCityHistory(int historicalDataId) throws SQLException {
          Connection connection = getConnection();
          String query = "DELETE FROM cityhistory WHERE historicalDataId = ?";
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, historicalDataId);
          statement.executeUpdate();
          statement.close();
          connection.close();
          System.out.println("The history of this city is deleted");
     }
     public static int getCityHistoryById(int id) throws SQLException {
          String sql = "SELECT * FROM City ,CityHistory WHERE City.cityId = ?";

          Connection connection = getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, id);
          ResultSet resultSet = statement.executeQuery();

          CityHistory cityHistory = null;
          if (resultSet.next()) {
               int cityId = resultSet.getInt("cityId");
               int temp = resultSet.getInt("temperature");
               LocalDate eventDate = resultSet.getDate("eventDate").toLocalDate();
               cityHistory = new CityHistory();
          }

          connection.close();
          return 0;
     }
}






