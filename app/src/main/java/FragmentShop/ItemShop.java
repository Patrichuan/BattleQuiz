package FragmentShop;

/**
 * Created by daniel on 20/02/15.
 */
public class ItemShop {
    String titulo,descripcion;
    int dinero, imagen;
    public ItemShop(String titulo,String descripcion,int imagen, int dinero){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.dinero=dinero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public int getDinero() {
        return dinero;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}
