package co.edu.unbosque;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ApuestasServer {

    static int PORT = 59897;
    private static MotorApuestas motor;

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(PORT)) {
            System.out.println("Servidor de apuestas escuchando en el puerto: "+ PORT);
            System.out.println("\n");
            while (true) {
            	Socket cliente = listener.accept();
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                out.writeUTF("Bienvenido\nSe ha conectado al servidor de apuestas\n");
                motor = new MotorApuestas(cliente);
                motor.run();
            }
        }
    }
}
