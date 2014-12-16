package spira.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WeatherFrame extends JFrame{
	
	public WeatherFrame(){//best to have all setup for frame here and not in main
		//set size
		setSize(800,600);
		//set title
		setTitle("Current Weather");
		//if application is just one window so need to set default close operation to exit when u close the window
		//or if its last window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//put our window in the center
		setLocationRelativeTo(null);
		//takes care of the center
		Container container = getContentPane();
		//put components in the window
		//set layout manager of window to horizontal- flowLayout
		//setLayout(new FlowLayout());
		//can do box layout which is vertical and left top corner, layout everything acording to yaxis
		//BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		//gonna use borderlayout
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout );
		
		//JLabel simply displays information, adds components. 
		container.add(new JLabel("This class Rocks!"),BorderLayout.SOUTH);
		container.add(new JLabel("Hello Tuesday"),BorderLayout.EAST);
		container.add(new JLabel("Happy Birthday"),BorderLayout.WEST);
		JLabel label = new JLabel("Thanks for your silence");
		label.setBackground(Color.green);
		//in order to show the backround color
		label.setOpaque(true);
		container.add(label,BorderLayout.CENTER);
		//how to put 2 things in one location ex north, 2 things side by side. must put containers nside containers
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer.add(new JLabel ("Hello World"));
		northContainer.add(new JLabel ("The World says hello"));
		// now adding labels to container and container  to north instead of adding labels to north
		container.add(northContainer,BorderLayout.NORTH);


	}
	
	public static void main(String [] args){
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
	
}
