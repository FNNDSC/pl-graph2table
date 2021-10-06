import java.util.ArrayList;

public class Schedules {

	public static boolean canGetThere(String ap1, String ap2, MyList<Flight> flights) {
		if (ap1.equals(ap2)) {
			return true;
		}

		else if (fastestItinerary(ap1, ap2, flights).isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public static MyList<Flight> fastestItinerary(String ap1, String ap2, MyList<Flight> flights) {

		if (ap1.equals(ap2)) {

			return MyLists.empty();
		} else {

			MyList<Flight> fastest = MyLists.empty();
			DijkstraState fasteststate = new DijkstraState();
			Graph g = new Graph();
			g.buildGraph(flights);
			ArrayList<Flight> initiallist = g.query(ap1);
			for (Flight f : initiallist) {
				DijkstraGraph dg = new DijkstraGraph(f, g);
				DijkstraState possiblebest = new DijkstraState(dg.getFastestItenerary(ap2));
				if (fasteststate.getBesttime() > possiblebest.getBesttime()) {
					fasteststate = possiblebest;
				}
			}

			if (fasteststate.isEmpty()) {
				return fastest;
			} else {
				for (int i = fasteststate.getBestpath().size() - 1; i >= 0; i--) {
					fastest.cons(fasteststate.getBestpath().get(i));
				}
				return fastest;
			}
		}

	}

	public static int travelTime(String ap1, String ap2, MyList<Flight> flights) {
		if (ap1.equals(ap2)) {
			return 0;
		} else {

			DijkstraState fasteststate = new DijkstraState();
			Graph g = new Graph();
			g.buildGraph(flights);
			ArrayList<Flight> initiallist = g.query(ap1);
			for (Flight f : initiallist) {
				DijkstraGraph dg = new DijkstraGraph(f, g);
				DijkstraState possiblebest = new DijkstraState(dg.getFastestItenerary(ap2));
				if (fasteststate.getBesttime() > possiblebest.getBesttime()) {
					fasteststate = possiblebest;
				}
			}
			return fasteststate.getBesttime();

		}
	}

}
