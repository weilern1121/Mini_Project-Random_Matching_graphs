import java.util.*;

import static java.util.Collections.shuffle;

public class BipartiteGraph {

    public enum VerticeType {
        NORMAL, SNODE, TNODE;
    }

    /***
     * node S : numOfVertex - 2
     * node T : numOfVertex - 1
     ***/

    private int numOfVertex;
    private Node[] Qnodes;
    private Node[] Pnodes;
    private LinkedList<Edge> edges;
    private int edgeCounter; //used as identifier to edges
    private int dRegularNum;
//    private int[] numOfNeighbors;
//    private int[][] vNeighborsArray;

    /*** constructors ***/
    private void Build2DBipartiteGraph() {
        int num = this.numOfVertex;
        for (int i = 0; i < num; i++) {
            Edge e1 = new Edge(Pnodes[i], Qnodes[i], edgeCounter++);
            Edge e2 = new Edge(Pnodes[i], Qnodes[((i + 1) % num)], edgeCounter++);
            Pnodes[i].addEdge(e1);
            Pnodes[i].addEdge(e2);
            Qnodes[i].addEdge(e1);
            Qnodes[((i + 1) % num)].addEdge(e2);
            this.edges.add(e1);
            this.edges.add(e2);
        }
    }

    private void Build3DBipartiteGraph() {
        int num = this.numOfVertex;
        for (int i = 0; i < num; i++) {
            Edge e1 = new Edge(Pnodes[i], Qnodes[i], edgeCounter++);
            Edge e2 = new Edge(Pnodes[i], Qnodes[(i + 1) % num], edgeCounter++);
            Edge e3 = new Edge(Pnodes[i], Qnodes[(i + 2) % num], edgeCounter++);
            Pnodes[i].addEdge(e1);
            Pnodes[i].addEdge(e2);
            Pnodes[i].addEdge(e3);
            Qnodes[i].addEdge(e1);
            Qnodes[(i + 1) % num].addEdge(e2);
            Qnodes[(i + 2) % num].addEdge(e3);
            this.edges.add(e1);
            this.edges.add(e2);
            this.edges.add(e3);
        }
    }

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
        int counter = 0;
        for (int i = 0; i < numOfVertex; i++)
            for (int j = 0; j < dRegularNum; j++)
                lst.add(i);

//        System.out.println(lst);
        boolean flag = false;
        while (!flag) {
            shuffle(lst);
            counter++;
            if (ValidListToCreate(lst, dRegularNum))
                flag = true;
        }
//        System.out.println("counter= " + counter);
       /* System.out.println("\n\n----------------- \ncounter= " + counter);

        for (int i = 0; i < lst.size(); i++) {
            if (i % dRegularNum == 0)
                System.out.println(" ");
            System.out.print(lst.get(i) + ",");
        }
        */
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
    /*
    public Node[] getAdjListArray() {
        return adjListArray;
    }
    */

    /*** setters ***/
    public void setdRegularNum(int dRegularNum) {
        this.dRegularNum = dRegularNum;
    }

    public void setEdgeCounter(int edgeCounter) {
        this.edgeCounter = edgeCounter;
    }


    /*** methods ***/
    /*public boolean contains(int from, int to) {
        return adjListArray[from].getEdges().contains(to);
    }*/

  /*  public void addEdge(Integer a, Integer b) {
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

    }*/

  /*  public void removeEdge(Edge e) {
        adjListArray[e.getV_from()].getEdges().remove(e.getV_to());
        adjListArray[e.getV_to()].getEdges().remove(e.getV_from());
        edges.remove(e);
    }
*/

