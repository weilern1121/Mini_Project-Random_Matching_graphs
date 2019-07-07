import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RunAlgorithm {


    /***
     * NODE S -> P -> Q -> NODE T
     ***/

    /*** fields ***/
    private LinkedList<Edge> Mj;
    private LinkedList<Edge> p;
    private int NodesNumber;
    private Node[] PNodes;
    private Node[] QNodes;
    private Node SNodes;
    private Node TNodes;
    private int edgesCounter;

    /*** constructors ***/
    public RunAlgorithm(Node[] PNodes, Node[] QNodes, int ecounter) {
        this.Mj = new LinkedList<>();
        this.p = new LinkedList<>();
        this.PNodes = PNodes;
        this.QNodes = QNodes;
        this.edgesCounter = ecounter;
        this.NodesNumber = PNodes.length;
    }

    /*** methods ***/
    //to get the perfect match this func get called via getPerfectMatch
    public int PerfectMatch() {
        int NodesNumber = PNodes.length;
        int j = 0, bj,pathErrCounter=0;
        boolean flag = false;
        Mj = new LinkedList<Edge>();
        while (j< NodesNumber) {
            bj = 2 * (2 + (NodesNumber / (NodesNumber - j)));
            TransformGraphH();
            flag = false;
            while (!flag) {
                p = new LinkedList<Edge>();
                if (Truncated_Walk(SNodes, bj))
                    flag = true;
                else
                    pathErrCounter++;
            }
            SetNewMj(p);
            j++;
            //used for debugging
            if(Checkpoint()){
                int k=0;
            }
        }
        if(validationCheck())
            return pathErrCounter;
        //got here - something wrong with the perfect match
        System.out.println("ERROR in PerfectMatch!");
        return -1;
    }


    private boolean Truncated_Walk(Node startNode, int bj) {
        Edge e;
        if (startNode.getVerType() == Node.VerticeType.TNODE)
            return true;
        if (bj == 0)
            return false;

        e = Sample_Out_Edge(startNode);
        p.add(e);
        if(startNode.isSuperNode() && startNode.getVerType()== Node.VerticeType.QNODE)
            return Truncated_Walk(e.getV_from(), (bj - 1));//if true- go in super node to the pnode
        return Truncated_Walk(e.getV_to(), (bj - 1));

    }

    private Edge Sample_Out_Edge(Node startNode) {
        Edge e;
        Random r = new Random();
        if (startNode.isSuperNode() && startNode.getVerType() == Node.VerticeType.QNODE)
            return startNode.getSuperEdge();
        else {
            int k = r.nextInt(startNode.getTransEdges().size()); //random a number between 0 to d-1
            e = (startNode.getTransEdges()).get(k);
            return e;
        }
    }

    private void TransformGraphH() {
        SNodes = new Node(10 * NodesNumber, Node.VerticeType.SNODE);
        TNodes = new Node(11 * NodesNumber, Node.VerticeType.TNODE);
        OrientEdges();
        SuperST();
    }

    private void OrientEdges() {
        Node pp, qq;
        for (int i = 0; i < NodesNumber; i++) {
            pp = PNodes[i];
            qq = QNodes[i];
            pp.setSuperNode(false, null);
            qq.setSuperNode(false, null);
            pp.setTransEdge(pp.getEdges());
            qq.setTransEdge(new LinkedList<Edge>());
        }
    }


    private void SuperST() {
        Node pp, qq;
        Edge ee;
        boolean superNode;
        LinkedList<Edge> TransEdges;
        int regNum = PNodes[0].getEdges().size();
        for (int i = 0; i < NodesNumber; i++) {
            pp = PNodes[i];
            superNode = false;
            TransEdges = pp.getEdges();
            for (int j = 0; j < TransEdges.size() && !superNode; j++) {
                ee = TransEdges.get(j);
                if (EdgeContain(ee,Mj)) {
                    ee.getV_from().setSuperNode(true, ee);
                    ee.getV_to().setSuperNode(true, ee);

                    pp.removeTransEdge(ee);
                    superNode = true;
                }
            }

            if (!superNode) {
                for(int j=0; j<regNum; j++)
                    SNodes.addTransEdge(new Edge(SNodes, pp,edgesCounter++));
            }
        }

        for (int k = 0; k < NodesNumber; k++) {
            qq = QNodes[k];
            if (!qq.isSuperNode()) {
                for(int j=0; j<regNum; j++)
                    qq.addTransEdge(new Edge(qq, TNodes,edgesCounter++));
            }
        }
    }


    private void SetNewMj(LinkedList<Edge> newPath) {
        //remove end edges from newPath
        newPath.removeIf(e -> (e.getV_from().getVerType() == Node.VerticeType.SNODE ||
                e.getV_to().getVerType() == Node.VerticeType.TNODE));

        //remove loops from newPath
        newPath=RemoveLoops(newPath);

        //make common list
        LinkedList<Edge> commonEdges=new LinkedList<>();
        for (Edge e:newPath) {
            if(EdgeContain(e,Mj))
                commonEdges.add(e);
        }

        //remove common from both lists
        Mj.removeIf(e->EdgeContain(e,commonEdges));
        newPath.removeIf(e->EdgeContain(e,commonEdges));

        //concat lists into Mj
        Mj.addAll(newPath);
    }

    private boolean EdgeContain(Edge ee, LinkedList<Edge> lst) {
        Edge currentE;
        for (int i = 0; i < lst.size(); i++) {
            currentE = lst.get(i);
            if ((currentE.getV_to() == ee.getV_to()) &&
                    (currentE.getV_from() == ee.getV_from()))
                return true;
            else if ((currentE.getV_to() == ee.getV_from()) &&
                    (currentE.getV_from() == ee.getV_to()))
                return true;
        }
        //got here no match for an edge, return false;
        return false;
    }

    private int EdgeLoopContain( Edge ee, LinkedList<Edge> lst, int from) {
        int i=from;
        Edge currentE;
        for (; i < lst.size(); i++) {
            currentE = lst.get(i);
            if ((currentE.getV_to() == ee.getV_to()) &&
                    (currentE.getV_from() == ee.getV_from()))
                return i;
            else if ((currentE.getV_to() == ee.getV_from()) &&
                    (currentE.getV_from() == ee.getV_to()))
                return i;
        }
        //got here no match for an edge, return false;
        return -1;
    }

    private LinkedList<Edge> RemoveLoops(LinkedList<Edge> lst){
        LinkedList<Edge> output = new LinkedList<>();
        Edge currentE;
        int tmp;
        for(int i=0; i<lst.size();){
            currentE = lst.get(i);
            tmp=EdgeLoopContain(currentE,lst,i+1);
            if(tmp ==-1) { //if -1 -> only show , no loop
                output.add(currentE);
                i++;
            }
            else //there is a loop between i and tmp ->skip it
                i=tmp;
        }
        return output;
    }

    private boolean Checkpoint(){
        LinkedList<Integer> lst= new LinkedList<>();
        for (Edge e :Mj) {
            if(lst.contains(e.getV_from().getIdNum()))
                return true;
            else
                lst.add(e.getV_from().getIdNum());
        }
        return false;
    }

    private boolean validationCheck(){
        AtomicInteger evenCounter= new AtomicInteger();
        AtomicInteger oddCounter= new AtomicInteger();
        Mj.forEach((tmp) -> {
            evenCounter.addAndGet(tmp.getV_from().getIdNum());
            oddCounter.addAndGet(tmp.getV_to().getIdNum());
        });
        int num=NodesNumber;
        int evenNum= evenCounter.get();
        int oddNum= oddCounter.get();
        return evenNum == num * (num + 1) && oddNum == num * num;
    }

    public LinkedList<Edge> getPerfectMatch(){
        PerfectMatch();
        return Mj;
    }

}
