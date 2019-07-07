import java.util.List;
import java.util.Random;

public class Edge {

    //fields
    private Node V_from;
    private Node V_to;
    private int num;

    //constructors
    public Edge ( Node from, Node to, int num ){
        this.V_from = from;
        this.V_to = to;
        this.num = num;
    }

    //methods
    public Node getV_from() {
        return V_from;
    }

    public Node getV_to() {
        return V_to;
    }

}
