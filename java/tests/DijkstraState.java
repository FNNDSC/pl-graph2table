import java.util.ArrayList;

public class DijkstraState {
	final int INFINITY_TIME = Integer.MAX_VALUE; // infinity time

	private Flight flight;
	private int besttime;
	private ArrayList<Flight> bestpath;

	// Java Constructor
	public DijkstraState() {
		besttime = INFINITY_TIME;
		flight = null;
		bestpath = new ArrayList<Flight>();
	}

	public DijkstraState(Flight f, int time, ArrayList<Flight> path) {
		flight = f;
		bestpath = path;
		besttime = time;
	}

	public DijkstraState(DijkstraState djs) {
		flight = djs.getFlight();
		besttime = djs.getBesttime();
		bestpath = new ArrayList<Flight>(djs.getBestpath());
	}

	public Flight getFlight() {
		return flight;
	}

	public int getBesttime() {
		return besttime;
	}

	public void setBesttime(int besttime) {
		this.besttime = besttime;
	}

	public ArrayList<Flight> getBestpath() {
		return bestpath;
	}

	public void setBestpath(ArrayList<Flight> bestpath) {
		this.bestpath = bestpath;
	}

	public boolean isEmpty() {
		if (besttime == INFINITY_TIME && flight == null && bestpath.isEmpty())
			return true;
		else
			return false;
	}

	public boolean isEqual(DijkstraState s2) {
		return (flight.equals(s2.getFlight()) && (besttime == s2.getBesttime()) && bestpath.equals(s2.getBestpath()));
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public boolean equals(Object x) {
		if (x instanceof DijkstraState) {
			DijkstraState s2 = (DijkstraState) x;
			return isEqual(s2);
		} else
			return false;
	}

}
