package spira.weather;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;

public class WeatherFrame extends JFrame {

	public WeatherFrame() throws IOException {

		setSize(1000, 200);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new GridLayout(1, 0));
		container.setBackground(Color.PINK);

		StringBuilder info = new StringBuilder();
		URL url = new URL(
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

		WeatherNow now = gson.fromJson(json, WeatherNow.class);// take json and
																// create a
																// weathernow
																// class, and
																// populates
																// with data
																// from json,
		// but need to tell it what member var are

		// want to change string form json and give us back a class
	
		//WeatherDownloadThread thread = new WeatherDownloadThread();
		//thread.start();
		
		Weather array[] = now.getWeather();
		// Image icon =null;
		URL picturUrl = new URL("http://openweathermap.org/img/w/"
				+ array[0].getIcon() + ".png");
		Image icon = ImageIO.read(picturUrl);
		String temp = String.valueOf(now.getMain().getTemp());
		String main = String.valueOf(array[0].getMain());
		String description = String.valueOf(array[0].getDescription());
		String min = String.valueOf(now.getMain().getTemp_min());
		String max = String.valueOf(now.getMain().getTemp_max());

		container.add(new JLabel("Temperature: " + temp));
		container.add(new JLabel("Min: " + min));
		container.add(new JLabel("Max: " + max));
		container.add(new JLabel(new ImageIcon(icon)));
		container.add(new JLabel("Main: " + main));
		container.add(new JLabel("Description: " + description));

	}

	public static void main(String[] args) throws IOException {

		WeatherFrame weather = new WeatherFrame();
		weather.setVisible(true);

	}
}
