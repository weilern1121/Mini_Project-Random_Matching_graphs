import java.util.Collections;
import java.util.LinkedList;

public class Node {

    public enum VerticeType {
        NORMAL, SNODE, TNODE;
    }

    private int idNum;
    private LinkedList<Integer> edges;
    private boolean isSuperNode;
    private VerticeType verType;

    /*** constructors ***/
    public Node(int num) {
        this.idNum = num;
        this.edges = new LinkedList<>();
        this.isSuperNode = false;
        this.verType = VerticeType.NORMAL;
    }

    public Node(int num, VerticeType type) {
        this.idNum = num;
        this.edges = new LinkedList<>();
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

    public LinkedList<Integer> getEdges() {
        return edges;
    }

    public boolean isSuperNode() {
        return isSuperNode;
    }


    /*** methods ***/
    public void resetEdges() {
        this.edges = new LinkedList<>();

    }

    public void addEdgesList(LinkedList edgesToAdd) {
        for (Object e : edgesToAdd) {
            this.edges.add((Integer)e);
        }
    }

    public void setSuperNode(boolean b){
        this.isSuperNode=b;
    }

    public void setIdNum(int num){this.idNum=num;}

    public boolean changeSuperInList( int elem , int changeTo){
        if(!this.edges.contains(elem)) //boolean used in graph's side
            return false;
        this.edges.removeFirstOccurrence(elem);
        this.edges.add(changeTo);
        Collections.sort(this.edges); //finnaly - sort by vNum
        return true;
    }

    public void fixEdgesList(){
        this.edges.removeFirstOccurrence(this.idNum);
        Collections.sort(this.edges);
    }

}
