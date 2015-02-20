package Logros;

/**
 * Created by daniel on 20/02/15.
 */
public class Logros {
    String titulo, descripcion, puntuacion;

    public Logros(String titulo, String descripcion, String puntuacion){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.puntuacion=puntuacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
}
