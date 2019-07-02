import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {


    /*
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

    */
    public static void main(String[] args) {

        //TODO - input for later
        /*Scanner in = new Scanner(System.in);
        System.out.println("Enter num of vertices");
        int v=in.nextInt();
        System.out.println("Enter d-regular number");
        int num=in.nextInt();
        if(num>v)
            throw new IllegalArgumentException("ERROR - in BipartiteGraph d-regular number < v/2 !!");
        BipartiteGraph g = new BipartiteGraph(v, num);
        */




        BipartiteGraph g = new BipartiteGraph(5, 2);
        g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));

        Edge check1 = g.getEdges().get(0);
        Edge check2 = g.getEdges().get(1);
        Edge check3 = g.getEdges().get(2);
        int counter1 = 0, counter2 = 0, counter3 = 0;
        for (int i = 0; i < 50; i++) {
//            System.out.println("---------------------------------");
//            System.out.println("\n\n\n");
            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            LinkedList<Edge> perfectMatch =run.PerfectMatch();

            /*perfectMatch.forEach((tmp) -> {
                System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum());
            });*/

            if (perfectMatch.contains(check1))
                counter1++;
            if (perfectMatch.contains(check2))
                counter2++;
            if (perfectMatch.contains(check3))
                counter3++;
        }
        System.out.println("---------------------------------");
        System.out.println("check1: " + check1.getV_from().getIdNum() + "->" + check1.getV_to().getIdNum());
        System.out.println("check2: " + check2.getV_from().getIdNum() + "->" + check2.getV_to().getIdNum());
        System.out.println("check3: " + check3.getV_from().getIdNum() + "->" + check3.getV_to().getIdNum());
        System.out.println("counter1: " + counter1 + " ; counter2: " + counter2 + " ; counter3: " + counter3+" ; TOTAL: "+(counter1+counter2));



       //TODO - check removeLOOP
        /*
        BipartiteGraph g = new BipartiteGraph(5, 3);
        LinkedList<Edge> gEdges = g.getEdges();
        LinkedList<Edge> edges = new LinkedList<>();
        int num = g.getNumOfVertex();
        edges.add(gEdges.get(0));
        edges.add(gEdges.get(6));
        edges.add(gEdges.get(3));
        edges.add(gEdges.get(9));
        edges.add(gEdges.get(10));
        edges.add(gEdges.get(12));
        edges.add(gEdges.get(9));
        edges.add(gEdges.get(3));
        System.out.println("BEFORE:");
        edges.forEach((tmp) -> {
            System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum());
        });
        RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
        edges = run.RemoveLoops(edges);

        System.out.println("AFTER:");
        edges.forEach((tmp) -> {
            System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum());
        });
    */


    }


}
