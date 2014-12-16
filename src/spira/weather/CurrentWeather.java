package spira.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class CurrentWeather {

	public static void main(String[] args) throws IOException {


		StringBuilder info = new StringBuilder();
		URL url = new URL(" http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");
		
		URLConnection connection = url.openConnection();
		
		InputStream in = connection.getInputStream();
		
		byte b[] = new byte[4096];
		int n =-1;
		while((n = in.read(b))!= -1){
		//int n = in.read(b);//amnt of bytes returnedup to 4096
		String s = new String(b ,0, n);
		
		info.append(s);
		}
		String json = info.toString();
		
		Gson gson = new Gson();
		
		
		WeatherNow now = gson.fromJson(json, WeatherNow.class);//take json and create a weathernow class, and populates with data from json, 
		//but need to tell it what member var are
		
		//want to change string form json and give us back a class
		
		System.out.println(now.getMain().getTemp());

	}

}
