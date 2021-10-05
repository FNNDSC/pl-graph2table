

import java.util.List;

public class Graph {
	private List<Vertex> vertices;
	private List<Edge> edges;
	Graph(List<Vertex> vertices,List<Edge> edges){
		this.edges=edges;
		this.vertices=vertices;
	}
	public List<Edge> getEdges(){
		return edges;
	}
	public List<Vertex> getVertices(){
		return vertices;
	}
	public void setVertices(List<Vertex> vertices) {
		this.vertices=vertices;
	}

}
