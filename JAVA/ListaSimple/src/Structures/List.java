
package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class List {
    private Node inicio, last;
    
    
    public List(){
        inicio=null;
        last=null;
    }
    
    public boolean Empty(){
        //if the inicio is null, the list is empty
        return inicio==null;
    }
    
    public void add(Node node){
        //this method insert node in the list
        if (Empty()) {//ini if
            inicio = node;
            last   = node;
            System.out.println("First node is added");
        }else{
            last.setNext(null);
            last.setNext(node);
            last=node;
            last.setNext(null);
            System.out.println("the new node is added");
        }//close else
    }//close add
    
    public void show(){
        Node temp = inicio;
        if (Empty()) {
            System.out.println("The list is empty");
        }else{
            while(temp.getNext()!=null){
                System.out.print(" Data: "+temp.getData()+" -> Data: "+temp.getNext().getData());
                temp = temp.getNext();
            }
        }
    
    }

    //Graphviz
    public void Graph() throws IOException, InterruptedException{
        if (Empty()) {
            System.out.println("La lista esta vacia, no se puede graficar");
        }else{
        File f = new File("Filess/Dot/GraphListSimple.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(Retorno());
        e.close();
        String cmd = "dot -Tpng Filess/Dot/GraphListSimple.dot -o Filess/GraphListSimple.png";
        Runtime.getRuntime().exec(cmd);// Execute on the system
        String path = new File("").getAbsolutePath()+"\\Filess\\GraphListSimple.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
        }
    }
    
    
    String Retorno(){
        String resp="";
        resp+="digraph Capas{\n "+ "edge[color = Red];\n"
                + "splines=line;"
                +"\nrankdir=LR"
                +"\nnode[shape= record, style=filled, fillcolor=lightblue, height= .1];\n";
        resp+=Apuntadores()+"\n";
        resp+="}";
        return resp;
    }

    String Apuntadores(){
       String resp="";
       Node aux = inicio;
       
        while(aux!= null){
           resp+=nodee(aux);
           aux=aux.getNext();
        }
        aux = inicio;
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
