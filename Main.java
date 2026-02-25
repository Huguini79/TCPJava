// Creando un servidor TCP en Java, para aceptar clientes, creado por Huguini79
// Tranquilito y sin estar obsesionado

import java.io.*; // Para leer Buffers
import java.net.*; // Para las funciones de red

class Servidor {
    public static ServerSocket socket; // Instancia del servidor
    public static Integer offset = 0;

    public static void AceptarCliente() throws IOException {
        Cliente.socket = socket.accept();
        offset++;
        Cliente.id = offset;
        Cliente.DireccionIpCliente = Cliente.socket.getInetAddress().getHostAddress(); // Conseguir la dirección IP del cliente conectado al servidor
        Cliente.NombreDeHost = Cliente.socket.getInetAddress().getHostName(); // Conseguir el nombre de host del cliente conectado al servidor
 
        System.out.println("USUARIO CONECTADO: \n{ IP: "+ Cliente.DireccionIpCliente+" }\n{ HOST: "+ Cliente.NombreDeHost+ " }\n{ ID: "+ Cliente.id+ " }");
    }

}

class Cliente {
    public static Integer id;
    public static Socket socket; // Instancia del cliente
    public static String DireccionIpCliente;
    public static String NombreDeHost;
}

public class Main {
    // Instancias
    // public static ServerSocket servidor; // Instancia del servidor
    // public static Socket cliente; // Instancia del cliente

    private static Servidor Servidor;
    private static Cliente Cliente;

    public static void main(String[] args) throws IOException {
        Servidor.socket = new ServerSocket(8080); // Creamos la instancia del servidor (escuchamos por el puerto 8080)

        System.out.println("SERVIDOR INICIALIZADO EN EL PUERTO 8080");

        // Bucle infinito que se encarga de aceptar las peticiones de los clientes
        while (true) {
            // Estar a la escucha de nuevos clientes
            Servidor.AceptarCliente();

            Servidor.socket.close();
            Cliente.socket.close();

        }
    }
}