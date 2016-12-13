import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Ventana extends JFrame{

	public Ventana(){
		super("Editor de Imagenes");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panelC pc=new panelC();
	
		this.add(pc);
		this.pack();
		
		
		
		this.setVisible(true);
	}
	
	
	public static void main(String[] args){
		Ventana v=new Ventana();
	}
}
