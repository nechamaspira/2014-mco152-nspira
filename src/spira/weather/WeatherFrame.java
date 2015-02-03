package spira.weather;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherFrame extends JFrame {

	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel imageLabel;

	public WeatherFrame() throws IOException {

		setSize(1000, 200);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new GridLayout(1, 0));
		container.setBackground(Color.PINK);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		this.label2 = new JLabel();
		this.label3 = new JLabel();
		this.label4 = new JLabel();
		this.label5 = new JLabel();
		this.label6 = new JLabel();

		imageLabel = new JLabel();
		// container.add(imageLabel);
		// StringBuilder info = new StringBuilder();
		// URL url = new URL(
		// " http://api.openweathermap.org/data/2.5/weather?q=brooklyn&units=imperial");

		// URLConnection connection = url.openConnection();

		// InputStream in = connection.getInputStream();
		// String json = IOUtils.toString(in);

		// Gson gson = new Gson();

		// WeatherNow now = gson.fromJson(json, WeatherNow.class);// take json
		// and
		// create a
		// weathernow
		// class, and
		// populates
		// with data
		// from json,
		// but need to tell it what member var are

		// want to change string form json and give us back a class

		label = new JLabel("downloading weather...");
		WeatherDownloadThread thread = new WeatherDownloadThread(this);
		thread.start();

		// Weather array[] = now.getWeather();
		// Image icon =null;
		// URL picturUrl = new URL("http://openweathermap.org/img/w/"
		// + array[0].getIcon() + ".png");
		// Image icon = ImageIO.read(picturUrl);
		// String temp = String.valueOf(now.getMain().getTemp());
		// String main = String.valueOf(array[0].getMain());
		// String description = String.valueOf(array[0].getDescription());
		// String min = String.valueOf(now.getMain().getTemp_min());
		// String max = String.valueOf(now.getMain().getTemp_max());

		container.add(this.label);// temp
		container.add(label4);// min
		container.add(label5);// max
		container.add(imageLabel);
		container.add(label2);// main
		container.add(label3);// description

	}

	public void displayWeather(WeatherNow now) {
		try {

			Weather[] array = now.getWeather();

			StringBuilder info = new StringBuilder();
			label.setText(String.valueOf(now.getMain().getTemp()));
			label2.setText(String.valueOf(array[0].getMain()));
			for(int i=0;i<array.length;i++){
				info.append(String.valueOf(array[i].getDescription()));

			}
			label3.setText(info.toString());
			label4.setText(String.valueOf(now.getMain().getTemp_min()));
			label5.setText(String.valueOf(now.getMain().getTemp_max()));
			String urlstring = "http://openweathermap.org/img/w/"
					+ array[0].getIcon() + ".png";
			URL picturUrl = new URL(urlstring);

			DownloadImageThread thread = new DownloadImageThread(picturUrl,
					imageLabel);// downloads url and displays on label
			thread.start();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		WeatherFrame weather = new WeatherFrame();
		weather.setVisible(true);

	}
}
