import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class mypanel extends JPanel {

	private Image imagen;
	
	public mypanel(){
		super();
		this.setPreferredSize(new Dimension(600,500));
		this.imagen=new ImageIcon("espacio.jpg").getImage();
		this.setBackground(Color.BLACK);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	//	g.drawImage(imagen, 0, 0, this);
		
		
	}
}
