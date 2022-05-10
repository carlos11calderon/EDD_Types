
package avl_tree;

import java.io.IOException;

public class Avl_Tree {

    
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        Tree avl = new Tree();
        
        avl.add(30);
        avl.add(40);
        avl.add(5);
        avl.add(4);
        avl.add(9);
        avl.add(15);
        avl.add(27);
        avl.add(56);
        avl.add(37);
        avl.add(98);
        avl.add(101);
        avl.add(52);
        avl.add(1);
        
        avl.GenerarGrafoAVL();
        
        
        
    }
    
}
