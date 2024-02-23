
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

    @Override
    public String toString() {
        return "CityHistory{" +
                "historicalDataId=" + historicalDataId +
                ", cityId=" + cityId +
                ", eventDate='" + eventDate + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
