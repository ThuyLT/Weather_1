package com.example.lethuy.weathersimpleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.Weather;

/**
 * Created by Le Thuy on 28/03/2016.
 */
public class Database extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = Database.class.getName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "WeatherManager";

    //Table Names
    private static final String TABLE_NAME_CITY = "city";
    private static final String TABLE_NAME_WEATHER = "weather";

    //Table Structure CITY
    public static final String CITY_ID = "id";
    public static final String CITY_NAME = "name";
    public static final String CITY_LON = "lon";
    public static final String CITY_LAT = "lat";
    public static final String CITY_COUNTRY = "country";

    //Table Structure WEATHER
    public static final String WEATHER_ID = "id";
    public static final String WEATHER_DT = "dt";
    public static final String WEATHER_TEMP = "temp";
    public static final String WEATHER_TEMP_MIN = "temp_min";
    public static final String WEATHER_TEMP_MAX = "temp_max";
    public static final String WEATHER_PRESSURE = "pressure";
    public static final String WEATHER_HUMIDITY = "humidity";
    public static final String WEATHER_MAIN = "main";
    public static final String WEATHER_ICON = "icon";
    public static final String WEATHER_ALL = "all";
    public static final String WEATHER_SPEED = "speed";
    public static final String WEATHER_DEG = "deg";
    public static final String WEATHER_SEA_LEVEL = "sea_level";
    public static final String WEATHER_GRND_LEVEL = "grnd_level";
    public static final String WEATHER_DT_TXT = "dt_txt";
    public static final String WEATHER_DESCRIPTION = "description";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private SQLiteDatabase mdb;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CITY = "CREATE TABLE" + TABLE_NAME_CITY
                + "(" + CITY_ID + " DOUBLE PRIMARY KEY, "
                + CITY_NAME + " TEXT, " + CITY_LON + " DOUBLE, "
                + CITY_LAT + " DOUBLE, " + CITY_COUNTRY + " TEXT)";
        db.execSQL(CREATE_TABLE_CITY);

        String CREATE_TABLE_WEATHER = "CREATE TABLE" + TABLE_NAME_WEATHER
                + "(" + WEATHER_ID + "DOUBLE PRIMARY KEY, "
                + WEATHER_DT + "DOUBLE, " + WEATHER_TEMP + "DOUBLE,"
                + WEATHER_TEMP_MIN + "DOUBLE, " + WEATHER_TEMP_MAX + "DOUBLE, "
                + WEATHER_PRESSURE + "DOUBLE, "
                + WEATHER_HUMIDITY + "DOUBLE, " + WEATHER_MAIN + "TEXT, "
                + WEATHER_ICON + "TEXT, " + WEATHER_ALL + "DOUBLE, "
                + WEATHER_SPEED + "DOUBLE, " + WEATHER_DEG + "DOUBLE, "
                + WEATHER_SEA_LEVEL + "DOUBLE, " + WEATHER_GRND_LEVEL + "DOUBLE, "
                + WEATHER_DT_TXT + "DOUBLE, " + WEATHER_DESCRIPTION + "TEXT)";
        db.execSQL(CREATE_TABLE_WEATHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME_CITY);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME_WEATHER);
        onCreate(db);
    }

    /**
     * Creating a city
     */
    public double createCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CITY_ID, city.getId());
        values.put(CITY_NAME, city.getName());
        values.put(CITY_LON, city.getLon());
        values.put(CITY_LAT, city.getLat());
        values.put(CITY_COUNTRY, city.getCountry());

        // insert row
        double city_id = db.insert(TABLE_NAME_CITY, null, values);

        return city_id;
    }


    /**
     * getting all city
     *
     * @param name
     */
    public List<City> getAllCities(String name) {
        List<City> cityList = new ArrayList<City>();
        String sql = "SELECT * FROM " + TABLE_NAME_CITY;
        String[] dk = null;
        Cursor c = mdb.rawQuery(sql, dk);

        if (c.moveToFirst()) {
            do {
                City city = new City();
                city.setId(Double.parseDouble(c.getString(0)));
                city.setName(c.getString(1));
                city.setLon(Double.parseDouble(c.getString(2)));
                city.setLat(Double.parseDouble(c.getString(3)));
                city.setCountry(c.getString(4));

                cityList.add(city);
            } while (c.moveToNext());
        }
        return cityList;
    }

    /**
     * Creating a city
     */
    public double createWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WEATHER_ID, weather.getId());
        values.put(WEATHER_DT, weather.getDt());
        values.put(WEATHER_TEMP, weather.getTemp());
        values.put(WEATHER_TEMP_MIN, weather.getTemp_min());
        values.put(WEATHER_TEMP_MAX, weather.getTemp_max());
        values.put(WEATHER_PRESSURE, weather.getPressure());
        values.put(WEATHER_HUMIDITY, weather.getHumidity());
        values.put(WEATHER_MAIN, weather.getMain());
        values.put(WEATHER_ICON, weather.getTemp());
        values.put(WEATHER_ALL, weather.getAll());
        values.put(WEATHER_SPEED, weather.getSpeed());
        values.put(WEATHER_DEG, weather.getDeg());
        values.put(WEATHER_SEA_LEVEL, weather.getSea_level());
        values.put(WEATHER_GRND_LEVEL, weather.getGrnd_level());
        values.put(WEATHER_DESCRIPTION, weather.getDescription());
        values.put(WEATHER_DT_TXT, weather.getDt_txt());

        // insert row
        double weather_id = db.insert(TABLE_NAME_WEATHER, null, values);

        return weather_id;
    }

    /**
     * getting all weather
     */

    public List<Weather> getAllWeathers() {
        List<Weather> weatherList = new ArrayList<Weather>();
        String sql = "SELECT * FROM " + TABLE_NAME_WEATHER;
        String[] dk = null;
        Cursor c = mdb.rawQuery(sql, dk);

        if (c.moveToFirst()) {
            do {
                Weather weather = new Weather();
                weather.setId(Double.parseDouble(c.getString(0)));
                weather.setDt(Double.parseDouble(c.getString(1)));
                weather.setTemp(Double.parseDouble(c.getString(2)));
                weather.setTemp_min(Double.parseDouble(c.getString(3)));
                weather.setTemp_max(Double.parseDouble(c.getString(4)));
                weather.setPressure(Double.parseDouble(c.getString(5)));
                weather.setHumidity(Double.parseDouble(c.getString(6)));
                weather.setMain(c.getString(7));
                weather.setIcon(c.getString(8));
                weather.setAll(Double.parseDouble(c.getString(9)));
                weather.setSpeed(Double.parseDouble(c.getString(10)));
                weather.setDeg(Double.parseDouble(c.getString(11)));
                weather.setSea_level(Double.parseDouble(c.getString(12)));
                weather.setGrnd_level(Double.parseDouble(c.getString(13)));
                weather.setDt_txt(c.getString(14));
                weather.setDescription(c.getString(15));

                weatherList.add(weather);
            } while (c.moveToNext());
        }
        return weatherList;
    }

    /**
     * Updating a city
     */
    public int updateCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CITY_NAME, city.getName());

        // updating row
        return db.update(TABLE_NAME_CITY, values, CITY_ID + " = ?",
                new String[]{String.valueOf(city.getId())});
    }

    /**
     * Updating a weather
     */
    public int updateWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // updating row
        return db.update(TABLE_NAME_WEATHER, values, WEATHER_ID + " = ?",
                new String[]{String.valueOf(weather.getId())});
    }

    /**
     * Deleting a city
     */
    public void deleteCity(City city, boolean should_delete_all_city) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (should_delete_all_city) {
            List<City> allCities = getAllCities(city.getName());
        }
        db.delete(TABLE_NAME_CITY, CITY_ID + " = ?",
                new String[]{String.valueOf(city.getId())});
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
