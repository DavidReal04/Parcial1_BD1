package co.edu.unbosque;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Modelo implements Runnable {

    private Socket socket;

    public Modelo(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        safePrintln("Conectado al cliente: " + socket + "\n");

        try {
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            while (in.hasNextLine()) {
                var id = in.nextLine();
                safePrintln("ID recibido: " + id);
                
                //Ejemplo de operación
                if(id.equals("001")) {
                	out.println("Ingreso completado");
                	safePrintln("Ingresó el usuario: " + id);
                }
                
            }
        } catch (Exception e) {
            safePrintln("Error:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {}
            safePrintln("Se cerró el Socket: " + socket);
        }
    }   

    public void safePrintln(String s) {
        synchronized (System.out) {
            System.out.println(s);
        }
    }
}