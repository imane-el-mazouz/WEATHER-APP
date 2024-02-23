
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CityHistory {
    private int historicalDataId;
    private int cityId;
    private String eventDate;
    private int temperature;

    // Constructor
    public CityHistory(int historicalDataId, int cityId, String eventDate, int temperature) {
        this.historicalDataId = historicalDataId;
        this.cityId = cityId;
        this.eventDate = eventDate;
        this.temperature = temperature;
    }

    public CityHistory() {
    }
    //getters

    public int getHistoricalDataId() {
        return historicalDataId;
    }

    public int getCityId() {
        return cityId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public int getTemperature() {
        return temperature;
    }

    //setters
    public void setHistoricalDataId(int historicalDataId) {
        this.historicalDataId = historicalDataId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    private static Connection getConnection() throws SQLException {
        return DatabaseManager.getConnection();
    }

    public ResultSet readAllHistoryForCity(int cityId) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM cityhistory WHERE cityId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cityId);
        return statement.executeQuery();
    }

    public void addCityHistory(CityHistory cityHistory) throws SQLException {
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

    public void updateCityHistory(CityHistory cityHistory) throws SQLException {
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

    public void deleteCityHistory(int historicalDataId) throws SQLException {
        Connection connection = getConnection();
        String query = "DELETE FROM cityhistory WHERE historicalDataId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, historicalDataId);
        statement.executeUpdate();
        statement.close();
        connection.close();
        System.out.println("The history of this city is deleted");
    }
    public static int getCityHistoryById() throws SQLException {
        String sql = "SELECT * FROM City ,CityHistory WHERE City.cityId = ?";

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, getCityHistoryById());
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
