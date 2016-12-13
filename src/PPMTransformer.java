import java.io.*;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
public class PPMTransformer {
	private Pixel[][] imagen;
	private String max;
	
	public PPMTransformer(String ruta) throws IOException{
		FileReader fr=new FileReader(ruta);
		BufferedReader br=new BufferedReader(fr);
		
		int a=0,
			b=0,
			c=0,
			n=0,
			R=-1,
			G=-1,
			B=-1,
			a1=0,
			b1=0;
		String linea;
		StringTokenizer st;

		while((linea=br.readLine()) !=null){
			c++;
			n++;
			if(c==2){
				try{
					n=Integer.parseInt(linea.substring(0,3));
					n=c+1;
				}catch(RuntimeException e){
					n=c;
				}
			}
			
			if(n<5){
				if(n==3){
					b=Integer.parseInt(linea.substring(0,3));
					a=Integer.parseInt(linea.substring(4,7));
					this.imagen=new Pixel[a][b];
				}
				else if(n==4){
					this.max=linea.substring(0,3);
				}
			}else{
				st=new StringTokenizer(linea);
				while (st.hasMoreTokens()){
					if(R==-1){
						R=Integer.parseInt(st.nextToken());
					}
					else if(G==-1){
						G=Integer.parseInt(st.nextToken());
					}
					else if(B==-1){
						B=Integer.parseInt(st.nextToken());
					}
					if(R!=-1 & G!=-1 & B!=-1){
						imagen[a1][b1++]=new Pixel(R,G,B);
						if(b1==b){
							b1=0;
							a1++;
						}
						R=G=B=-1;
					}
				}
			}
		}
		br.close();
		
	}
	
	public PPMTransformer(Pixel[][]imagen, String max){
		this.imagen=imagen;
		this.max=max;
	}
	
	public PPMTransformer rotarDer(){
		Pixel[][] Rarray=new Pixel[this.imagen[0].length][this.imagen.length];
	
		
		for(int i=0;i<Rarray.length;i++){
			for(int j=0;j<Rarray[i].length;j++){
				Rarray[i][j]=this.imagen[this.imagen.length-j-1][i];
			}
		}
		
		
		
		return new PPMTransformer(Rarray,this.max);
	}
	
	public PPMTransformer rotarIzq(){
		Pixel[][] Larray=new Pixel[this.imagen[0].length][this.imagen.length];
		
		for(int i=0;i<Larray.length;i++){
			for(int j=0;j<Larray[i].length;j++){
				Larray[i][j]=this.imagen[j][this.imagen[0].length-i-1];
			}
		}
		
		return new PPMTransformer(Larray,this.max);
	}
	
	public PPMTransformer espejo(){
		Pixel[][] Harray=new Pixel[this.imagen.length][this.imagen[0].length];
		
		for (int i=0;i<Harray.length;i++){
			for (int j=0;j<Harray[i].length;j++){
				Harray[i][j]=this.imagen[i][this.imagen[i].length-1-j];
			}
		}
		

		return new PPMTransformer(Harray,this.max);
	}
	
	public PPMTransformer grises(){
		Pixel[][] Garray=new Pixel[this.imagen.length][this.imagen[0].length];
		int R,
			G,
			B;
		for (int i=0;i<Garray.length;i++){
			for (int j=0;j<Garray[i].length;j++){
				R=this.imagen[i][j].getR();
				G=this.imagen[i][j].getG();
				B=this.imagen[i][j].getB();
				Garray[i][j]=new Pixel(R,G,B);
				Garray[i][j].escalaDeGris();
			}
		}
		
		return new PPMTransformer(Garray,this.max);
	}
	
	public void guardarImagen(String ruta)throws IOException{
		PrintWriter writer=new PrintWriter(new FileWriter(ruta));
		String pixeles="";
			
		writer.println("P3#");
		writer.println(this.imagen[0].length+" "+this.imagen.length);
		writer.println(this.max);
		
		for(int i=0;i<this.imagen.length;i++){
			for(int j=0;j<this.imagen[i].length;j++){
				pixeles+=this.imagen[i][j].getR()+" "+this.imagen[i][j].getG()+" "+this.imagen[i][j].getB()+" ";
			}
		}
		writer.println(pixeles);
		writer.close();
	}
		
