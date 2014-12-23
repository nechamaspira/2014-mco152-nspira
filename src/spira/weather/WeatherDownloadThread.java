package spira.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {

	@Override
	public void run() {
		//put inside everything want to be done, all io stuff, get data and put it in json
		try{
		StringBuilder info = new StringBuilder();
		URL url;
		
			url = new URL(
					" http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");
		

		URLConnection connection = url.openConnection();

		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		while ((n = in.read(b)) != -1) {
			// int n = in.read(b);//amount of bytes returned up to 4096
			String s = new String(b, 0, n);

			info.append(s);
		}
		String json = info.toString();

		Gson gson = new Gson();

		WeatherNow now = gson.fromJson(json, WeatherNow.class);
		
					
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
