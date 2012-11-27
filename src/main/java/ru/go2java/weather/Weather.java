package ru.go2java.weather;

/**
 * Класс хранит информацию по погоде для конкретного индекса
 * @author agrigorev
 *
 */
public class Weather {
	/**
	 * Название города
	 */
	private String city;
	/**
	 * Название региона
	 */
    private String region;
    /**
     * Название страны
     */
    private String country;
    /**
     * Погодные условия
     */
    private String condition;
    /**
     * Температура
     */
    private String temp;
    /**
     * Ветер
     */
    private String chill;
    /**
     * Влажность
     */
    private String humidity;

    public Weather() {}

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getTemp() { return temp; }
    public void setTemp(String temp) { this.temp = temp; }

    public String getChill() { return chill; }
    public void setChill(String chill) { this.chill = chill; }

    public String getHumidity() { return humidity; }
    public void setHumidity(String humidity) { this.humidity = humidity; }
}
