package co.edu.unbosque;

import java.io.*;
import java.net.Socket;

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
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            boolean flag=false;
            while(flag==false) {
                out.writeUTF("Ingrese su numero de ID:");
                String id = in.readUTF();
                safePrintln("ID recibido: " + id);
                int idC = Integer.parseInt(id);
                if(manejoArchivo.encontrarCliente(idC)) {
                    safePrintln("Ingreso del usuario: " + id);
                    String encuentros = "Lista de encuentros\n"+"Id     Encuentro       Equipo Local    Equipo Visitante        Fecha           Deporte\n"+manejoArchivo.leerEncuentros("Encuentros.csv");
                    encuentros = "Ingreso completado\n"+encuentros;
                    encuentros =encuentros+"\nIngrese el Id del encuentro por el que desea apostar";
                    boolean falgEncuentro=false;
                    do{
                        out.writeUTF(encuentros);
                        String consecutivo = in.readUTF();
                        safePrintln("Consecutivo: " + consecutivo);
                        int idEn=Integer.parseInt(consecutivo) ;
                        if(manejoArchivo.encontrarEncuentro(idEn)){
                            boolean flagValor=false;
                            do{
                                out.writeUTF("Ingrese el valor que desea apostar");
                                var valor = in.readUTF();
                                safePrintln("Valor apuesta: " + valor);
                                int valorapuesta=Integer.parseInt(valor);
                                if(valorapuesta<manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo()){
                                    falgEncuentro=true;
                                    flag=true;
                                    flagValor=true;
                                    manejoArchivo.getApuestas().add(new Apuestas(manejoArchivo.getApuestas().size()+1,idEn,valorapuesta,idC));
                                    manejoArchivo.escribirApuestas("Apuestas.csv");
                                    String resumen = "Resumen de la transaccion\n"+manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getNombre();
                                    resumen=resumen+("\nSaldo inicial: "+manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo());
                                    resumen=resumen+("\nValor apostado: "+valor);
                                    resumen=resumen+("\nEncuentro : \nDeporte :"+manejoArchivo.getEncuentros().get(manejoArchivo.getPosicionE()).getDeporte()+"\nEquipo local: "+manejoArchivo.getEncuentros().get(manejoArchivo.getPosicionE()).getEquipoLocal()+" vs "+manejoArchivo.getEncuentros().get(manejoArchivo.getPosicionE()).getEquipoVisitante());
                                    resumen=resumen+("\nNuevo Saldo : "+(manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo()-valorapuesta));
                                    out.writeUTF("Fin");
                                    out.writeUTF(resumen);
                                    out.writeUTF("Gracias por su apuesta. Hasta Pronto");
                                }else{
                                    out.writeUTF("Error");
                                    out.writeUTF("Lo sentimos su el valor de su apuesta es mayor que su saldo\nSus saldo es de "+manejoArchivo.getClientes().get(manejoArchivo.getPosicionC()).getSaldo());
                                }
                            }while(flagValor==false);
                        }else {
                            out.writeUTF("Error");
                            out.writeUTF("Por favor ingrese un id del encuentro valido");
                        }
                    }while(falgEncuentro==false);

                }else  {
                    out.writeUTF("Error");
                    out.writeUTF("Cliente no encontrado por favor intente nuevamente");
                }
            }
        } catch (Exception e) {
            safePrintln("Error:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            safePrintln("Se cerro el Socket: " + socket);
        }
    }

    public void safePrintln(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }
}