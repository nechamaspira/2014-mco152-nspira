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
import com.google.gson.Gson;

public class NytimesFrame extends JFrame  {


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
		URL url = new URL("http://api.nytimes.com/svc/search/v2/articlesearch.json?end_date="+dateFormat.format(date)+"&sort=newest&page=0&api-key=a75256ffbb7fa3aa08f0350e280ec79f%3A17%3A70508260");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		
		StringBuilder info = new StringBuilder();

		byte b[] = new byte[4096];
		int n = -1;
		while ((n = in.read(b)) != -1) {
			String s = new String(b, 0, n);
			info.append(s);
		}
		String json = info.toString();
		Gson gson = new Gson();


        Nytimes now = gson.fromJson(json, Nytimes.class);
        Docs[] docs = now.getResponse().getDocs();

       
       
        DefaultListModel<String>  model = new DefaultListModel<String>();
      //  JButton button = new JButton();

        for (int i = 0; i < 10; i++) {
            String headline = docs[i].getHeadline().getMain();
            String leadPar = docs[i].getLead_paragraph();
                    
            model.addElement("Headline " + (i + 1) + ": " + headline);
            model.addElement("Lead Paragraph " + (i + 1) + ": " + leadPar);          
        }
        JList<String>  list = new JList<String>(model);
        list.setSelectedIndex(0);
        // JScrollPane listScrollPane = new JScrollPane(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(Color.GREEN);

        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button10 = new JButton("10");

       button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                java.net.URI theURL = docs[0].getWeb_url();
                try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }

        });
       
       button2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[1].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[2].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[3].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button5.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[4].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button6.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[5].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button7.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[6].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button8.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[7].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button9.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[8].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
       button10.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               java.net.URI theURL = docs[9].getWeb_url();
               try {
					java.awt.Desktop.getDesktop().browse(theURL);
				} catch (IOException e) {
					e.printStackTrace();
				}
           }

       });
       
     
        cont.add(button1);
        cont.add(button2);
        cont.add(button3);
        cont.add(button4);
        cont.add(button5);
        cont.add(button6);
        cont.add(button7);
        cont.add(button8);
        cont.add(button9);
        cont.add(button10);

        container.add(new JScrollPane(list), BorderLayout.CENTER);
        container.add(cont, BorderLayout.WEST);

    }

   
	public static void main(String[] args) throws IOException {

		NytimesFrame nytimes = new NytimesFrame();
		nytimes.setVisible(true);

	}
	
	
	
}
