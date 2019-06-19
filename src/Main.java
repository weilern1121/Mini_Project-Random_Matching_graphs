import java.util.Random;

public class Main {

    private static void Build2DBipartiteGraph(BipartiteGraph graph) {
        int num = graph.getNumOfVertex() - 2;
        for (int i = 0; i < num; i += 2) {
            graph.addEdge(i, (i + 1));
            graph.addEdge(i, (i + 3) % num);
        }
    }

    private static void Build3DBipartiteGraph(BipartiteGraph graph) {
        int num = graph.getNumOfVertex() - 2;
        for (int i = 0; i < num; i += 2) {
            graph.addEdge(i, (i + 1));
            graph.addEdge(i, (i + 3) % num);
            graph.addEdge(i, (i + 5) % num);
        }
    }

    private static void printMatrixGraph(int[][] adjListArray) {
        int numOfVertex = adjListArray[0].length;
        System.out.println("The Graph:");
        for (int i = 0; i < numOfVertex; i++)
            System.out.print("V" + i + "   ");
        System.out.println(" ");
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = -1; j < numOfVertex; j++) {
                if (j == -1) {
                    if (i > 9)
                        System.out.print("V" + i + "  ");
                    else
                        System.out.print("V" + i + "   ");
                } else
                    System.out.print(adjListArray[i][j] + "    ");
                if (j > 9)
                    System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    private static void printMatrixGraph(BipartiteGraph g) {
        int numOfVertex = g.getAdjListArray().length;
        int dregNum = g.getDRegularNum();
        int s = numOfVertex - 2, t = numOfVertex - 1;
        System.out.println("The Graph:");
        System.out.print("    ");
        for (int i = 0; i < numOfVertex; i++) {
            if (i == s)
                System.out.print(" S    ");
            else if (i == t)
                System.out.print("T");
            else
                System.out.print("V" + i + "   ");
        }
        System.out.println("    ");
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = -1; j < numOfVertex; j++) {
                if (j == -1) {
                    if (i == s)
                        System.out.print(" S   ");
                    else if (i == t)
                        System.out.print(" T   ");
                    else {
                        if (i > 9)
                            System.out.print("V" + i + "  ");
                        else
                            System.out.print("V" + i + "   ");
                    }
                } else {
                    if (g.getAdjListArray()[i].getEdges().contains(j)) {
                        if (i == s || i == t || j == s || j == t)
                            System.out.print(dregNum + "    ");
                        else
                            System.out.print(1 + "    ");
                    } else
                        System.out.print(0 + "    ");

                }
            }
            System.out.println(" ");
        }
    }

    private static void printVNeighborMatrix(BipartiteGraph g) {
        int numOfVertex = g.getNumOfVertex();
        System.out.println("The Neighbor Graph:");
        for (int i = 0; i < numOfVertex; i++) {
            if (i == numOfVertex - 2)
                System.out.print("S:\t");
            else if (i == numOfVertex - 1)
                System.out.print("T:\t");
            else
                System.out.print("V" + i + ":\t");
            g.printEdges(i);
        }
    }

    private static int randomStepToNeighbor(BipartiteGraph graph, int from) {
        if (from > graph.getNumOfVertex() - 1)
            throw new IllegalArgumentException("ERROR - randomStep : from index illegal!");
        Random r = new Random();
        int tmp = r.nextInt(graph.getDRegularNum()); //random a number between 0 to d-1
        return graph.getAdjListArray()[from].getEdges().get(tmp);
    }

    private static int randomStepToSomeoneRandomaly(BipartiteGraph graph, int from) {
        if (from > graph.getNumOfVertex() - 1)
            throw new IllegalArgumentException("ERROR - randomStep : from index illegal!");
        Random r = new Random();
        int tmp = r.nextInt(graph.getNumOfVertex()); //random a number between 0 to NumOfVertex+2
        if(tmp>=graph.getAdjListArray()[from].getEdges().size()) //if true - random to not neighbor
            return -1;
        return graph.getAdjListArray()[from].getEdges().get(tmp);
    }

    public static void main(String[] args) {

        BipartiteGraph graph = new BipartiteGraph(10, 3);
        Build3DBipartiteGraph(graph);
        System.out.println("\n\n\n3d graph:");
        printMatrixGraph(graph);
        System.out.println("\nnum of edges: " + graph.getEdges().size() + "\n\n");
        printVNeighborMatrix(graph);

        graph.makeDirectedGraph();
        System.out.println("\n\n\n3d graph:");
        printMatrixGraph(graph);
        System.out.println("\nnum of edges: " + graph.getEdges().size() + "\n\n");
        printVNeighborMatrix(graph);


//        BipartiteGraph g2 = graph.copyOFGraph(graph);


        graph.mergeNodesToSuper(0,1);
        System.out.println("\n\n\n3d graph:");
        printMatrixGraph(graph);
        System.out.println("\nnum of edges: " + graph.getEdges().size() + "\n\n");
        printVNeighborMatrix(graph);



        graph.mergeNodesToSuper(2,3);
        System.out.println("\n\n\n3d graph:");
        printMatrixGraph(graph);
        System.out.println("\nnum of edges: " + graph.getEdges().size() + "\n\n");
        printVNeighborMatrix(graph);


        graph.connectSnTNodes();
        System.out.println("\n\n\n3d graph:");
        printMatrixGraph(graph);
        System.out.println("\nnum of edges: " + graph.getEdges().size() + "\n\n");
        printVNeighborMatrix(graph);

/*
        System.out.println("\n\n\n--------------------------------\n\n");
        System.out.println("\ng2:");
        printMatrixGraph(g2);
        System.out.println("\nnum of edges: " + g2.getEdges().size() + "\n\n");
        printVNeighborMatrix(g2);
        */



/*
        //test randomStep funcs
        int counter1=0, counter3=0, counter5=0;
        for(int i=0; i<100; i++){
            switch (randomStepToNeighbor(graph,3)){
                case 0:
                    counter1++;
                    break;
                case 2:
                    counter3++;
                    break;
                case 8:
                    counter5++;
                    break;
            }
        }
        System.out.println("counter1="+counter1+"\tcounter3= "+counter3+"\tcounter5= "+counter5+"\n\n");

        counter1=0;
        counter3=0;
        counter5=0;
        for(int i=0; i<100; i++){
            switch (randomStepToSomeoneRandomaly(graph,3)){
                case 0:
                    counter1++;
                    break;
                case 2:
                    counter3++;
                    break;
                case 8:
                    counter5++;
                    break;
            }
        }
        System.out.println("counter1="+counter1+"\tcounter3= "+counter3+"\tcounter5= "+counter5);
*/



    }
}
