import java.util.List;
import java.util.ArrayList;
public class GraphImpl {

    private Graph graph;

    public Graph getGraph(){

	return this.graph;
	
    }
    GraphImpl(String [] prev, String [] next, String [] plugins){

	int n = prev.length;
	
	System.out.println("Creating vertices");

	// Lets create the vertices
	List<Vertex> vertices = new ArrayList<>();
	for(String plugin:plugins){
	    Vertex ver = new Vertex(plugin);
	    vertices.add(ver);
	}

	System.out.println("Creating edges");
	// Lets create edges
	List<Edge> edges = new ArrayList<>();
	for(int i =0;i<n; i++ ){
	    
                   // if path exists, create an edge
                   int x = Integer.parseInt(prev[i]);
                   int y = Integer.parseInt(next[i]);
		    Edge eg = new Edge(vertices.get(x),vertices.get(y),0,0,0);
		    edges.add(eg);

	}

        System.out.println("Creating graph");
	this.graph = new Graph(vertices,edges); 
	    
    }
    //Clean up args frpm python
    public static String cleanUp(String input){
        input=input.replace("[","");
        input=input.replace("]","");
        input=input.replace("'","");
        input=input.replace(" ","");
        
        return input;
        
    }
    
    public static void main (String args[]){
    
        String [] inputs = args;
        String [] prev = cleanUp( inputs[0]).split(",");
        String [] next = cleanUp( inputs[1]).split(",");
        String [] plugins = cleanUp( inputs[2]).split(",");

	

	GraphImpl grphImpl = new GraphImpl(prev,next,plugins);
       

	Graph g1 = grphImpl.getGraph();

	GraphSearch gsImpl = new GraphSearch(g1);
        
        System.out.println("Initializing source as " + g1.getVertices().get(0).name + " and running BFS");
        System.out.println("\n\n");
	gsImpl.BFS(g1.getVertices().get(0));
        
	
    }
}
