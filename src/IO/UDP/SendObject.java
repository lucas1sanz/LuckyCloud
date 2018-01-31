package IO.UDP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class SendObject {
    public static void Send(Object objeto, String hostdestino, int puerto) {
        try {
            //Puerto UDP
            DatagramSocket sck;

            //Paquete
            DatagramPacket packet;

            //Array de bytes
            byte bytes[];

            //Serializamos el objeto
            bytes = serializar(objeto);

            //Le asiganamos un valor al socket
            sck = new DatagramSocket();

            //Y al paquete
            packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(hostdestino), puerto);

            //Enviamos
            sck.send(packet);
            //Cerramos conexion
            sck.close();

            //Excepciones
        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            System.out.println("Error al crear el paquete");
        } catch (IOException ex) {
            System.out.println("Error en el envío del paquete");
        }

    }

    //Metodo para serializar el objeto
    static byte[] serializar(Object objeto) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (
                ObjectOutputStream os = new ObjectOutputStream(bytes)) {
            os.writeObject(objeto);
        } catch (IOException ex) {
            System.out.println("Error al crear el array de bytes");
        }
        return bytes.toByteArray();
    }
}
