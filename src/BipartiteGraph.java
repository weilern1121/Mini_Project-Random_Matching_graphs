import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class BipartiteGraph {

    public enum VerticeType {
        NORMAL, SNODE, TNODE;
    }

    /***
     * node S : numOfVertex - 2
     * node T : numOfVertex - 1
     ***/

    private int numOfVertex;
    private Node[] adjListArray;
    private LinkedList<Edge> edges;
    private int edgeCounter; //used as identifier to edges
    private int dRegularNum; //TODO - might use this
//    private int[] numOfNeighbors;
//    private int[][] vNeighborsArray;

    /*** constructors ***/
    BipartiteGraph(int numOfVer, int dregNum) {
        if (dregNum >= numOfVer)
            throw new IllegalArgumentException("ERROR - d-regular num must be smaller then number of vertices!");
        this.numOfVertex = numOfVer + 2;
        this.adjListArray = new Node[numOfVer + 2];
        for (int i = 0; i < numOfVer; i++) //reset normal vertices
            adjListArray[i] = new Node(i);
        adjListArray[numOfVer] = new Node(100, Node.VerticeType.SNODE); //set s node
        adjListArray[numOfVer + 1] = new Node(200, Node.VerticeType.TNODE); //set t node
        this.edges = new LinkedList<>();
        this.edgeCounter = 1;
        this.dRegularNum = dregNum;
    }



    /*
    BipartiteGraph(int V, int regularNum) {
        if (V % 2 != 0)//validation check
            throw new IllegalArgumentException("illegal number of vertex ->should be even!");
        this.numOfVertex = V;
        this.edgeCounter = 1;
        // define the size of array as
        // number of vertices
        this.adjListArray = new int[V][V];
        this.numOfNeighbors = new int[V];
        // Create a new adjacency matrix
        // reset the new value
        for (int i = 0; i < V; i++) {
            this.numOfNeighbors[i] = 0;
            for (int j = 0; j < V; j++)
                this.adjListArray[i][j] = 0;
        }
        edges = new LinkedList();
        this.dRegularNum = regularNum;
        this.vNeighborsArray = new int[V][regularNum];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < regularNum; j++)
                this.vNeighborsArray[i][j] = 0;
    }

    BipartiteGraph(int[][] adjListArray, int regularNum) {
        int vNum = adjListArray[0].length;
        if (vNum % 2 != 0)//validation check
            throw new IllegalArgumentException("illegal number of vertex ->should be even!");
        this.numOfVertex = vNum;
        this.edgeCounter = 0;
        // define the size of array as
        // number of vertices
        this.adjListArray = adjListArray;
        this.numOfNeighbors = new int[vNum];
        edges = new LinkedList();
        // reset the new value
        for (int i = 0; i < vNum; i += 2) { //skip odd index ->because BipartiteGraph
            int counter = 0;
            for (int j = 0; j < vNum; j++) {
                if (adjListArray[i][j] == 1) {
                    counter++;
                    this.edges.add(new Edge(i, j, this.edgeCounter));
                    this.edgeCounter++;
                }
            }
            this.numOfNeighbors[i] = counter;
            this.dRegularNum = counter;
        }
        this.dRegularNum = regularNum;
        this.vNeighborsArray = new int[vNum][regularNum];
        fillVNeighborArr();

    }

    */

    /*** getters ***/
    public int getNumOfVertex() {
        return numOfVertex;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }


    public Node[] getAdjListArray() {
        return adjListArray;
    }


    /*** methods ***/
    public boolean contains(int from, int to) {
        return adjListArray[from].getEdges().contains(to);
    }

    public void addEdge(Integer a, Integer b) {
        if (a % 2 == 0 && b % 2 == 0)
            throw new IllegalArgumentException("ERROR - addEdge - both vertex index are EVEN!");
        if (a % 2 != 0 && b % 2 != 0)
            throw new IllegalArgumentException("ERROR - addEdge - both vertex index are ODDS!");
        //TODO - maybe need to check that a and b don't have already d-regular neighbors
        Edge e = new Edge(a, b, edgeCounter);
        edgeCounter++; //to unsure unique edge number
        adjListArray[a].getEdges().add(b);
        adjListArray[b].getEdges().add(a);
//        numOfNeighbors[a]++;
//        numOfNeighbors[b]++;
        edges.add(e);

    }

    public void removeEdge(Edge e) {
        adjListArray[e.getV_from()].getEdges().remove(e.getV_to());
        adjListArray[e.getV_to()].getEdges().remove(e.getV_from());
        edges.remove(e);
    }


    public void printEdges(int index) {

        adjListArray[index].getEdges().forEach((tmp) -> {
            if(tmp == numOfVertex-1)
                System.out.print("T , ");
            else
                System.out.print(tmp + " , ");
        });
        System.out.println(" ");
    }

    public int getNumOfNeighbors(int a) {
        return adjListArray[a].getEdges().size();
    }

    public int getDRegularNum() {
        return dRegularNum;
    }

    public void connectSnTNodes() {
        //this func connect S node to all nodes that are not superNodes (from P)
        // andd     connect all nodes that are not superNodes (from Q) to T
        int s = numOfVertex - 2;
        int t = numOfVertex - 1;
        for (int i = 0; i < numOfVertex - 2; i++) {
            if (i % 2 == 0 && !adjListArray[i].isSuperNode()) { //if even and not superNode -> add d times edges s->v
                for (int j = 0; j < this.dRegularNum; j++) {
                    Edge e = new Edge(s, i, edgeCounter++);
                    edges.add(e);
                    this.adjListArray[s].getEdges().add(i);
                }
            } else {//if odd and not superNode -> add d times edges s->v
                if (!adjListArray[i].isSuperNode()) {
                    for (int j = 0; j < this.dRegularNum; j++) {
                        Edge e = new Edge(i, t, edgeCounter++);
                        edges.add(e);
                        this.adjListArray[i].getEdges().add(t);
                    }
                }
            }
        }
    }


    //have to call this func AFTER making the graph directed!
    public void mergeNodesToSuper(int from, int to) {
        //VALIDATION CHECKS
        if(from<0 || from>numOfVertex-2 || to<0||to>numOfVertex-2)
            throw new IllegalArgumentException("ERROR - mergeNodesToSuper: illegal indexes!");
        if(from%2!=0 || to%2==0)
            throw new IllegalStateException("Error - mergeNodesToSuper: illegal indexes!(2)");
        if(!adjListArray[from].getEdges().contains(to)||!adjListArray[to].getEdges().contains(from))
            throw new IllegalStateException("Error - mergeNodesToSuper: can't merge nodes that are not already connected!!");

        adjListArray[from].setSuperNode(true);
        adjListArray[to].setSuperNode(true);
        //UPDATE ALL OTHER NODES
        int counter = 0; //used to make sure that exactly (d-1) nodes changed their edge list
        for (int i = 0; i < numOfVertex - 2; i++) {
            if (i != from && i != to && adjListArray[i].changeSuperInList(to, from))
                counter++;
        }
        if(counter!= dRegularNum-1)
            throw new IllegalStateException("Error - mergeNodesToSuper: wrong number of nodes' edges updating!");
        //TODO - NOT SURE THAT NEED TO UPDATE EDGES HERE

        //UPDATE FROM AND TO NODE'S LISTS
        adjListArray[to].getEdges().removeFirstOccurrence(from);
        adjListArray[from].getEdges().removeFirstOccurrence(to);
        adjListArray[from].addEdgesList(adjListArray[to].getEdges());
        adjListArray[to].resetEdges();

    }


    /*
    public static void deepCopyGraph(BipartiteGraph from, BipartiteGraph to) {
        int max = from.numOfVertex;
        //copy the adjacency matrix
        for (int i = 0; i < max; i++) {
            to.getNumOfNeighbors()[i] = from.getNumOfNeighbors()[i];
            for (int j = 0; j < max; j++)
                to.getAdjListArray()[i][j] = from.getAdjListArray()[i][j];
        }
        //reset the to.edges list
        while (!to.getEdges().isEmpty())
            to.getEdges().pop();
        //copy the edges list
        for (Edge tmp : from.getEdges()) {
            int tmp_from = tmp.getV_from();
            int tmp_to = tmp.getV_to();
            to.getEdges().add(new Edge(tmp_from, tmp_to, tmp.getV_num()));
            to.getNumOfNeighbors()[tmp_from]++;
            to.getNumOfNeighbors()[tmp_to]++;
        }
        //copy the number of neighbors
        for (int i = 0; i < max; i++)
            to.getNumOfNeighbors()[i] = to.getNumOfNeighbors()[i] / 2;
        ;
    }



    private void fillVNeighborArr() {
        int index = 0;
        for (int i = 0; i < this.dRegularNum; i++) {
            for (int j = 0; j < this.dRegularNum; j++) {
                if (this.adjListArray[i][j] == 1)
                    this.vNeighborsArray[i][index++] = j;

            }
            if (index != this.dRegularNum)
                throw new IllegalStateException("Error - fillVNeighborArr : not legat" +
                        " input! index= " + index + "\tdNum= " + this.dRegularNum);
            index = 0;
        }
    }

    public int[][] getvNeighborsArray() {
        return this.vNeighborsArray;
    }
    public int[] getAdjListArray(int i) {return adjListArray[i]; }


    public int[] getNumOfNeighbors() {
        return numOfNeighbors;
    }


    public void sortEdges() {
        //sort the edges by weights
        Collections.sort(this.edges, Comparator.comparingInt(Edge::getV_num));
    }
    */

}
