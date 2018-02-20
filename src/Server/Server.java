package Server;

import IO.FilesIO.EstructuraArchivosIO;

import javax.swing.*;
import java.io.File;

public class Server {

    //Puerto para recibir
    static int puerto = 57890;

    //Ruta
    private static String ruta = "";

    //Estructura de archivos
    private EstructuraArchivosIO estructuraArchivosIO;

    public Server() {
        on();
    }

    private void on() {
        //FileChooser para elegir la carpeta a compartir
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Solo directorios
        jfc.setCurrentDirectory(new File("c:\\")); //
        jfc.setDialogTitle("LuckyCloud selection");//Titulo de la nueva ventana

        //If por si el cliente es un noob
        if ((jfc.showDialog(jfc, "Seleccionar") == JFileChooser.CANCEL_OPTION)) {
            JOptionPane.showMessageDialog(null, "El servidor de archivos no ha arrancado");
        } else {
            File archivo = jfc.getSelectedFile();
            ruta = archivo.getAbsolutePath();
            estructuraArchivosIO = new EstructuraArchivosIO(ruta);

            //Creo el hilo que escuchará las peticiones de los clientes
            Thread escuchaPeticionesAlServidor = new Thread(new Caffeine(ruta, estructuraArchivosIO));
            escuchaPeticionesAlServidor.start();
            JOptionPane.showMessageDialog(null, "El servidor de archivos ha arrancado en el puerto 12345");
        }
    }

    public static void main(String args[]){
        new Server();
    }
}


