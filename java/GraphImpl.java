import java.util.List;
import java.util.ArrayList;
public class GraphImpl {

    private Graph graph;

    public Graph getGraph(){

	return this.graph;
	
    }
    GraphImpl(int[][]adjMat){

	int n = adjMat.length;
	
	System.out.println("Creating vertices");

	// Lets create the vertices
	List<Vertex> vertices = new ArrayList<>();
	for(int v=0; v<n;v++){
	    Vertex ver = new Vertex(Integer.toString(v));
	    vertices.add(ver);
	}

	System.out.println("Creating edges");
	// Lets create edges
	List<Edge> edges = new ArrayList<>();
	for(int i =0;i<n; i++ ){
	    for(int j=0; j<n; j++){
		if(adjMat[i][j]==1){
                    // if path exists, create an edge
		    Edge eg = new Edge(vertices.get(i),vertices.get(j),0,0,0);
		    edges.add(eg);
		    
		}
	    }
	}

        System.out.println("Creating graph");
	this.graph = new Graph(vertices,edges); 
	    
    }

    public static void main (String args[]){
    
        System.out.println("Printing message from python :" + args[0]);

	int [][] arr = {{0,1,0,0,0,0,0,0,0,0}
	               ,{0,0,1,1,0,0,0,0,0,0}
	               ,{0,0,0,0,0,0,0,0,0,0}
	               ,{0,0,0,0,1,1,0,0,0,0}
	               ,{0,0,0,0,0,0,0,0,0,0}
	               ,{0,0,0,0,0,0,1,0,0,0}
	               ,{0,0,0,0,0,0,0,1,0,1}
	               ,{0,0,0,0,0,0,0,0,1,0}
	               ,{0,0,0,0,0,0,0,0,0,0}
	               ,{0,0,0,0,0,0,0,0,0,0}};


	GraphImpl grphImpl = new GraphImpl(arr);
       

	Graph g1 = grphImpl.getGraph();

	GraphSearch gsImpl = new GraphSearch(g1);
        
        System.out.println("Initializing source as " + g1.getVertices().get(0).name + " and running BFS");
        System.out.println("\n\n");
	gsImpl.BFS(g1.getVertices().get(0));
        System.out.println("\t\tCurrent Node:\t\tParent Node");
	for(Vertex v :g1.getVertices()){
	    System.out.println("\t\t\t"+v.name + ":\t\t" + (v.getParent()!=null? v.getParent().name:"Root"));
	}
	
    }
}
