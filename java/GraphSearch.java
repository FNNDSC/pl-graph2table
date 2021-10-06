

import java.util.ArrayList;
import java.util.List;

public class GraphSearch {
	private Graph graph;
	GraphSearch(Graph graph){
		this.graph=graph;
	}
	private List<Vertex> adj(Vertex v){
		List<Vertex> adjacent=new ArrayList<Vertex>();
		for(Edge e:graph.getEdges()) {
			if (e.getSrc()==v)
				adjacent.add(e.getDst());
		}
		return adjacent;
			
	}
	public void DFS() {
		for(Vertex v:graph.getVertices()) {
			v.setColor("White");
			v.setParent(null);
		}
		int time=0;
		for(Vertex u:graph.getVertices()) {
			if(u.getColor()=="White")
				dfsVisit(u,time);
		}
		
	}
	public void dfsVisit(Vertex u, int time) {
		time=time+1;
		u.setDistance(time);
		u.setColor("Gray");
		for(Vertex v:adj(u)) {
			if (v.getColor()=="white") {
				v.setParent(u);
				dfsVisit(v,time);
			}
		}
		u.setColor("Black");
		time=time+1;
		
	}
	public void BFS(Vertex s) {
		for(Vertex u:graph.getVertices()) {
			u.setColor("White");
			u.setDistance(Integer.MAX_VALUE);
			u.setParent(null);
		}
		s.setColor("Gray");
		s.setDistance(0);
		s.setParent(null);
		List<Vertex> visited=new ArrayList<Vertex>(); 
		visited.add(s);
		while(!visited.isEmpty()) {
			Vertex u=visited.remove(0);
			for(Vertex v:adj(u)) {
				if(v.getColor()=="White") {
					v.setColor("Gray");
					v.setDistance(u.getDistance()+1);
					v.setParent(u);
					visited.add(v);
				}
			}
			u.setColor("Black");
			System.out.println("Visited node :" + u.name);
				
		}
		
	}
	public boolean bellmanFord(Vertex s) {
		initializeSource(s);
		for(int i=1;i<graph.getVertices().size();i++) {
			for(Edge e:graph.getEdges())
				relax(e.getSrc(),e.getDst());
		}
		for(Edge e:graph.getEdges()) {
			if(e.getDst().getDistance()>e.getSrc().getDistance()+weight(e.getSrc(),e.getDst()))
				return false;
		}
		return true;
	}
	public void dijkstra(Vertex s) {
		initializeSource(s);
		List<Vertex> S=new ArrayList<Vertex>();
		List<Vertex> Q=new ArrayList<Vertex>();
		Q.add(s);
		while(!Q.isEmpty()) {
			Vertex u=exMin(Q);
			S.add(u);
			Q.remove(u);
			for(Vertex v:adj(u)) {
				relax(u,v);
				Q.add(v);
			}
			
		}
	}
	public void initializeSource(Vertex s) {
		for(Vertex v:graph.getVertices()) {
			v.setDistance(Integer.MAX_VALUE);
			v.setTime(Integer.MAX_VALUE);
			v.setParent(null);
		}
		s.setDistance(0);
		s.setTime(0);
	}
	public void relax(Vertex u, Vertex v) {
		if(v.getDistance()>u.getDistance()+weight(u,v)) {
			v.setDistance(u.getDistance()+weight(u,v));
			v.setParent(u);
		}
			
	}
	
	public int weight(Vertex u, Vertex v) {
		int minWt=Integer.MAX_VALUE;
		for(Edge e:graph.getEdges()) {
			if(e.getSrc().equals(u) && e.getDst().equals(v) && minWt>e.getWeight() && (e.getDep()>u.getTime())) {
				minWt= e.getWeight();
				//u.setTime(e.getDep());
				v.setTime(e.getArr());
			}
			
		}
		return minWt;
		//throw new RuntimeException("No path available");
	}
	public Vertex exMin(List<Vertex> Q) {
		Vertex minQ=null;
		for(Vertex v:Q) {
			if(minQ==null) {
				minQ=v;
			}
			else {
				if(v.getDistance()<minQ.getDistance()) {
				minQ=v;
				
			}
			}
		}
		return minQ;
	}
}
