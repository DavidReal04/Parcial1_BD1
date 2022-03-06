package co.edu.unbosque;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;


public class ApuestasServer {

    static int PORT = 59897;
    private static MotorApuestas motor;
    private static Scanner leer=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
       
        try (var listener = new ServerSocket(PORT)) {

            System.out.println("Servidor de apuestas escuchando en el puerto: "+ PORT);
            System.out.println("\n");

           

            while (true) {
            	Socket cliente = listener.accept();
            	var in = new Scanner(cliente.getInputStream());
                var out = new PrintWriter(cliente.getOutputStream(), true);
                motor=new MotorApuestas(cliente);
                motor.run();
               
            }
        }
    }
}
