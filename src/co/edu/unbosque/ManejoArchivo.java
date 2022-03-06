package co.edu.unbosque;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.EmptyStackException;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class ManejoArchivo {
	
	private  ArrayList<Encuentros> encuentros;
	private  ArrayList<Clientes> clientes;
	private  ArrayList<Apuestas> apuestas;
	private  CSVReader csvReader;
	private  FileReader csvFile;
	private int posicionC;
	private int posicionE;

	
	public ManejoArchivo() {
		encuentros = new ArrayList<>();
		clientes = new ArrayList<>();
		apuestas = new ArrayList<>();
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
			resul+=encuentros.get(i).getConsecutivo()+"          "+encuentros.get(i).getEquipoLocal()+"        "+encuentros.get(i).getEquipoVisitante()+"        "+encuentros.get(i).getFecha()+"     "+encuentros.get(i).getDeporte()+"\n";
		}
		return resul;
		
	}

	public void  leerMaeclientes(String archivo) {
		try {
			csvFile = new FileReader(archivo);
			CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
			String[] fila = null;
			csvReader.readNext();
			while ((fila = csvReader.readNext()) != null) {
				try {
					clientes.add(new Clientes(Integer.parseInt(fila[0]), fila[1], Integer.parseInt(fila[2])));
				} catch (EmptyStackException e) {
				} catch (NumberFormatException e) {
				}
			}
			csvFile.close();
			csvReader.close();

		} catch (IOException e) {
			
		} catch (CsvValidationException e) {

		}
		
	}

	public void  leerApuestas(String archivo) {

		try {
			csvFile = new FileReader(archivo);
			CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(csvFile).withCSVParser(conPuntoYComa).build();
			String[] fila = null;
			csvReader.readNext();
			while ((fila = csvReader.readNext()) != null) {
				try {
					
					apuestas.add(new Apuestas(Integer.parseInt(fila[0]), Integer.parseInt(fila[1]), Integer.parseInt(fila[2]),Integer.parseInt(fila[3])));

				} catch (EmptyStackException e) {
					
				} catch (NumberFormatException e) {
					
				}
			}
			csvFile.close();
			csvReader.close();

		} catch (IOException e) {
			
		} catch (CsvValidationException e) {

		}
		
	}
	
	public String escribirApuestas(String archivo) {
		File f = new File(archivo);
		try {
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw); 
			String datosString = "";
			for(int i = 0;i<apuestas.size();i++) {
				int consecutivo = apuestas.size();
				int idEncuentro = apuestas.get(i).getIdEncuentro();
				int valorApostado = apuestas.get(i).getValorApostado();
				int idUsuario = apuestas.get(i).getIdUsuario();
				datosString = consecutivo+";"+idEncuentro+";"+valorApostado+";"+idUsuario;
			
				pw.println(datosString); 
			}
			fw.close();
		} catch (IOException e) {
			return "Registro no exitoso";
		}
		return "Registro exitoso";
	}

	public boolean encontrarCliente(int id){
		boolean respuesta=false;
		for(int i=0;i<clientes.size();i++){
			if(id==clientes.get(i).getId()){
				respuesta=true;
				posicionC=i;
				i=clientes.size();
			}else{
				respuesta=false;
			}
		}
		return respuesta;
	}

	public boolean encontrarEncuentro(int id){
		boolean respuesta=false;
		for(int i =0;i<encuentros.size();i++){
			if(id==encuentros.get(i).getConsecutivo()){
				respuesta=true;
				posicionE=i;
				i=encuentros.size();
			}else{
				respuesta=false;
			}
		}
		return respuesta;
	}

	public ArrayList<Encuentros> getEncuentros() {
		return encuentros;
	}

	
	public int getPosicionC() {
		return posicionC;
	}

	public void setPosicionC(int posicionC) {
		this.posicionC = posicionC;
	}

	public int getPosicionE() {
		return posicionE;
	}

	public void setPosicionE(int posicionE) {
		this.posicionE = posicionE;
	}

	public ArrayList<Apuestas> getApuestas() {
		return apuestas;
	}

	public void setApuestas(ArrayList<Apuestas> apuestas) {
		this.apuestas = apuestas;
	}

	public void setEncuentros(ArrayList<Encuentros> encuentros) {
		this.encuentros = encuentros;
	}

	public ArrayList<Clientes> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Clientes> clientes) {
		this.clientes = clientes;
	}

	public CSVReader getCsvReader() {
		return csvReader;
	}

	public void setCsvReader(CSVReader csvReader) {
		this.csvReader = csvReader;
	}

	public FileReader getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(FileReader csvFile) {
		this.csvFile = csvFile;
	}
	
		

}
