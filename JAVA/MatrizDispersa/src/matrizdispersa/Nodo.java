
package matrizdispersa;

/**
 *
 * @author carlo
 */
public class Nodo {
    public int x, y,dimx,dimy;
    public String color;
    public Nodo siguiente, anterior, arriba, abajo, izquierda, derecha;

    public Nodo(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color=color;
        this.siguiente = null;
        this.anterior = null;
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }
}
