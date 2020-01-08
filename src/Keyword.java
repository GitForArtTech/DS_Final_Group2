
public class Keyword {
	public String Name;
	public double Weight;
	
	public Keyword(String n , double w) {
		this.Name = n;
		this.Weight = w;
	}
	
	public String toString() {
		return "["+Name+","+Weight+"]";
	}
}
