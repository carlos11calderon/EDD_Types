package listasimple;

//imports
import Structures.*; 
import java.io.IOException;

public class ListaSimple {
  
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
        List list = new List();
        Pila list2 = new Pila();// object of the simple list 
        Cola list3 = new Cola();// object of the simple list 
        
        for (int i = 0; i < n; i++) {
            /*parameters of the constructor in order is (id, data, node)
            and node parameter is null because it not have a reference*/
            Node new_node = new Node(i, data[i], null);
            Node new_node2 = new Node(i, data[i], null);
            Node new_node3 = new Node(i, data[i], null);
            list.add(new_node);//add the node to the list
            list2.push(new_node2);//add the node to the stack
            list3.push(new_node3);//add the node to the cola
        }//close for
        
        list.show();//view de list in console
        list.Graph();//creat a graph
        list2.show();//view de list in console
        list2.Graph();//creat a graph
        list3.show();//view de list in console
        list3.Graph();//creat a graph
    }
    
}
