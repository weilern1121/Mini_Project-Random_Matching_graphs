import java.util.*;

import static java.util.Collections.shuffle;

public class BipartiteGraph {

    /*** fields ***/
    private int numOfVertex;
    private Node[] Qnodes;
    private Node[] Pnodes;
    private LinkedList<Edge> edges;
    private int edgeCounter; //used as identifier to edges
    private int dRegularNum;

    /*** constructors ***/
    private static boolean ValidListToCreate(LinkedList<Integer> lst, int dReg) {
        for (int i = 0; i < lst.size(); i += dReg) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int j = 0; j < dReg; j++) {
                tmp.add(lst.get(i + j));
            }
            HashSet<Integer> hs = new HashSet<>(tmp);
            if (!(hs.size() == dReg))
                return false;
        }
        return true;
    }

    private LinkedList<Integer> PrepareListOfEdges() {
        LinkedList<Integer> lst = new LinkedList<>();
        for (int i = 0; i < numOfVertex; i++)
            for (int j = 0; j < dRegularNum; j++)
                lst.add(i);
        boolean flag = false;
        while (!flag) {
            shuffle(lst);
            if (ValidListToCreate(lst, dRegularNum))
                flag = true;
        }
        return lst;
    }

    private void BuildRandomBipartiteGraph() {
        LinkedList<Integer> lst = PrepareListOfEdges();
        for (int i = 0, k=0; k < lst.size(); k += dRegularNum, i++) {
            for (int j = 0; j < dRegularNum; j++) {
                Edge e = new Edge(Pnodes[i], Qnodes[lst.get(k + j)], edgeCounter++);
                Pnodes[i].addEdge(e);
                edges.add(e);
            }
        }
    }

    private void BuildKDBipartiteGraph() {
        int num = this.numOfVertex;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < dRegularNum; j++) {
                Edge e1 = new Edge(Pnodes[i], Qnodes[(i + j) % num], edgeCounter++);
                Pnodes[i].addEdge(e1);
                Qnodes[(i + j) % num].addEdge(e1);
                this.edges.add(e1);

            }
        }
    }

    //numofver if per list
    BipartiteGraph(int numOfVer, int degNum, int typeOfEdgesCostructor) {
        if (degNum > numOfVer)
            throw new IllegalArgumentException("ERROR - d-regular num must be smaller then number of vertices!");
        this.numOfVertex = numOfVer;
        this.Qnodes = new Node[numOfVer];
        this.Pnodes = new Node[numOfVer];
        int tmp = 1;
        for (int i = 0; i < numOfVer; i++) { //reset normal vertices
            Qnodes[i] = new Node(tmp++, Node.VerticeType.QNODE);
            Pnodes[i] = new Node(tmp++, Node.VerticeType.PNODE);
        }
        this.edges = new LinkedList<>();
        this.edgeCounter = 1;
        this.dRegularNum = degNum;
        //build the graph edges
        switch (typeOfEdgesCostructor) {
            case 1:
                BuildKDBipartiteGraph();
                break;
            case 2:
                BuildRandomBipartiteGraph();
                break;
        }
    }


    /*** getters ***/
    public int getNumOfVertex() {
        return numOfVertex;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public Node[] getQnodes() {
        return Qnodes;
    }

    public Node[] getPnodes() {
        return Pnodes;
    }

    public int getEdgeCounter() {
        return edgeCounter;
    }

    public int getDRegularNum() {
        return dRegularNum;
    }

}
