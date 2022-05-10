/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizdispersa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class Matriz {
    public Nodo inicio;
    public int dimx, dimy;
    
    //constructor
    public Matriz(){
        inicio = new Nodo(-1,-1,"nulo");
        dimx=0;
        dimy=0;
    }
    
    public void insertarNodo(int x, int y, String color){
        Nodo nuevo= new Nodo(x,y,color);// si el nodo ya esta agregado, se reescribe
        Nodo col = buscarcol(y);
        Nodo fil = buscarfila(x);
        if ((fil==null)&&(col==null)) {//caso 1 **No existe la fila ni la columna**
            crearCol(y);
            crearFila(x);
            col = buscarcol(y);
            fil = buscarfila(x);
            //se inserta el contenido
            insertarOrdenCol(nuevo, fil);
            insertarOrdenfil(nuevo,col);
        }else if (fil==null && col!=null) {//caso2 si no existe solo la fila
            crearFila(x);
            fil = buscarfila(x);
            insertarOrdenCol(nuevo, fil);
            insertarOrdenfil(nuevo,col);
        }else if(fil!=null && col==null){//caso 3 si no existe solo la columna
            crearCol(y);
            col = buscarcol(y);
            insertarOrdenCol(nuevo, fil);
            insertarOrdenfil(nuevo,col);
        }else if ((fil!=null) && (col!=null)) {//si existen ambos
            insertarOrdenCol(nuevo, fil);
            insertarOrdenfil(nuevo,col);
        }
        if(this.dimx<x){//modifica la dimension en x
            this.dimx=x;
        }
        if (this.dimy<y) {//modifica la dimension en y
            this.dimy=y;
        }
    }//cierra metodo
    
    public void imprimir(Nodo ini){
        Nodo aux = ini;
        System.out.println("filas"+dimx);
        System.out.println("col: "+dimy);
        while (aux!=null){
            String text="";
            Nodo aux2=aux;
            while (aux2!=null){
                text+="[Fila: "+aux2.x+" Col:"+aux2.y+"]";
                aux2 = aux2.siguiente;
            }
            System.out.println(text);
            aux = aux.abajo;
        }
    }
    
    Nodo buscarfila(int x){
        Nodo aux = inicio;
        while(aux!=null){
            if (aux.x==x) {
               return aux; 
            }else{
            aux=aux.abajo;
            }
        }
        return null;
    }
    
    Nodo buscarcol(int y){
    Nodo aux = inicio;
        while(aux!=null){
            if (aux.y==y) {
               return aux; 
            }else{
            aux=aux.siguiente;
            }
        }
        return null;
    }
    
    void crearCol(int y){
        Nodo nodo_col = inicio;
        Nodo nuevo = new Nodo(-1,y,"");
        insertarOrdenCol(nuevo,nodo_col);
    }
    
    void insertarOrdenCol(Nodo nuevo,Nodo Cabeza){//insertar en orden columnas
    Nodo aux=Cabeza;
    boolean insertado=false;
        while(true){
            if (nuevo.y==aux.y) {//si la posicion ya existe se vuelve a escribir
                aux.x=nuevo.x;
                aux.color=nuevo.color;
                return;
            }else if (aux.y>nuevo.y) {//insertamos en medio antes que el aux
                insertado=true;
                break;
            }
            if (aux.siguiente!=null) {
                aux=aux.siguiente;
            }else{//insertar despues del aux
                insertado=false;
                break;
            }
        }
    if (insertado) {
            nuevo.siguiente=aux;
            aux.anterior.siguiente=nuevo;
            nuevo.anterior=aux.anterior;
            aux.anterior=nuevo;
        }else{
            aux.siguiente=nuevo;
            nuevo.anterior=aux;
        }
    }
    
    void crearFila(int x){
    Nodo nodo_fila=inicio;
    Nodo nuevo = new Nodo(x,-1,"");
    insertarOrdenfil(nuevo,nodo_fila);
    
    }
    
    void insertarOrdenfil(Nodo nuevo,Nodo Cabeza){//insertar en orden columnas
    Nodo aux=Cabeza;
    boolean insertado=false;
    while(true){
        if (nuevo.x==aux.x) {//si la posicion ya existe se vuelve a escribir
        aux.y=nuevo.y;
        aux.color=nuevo.color;
        return;
        }else if (aux.x>nuevo.x) {//insertamos en medio antes que el aux
            insertado=true;
            break;
        }
        
        if (aux.abajo!=null) {
            aux=aux.abajo;
        }else{//insertar despues del aux
        insertado=false;
        break;
        }
        }
    if (insertado) {
            nuevo.abajo=aux;
            aux.arriba.abajo=nuevo;
            nuevo.arriba=aux.arriba;
            aux.arriba=nuevo;
        }else{
            aux.abajo=nuevo;
            nuevo.arriba=aux;
        }
    
    }
    
    //------------------- se crea la grafica de la matriz dispersa--------------
    public void graficar() throws IOException, InterruptedException{
        File f = new File("Archivos/Dot/GrafoMatrizDispersa.dot");//create and open new file
        FileWriter e = new FileWriter(f);//write in file
        e.write(RetornoCapa());
        e.write("}");
        e.close();
        System.out.println("Termino de escribir");

        String cmd = "dot -Tpng Archivos/Dot/GrafoMatrizDispersa.dot -o Archivos/GrafoMatrizDispersa.png";
        Runtime.getRuntime().exec(cmd); 
        String path = new File("").getAbsolutePath()+"\\Archivos\\GrafoMatrizDispersa.png";
        Thread.sleep(2*1000);
        cmd="explorer "+path;
        Runtime.getRuntime().exec(cmd);// Execute on the system
        System.out.println("Se envioo");
         
    }//grafica
    
    String RetornoCapa(){
    String resp="";
    resp+="digraph Matriz{\n "+ "edge[color = black;]\n"
                + "splines=ortho;\n"+
                "node[shape= record, style=filled, fillcolor=lightblue];\n";
    resp+=GrafoEncabezados();
    resp+=ApuntadoresH();
    resp+=ApuntadoresV();
    
    return resp;
    }
    String GrafoEncabezados(){
    String resp="";
    resp+="raiz[label=\"Raiz"+"\", group=1];\n";
    resp+="edge[dir=\"both\"]\n";
        for (int i = 0; i <= dimx; i++) {
            resp+="Fila"+i+"[label=\""+i+"\" group=1]\n";
        }
        resp+="Fila0";
        for (int i = 1; i <= dimx; i++) {
            resp+="->Fila"+(i)+"\n";
        }
        resp+=";";
        for (int i = 0; i <= dimy; i++) {
            resp+="Columna"+i+"[label=\""+i+"\",group="+(i+2)+"]\n";
        }
        resp+="Columna0";
        for (int i = 1; i <= dimy; i++) {
            resp+="->Columna"+(i)+"\n";
        }
        resp+=";\n";
        if (dimx>0) {
           resp+="raiz->Fila0\n";  
        }
        if (dimy>0) {
           resp+="raiz->Columna0\n"; 
        }
        resp+="{rank=same;raiz";
        for (int i = 0; i <= dimy; i++) {
            resp+=";Columna"+i;
        }
        resp+="}\n";
    return resp;
    }
    String ApuntadoresH(){
    String resp="";
    String Edge="";
    String EdgeRank="";
    
    Nodo aux = inicio.abajo;
        System.out.println("Inicia el grapvis");
    while (aux!=null){
        
        Nodo aux2=aux.siguiente;
        Edge+="Fila"+aux2.x;
        EdgeRank="";
        EdgeRank+="{rank=same;Fila"+aux2.x;
        
        while(aux2!=null){
            resp+="NodoFila"+aux2.x+"Col"+aux2.y+"[label=\"\", fillcolor=\""+aux2.color+"\",group="+(aux2.y+2)+" ]\n";    
            Edge+="\n->NodoFila"+aux2.x+"Col"+aux2.y;
            EdgeRank+=";NodoFila"+aux2.x+"Col"+aux2.y;
        aux2 = aux2.siguiente;
        }
        EdgeRank+="}\n";
        Edge+=";\n"+EdgeRank;
        aux = aux.abajo;
    }
    resp+=Edge;
        
        
        
    return resp;
    }
    String ApuntadoresV(){
    String resp="";
    String EdgeC="";
    
    Nodo aux = inicio.siguiente;
        System.out.println("Inicia el grapvis Vertical");
    while (aux!=null){
        Nodo aux2=aux.abajo;
        EdgeC+="Columna"+aux2.y;
        
        while(aux2!=null){
            EdgeC+="\n->NodoFila"+aux2.x+"Col"+aux2.y;
            if (aux2.abajo==null) {
                EdgeC+=";\n";
            }
            aux2 = aux2.abajo;
            
        }
        aux = aux.siguiente;
        
    }
    resp+=EdgeC;
        System.out.println("Termino de escribir las columnas");
      //  System.out.println(resp);
    return resp;
    }

    
   
    
    
    
}
