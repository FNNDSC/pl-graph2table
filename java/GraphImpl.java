import java.util.List;
import java.util.ArrayList;
public class GraphImpl {

    public static void main (String args[]){

        Vertex v1 = new Vertex("a");
	Vertex v2 = new Vertex("b");
	Vertex v3 = new Vertex("c");
	Vertex v4 = new Vertex("d");

	Edge e1 = new Edge(v1,v2,10,10,10);
	Edge e2 = new Edge(v2,v3,10,10,10);
	Edge e3 = new Edge(v3,v4,10,10,10);

	List<Vertex> vertices = new ArrayList <>();
	vertices.add(v1);
	vertices.add(v2);
	vertices.add(v3);
	vertices.add(v4);

	List<Edge> edges = new ArrayList<>();
	edges.add(e1);
	edges.add(e2);
	edges.add(e3);

	Graph g1 = new Graph (vertices,edges);

	GraphSearch gsImpl = new GraphSearch(g1);

	gsImpl.BFS(v1);

	for(Vertex v :g1.getVertices()){
	    System.out.println(v.name + ":" + (v.getParent()!=null? v.getParent().name:"Root"));
	}
	
    }
}
