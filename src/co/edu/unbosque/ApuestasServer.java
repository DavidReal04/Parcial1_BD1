package co.edu.unbosque;

import java.net.ServerSocket;
import java.util.concurrent.Executors;


public class ApuestasServer {

    static int PORT = 59897;

    public static void main(String[] args) throws Exception {
        
        try (var listener = new ServerSocket(PORT)) {

            System.out.println("Servidor de apuestas escuchando en el puerto: "+ PORT);
            System.out.println("\n");

            var nThreads = Executors.newFixedThreadPool(1);

            while (true) {
                nThreads.execute(new MotorApuestas(listener.accept()));
            }
        }
    }
}
