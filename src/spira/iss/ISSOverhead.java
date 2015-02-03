package spira.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;

import spira.nytimes.Nytimes;

import com.google.gson.Gson;

public class ISSOverhead {

	// public ISSOverhead() throws IOException{

	public static void main(String[] arg) throws IOException {

		
		URL url = new URL(
				"https://maps.googleapis.com/maps/api/geocode/json?address=1602+Avenue+J+Brooklyn+NY&key=AIzaSyDqMwyVw6mmp_4UQshDB4ae_Qrm04WaP44");
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

		//Gson gson2 = new Gson();

		String json2 = IOUtils.toString(in2); // reads it in to a string

		GetInfo get = gson.fromJson(json2, GetInfo.class);
		Response[] info = get.getResponse();
		
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < info.length; i++) {
			long unixSeconds = info[i].getRisetime();
			Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
			String formattedDate = sdf.format(date);
			builder.append(formattedDate);
			builder.append(" ");
		
			  
			
			builder.append("\n");

		}
		System.out.println(builder.toString());
	}
}
