package spira.weather;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DownloadImageThread extends Thread {

	private URL url;
	private JLabel imageLabel;
	
	
	public DownloadImageThread(URL url, JLabel imageLabel) {
		super();
		this.url = url;
		this.imageLabel = imageLabel;
	}
	
	public void run(){
		
		ImageIcon image = new ImageIcon(url);
		imageLabel.setIcon(image);
	}

}
