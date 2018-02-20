package IO.Petitions;

import IO.FilesIO.FileIO;

import java.io.Serializable;

public class Upload  extends NewUser implements Serializable {
    private FileIO objetoFichero;

    public Upload(String host, Integer puerto, FileIO objetoFichero) {
        super(host, puerto);
        this.objetoFichero = objetoFichero;
    }

    public FileIO getObjetoFichero() {
        return objetoFichero;
    }
}
