import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class panelC extends JPanel implements ActionListener {
	private JButton bAceptar,
					bAbrir,
					bGuardar;
	
	private JRadioButton brRotard,
						 brRotarIzq,
						 brEspejo,
						 brGrises;
	private ButtonGroup bg;
	
	private JLabel titulo;
	
	private JTextField ruta;
	
	private Image imagen;
	
	private PPMTransformer ppm;
	
	public panelC(){
		super();
		this.setPreferredSize(new Dimension(200,350));
		this.setBackground(Color.BLACK);
	//	this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.titulo=new JLabel("Editor de Imagen");
		this.titulo.setOpaque(true);
		
		this.ruta=new JTextField(17);
		
		this.bAbrir=new JButton("Abrir Imagen");
		this.bAbrir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ppm=new PPMTransformer(ruta.getText());
					JOptionPane.showMessageDialog(null, "Exito");
					brRotard.setEnabled(true);
					brRotarIzq.setEnabled(true);
					brEspejo.setEnabled(true);
					brGrises.setEnabled(true);
					bAceptar.setEnabled(true);
					bGuardar.setEnabled(true);
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error");
				}
				
				
			
		}});
		
		this.bAceptar= new JButton("Aplicar");
		this.bAceptar.addActionListener(this);
		
		this.brRotard=new JRadioButton("Rotar Derecha");
		this.brRotarIzq=new JRadioButton("Rotar Izquierda");
		this.brEspejo=new JRadioButton("Espejo");
		this.brGrises=new JRadioButton("Escala de grises");
		this.brRotard.setSelected(true);
		this.brRotard.setEnabled(false);
		this.brRotarIzq.setEnabled(false);
		this.brEspejo.setEnabled(false);
		this.brGrises.setEnabled(false);
		this.bAceptar.setEnabled(false);

		
		
		this.bGuardar=new JButton("Guardar");
		this.bGuardar.setEnabled(false);
		this.bGuardar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ppm.guardarImagen("auto.ppm");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		this.bg=new ButtonGroup();
		
		this.bg.add(this.brRotard);
		this.bg.add(this.brRotarIzq);
		this.bg.add(this.brEspejo);
		this.bg.add(this.brGrises);
		
		this.add(this.titulo);
		this.add(this.ruta);
		this.add(this.bAbrir);
		
		this.add(this.brRotard);
		this.add(this.brRotarIzq);
		this.add(this.brEspejo);
		this.add(this.brGrises);	
		this.add(this.bAceptar);
		this.add(this.bGuardar);
		
		this.imagen=new ImageIcon("espacio.jpg").getImage();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imagen, 0, 0, this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.bAceptar){
			if(this.brRotard.isSelected()){
				this.ppm=this.ppm.rotarDer();
			}else if(this.brRotarIzq.isSelected()){
				this.ppm=this.ppm.rotarIzq();
			}else if(this.brGrises.isSelected()){
				this.ppm=this.ppm.grises();
			}else{
				this.ppm=this.ppm.espejo();
			}
		}
		
	}
}
