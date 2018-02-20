package IO.Petitions;

import java.io.Serializable;

public class NewUser implements Serializable{
    String host;
    Integer puerto;

    public NewUser(String host, Integer puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public String getHost() {
        return host;
    }

    public Integer getPuerto() {
        return puerto;
    }
}
