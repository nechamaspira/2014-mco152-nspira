package spira.iss;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverheadGui extends JFrame {
	private JList<String> list;
	private JTextField input;

	public ISSOverheadGui() {
		setSize(700, 500);
		this.setLocationRelativeTo(null);
		setTitle("ISS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());

		list = new JList<String>();
		container.add(list, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, 2));
		container.add(panel, BorderLayout.NORTH);
		JButton button = new JButton("ISS");
		input = new JTextField();
		input.getText();
		panel.add(input);
		panel.add(button);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				IssOverheadRequestThread t = new IssOverheadRequestThread(list, input.getText());
				t.start();
				
			}
				
		});
			
		
	}
	public static void main(String[] args){
		ISSOverheadGui iss= new ISSOverheadGui();
		iss.setVisible(true);
	}
}
