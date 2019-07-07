import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static void Test1(int numOfVer, int iterNum, int degnum) {
        double sum = 0;
        for (int i = 0; i < iterNum; i++) {
            BipartiteGraph g = new BipartiteGraph(numOfVer, degnum, 1);
            //check that the graph is d-regular
            ValidatConstructorTest(g);

            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            sum+= run.PerfectMatch();

        }
        System.out.println("fault avg. for numofVertices:" + numOfVer + " and dregNum: " + degnum + " = " + sum / iterNum);
    }

    //private func that validate that the graphs are as supposed to be
    private static void ValidatConstructorTest(BipartiteGraph g) {
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
    }

    //print avg. number of faults per run
    private static void Test2(int numOfVer, int iterNum, int degnum) {
        BipartiteGraph g;
        double sum = 0;
        for (int i = 0; i < iterNum; i++) {
            g = new BipartiteGraph(numOfVer, degnum, 2);
            //check that the graph is d-regular
            ValidatConstructorTest(g);
            //run the algoritjm and resotore number of faults
            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            sum += run.PerfectMatch();
        }
        System.out.println("fault avg. for numofVertices:" + numOfVer + " and dregNum: " + degnum + " = " + sum / iterNum);
    }

    //print the perfect matches - both random and explicit
    private static void Test3(int numOfVer, int iterNum, int degnum, int typeOfEdgesCostructor) {
        for (int i = 0; i < iterNum; i++) {
            BipartiteGraph g = new BipartiteGraph(numOfVer, degnum, typeOfEdgesCostructor);
            //check that the graph is d-regular
            ValidatConstructorTest(g);
            //print the graph
            System.out.println("BipartiteGraph:");
            g.getEdges().forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));
            //run the algorithm
            RunAlgorithm run = new RunAlgorithm(g.getPnodes(), g.getQnodes(), g.getEdgeCounter());
            LinkedList<Edge> pmatch = run.getPerfectMatch();
            //print result
            System.out.println("\nThe Perfect Match:");
            pmatch.forEach((tmp) -> System.out.println(tmp.getV_from().getIdNum() + "->" + tmp.getV_to().getIdNum()));
            System.out.println("\n------------------------\n");
        }
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Test number:\n1) Test the average fault in finding augmenting path of the Algorithm with Explicit constructed BipartiteGraph \n" +
                "2) Test the average fault in finding augmenting path of the  Algorithm with Random constructed BipartiteGraph\n" +
                "3) Print the Perfect match (Explicit constructed)\n" +
                "4) Print the Perfect match (Random constructed)");
        int type = in.nextInt();
        System.out.println("Enter num of vertices (per group of nodes)");
        int v = in.nextInt();
        System.out.println("Enter d-regular number");
        if (type == 2 || type == 4)
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
                Test3(v, run, dnum, 1);
                break;
            case 4:
                Test3(v, run, dnum, 2);
                break;
        }

    }


}
