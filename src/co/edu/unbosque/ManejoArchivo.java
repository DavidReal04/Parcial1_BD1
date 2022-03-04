package co.edu.unbosque;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class ManejoArchivo {
	
	private  ArrayList<Encuentros> encuentros;
	private  CSVReader csvReader;
	private  FileReader csvFile;

	
	public ManejoArchivo() {
		encuentros=new ArrayList<>();
		System.out.println(leerEncuentros("Libro1.txt"));
	}
	
	public  String leerEncuentros(String archivo) {
		String resul="";
		try {
			csvFile = new FileReader(archivo);
			CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
			String[] fila = null;
			csvReader.readNext();
			while ((fila = csvReader.readNext()) != null) {
				try {
					
					encuentros.add(new Encuentros(Integer.parseInt(fila[0]), fila[1], fila[2], fila[3],fila[4]));

				} catch (EmptyStackException e) {
					
				} catch (NumberFormatException e) {
					
				}
			}
			csvFile.close();
			csvReader.close();

		} catch (IOException e) {
			
		} catch (CsvValidationException e) {

		}
		
		for(int i=0;i<encuentros.size();i++) {
			resul+=encuentros.get(i).getConsecutivo()+"  "+encuentros.get(i).getEquipoLocal()+" "+encuentros.get(i).getEquipoVisitante()+" "+encuentros.get(i).getFecha()+" "+encuentros.get(i).getDeporte()+"\n";
		}
		return resul;
		
	}

}
