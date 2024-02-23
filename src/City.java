import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class City {
    private int cityId;
    private String cityName;
    private int currentTemperature;
    private int currentHumidity;
    private int currentWindSpeed;

    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }

    public City() {
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public int getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public void setCurrentWindSpeed(int currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    private static Connection getConnection() throws SQLException {
        return DatabaseManager.getConnection();
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
}