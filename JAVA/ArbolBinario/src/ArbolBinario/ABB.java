
package ArbolBinario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class ABB {
    public Nodo raiz;
    
    public ABB(){
    raiz=null;
    }
    public void insertar(int id){
        Nodo node = new Nodo(id);
        if (raiz==null) {
            raiz = node;
        }else{
        Nodo aux = raiz;
        while(aux!=null){
        node.padre=aux;
            if (node.id >= aux.id) {
                aux = aux.right;
            }else{
                aux = aux.left;
            }//cierra else
        }//cierra while
            if (node.id<node.padre.id) {
                node.padre.left=node;
            }else{
            node.padre.right=node;
            }//cierra else
        }
    }//cierra insertar
    
    public Nodo getNode(Nodo aux,int id){
        
        if (aux!=null) {
            if (aux.id==id) {
             return aux;   
            }else if (id>aux.id) {
                if (aux.right!=null) {
                    aux=getNode(aux.right,id);
                    if (aux.id==id) {
                        return aux;
                    }
            }//cierra if right
            }else if (id<aux.id) {
                if (aux.left!=null) {
                    aux = getNode(aux.left, id);
                }
            }
            
        }
    return aux;
    }
    
    
    
    public void inOrder(Nodo node){
        if (node!=null) {
            inOrder(node.left);
            System.out.println("id nodo In-Order: "+node.id);
            inOrder(node.right);
        }
    }
    
    public void PreOrder(Nodo node){
        if (node!=null) {
            System.out.println("id nodo Pre-Order: "+node.id);
            PreOrder(node.left);
            PreOrder(node.right);
        }
    }
   
    public void PostOrder(Nodo node){
        if (node!=null) {
                PostOrder(node.left);
                PostOrder(node.right);
                System.out.println("id nodo Post-Order: "+node.id);
        }
    }
    
    
  
    
    //Graphvizz
    public String Node(Nodo node){
        String resp = "";
        resp += "node"+node.id+"[label=\"<C0>|"+ node.id+"|<C1>\"];\n";
        return resp;
    }//cierra metodo para devolver el string del nodo
    
    
    public String Apuntadores(Nodo node){
        String resp ="";
        resp+=Node(node);
        if (node.left != null) {
            resp+=Apuntadores(node.left);
            resp+="node"+node.id+":C0->node"+node.left.id+";\n";
        }
        if(node.right!=null){
            resp+=Apuntadores(node.right);
            resp+="node"+node.id+":C1->node"+node.right.id+";\n";
        }
        return resp;
    }//cierra metodo para obtener el string de apuntadores
    
    public String Retorno(){
        String resp="";
        resp+="digraph ABB{\n "+ "edge[color = Red;]\n"
                + "splines=line;\n"+
                "node[shape= record, style=filled, fillcolor=lightblue, height= .1];\n";
        resp+=Apuntadores(this.raiz)+"\n";
        resp+="}";
        return resp;
    }
    
    
    public void GenerarGrafoABB() throws IOException, InterruptedException{
        File f = new File("Filess/Dot/GrafoABB.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(Retorno());
        e.close();
        String cmd = "dot -Tpng Filess/Dot/GrafoABB.dot -o Filess/GrafoABB.png";
        Runtime.getRuntime().exec(cmd);// Execute on the system
        String path = new File("").getAbsolutePath()+"\\Filess\\GrafoABB.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
        
    }//cierra grafo
    
        
}
