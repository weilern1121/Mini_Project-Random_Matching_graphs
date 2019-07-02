import java.util.Collections;
import java.util.LinkedList;

public class Node {

    public enum VerticeType {
        NORMAL, PNODE, QNODE, SNODE, TNODE;
    }

    private int idNum;
    private LinkedList<Edge> edges;
    private VerticeType verType;
	private LinkedList<Edge> TransEdges;
    private Edge SuperEdge;
    private boolean isSuperNode;


    /*** constructors ***/
    public Node(int num) {
        this.idNum = num;
        this.edges = new LinkedList<>();
        this.TransEdges = new LinkedList<>();
        this.isSuperNode = false;
        this.verType = VerticeType.NORMAL;
    }

    public Node(int num, VerticeType type) {
        this.idNum = num;
        this.edges = new LinkedList<>();
        this.TransEdges = new LinkedList<>();
        this.isSuperNode = false;
        this.verType = type;
       
    }



    /*** getters ***/
    public int getIdNum() {
        return idNum;
    }

    public VerticeType getVerType() {
        return verType;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }
	

    /*** methods ***/
    public void resetTransEdges() {
        this.TransEdges = new LinkedList<>();

    }

    public void addEdge( Edge e){
        this.edges.add(e);
    }

  /*  public void addEdgesList(LinkedList edgesToAdd) {
        for (Object e : edgesToAdd) {
            this.edges.add((Integer)e);
        }
    }
	*/

   /* public boolean changeSuperInList( int elem , int changeTo){
        if(!this.edges.contains(elem)) //boolean used in graph's side
            return false;
        this.edges.removeFirstOccurrence(elem);
        this.edges.add(changeTo);
        Collections.sort(this.edges); //finnaly - sort by vNum
        return true;
    }

    public void fixEdgesList(){
        this.edges.removeFirstOccurrence(this.idNum);
        Collections.sort(this.edges);
    }

*/





/* ------------------------YOAV ADDITION---------------------*/





	public LinkedList<Edge> getTransEdges() {
        return TransEdges;
	}
	
	
	public void addTransEdge( Edge e ) {
		this.TransEdges.add( e );
	}

	public void removeTransEdge( Edge e ) {
		this.TransEdges.remove( e );
	}

	//TODO - need deep copy constructor!!
	public void setTransEdge( LinkedList<Edge> e ) {
//		if(e!=null){
//            for (Edge tmp :e) {
        resetTransEdges();
                this.TransEdges.addAll(e);
//            }
//        }
//	    this.TransEdges = e ;
	}
	
	
	
	public boolean isSuperNode() {
        return isSuperNode;
    }


    
    public void setSuperNode( boolean b , Edge e){
        this.isSuperNode = b;
        if( e != null)
		    this.SuperEdge = e;
    }
	
	public Edge getSuperEdge(){
		return this.SuperEdge;
	}
	
	
	
	
}
