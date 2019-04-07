import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JFileChooser;

public class Csv {
	/**
	 * read data from file
	 * @param file
	 * @return
	 */
	public ArrayList<String[]> readData(String file) {
		ArrayList<String[]> arrayList=new ArrayList<>();
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader("Data/"+file));
			String line;
			while ((line=bufferedReader.readLine())!=null) {
				arrayList.add(line.split(","));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	/**
	 * export manifest
	 * @param manifest
	 */
	public void export(Manifest manifest) {
		JFileChooser jFileChooser=new JFileChooser("Data");
		jFileChooser.showSaveDialog(null);
		System.out.println(jFileChooser.getSelectedFile().getName());
		try {
			PrintWriter printWriter=new PrintWriter(new FileWriter(jFileChooser.getSelectedFile().getAbsolutePath()+".csv"));
			ArrayList<Truck> trucks=manifest.getTrucks();
			for (Truck truck : trucks) {
				if (truck instanceof RefrigeratedTruck) {
					printWriter.println(">Refrigerated");
				}else {
					printWriter.println(">Ordinary");
				}
				ArrayList<Entry<Item, Integer>> cargo=truck.cargo;
				for (Entry<Item, Integer> entry : cargo) {
					printWriter.println(String.format("%s,%d", entry.getKey().getName(),entry.getValue()));
				}
			}
			printWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