	/*public static void main(String[] args){
		String  sel,
				ruta,
				destino;
		boolean x=false;
		int y=0;
		PPMTransformer img=null;
		
		try{
			do{
				do{
					sel=JOptionPane.showInputDialog(null,"Seleccione la operación a realizar:"
							+ "\n\n1.Convertir a escala de grises\n2.Rotar a la izquierda\n"
							+ "3.Rotar a la derecha\n4.Voltear Horizontalmente\n5.Guardar la imagen\n"
							+ "6.Abrir una nueva imagen\n7.Salir","Menú",JOptionPane.QUESTION_MESSAGE);
					
					if (!sel.equals("1") && !sel.equals("2") && !sel.equals("3") && !sel.equals("4") && !sel.equals("5") && !sel.equals("6") && !sel.equals("7")){
						JOptionPane.showMessageDialog(null,"        Opción incorrecta",
								"Error",JOptionPane.WARNING_MESSAGE);
					}
					
				}while(!sel.equals("1") && !sel.equals("2") && !sel.equals("3") && !sel.equals("4") && !sel.equals("5") && !sel.equals("6") && !sel.equals("7"));
				
				
				if(!sel.equals("7") && sel!=null){
					if(sel.equals("6")){
						do{
							ruta=JOptionPane.showInputDialog("Ingrese la ruta completa de la imagen:\n"
									+ "Ejemplo: C:\\Users\\manuel\\Desktop\\arch.ppm");
							
							if(ruta!=null){	
								try{
									img=new PPMTransformer(ruta);
									JOptionPane.showMessageDialog(null, "                  Hecho");
									x=true;
								}catch(IOException e){
									JOptionPane.showMessageDialog(null,"La ruta especificada no existe",
											"Error",JOptionPane.ERROR_MESSAGE);
								}catch(RuntimeException e){
									JOptionPane.showMessageDialog(null,"Asegurese de seleccionar un archivo con extensión .ppm",
											"Archivo inválido",JOptionPane.ERROR_MESSAGE);
								}
							}
						}while(x!=true && ruta!=null);
						x=false;
					}
					else if(img==null){
						JOptionPane.showMessageDialog(null,"Seleccione primero la imagen a modificar",
								"Advertencia",JOptionPane.WARNING_MESSAGE);
					}
					else if(sel.equals("1")){
						img=img.grises();
					}
					else if(sel.equals("2")){
						img=img.rotarIzq();
					}
					else if(sel.equals("3")){
						img=img.rotarDer();
					}
					else if(sel.equals("4")){
						img=img.espejo();
					}
					else if(sel.equals("5")){
						do{	
							destino=JOptionPane.showInputDialog("Ingrese la ruta para guardar el archivo\n"
									+ "Ejemplo: C:\\Users\\manuel\\Desktop\\arch.ppm");
							
							if(destino!=null){
								if(destino.length()>3){
									if(!((destino.toLowerCase()).substring(destino.length()-4,destino.length())).equals(".ppm")){
										y=JOptionPane.showOptionDialog(null,"Este no es un archivo con extensión .ppm\n"
												+ "¿Continuar de todos modos?", "Advertencia", JOptionPane.YES_NO_OPTION, 
												JOptionPane.WARNING_MESSAGE, null, null, null);
									}	
								}else{
									y=JOptionPane.showOptionDialog(null,"Este no es un archivo con extensión .ppm\n"
											+ "¿Continuar de todos modos?", "Advertencia", JOptionPane.YES_NO_OPTION, 
											JOptionPane.WARNING_MESSAGE, null, null, null);
								}
								if(y==0 || y==JOptionPane.YES_OPTION){
									try{
										img.guardarImagen(destino);
										JOptionPane.showMessageDialog(null, "Imagen guardada con éxito");
										x=true;
									}catch(IOException e){
										JOptionPane.showMessageDialog(null,"No se puede acceder a la ruta "
												+ "especificada\nFavor de verificarla",
												"Error",JOptionPane.ERROR_MESSAGE);
									}
								}
								y=0;
							}
						}while(x!=true && destino!=null);
						x=false;
					}
					
					if(img!=null && !sel.equals("5") && !sel.equals("6")){
						JOptionPane.showMessageDialog(null, "                  Hecho");
					}
				}
				
			}while(!sel.equals("7"));
		}catch(NullPointerException e){
			
		}
	}*/

}
