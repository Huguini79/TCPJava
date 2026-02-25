// Creando un servidor TCP en Java, para aceptar clientes, creado por Huguini79
// Tranquilito y sin estar obsesionado

import java.io.*; // Para leer Buffers
import java.net.*; // Para las funciones de red

class Servidor {
    public static ServerSocket socket; // Instancia del servidor
    public static Integer offset = 0;
    public static Integer UsuariosMaximos = 1024; // Número de usuarios máximos
    public static String[] IpSalvadas = new String[UsuariosMaximos];

    public static void AceptarCliente() throws IOException {
        Cliente.socket = socket.accept();
        offset++;
        Cliente.id = offset;
        Cliente.DireccionIpCliente = Cliente.socket.getInetAddress().getHostAddress(); // Conseguir la dirección IP del cliente conectado al servidor
        Cliente.NombreDeHost = Cliente.socket.getInetAddress().getHostName(); // Conseguir el nombre de host del cliente conectado al servidor

        for (int i = 0; i < IpSalvadas.length; i++) {
            if(IpSalvadas[i] == null) {
                // Es un usuario nuevo
                IpSalvadas[i] = Cliente.DireccionIpCliente;
                System.out.println("NUEVO USUARIO CONECTADO: \n{ IP: "+ Cliente.DireccionIpCliente+" }\n{ HOST: "+ Cliente.NombreDeHost+ " }\n{ ID: "+ Cliente.id+ " }");
                break;    
            }

            
            if (IpSalvadas[i].equals(Cliente.DireccionIpCliente)) {
                // Un cliente dentro de las ip guardadas, se ha vuelto a conectar al servidor de nuevo | No es un usuario nuevo
                System.out.println("UN USUARIO SE ACABA DE CONECTAR OTRA VEZ AL SERVIDOR: "+ IpSalvadas[i]);
                break;

            }

        }
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

           Cliente.socket.close();

        }
    }
}