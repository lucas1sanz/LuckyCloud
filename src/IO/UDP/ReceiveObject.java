package IO.UDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveObject {
    public static Object receive(int puerto) {

        //Nuevos sockt y paquete
        DatagramSocket sck;
        DatagramPacket packet = null;

        //Nuevo array de bytes
        byte bytes[] = new byte[50000];


        try {
            sck = new DatagramSocket(puerto);

            packet = new DatagramPacket(bytes, bytes.length);
            sck.receive(packet);

            sck.close();

        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error en la recepción del paquete");
        }

        return deserializar(packet.getData());
    }

    //Metodo para deserilizar
    static Object deserializar(byte[] bytesRecibidos) {
        Object objeto = null;
        ByteArrayInputStream bytesDelPaquete = new ByteArrayInputStream(bytesRecibidos);


        try (ObjectInputStream is = new ObjectInputStream(bytesDelPaquete)) {
            objeto = is.readObject();

        } catch (ClassNotFoundException ex) {
            System.out.println("Error al convertir el objeto");
        } catch (IOException ex) {
            System.out.println("Error al extraer datos del paquete");
        }
        return objeto;
    }
}
