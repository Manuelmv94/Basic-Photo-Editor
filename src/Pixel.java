
public class Pixel {
	private int R,
			    G,
			    B;
	
	public Pixel(int R,int G,int B){
		this.R=R;
		this.G=G;
		this.B=B;
			

	}
	
	public int getR(){
		return this.R;
	}
	
	public int getG(){
		return this.G;
	}
	
	public int getB(){
		return this.B;
	}
	
	public void setR(int R){
		this.R=R;
	}
	
	public void setG(int G){
		this.G=G;
	}
	
	public void setB(int B){
		this.B=B;
	}
	public void escalaDeGris(){
		int P=(this.R+this.G+this.B)/3;
		this.setR(P);
		this.setG(P);
		this.setB(P);
	}
	
	/*public String toString(){
		return ("R es "+this.R+" G: "+this.G+" B: "+this.B);
	}
	
	public static void main(String[] args){
		Pixel p=new Pixel(255,100,300);
		p.escalaDeGris();
		System.out.println(p);
	}*/
}

