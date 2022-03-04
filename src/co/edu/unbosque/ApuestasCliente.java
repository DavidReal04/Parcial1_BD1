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
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Bienvenido\nSe ha conectado al servidor de apuestas\n");
            System.out.println("Ingrese su número de ID:");
            while (leer.hasNextLine()) {
                // Enviar al servidor
                out.println(leer.nextLine());
                //Recibir respuesta
                while (in.hasNextLine()){
                    System.out.println(in.nextLine());
                }
            }
        }
    }
}