import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class DijkstraGraph {
	private Flight start;
	private PriorityQueue<DijkstraState> unvisited;
	private Map<String, DijkstraState> bestit;
	private List<String> visited;
	Graph graph;

	// java constructor
	DijkstraGraph(Flight f, Graph g) {
		start = f;
		graph = g;
		unvisited = new PriorityQueue<DijkstraState>(g.getSize() * g.getSize(), DijkstraStateomparator);
		visited = new ArrayList<String>();
		ArrayList<Flight> initiallist = new ArrayList<Flight>();
		initiallist.add(f);
		DijkstraState state = new DijkstraState(f, 0, initiallist);
		bestit = new HashMap<String, DijkstraState>();
		bestit.put(f.name(), state);
		unvisited.add(state);

	}

	public static Comparator<DijkstraState> DijkstraStateomparator = new Comparator<DijkstraState>() {
		@Override
		public int compare(DijkstraState s1, DijkstraState s2) {
			return (s2.getBesttime() - s1.getBesttime());
		}
	};

	public Flight getStart() {
		return start;
	}

	public void setStart(Flight start) {
		this.start = start;
	}

	public PriorityQueue<DijkstraState> getUnvisited() {
		return unvisited;
	}

	public void setUnvisited(PriorityQueue<DijkstraState> unvisited) {
		this.unvisited = unvisited;
	}

	public Map<String, DijkstraState> getBestit() {
		return bestit;
	}

	public void setBestit(Map<String, DijkstraState> bestit) {
		this.bestit = bestit;
	}

	public List<String> getVisited() {
		return visited;
	}

	public void setVisited(List<String> visited) {
		this.visited = visited;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public boolean isEqual(DijkstraGraph d2) {
		return (start.equals(d2.getStart())) && (unvisited.equals(d2.getUnvisited()))
				&& (visited.equals(d2.getVisited())) && (bestit.equals(d2.getBestit()))
				&& (graph.equals(d2.getGraph()));
	}

	public boolean equals(Object x) {
		if (x instanceof DijkstraGraph) {
			DijkstraGraph d2 = (DijkstraGraph) x;
			return isEqual(d2);
		} else
			return false;
	}

	public DijkstraState getFastestItenerary(String end) {
		this.expand();
		DijkstraState fastest = new DijkstraState();
		Iterator<Entry<String, DijkstraState>> iterator = bestit.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, DijkstraState> entry = iterator.next();
			if (entry.getValue().getFlight().to() == end) {
				if (fastest.getBesttime() > entry.getValue().getBesttime()) {
					fastest = entry.getValue();
				}
			}
		}
		return fastest;
	}

	private static int timeDifference(int start, int end) {
		int differenceInMinutes = end - start;

		return (differenceInMinutes) % 1440;
	}

	private static int flightDuration(Flight f) {
		return timeDifference(f.departsAt(), f.arrivesAt());
	}

	private static int layoverDuration(Flight ffirst, Flight fsecond) {
		int l = timeDifference(ffirst.arrivesAt(), fsecond.departsAt());
		if (l >= 30 && l < 1470)
			return l + Math.abs(60 - l);
		else
			return 1440;

	}

	private static int itineraryDuration(ArrayList<Flight> flst) {
		if (flst.isEmpty()) {
			return 0;
		} else if (flst.size() == 1) {
			return flightDuration(flst.get(0));
		} else {
			int time = 0;
			for (int i = 0; i < flst.size(); i++) {
				time += flightDuration(flst.get(i));
			}
			for (int i = 0; i < flst.size() - 1; i++) {
				time += layoverDuration(flst.get(i), flst.get(i + 1));
			}
			return time;
		}
	}

	private static boolean hasloop(ArrayList<Flight> flst, Flight f) {
		for (Flight finlist : flst) {
			if (f.to() == finlist.from()) {
				return true;
			}
		}
		return false;
	}

	private void expand() {
		Flight exploreflight = this.getUnvisited().poll().getFlight();
		visited.add(exploreflight.name());
		ArrayList<Flight> flightlist = graph.query(exploreflight.to());
		update(exploreflight, flightlist);
		while (!unvisited.isEmpty()) {
			expand();
		}
	}

	private void update(Flight exploreflight, ArrayList<Flight> flightlist) {

		if (!flightlist.isEmpty()) {
			for (Flight f : flightlist) {
				DijkstraState beststate = bestit.get(exploreflight.name());
				ArrayList<Flight> path = new ArrayList<Flight>(beststate.getBestpath());

				if (!hasloop(path, f)) {
					path.add(f);
					DijkstraState newstate = new DijkstraState(f, itineraryDuration(path), path);
					if (!visited.contains(f.name())) {
						unvisited.add(newstate);
					}
					if (bestit.get(f.name()) == null) {
						bestit.put(f.name(), newstate);
					} else {
						DijkstraState currentstate = bestit.remove(f.name());
						if (newstate.getBesttime() < currentstate.getBesttime()) {
							bestit.put(f.name(), newstate);
						} else {
							bestit.put(f.name(), currentstate);
						}
					}
				}
			}
		}

	}

}
