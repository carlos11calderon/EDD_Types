
package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Pila {
    private Node last;
    
    public Pila(){//inicia constructor
        last= null;// nodo
    }
    
    boolean Empty(){// method for validate if is empty the stack
        return last==null;
    }
    
    public void push(Node nodo){// add a node in the peak
        nodo.setNext(last);
        last=nodo;
    }//close push
    
    public Node pop(){// return de last node added
        if (!Empty()) {
            Node temp = last;
            last = last.getNext();
            return temp;
        }
        return null;
    }
    
    
    public void show(){//show the pila
        Node temp = last;// node temp for travel the stack/Pila
        while(temp!=null){//cycle to travel the stack
            System.out.println("Data: "+temp.getData());
            temp=temp.getNext();
        }//close while
        System.out.println("end pila");
    }//close show
    
    
    public void Graph() throws IOException, InterruptedException{
        if (Empty()) {
            System.out.println("La lista esta vacia, no se puede graficar");
        }else{
        File f = new File("Filess/Dot/GraphStack_Pila.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(Retorno());
        e.close();
        String cmd = "dot -Tpng Filess/Dot/GraphStack_Pila.dot -o Filess/GraphStack_Pila.png";
        Runtime.getRuntime().exec(cmd);// Execute on the system
        String path = new File("").getAbsolutePath()+"\\Filess\\GraphStack_Pila.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
        }
    }
    
    String Retorno(){
        String resp="";
        resp+="digraph Stack{\n "+ "edge[color = Red, dir=back];\n"
                + "splines=line;"
                +"\nnode[shape= record, style=filled, fillcolor=lightblue, height= .1];\n";
        resp+=Apuntadores()+"\n";
        resp+="}";
        return resp;
    }
    
    String Apuntadores(){
       String resp="";
       Node aux = last;
       
        while(aux!= null){
           resp+=nodee(aux);
           aux=aux.getNext();
        }
        aux = last;
        while(aux!=null){
            if (aux.getNext()!=null) {
                resp+="node"+aux.getId()+"->node"+aux.getNext().getId()+";\n";
                
            }//cierra if
            aux=aux.getNext();
            }
       
       return resp;
    }
    
    String nodee(Node node){
        String resp="";
        resp+="node"+node.getId()+"[label=\"ID:"+node.getId()+"\\nDatos: "+node.getData()+" \"];\n"; 
        return resp;
    }
    
    
    
    
    
}
