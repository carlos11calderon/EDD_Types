/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolBinario;

/**
 *
 * @author carlo
 */
public class Nodo {
    public Nodo padre, right,left;
    public int id;

    public Nodo(int id){
    this.id=id;
    padre=null;
    right=null;
    left = null;
    }
}
