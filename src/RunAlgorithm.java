import java.util.LinkedList;
import java.util.List;

public class RunAlgorithm {

/*


    List<Edge> Mj;
    List<Edge> p;
    int NodeS = -1;
    int NodeT = -2;
    int k = 0;
    int ERRORNUM = -5;
    Node PNodes[];
    Node QNodes[];
    Node SNodes;
    Node TNodes;
    Edge EEdges[];
    Edge EDGEERROR = new Edge( ERRORNUM , ERRORNUM , ERRORNUM );
    Edge NODEERROR = new Node( ERRORNUM );


    public LinkedList<Edge> PerfectMatch(int[] P , int[] Q, LinkedList<Edge> E){

        int n = p.length();
        int j = 0;
        int bj = 0;
        boolean flag = false;
        Mj = new LinkedList<Edge>();

        while( j < n ){
            bj = 2 * ( 2 + n/(n-j) );

            while( !flag ){
                p = new LinkedList<Edge>();

                if( Truncated-Walk( 0 , bj ) )  // VVVVVVVVVVVVVVVVVVVVVVVVVVVV!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        flag = true;
            }

            if( !SetNewMj( p ) ); //VVVVVVVVVVVVVVVVVVVVVVVVVVVV!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    panic("ERROR");

            j++;

        }


        return Mj;

    }



    public boolean Truncated-Walk( int startNode , int bj ){
        Edge e;

        if( startNode == NodeT )
            return true;

        if( bj == 1 )
            return false;

        TransformGraphH(); //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                e = Sample_Out_Edge( startNode );// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                p.add( e );

        return Truncated-Walk( e.getV_to() , (bj-1) );


    }

    public void TransformGraphH(){


        OrientEdges();			//VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    }

    public Node Sample_Out_Edge( int startNode ){

        if( startNode == SNodes )
            return Sample_Out_Start( startNode );
        else if( startNode == TNodes )
            return NODEERROR;
        else
            return Sample_Out_Mid( startNode );


    }


    public Node Sample_Out_Start( int startNode ){


    }


    public Node Sample_Out_Mid( int startNode ){



    }

    public void OrientEdges(){

        int qq , pp;
        int e = ERRORNUM;
        for( int i = 0 ; i < n ; i++ ){
            p = PNodes[i];
            q = QNodes[i];

            if( ! PNodes[i].isSuperNode() ){
                e = NodeContain( p );   //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                if( e != ERRORNUM ){

                    qq = Mj.get(e).getV_to();
                    p = Mj.get(e).getV_from();
                    Mj.get(e).setSuperNode(true);
                    PNodes[p].setSuperNode(true , qq );
                    QNodes[qq].setSuperNode(true , p );


                }
            }

            if( ! QNodes[i].isSuperNode() ){
                e = NodeContain( q );
                if( e != ERRORNUM ){

                    q = Mj.get(e).getV_to();
                    pp = Mj.get(e).getV_from();
                    Mj.get(e).setSuperNode(true);
                    PNodes[pp].setSuperNode(true , q );
                    QNodes[q].setSuperNode(true , pp );


                }
            }

        }
    }



    public boolean SetNewMj( LinkedList<Edge> pp ){

        Edge e = pp.get(0);
        if( e.getV_from() != NodeS )
            return false;

        //TODO MAYBE JUMP TO NEXT.
        for( int i = 1 ; i < pp.size() ; i++ ){
            e = pp.get(i);
            if( e.getV_to() == NodeT )
                continue;

            if( EdgeContain( e ) ) //VVVVVVVVVVVVVVVVVVVVVVVVVVVV!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    p.remove( e );


        }

        Mj = p ;
        return true;

    }


    public boolean EdgeContain( Edge ee ){
        Edge currentE;

        //TODO MAYBE JUMP TO NEXT.
        for( int i = 0 ; i < Mj.size() ; i++ ){
            currentE = Mj.get(i);

            if( 	( currentE.getV_to()   == ee.getV_to() 	 ) &&
                    ( currentE.getV_from() == ee.getV_from() ) 		)

                return true;

            else if(( currentE.getV_to()   == ee.getV_from() ) &&
                    ( currentE.getV_from() == ee.getV_to() 	) 		)

                return true;


        }

        //got here no match for an edge, return false;

        return false;


    }





    public int NodeContain( int ee ){
        Edge currentE;

        //TODO MAYBE JUMP TO NEXT.
        for( int i = 0 ; i < Mj.size() ; i++ ){
            currentE = Mj.get(i);

            if( ( currentE.getV_to() == ee ) || ( currentE.getV_from() == ee ) )
                return i;

        }
        return EDGEERROR;
    }




























*/


}
