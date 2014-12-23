package spira.earthquake;

import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import com.google.gson.Gson;

public class EarthquakeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EarthquakeFrame() throws IOException {

		setSize(500,500);
		setTitle("Current Earthquakes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		//container.setLayout(new FlowLayout());
		//container.setBackground(Color.PINK);

		StringBuilder info = new StringBuilder();

		URL url = new URL(
				" http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");

		URLConnection connection = url.openConnection();

		InputStream in = connection.getInputStream();

		byte b[] = new byte[4096];
		int n = -1;
		while ((n = in.read(b)) != -1) {
			String s = new String(b, 0, n);

			info.append(s);
		}
		String json = info.toString();

		Gson gson = new Gson();

		Earthquake now = gson.fromJson(json, Earthquake.class);
		Features[] pro = now.getFeatures();
		DefaultListModel model = new DefaultListModel();

		for (int i = 0; i < pro.length; i++) {
			String place = String.valueOf(pro[i].getBla().getPlace());
			double mag = pro[i].getBla().getMag();
			//System.out.println(place);
			//System.out.println(mag);
			model.addElement(place);
			model.addElement(mag);
		}

		JList list = new JList(model);

		  // list.setSelectedIndex(0);
		// JScrollPane listScrollPane = new JScrollPane(list);
		//  list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  list.setBackground(Color.GREEN);
		  
		// container.add(new JScrollPane(list));
		  container.add(list);
		 
	}

	public static void main(String[] args) throws IOException {

		EarthquakeFrame earthquake = new EarthquakeFrame();
		earthquake.setVisible(true);

	}

}
