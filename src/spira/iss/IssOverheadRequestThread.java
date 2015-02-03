package spira.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JList;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class IssOverheadRequestThread extends Thread{

	private JList<String> list;
	private String address;

	public IssOverheadRequestThread(JList<String> list, String address) {

		this.list = list;
		this.address = address;
	
	}
	public void run(){
		URL url;
		try {
			address = URLEncoder.encode(address,"UTF-8");
			url = new URL(
					"https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key=AIzaSyDqMwyVw6mmp_4UQshDB4ae_Qrm04WaP44");
		
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();

		Gson gson = new Gson();

		String json = IOUtils.toString(in); // reads it in to a string

		First now = gson.fromJson(json, First.class);
		Results[] result = now.getResults();

		double lng = result[0].getGeometry().getLocation().getLng();
		double lat = result[0].getGeometry().getLocation().getLat();

		URL url2 = new URL("http://api.open-notify.org/iss-pass.json?lat="
				+ lat + "&lon=" + lng);
		URLConnection connection2 = url2.openConnection();
		InputStream in2 = connection2.getInputStream();

		String json2 = IOUtils.toString(in2); // reads it in to a string

		GetInfo get = gson.fromJson(json2, GetInfo.class);
		Response[] info = get.getResponse();
		
		String [] array = new String[info.length];
		//StringBuilder builder = new StringBuilder();
		for (int i = 0; i < info.length; i++) {
			long unixSeconds = info[i].getRisetime();
			Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
			String formattedDate = sdf.format(date);
			//builder.append(formattedDate);
			array[i] =formattedDate;
		
		}
		
		list.setListData(array);
	
		} catch ( IOException e) {
			
			e.printStackTrace();
		}
	}
		
	
	

}
