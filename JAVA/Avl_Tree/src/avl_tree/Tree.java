
package avl_tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class Tree {
    public Nodo raiz;
    
    public Tree(){
        this.raiz = null;
    }
    
    
    
    public Nodo getRaiz(){
        return raiz;
    }
    
    public void add(int id){// en este metodo creamos el nodo y verificamos si el arbol ya esta creado o no
        Nodo nodo = new Nodo(id, null, null);
        if (raiz==null) {
            raiz = nodo;
        }else{
        raiz = insertar(nodo,raiz);
        }
    }
    
    public Nodo insertar(Nodo nuevo, Nodo node){//metodo para insertar un nodo en el arbol AVL
        Nodo Npadre = node;
        if (nuevo.id<node.id) {
            if (node.left==null) {
                node.left = nuevo;
            }else{//abre else
                node.left = insertar(nuevo,node.left);
                if ((FactE(node.left) - FactE(node.right))==2) {
                    if (nuevo.id<node.left.id) {
                        Npadre = RLeftS(node);//rotamos simple a la izquierda
                    }else{//abre else
                        Npadre = RLeft(node);//rotamos izquierda izquierda
                    }//cierra else
                }//cierra if de Fact E - FactE
                
            }//cierra else
            
        }//cierra if node.left ==null
        else if(nuevo.id>node.id){
            if (node.right==null) {
                node.right=nuevo;
            }else{
            node.right = insertar(nuevo, node.right);
                if ((FactE(node.right) - FactE(node.left))==2) {
                    if (nuevo.id>node.right.id) {
                        Npadre=RRightS(node);
                    }else{
                    Npadre=RRight(node);
                    }//cierra else
                }//cierra if de resta
            }//cierra else
            
        }//cierra else if
        else{
            System.out.println("El nodo ya existe");
        }//cierra else
        //actualizamos la altura despues de insertar el nodo
        if ((node.left==null) && (node.right != null)) {
            node.factorE = node.right.factorE +1 ;
        }else if((node.right==null)&&(node.left != null)){
            node.factorE = node.left.factorE + 1;
        }else{
            node.factorE = Math.max(FactE(node.left), FactE(node.right))+1;
        }
        return Npadre;
    }//cierra metodo
    
    public boolean delete(int id){
        Nodo aux = this.raiz;
        Nodo padre = this.raiz;
        boolean hijoleft =true;
        
        while(!(aux.id==id)){//busca el nodo a eliminar y verifica si es un hijo en la izq o en la derecha
        padre = aux;
            if (id<aux.id) {
                hijoleft=true;
                aux=aux.left;
            }else{
                hijoleft=false;
                aux=aux.right;
            }//cierra else
            
            if (aux==null) {
                return false;
            }//cierra if
        }//cierra while
        
        //caso en que el nodo no tenga ningun hijo
        if ((aux.left == null) && (aux.right==null)) {
            if (aux==this.raiz) {
                raiz=null;
            }else if(hijoleft){
                padre.left=null;
            }else{
                padre.right=null;
            }//cierra else
        }//cierra if primero
        else if (aux.right==null) {//si en caso no tenga ni un hijo en la derecha...
            if (aux==this.raiz) {
                raiz = aux.left;
            }else if (hijoleft) {
                padre.left=aux.left;
            }else{
                padre.right=aux.left;
            }
        }//cierra else if 2
        else if (aux.left==null) {//si el nodo no tiene ni un hijo por la izq
            if (aux==this.raiz) {
                raiz = aux.right;
            }else if(hijoleft){
                padre.left=aux.right;
            }else{
                padre.right=aux.right;
            }//cierra else
        }//cierra else if 3
        else{// por ultimooo, si hay mas hijos ya sea en izq o der
            Nodo ree = Reemplazar(aux);
            if (aux==this.raiz) {
                raiz=ree;
            }else if (hijoleft) {
                padre.left=ree;
            }else{
                padre.right=ree;
            }
            ree.left=aux.left;
            
            //actualizamos la altura
            if ((aux.left==null) && (aux.right != null)) {
                aux.factorE = aux.right.factorE +1 ;
            }else if((aux.right==null)&&(aux.left != null)){
                aux.factorE = aux.left.factorE + 1;
            }else{
                aux.factorE = Math.max(FactE(aux.left), FactE(aux.right))+1;
            }//cierra else
        }//cierra else
    return true;
    }//cierra metodo delete
    
    public Nodo BuscarNodo(int id, Nodo raizTemp){//id del nodo a buscar y una raiz temporal
        if (raiz==null) {
            return null;
        }//cierra if "si es nula la raiz"
        else if(raizTemp.id==id){
            return raizTemp;
        }//cierra else if 1
        else if (raizTemp.id<id) {
            return BuscarNodo(id,raizTemp.right);
        }//cierra else if 2
        else {//la ultima condicion que puede haber
            return BuscarNodo(id,raizTemp.left);
        }//cierra else
        
    }//cierra metodo buscar nodo
    
    //------------------------Cierra metodos publicos--------------------------------------
    //-----------------------------metodos internos----------------------------------------
    
    int FactE(Nodo node){//metodo que retorna el factor de equilibrio
        if (node==null) {//si el nodo es nulo, retorna -1
            return -1;
        }else{//else
        return node.factorE;// retorna el factor de equilibrio de este nodo
        }//cierra else
    }//cierrra metodo factor de equilibrio
    
     /// inicia metodos de rotacion hacia la izquierda
    Nodo RLeftS(Nodo node){// metodo de rotacion simple hacia la izquierda
        Nodo aux = node.left;
        node.left = aux.right;
        aux.right= node;
        node.factorE = Math.max(FactE(node.left), FactE(node.right))+1;
        aux.factorE= Math.max(FactE(aux.left),FactE(aux.right))+1;
        return aux;
    }// cierra metodo Rlefts
    
    Nodo RLeft(Nodo node){// metodo de rotacion izquierda izquierda
        Nodo temp;
        node.left=RRightS(node.left);
        temp = RLeftS(node);
        return temp;
    }//cierra metodo Rleft
    /// cierra metodos de rotacion hacia la izquierda
    
    //inicia metodos de rotar hacia la derecha
    Nodo RRightS(Nodo node){//Metodo de rotar hacia la derecha de forma simple
        Nodo aux = node.right;
        node.right=aux.left;
        aux.left = node;
        node.factorE = Math.max(FactE(node.left), FactE(node.right))+1;
        aux.factorE= Math.max(FactE(aux.left),FactE(aux.right))+1;
    return aux;
    }//cierra metodo de rotar de forma simple a la derecha
    
    Nodo RRight(Nodo node){//metodo de rotacion de derecha a derecha
        Nodo temp;//nodo temporal para rotar simple a la derecha
        node.right = RLeftS(node.right);// el nodo de la derecha se rota de forma simple en la izquierda
        temp = RRightS(node);//rotamos a la derecha
    return temp;//retornamos el nodo
    }//cierra metodo de rotar derecha derecha
    //cierra metodos de rotar hacia la derecha
    
    Nodo Reemplazar(Nodo reem){
        Nodo rpadre = reem;
        Nodo reem2 = reem;
        Nodo aux = reem.right;

        while(aux!=null){
            rpadre=reem2;
            reem2=aux;
            aux=aux.left;
        }//cierra while
        if (reem2 != reem.right) {
            rpadre.left = reem2.right;
            reem2.right = reem.right;
        }
     return reem2;
    }
    
    /*-----------------------Cierra metodos internos----------------------------------------
    
    ----------------------------------recorridos en consola-------------------------------*/
    
    public void inOrden(Nodo node){
        if (node!=null) {
            inOrden(node.left);
            System.out.println(node.id+" ");
            inOrden(node.right);
        }//cierra if
    }
    
    public void preOrden(Nodo node){
        if(node!=null){
            System.out.println(node.id+" ");
            inOrden(node.left);
            inOrden(node.right);
        }//cierra if
    }
    
    public void PostOrden(Nodo node){// recorrido post orden
        if(node!=null){
            System.out.println(node.id+" ");
            inOrden(node.left);
            inOrden(node.right);
        }//cierra if
    }
    /*-------------------cierran los recorridos en consola----------------------
    --------------------- GRAFICO CON HERRAMIENTA GRAPHVIZZ--------------------*/
    
    //Graphvizz
    public void GenerarGrafoAVL() throws IOException, InterruptedException{
        File f = new File("Archivos/Dot/GrafoAVL.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(Retorno());
        e.close();
        String cmd = "dot -Tpng Archivos/Dot/GrafoAVL.dot -o Archivos/GrafoAVL.png";
        
        Runtime.getRuntime().exec(cmd);// Execute on the system
        String path = new File("").getAbsolutePath()+"\\Archivos\\GrafoAVL.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
    }//cierra grafo
    
    String Node(Nodo node){
        String resp = "";
        resp += "node"+node.id+"[label=\"<izq> | <f>"+node.id+"|<der>\"];\n";
        return resp;
    }//cierra metodo para devolver el string del nodo
    
    
    String Apuntadores(Nodo node){
        String resp ="";
        resp+=Node(node);
        if (node.left != null) {
            resp+=Apuntadores(node.left);
            resp+="\"node"+node.id+"\":izq -> \"node"+node.left.id+"\":f;\n";
        }
        if(node.right!=null){
            resp+=Apuntadores(node.right);
            resp+="\"node"+node.id+"\":der -> \"node"+node.right.id+"\":f;\n";
        }
        return resp;
    }//cierra metodo para obtener el string de apuntadores
    
    String Retorno(){
        String resp="";
        resp+="digraph Imagenes{\n "+ "edge[color = Red;]\n"
                + "splines=line;\n"+
                "node[shape= record, style=filled, fillcolor=lightblue, height= .1];\n";
        resp+=Apuntadores(this.raiz)+"\n";
        resp+="}";
        return resp;
    }
    
    
}// cierra clase
