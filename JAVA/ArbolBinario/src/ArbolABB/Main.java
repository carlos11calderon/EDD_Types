
package ArbolABB;

import ArbolBinario.*;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class Main {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        ABB arbol = new ABB();
        
        arbol.insertar(25);
        arbol.insertar(24);
        arbol.insertar(34);
        arbol.insertar(12);
        arbol.insertar(110);
        arbol.insertar(23);
        arbol.insertar(11);
        arbol.insertar(17);
        arbol.insertar(46);
        arbol.insertar(87);
        arbol.insertar(81);
        arbol.insertar(57);
        arbol.insertar(62);
        arbol.insertar(5);
        
        
        arbol.GenerarGrafoABB();
        
        arbol.PreOrder(arbol.raiz);
        arbol.inOrder(arbol.raiz);
        arbol.PostOrder(arbol.raiz);
        
    }
    
}
