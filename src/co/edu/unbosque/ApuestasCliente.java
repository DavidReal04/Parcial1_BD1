package co.edu.unbosque;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ApuestasCliente {

    static String IP = "127.0.0.1";
    static int PORT = 59897;
    private static Scanner leer=new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        
        try (var socket = new Socket(IP, PORT)) {
            System.out.println("Se ha conectado al servidor de apuestas " + socket+"\n");

            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Ingrese el mensaje de prueba:");
            
            while (leer.hasNextLine()) {
                // Enviar al servidor
                out.println(leer.nextLine());
                System.out.println("Mensaje retornado: " + in.nextLine()+"\n");
                System.out.println("Ingrese el mensaje de prueba:");
            }
        }
    }
}