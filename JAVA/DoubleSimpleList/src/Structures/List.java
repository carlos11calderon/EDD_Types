/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class List {
   private Node inicio, last;
    
    
    public List(){
        inicio=last=null;
    }
    
    public boolean Empty(){
        //if the inicio is null, the list is empty
        return inicio==null;
    }
    
    public void add(Node node){
        //this method insert node in the list
        if (Empty()) {//ini if
            inicio = node;
            last = node;
            System.out.println("-First node is added");
        }else{
            last.setNext(node);
            node.setPrevious(last);
            last = node;
            System.out.println("-The new node is added");
        }//close else
    }//close add
    
    public void show(){
        if (Empty()) {
            System.out.println("The list is empty");
        }else{
            Node temp = inicio;
            while(temp.getNext()!=null){
                System.out.print("Data: "+temp.getData()+
                                " <-> Data: "+temp.getNext().getData());
                temp = temp.getNext();
            }
        }
    
    }

    //Graphviz
    public void Graph() throws IOException, InterruptedException{
        if (Empty()) {
            System.out.println("La lista esta vacia, no se puede graficar");
        }else{
        File f = new File("Filess/Dot/GraphDoubleListSimple.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(Retorno());
        e.close();
        String cmd = "dot -Tpng Filess/Dot/GraphDoubleListSimple.dot -o Filess/GraphDoubleListSimple.png";
        Runtime.getRuntime().exec(cmd);// Execute on the system
        String path = new File("").getAbsolutePath()+"\\Filess\\GraphDoubleListSimple.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
        }
    }
    
    
    String Retorno(){
        String resp="";
        resp+="digraph DoubleList{\n "+ "edge[color = Red];\n"
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
                resp+="node"+aux.getNext().getId()+"->node"+aux.getId()+";\n";
                
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
