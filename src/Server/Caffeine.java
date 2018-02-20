package Server;

import IO.FilesIO.EstructuraArchivosIO;
import IO.Petitions.Download;
import IO.Petitions.NewUser;
import IO.Petitions.Upload;
import IO.UDP.ReceiveObject;
import IO.UDP.SendObject;

import static Server.Server.puerto;

public class Caffeine implements Runnable {

    private String ruta;
    private EstructuraArchivosIO eAIO;


    public Caffeine(String ruta, EstructuraArchivosIO estructuraArchivosIO) {
        this.ruta = ruta;
        this.eAIO=estructuraArchivosIO;
    }

    @Override
    public void run() {
        //Bucle infinito
        while (true) {
            //Recibo objeto
            Object o = ReceiveObject.receive(puerto);

            //Si la peticion es de subida
            if (o instanceof Upload) {
                Upload up = (Upload) o;
                up.getObjetoFichero().crearArchivoDesdeBuffer();
                eAIO = new EstructuraArchivosIO(ruta);
                SendObject.Send(eAIO, up.getHost(), up.getPuerto());
            }

            //Si la peticion es de bajada
            if (o instanceof Download) {
                Download dw = (Download) o;
                dw.getObjetoFichero().crearBufferDesdeArchivo();
                SendObject.Send(dw, dw.getHost(), dw.getPuerto());
            }

            //Si es un nuevo cliente
            if (o instanceof NewUser) {
                NewUser newUser = (NewUser) o;
                //Le mando la estructura de archivos
                SendObject.Send(eAIO, newUser.getHost(), newUser.getPuerto());
            }
        }
    }
}
