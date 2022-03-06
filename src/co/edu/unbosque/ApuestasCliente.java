package co.edu.unbosque;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ApuestasCliente {

    static String IP = "127.0.0.1";
    static int PORT = 59897;
    private static Scanner leer=new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        
        try {
            Socket socket = new Socket(IP, PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println(in.readUTF());
            boolean flag=true;
            while (flag){
                String msg = in.readUTF();
                if(msg.equals("Error")){
                    System.out.println(in.readUTF());
                    System.out.println(in.readUTF());
                    out.writeUTF(leer.nextLine());
                }else if (msg.equals("Fin")){
                    System.out.println(in.readUTF());
                    System.out.println(in.readUTF());
                    flag = false;
                }else{
                    System.out.println(msg);
                    out.writeUTF(leer.nextLine());
                }
            }

        }catch (IOException e){
        	e.printStackTrace();
        }
    }
}