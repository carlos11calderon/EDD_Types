/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

/**
 *
 * @author carlo
 */
public class Node {
    /*data are private because is accesible for sets and gets*/
    private String data;//data string for example
    private int id;//data id for example 
    private Node previous, next;//prompter to the next node

    public Node(int id, String data,Node previous, Node next) {//this is the constructor 
        this.data = data;
        this.id = id;
        this.previous=previous;
        this.next = next;
    }
    /*gets and sets*/
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    
    
}
