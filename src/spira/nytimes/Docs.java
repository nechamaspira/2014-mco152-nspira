package spira.nytimes;

import java.net.URL;

import com.sun.org.apache.xerces.internal.util.URI;

public class Docs {
	private java.net.URI web_url;
	private String snippet;
	private String lead_paragraph;
	private String source;
	private HeadLine headline;
	private String pub_date;
	private String news_desk;
	
	public java.net.URI getWeb_url() {
		return web_url;
	}
	public String getSnippet() {
		return snippet;
	}
	public String getLead_paragraph() {
		return lead_paragraph;
	}
	public String getSource() {
		return source;
	}
	public HeadLine getHeadline() {
		return headline;
	}
	public String getPub_date() {
		return pub_date;
	}
	public String getNews_desk() {
		return news_desk;
	}

}
