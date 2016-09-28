package hoh;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Console {
	protected static ArrayList<Beer> beers = new ArrayList<Beer>();

	protected static void listBeer(String[] cmd) {
		if (cmd.length > 1) {
			switch (cmd[1]) {
			case "Name":
				Collections.sort(beers, new NameComparator());
				break;
			case "Style":
				Collections.sort(beers, new StyleComparator());
				break;
			case "Strength":
				Collections.sort(beers, new StrengthComparator());
				break;
			}

		}
		for (Beer beer : beers) {
			System.out.println(beer.Name() + " " + beer.Style() + " with " + beer.Strength() + " alcohol");
		}
	}

	protected static void addBeer(String[] cmd) throws IOException {
		if (cmd.length < 4) {
			throw new IOException("Not enough data");
		} else {
			beers.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
		}
	}

	protected static void saveBeers(String[] cmd) throws Exception {
		if (cmd.length > 1) {
			FileOutputStream f = new FileOutputStream(cmd[1]);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(beers);
			out.close();
			f.close();
		} else {
			FileOutputStream f = new FileOutputStream("defaultbeersave");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(beers);
			out.close();
			f.close();
		}
	}

	protected static ArrayList<Beer> loadBeers(String[] cmd) throws Exception {
		ArrayList<Beer> myBeers = new ArrayList<Beer>();
		if (cmd.length > 1) {
			FileInputStream fis = new FileInputStream(cmd[1]);
			ObjectInputStream ois = new ObjectInputStream(fis);
			myBeers = (ArrayList<Beer>) ois.readObject();
			ois.close();
			fis.close();
		} else {
			FileInputStream fis = new FileInputStream("defaultbeersave");
			ObjectInputStream ois = new ObjectInputStream(fis);
			myBeers = (ArrayList<Beer>) ois.readObject();
			ois.close();
			fis.close();
		}
		return myBeers;
	}
	protected static void deleteBeer(String[] cmd) throws IOException{
		if(cmd.length<2){
			throw new IOException("No beer specified");
		}
		else{
			//Collections.sort(beers, new NameComparator());
			int index = Collections.binarySearch(beers, new Beer(cmd[1]," ",0) , new NameComparator());
			if(index >=0){
				beers.remove(index);
			}
			else{
				throw new IOException("No beer with name " + cmd[1]);
			}
		}
		
	}
	public static void main(String[] args) {
		boolean running = true;
		System.out.println("Type command...");
		while (running) {
			Scanner scan = new Scanner(System.in);
			String s = scan.nextLine();
			String[] cmd = s.split(" ");
			if (cmd[0].equals("add")) {
				try {
					addBeer(cmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (cmd[0].equals("list")) {
				listBeer(cmd);
			} else if (cmd[0].equals("save")) {
				try {
					saveBeers(cmd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (cmd[0].equals("load")) {
				try {
					beers = loadBeers(cmd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (cmd[0].equals("delete")){
				try {
					deleteBeer(cmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