 /*   public void printEdges(int index) {

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

*/
    public int getDRegularNum() {
        return dRegularNum;
    }
/*
    public void connectSnTNodes() {
        //this func connect S node to all nodes that are not superNodes (from P)
        // andd     connect all nodes that are not superNodes (from Q) to T
        int s = numOfVertex - 2;
        int t = numOfVertex - 1;
        for (int i = 0; i < numOfVertex - 2; i++) {
            if (i % 2 == 0 && !adjListArray[i].isSuperNode()) { //if even and not superNode -> add d times edges s->v
                for (int j = 0; j < this.dRegularNum; j++) {
                    InitEdge e = new InitEdge(s, i, edgeCounter++);
                    edges.add(e);
                    this.adjListArray[s].getEdges().add(i);
                }
            } else {//if odd and not superNode -> add d times edges s->v
                if (!adjListArray[i].isSuperNode()) {
                    for (int j = 0; j < this.dRegularNum; j++) {
                        InitEdge e = new InitEdge(i, t, edgeCounter++);
                        edges.add(e);
                        this.adjListArray[i].getEdges().add(t);
                    }
                }
            }
        }
    }


    //TODO - NEED TO REPLACE THIS FUNC IN RUNALGORITHM LATER
    //TODO - THIS FUNC MAKE THE FROM NODE TO THE SUPERNODE! NOT CREATING ANOTHER COPY!!
    //have to call this func AFTER making the graph directed!
    public void mergeNodesToSuper(int from, int to) {
        //VALIDATION CHECKS
        if(from<0 || from>numOfVertex-2 || to<0||to>numOfVertex-2)
            throw new IllegalArgumentException("ERROR - mergeNodesToSuper: illegal indexes!");
        if(from%2!=0 || to%2==0)
            throw new IllegalStateException("Error - mergeNodesToSuper: illegal indexes!(2)");
        if(!adjListArray[from].getEdges().contains(to))
            throw new IllegalStateException("Error - mergeNodesToSuper: can't merge nodes that are not already connected!!");

        adjListArray[from].setSuperNode(true);
        adjListArray[to].setSuperNode(true);
        //UPDATE ALL OTHER NODES
        int counter = 0, num=numOfVertex - 2; //used to make sure that exactly (d-1) nodes changed their edge list
        for (int i = 0; i < num; i++) {
            if (i != from && i != to && adjListArray[i].changeSuperInList(to, from))
                counter++;
        }
        if(counter!= dRegularNum-1)
            throw new IllegalStateException("Error - mergeNodesToSuper: wrong number of nodes' edges updating!");
        //TODO - NOT SURE THAT NEED TO UPDATE EDGES HERE

        //UPDATE FROM AND TO NODE'S LISTS
        adjListArray[to].getEdges().removeFirstOccurrence(from);
        adjListArray[from].getEdges().removeFirstOccurrence(to);
        adjListArray[to].resetEdges();
        adjListArray[from].addEdgesList(adjListArray[to].getEdges());

        for (int i = 0; i < num; i++)
            adjListArray[i].fixEdgesList();
    }

    //TODO - NEED TO REPLACE THIS FUNC IN RUNALGORITHM LATER
    public BipartiteGraph copyOfGraph(BipartiteGraph src){
        BipartiteGraph output = new BipartiteGraph(src.numOfVertex-2,src.getDRegularNum());
        for (InitEdge e:src.getEdges()) {
            output.getAdjListArray()[e.getV_from()].getEdges().add(e.getV_to());
            output.getAdjListArray()[e.getV_to()].getEdges().add(e.getV_from());
            InitEdge tmp=new InitEdge(e.getV_from(),e.getV_to(),e.getV_num());
            output.edges.add(tmp);
        }
        for(int i=0; i<src.numOfVertex-2;i++){
            output.adjListArray[i].setSuperNode(src.adjListArray[i].isSuperNode());
            output.adjListArray[i].setIdNum(src.adjListArray[i].getIdNum());
        }
        output.setdRegularNum(src.dRegularNum);
        output.setEdgeCounter(src.edgeCounter);

        return output;
    }

    //TODO - NEED TO REPLACE THIS FUNC IN RUNALGORITHM LATER
    public void makeDirectedGraph(){
        for(int i=1; i<numOfVertex-2; i+=2)
            adjListArray[i].resetEdges();
    }

*/
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
