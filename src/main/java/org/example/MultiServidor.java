package org.example;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class MultiServidor extends Thread {
    public static void main(String[] args) throws IOException {
        int serverPort = 12345;

        // Creamos un socket de servidor y lo enlazamos al puerto especificado
        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("Servidor esperando conexiones en el puerto " + serverPort);

        while (true) {
            // Esperamos a que un cliente se conecte
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

            // Obtenemos los flujos de entrada y salida del socket
            InputStream in = clientSocket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            OutputStream out = clientSocket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            // Recibimos la solicitud del cliente
            String request = dis.readUTF();
            System.out.println("Solicitud del cliente: " + request);

            // Procesamos la solicitud del cliente y enviamos una respuesta
            String response = JOptionPane.showInputDialog("Servidor");
            dos.writeUTF(response);

            // Cerramos los flujos de entrada y salida y el socket del cliente
            dis.close();
            dos.close();
            clientSocket.close();

            if (request.equals("fin")){
                System.out.println("El servidor se finalisa");
                break;
            }
        }

    }
}