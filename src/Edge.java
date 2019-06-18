import java.util.List;
import java.util.Random;

public class Edge {

    //fields
    private int V_from;
    private int V_to;
    private int num;

    //constructors
    public Edge (int from, int to, int num){
        this.V_from=from;
        this.V_to=to;
        this.num=num;
    }

    //methods


    public int getV_from() {
        return V_from;
    }

    public int getV_to() {
        return V_to;
    }

    public int getV_num() {return num;}
}
