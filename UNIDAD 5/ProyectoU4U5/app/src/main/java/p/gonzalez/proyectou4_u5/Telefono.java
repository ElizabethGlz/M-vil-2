package p.gonzalez.proyectou4_u5;

public class Telefono {
    private String telefono;
    private int id;

    public Telefono( int id, String telefono) {
        this.telefono = telefono;
        this.id = id;
    }
    public Telefono(){}

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
