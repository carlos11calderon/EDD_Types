
package matrizdispersa;

import java.io.IOException;

/**
 *
 * @author carlo
 */
public class MatrizDispersa {

    
    public static void main(String[] args) throws IOException, InterruptedException {
        //this is the class main 
        
        Matriz matriz = new Matriz();
        matriz.insertarNodo(1, 1, "#913737");
        matriz.insertarNodo(3, 2, "#ba9a56");
        matriz.insertarNodo(4, 4, "#a12525");
        matriz.insertarNodo(5, 3, "#862dbd");
        matriz.insertarNodo(5, 2, "#6bbd2d");
        matriz.insertarNodo(4, 2, "#dce305");
        
        matriz.graficar();
    }
    
}
