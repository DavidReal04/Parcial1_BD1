package co.edu.unbosque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MotorApuestas implements Runnable {

    private Socket socket;
    private ManejoArchivo manejoArchivo;

    public MotorApuestas(Socket socket) {
        this.socket = socket;
        manejoArchivo = new ManejoArchivo();
        manejoArchivo.leerMaeclientes("Maeclientes.csv");
    }

    @Override
    public void run() {

        safePrintln("Conectado al cliente: " + socket + "\n");

        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new PrintWriter(socket.getOutputStream(), true);

            boolean flag=false;
                while(flag==false) {
                    String id = in.readLine();
                    safePrintln("ID recibido: " + id);
                     int idC = Integer.parseInt(id);
                    if(manejoArchivo.encontrarCliente(idC)) {
                        
                        String encuentros = "Lista de encuentros\n"+"Id Encuentro  Equipo Local    Equipo Visitante    Fecha       Deporte\n"+manejoArchivo.leerEncuentros("Encuentros.csv");
                        out.println("Ingreso completado\n"+encuentros);
                        safePrintln("Ingres� el usuario: " + id);
                        boolean falgEncuentro=false;
                        do{
                            out.println("ingrese el Id del encuentro por el que desea apostar");
                            System.out.println("hasta aca");
                            String consecutivo = in.readLine();
                            System.out.println("consecutivo= "+consecutivo);
                            safePrintln("Consecutivo: " + consecutivo);
                            int idEn=Integer.parseInt(consecutivo) ;
                            if(manejoArchivo.encontrarEncuentro(idEn)){
                                
                                boolean flagValor=false;
                                do{
                                    out.println("ingrese el valor que desea apostar");
                                    var valor = in.readLine();
                                    safePrintln("Consecutivo: " + consecutivo);
                                    int valorapuesta=Integer.parseInt(valor);
                                    if(valorapuesta<manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo()){
                                    	falgEncuentro=true;
                                    	flag=true;
                                        flagValor=true;
                                        manejoArchivo.getApuestas().add(new Apuestas(manejoArchivo.getApuestas().size()+1,idEn,valorapuesta,idC));
                                        manejoArchivo.escribirApuestas("Apuestas.csv");
                                        out.println("Resumen de la transaccion");
                                        out.println(manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getNombre());
                                        out.println("Saldo inicial: "+manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo());
                                        out.println("Valor apostado: "+valor);
                                        out.println("Encuentro : \nDeporte :"+manejoArchivo.getEncuentros().get(manejoArchivo.getPosicionE()).getDeporte()+"\nEquipo local: "+manejoArchivo.getEncuentros().get(manejoArchivo.getPosicionE()).getEquipoLocal()+" vs "+manejoArchivo.getEncuentros().get(manejoArchivo.getPosicionE()).getEquipoVisitante());
                                        out.println("Nuevo Saldo : "+(manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo()-valorapuesta));
                                    }else{
                                        out.println("Lo sentimos su el valor de su apuesta es mayor que su saldo\nSus saldo es de "+manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo());
                                    }
                                }while(flagValor==false);
                            }else {
                                out.println("Por favor ingrese un id del encuentro valido");
                            }
                        }while(falgEncuentro==false);

                        
                    }else  {
                        out.println("Cliente no encontrado por favor intente nuevamente");
                    }
                }
                
                
            
        } catch (Exception e) {
            safePrintln("Error:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            safePrintln("Se cerr� el Socket: " + socket);
        }
    }

    public void safePrintln(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }
}