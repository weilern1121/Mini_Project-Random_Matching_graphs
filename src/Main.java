import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
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
    private static void Test1(int numOfVer, int iterNum) {
        for (int k = 0; k < 9; k++) {
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

            }
//        g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));

            int[] faults = new int[iterNum];
            for (int i = 0; i < iterNum; i++) {
                BipartiteGraph g = new BipartiteGraph(numOfVer, degnum, 1);
                RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
                faults[i] = run.PerfectMatch();

            }

            double tmp = 0;
            for (int i = 0; i < iterNum; i++)
                tmp += faults[i];
            System.out.println("fault avg. for numofVertices:" + numOfVer + " and dregNum: " + degnum + " = " + tmp / iterNum);
        }
    }

    private static void Test2() {
        BipartiteGraph g = null;

        int i, k=-1;
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
            for (int j = 2; j < 6; j+=3) {
                System.out.println("-------  k= " + k + " , j= " + j + "  --------------");
                g = new BipartiteGraph(k, j, 2);
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
                else
                    System.out.println("OK");
            }
        }
    }

    private static void Test3(int numOfVer, int iterNum) {
        BipartiteGraph g;
        for (int k = 0; k < 3; k++) {
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

            }
//        g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));

            int[] faults = new int[iterNum];
            for (int i = 0; i < iterNum; i++) {
                g = new BipartiteGraph(numOfVer, degnum, 2);
                RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
                faults[i] = run.PerfectMatch();

            }

            double tmp = 0;
            for (int i = 0; i < iterNum; i++)
                tmp += faults[i];
            System.out.println("fault avg. for numofVertices:" + numOfVer + " and dregNum: " + degnum + " = " + tmp / iterNum);
        }
    }





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

//        Test1(50, 10);
//        Test2();

        Test3(5,50);
        System.out.println("------------");
        Test3(10,50);
        System.out.println("------------");
        Test3(20,50);
        System.out.println("------------");
        Test3(50,50);
        System.out.println("------------");



        /*for (int i = 0; i < 10; i++) {
            BipartiteGraph g = new BipartiteGraph(5, 2, 2);
            g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));
            System.out.println("-----------");
            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            System.out.println(run.PerfectMatch());
        }*/


        //TODO - try to shuffle

//        System.out.println("\n\n"+lst);


    }


}
