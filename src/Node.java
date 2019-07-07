import java.util.Collections;
import java.util.LinkedList;

public class Node {

    public enum VerticeType {
        NORMAL, PNODE, QNODE, SNODE, TNODE;
    }

    private int idNum;
    private LinkedList<Edge> edges;
    private VerticeType verType;
    private LinkedList<Edge> TransEdges;
    private Edge SuperEdge;
    private boolean isSuperNode;


    /*** constructors ***/

    public Node(int num, VerticeType type) {
        this.idNum = num;
        this.edges = new LinkedList<>();
        this.TransEdges = new LinkedList<>();
        this.isSuperNode = false;
        this.verType = type;
    }

    /*** getters ***/
    public int getIdNum() {
        return idNum;
    }

    public VerticeType getVerType() {
        return verType;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }


    /*** methods ***/
    public void resetTransEdges() {
        this.TransEdges = new LinkedList<>();

    }

    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    public LinkedList<Edge> getTransEdges() {
        return TransEdges;
    }

    public void addTransEdge(Edge e) {
        this.TransEdges.add(e);
    }

    public void removeTransEdge(Edge e) {
        this.TransEdges.remove(e);
    }

    public void setTransEdge(LinkedList<Edge> e) {
        resetTransEdges();
        this.TransEdges.addAll(e);
    }

    public boolean isSuperNode() {
        return isSuperNode;
    }


    public void setSuperNode(boolean b, Edge e) {
        this.isSuperNode = b;
        if (e != null)
            this.SuperEdge = e;
    }

    public Edge getSuperEdge() {
        return this.SuperEdge;
    }

}
