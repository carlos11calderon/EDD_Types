package Structures;

public class Node {
    /*data are private because is accesible for sets and gets*/
    private String data;//data string for example
    private int id;//data id for example 
    private Node next;//prompter to the next node

    public Node(int id, String data, Node next) {//this is the constructor 
        this.data = data;
        this.id = id;
        this.next = null;
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
}
