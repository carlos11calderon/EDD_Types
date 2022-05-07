
package doublesimplelist;

import Structures.List;
import Structures.Node;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class DoubleSimpleList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        int n = 5;
        String[] data = new String[n];
        //data random, example names
        data[0]="Carlos";
        data[1]="Alejandro";
        data[2]="Marta";
        data[3]="Valery";
        data[4]="Jose";
        
        List list = new List();// object of the simple list 
        
        for (int i = 0; i < n; i++) {
            /*parameters of the constructor in order is (id, data, node)
            and node parameter is null because it not have a reference*/
            Node new_node = new Node(i, data[i],null,null);
            list.add(new_node);//add the node to the list
        }//close for
        
        list.show();//view de list in console
        System.out.println("\n----view de graph----");
        list.Graph();//creat a graph
       
    }
    
    
}
