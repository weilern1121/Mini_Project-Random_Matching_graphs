import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Collections.shuffle;

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
    private static void Test1(int numOfVer, int iterNum, int degnum) {
       /* for (int k = 0; k < 9; k++) {
            int degnum = 0;
            switch (k) {
                case 0:
                    degnum = 2;
                    break;
                case 1:
                    degnum = 3;
                    break;
                case 2:
                    degnum = 5;
                    break;
                case 3:
                    degnum = 7;
                    break;
                case 4:
                    degnum = 10;
                    break;
                case 5:
                    degnum = 15;
                    break;
                case 6:
                    degnum = 20;
                    break;
                case 7:
                    degnum = 40;
                    break;
                case 8:
                    degnum = 50;
                    break;

            }*/
//        g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));

        int[] faults = new int[iterNum];
        for (int i = 0; i < iterNum; i++) {
            BipartiteGraph g = new BipartiteGraph(numOfVer, degnum, 1);
            //check that the graph is d-regular
            ValidatConstructorTest(g);

            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            faults[i] = run.PerfectMatch();

        }

        double tmp = 0;
        for (int i = 0; i < iterNum; i++)
            tmp += faults[i];
        System.out.println("fault avg. for numofVertices:" + numOfVer + " and dregNum: " + degnum + " = " + tmp / iterNum);
//        }
    }

    private static void ValidatConstructorTest(BipartiteGraph g) {
        /*int i, k=-1;
        for (i=0; i<6; i++) {
            switch (i) {
                case 0:
                    k = 5;
                    break;
                case 1:
                    k = 10;
                    break;
                case 2:
                    k = 20;
                    break;
                case 3:
                    k = 40;
                    break;
                case 4:
                    k = 50;
                    break;
                case 5:
                    k = 100;
                    break;
            }
            for (int j = 2; j < 6; j+=3) {*/
//                System.out.println("-------  k= " + k + " , j= " + j + "  --------------");
//                g = new BipartiteGraph(k, j, 2);
//                g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));


        LinkedList<Edge> tmp = g.getEdges();
        int num = 2 * g.getNumOfVertex() + 1;
        int[] counters = new int[num];
        for (Edge e : tmp) {
            counters[e.getV_from().getIdNum()]++;
            counters[e.getV_to().getIdNum()]++;
        }
        boolean flag = false;
        int num2 = g.getDRegularNum();
        int n;
        for (n = 1; n < num && !flag; n++) {
            if (counters[n] != num2)
                flag = true;
        }
        if (flag)
            System.out.println("ERROR in building the graph in i=" + n);
//                else
//                    System.out.println("OK");
//            }
//        }
    }

    private static void Test2(int numOfVer, int iterNum, int degnum) {
        BipartiteGraph g;
//        for (int k = 0; k < 3; k++) {
            /*int degnum = 0;
            switch (k) {
                case 0:
                    degnum = 2;
                    break;
                case 1:
                    degnum = 3;
                    break;
                case 2:
                    degnum = 5;
                    break;
                case 3:
                    degnum = 7;
                    break;
                case 4:
                    degnum = 10;
                    break;
                case 5:
                    degnum = 15;
                    break;
                case 6:
                    degnum = 20;
                    break;
                case 7:
                    degnum = 40;
                    break;
                case 8:
                    degnum = 50;
                    break;

            }*/
//        g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));

        int[] faults = new int[iterNum];
        for (int i = 0; i < iterNum; i++) {
            g = new BipartiteGraph(numOfVer, degnum, 2);
            //check that the graph is d-regular
            ValidatConstructorTest(g);

            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            faults[i] = run.PerfectMatch();

        }

        double tmp = 0;
        for (int i = 0; i < iterNum; i++)
            tmp += faults[i];
        System.out.println("fault avg. for numofVertices:" + numOfVer + " and dregNum: " + degnum + " = " + tmp / iterNum);
//        }
    }

    private static void Test3(int numOfVer, int iterNum, int degnum) {

        for(int i=0; i<iterNum; i++) {
            BipartiteGraph g = new BipartiteGraph(numOfVer, degnum, 1);
            //check that the graph is d-regular
            ValidatConstructorTest(g);
            //print the graph
            System.out.println("BipartiteGraph:");
            g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));
            //run the algorithm
            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            LinkedList<Edge> pmatch = run.getPerfectMatch();
            //print result
            System.out.println("\n The Perfect Match:");
            pmatch.forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));
            System.out.println("\n------------------------\n");
        }
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Test number:\n1) Test the average fault in finding path of the Algorithm with Explicit constructed BipartiteGraph \n" +
                "2) Test  the average fault in finding path of the  Algorithm with Random constructed BipartiteGraph\n" +
                "3) Print the Perfect match");
        int type = in.nextInt();
        System.out.println("Enter num of vertices");
        int v = in.nextInt();
        System.out.println("Enter d-regular number");
        if (type == 2)
            System.out.println("(NOTE - for fluently running please enter d-regular number <6)");
        int dnum = in.nextInt();
        if (dnum > v) {
            throw new IllegalArgumentException("ERROR - in BipartiteGraph d-regular number < v/2 !!");
        }
        System.out.println("Enter num of runs");
        int run = in.nextInt();

        switch (type) {
            case 1:
                Test1(v, run, dnum);
                break;
            case 2:
                Test2(v, run, dnum);
                break;
            case 3:
                Test3(v, run, dnum);
                break;


        }

    }


}
