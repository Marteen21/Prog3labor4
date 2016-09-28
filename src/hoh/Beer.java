package hoh;

import java.io.Serializable;
import java.util.Comparator;

public class Beer implements Serializable {
	private String name;
	private String style;
	private double strength;
	
	public Beer(String nm, String stl, double str){
		this.name=nm;
		this.style=stl;
		this.strength = str;
	}
	public String Name(){
		return this.name;
	}
	public void Name(String nm){
		this.name=nm;
	}
	public String Style(){
		return this.style;
	}
	public void Style(String stl){
		this.style=stl;
	}
	public double Strength(){
		return this.strength;
	}
	public void Name(double str){
		this.strength=str;
	}
}
class NameComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer a, Beer b) {
        return a.Name().compareToIgnoreCase(b.Name());
    }
}

class StyleComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer a, Beer b) {
    	return a.Style().compareToIgnoreCase(b.Style());
    }
}
class StrengthComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer a, Beer b) {
        return a.Strength() < b.Strength() ? -1 : a.Strength() == b.Strength() ? 0 : 1;
    }
}