
package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Cola {
    private Node inicio, last;
    
    public Cola(){
        inicio=last=null;   
    }
    
    boolean Empty(){// validate if the structure is empty
        return inicio==null;
    }//close empty
    
    public void push(Node node){
        if (Empty()) {
            inicio=node;
            last=node;
        }else{
        last.setNext(node);
        last=node;
        }
    }// close push
    
    public Node pop(){
        Node aux;
        if (!Empty()) {
            if (inicio==last) {
                aux=inicio;
                inicio=last=null;
            }else{
                aux=inicio;
                inicio=inicio.getNext();
                return aux;
            }
        }//close if
         return null;
    }//close pop
    
    public void show(){
        Node aux = inicio;
        while(aux!=null){
            System.out.println("->"+aux.getData());
            aux= aux.getNext();
        }
        System.out.println("Fin de la cola");
    }//close show
    
    String Retorno(){
        String resp="";
        resp+="digraph Cola{\n "+ "edge[color = Red, dir=back];\n"
                + "splines=line;"
                +"\nrankdir=LR"
                +"\nnode[shape= record, style=filled, fillcolor=lightblue, height= .1];\n";
        resp+=Apuntadores()+"\n";
        resp+="}";
        return resp;
    }
    
    public void Graph() throws IOException, InterruptedException{
        if (Empty()) {
            System.out.println("La lista esta vacia, no se puede graficar");
        }else{
        File f = new File("Filess/Dot/GraphCola.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(Retorno());
        e.close();
        String cmd = "dot -Tpng Filess/Dot/GraphCola.dot -o Filess/GraphCola.png";
        Runtime.getRuntime().exec(cmd);// Execute on the system
        String path = new File("").getAbsolutePath()+"\\Filess\\GraphCola.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
        }
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
}//close class
