package spira.triangle;

public class Triangle {

	private int height;
	private int bottomLength;
	
	
	

	public Triangle(int height){
		this.height = height;
		this.bottomLength = (this.height*2)-1;
		

	
	}

	public String toString(){
		StringBuilder info = new StringBuilder();
		int spaces;
		int stars;
		
		for(int i=0; i<bottomLength-1; i+=2){
			spaces= (bottomLength-(i+1))/2;
			stars= i+1; 

			for(int k=0;k<spaces;k++){
				info.append(" ");
			}

			info.append("*");

			for(int l=0;l<(stars-2);l++){
				info.append(" ");
			}

			if(i!=0){
				info.append("*");
			}
			
				info.append("\n");
			
		}
		for(int n = 0; n<bottomLength; n++){
			info.append("*");
		}
		return info.toString();

	}
	public static void main(String[]args){
		Triangle triangle = new Triangle(5);
		String wow = triangle.toString();
		System.out.println(wow);
	}
}
