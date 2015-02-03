package spira.nytimes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class NytimesFrame extends JFrame {

	public NytimesFrame() throws IOException {
		setSize(800, 425);
		setTitle("NY TIMES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		Container cont = new Container();
		cont.setLayout(new GridLayout(0, 1));

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		// dateFormat.format(date);
		URL url = new URL(
				"http://api.nytimes.com/svc/search/v2/articlesearch.json?end_date="
						+ dateFormat.format(date)
						+ "&sort=newest&page=0&api-key=a75256ffbb7fa3aa08f0350e280ec79f%3A17%3A70508260");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		Gson gson = new Gson();

		String json = IOUtils.toString(in); // reads it in to a string
		Nytimes now = gson.fromJson(json, Nytimes.class);
		Docs[] docs = now.getResponse().getDocs();

		DefaultListModel<String> model = new DefaultListModel<String>();
		// JButton button = new JButton();

		for (int i = 0; i < 10; i++) {
			String headline = docs[i].getHeadline().getMain();
			String leadPar = docs[i].getLead_paragraph();

			model.addElement("Headline " + (i + 1) + ": " + headline);
			model.addElement("Lead Paragraph " + (i + 1) + ": " + leadPar);
		}
		JList<String> list = new JList<String>(model);
		// list.setSelectedIndex(3);
		// JScrollPane listScrollPane = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(Color.GREEN);

		for (int i = 0; i < 10; i++) {
			JButton button = new JButton(String.valueOf(i + 1));
			final java.net.URI theURL = docs[i].getWeb_url();
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						java.awt.Desktop.getDesktop().browse(theURL);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			});
			cont.add(button);
		}

		container.add(new JScrollPane(list), BorderLayout.CENTER);
		container.add(cont, BorderLayout.WEST);

	}

	public static void main(String[] args) throws IOException {

		NytimesFrame nytimes = new NytimesFrame();
		nytimes.setVisible(true);

	}

}
