import java.util.ArrayList;

import java.util.Set;

public class Graph {
	private int size;
	private int length;
	private ArrayList<Vertex> vertices; // list of nodes

	// java Constructor
	public Graph() {
		length = 0;
		size = 0;
		vertices = new ArrayList<Vertex>();
	}

	public int getLength() {
		return length;
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public int getSize() {
		return size;
	}

	public void addFlight(Flight f) {
		if (!vertices.isEmpty()) {
			boolean inserted = false;
			for (Vertex n : vertices) {
				if (n.getAirport() == f.from()) {
					n.add(f);
					size += 1;
					inserted = true;
					break;
				}
			}
			if (inserted == false) {
				Vertex newnode = new Vertex(f.from(), f);
				vertices.add(newnode);
				size += 1;
				length += 1;
			}

		} else {
			Vertex newnode = new Vertex(f.from(), f);
			vertices.add(newnode);
			size += 1;
			length += 1;
		}

	}

	public ArrayList<Flight> query(String a) {
		ArrayList<Flight> flst = new ArrayList<Flight>();
		if (!vertices.isEmpty()) {
			for (Vertex n : vertices) {
				if (n.getAirport() == a) {
					flst = n.getFlst();
					break;
				}
			}
		}

		return flst;
	}

	public Graph buildGraph(MyList<Flight> flst) {
		if (flst.isEmpty()) {
			return this;
		} else if (flst.rest() == null) {
			this.addFlight(flst.first());
			return this;
		} else {
			this.addFlight(flst.first());
			return this.buildGraph(flst.rest());
		}
	}

	public boolean isEqual(Graph g2) {
		return (size == g2.getSize()) && (length == g2.getLength()) && vertices.equals(g2.getVertices());
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public boolean equals(Object x) {
		if (x instanceof Graph) {
			Graph g2 = (Graph) x;
			return isEqual(g2);
		} else
			return false;
	}

}
