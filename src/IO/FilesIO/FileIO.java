package IO.FilesIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO {
    String nombre;
    String ruta;
    byte[] bufferArchivo;

    public FileIO(String nombreArchivo, String ruta) {
        this.nombre = nombreArchivo;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    //En la solicitud de bajar archivo, el servidor crea un buffer de bytes con
    //el contenido del fichero seleccionado
    public void crearBufferDesdeArchivo(){
        try {
            bufferArchivo = Files.readAllBytes( Paths.get(ruta, nombre));
        } catch (IOException ex) {
            System.out.println("Error al crear un buffer basado en archivo");
        }
    }

    //En la solicitud de subir archivo, el servidor convierte el buffer recibido en un fichero
    public void crearArchivoDesdeBuffer(){
        try {
            Files.write(Paths.get(ruta, nombre), bufferArchivo);
        } catch (IOException ex) {
            System.out.println("Error al crear un archivo basado en buffer");
        }
    }
}
