package pe.edu.idat.appidatpatitas.bd.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "persona")
public class Persona {
    @PrimaryKey
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
    private String usuario;
    private String password;
    private String esvoluntario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEsvoluntario() {
        return esvoluntario;
    }

    public void setEsvoluntario(String esvoluntario) {
        this.esvoluntario = esvoluntario;
    }
}
