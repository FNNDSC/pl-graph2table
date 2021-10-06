
// Given a set of flights, an origin, and a destination,
// throws a RuntimeException if there is no sequence of the given
// flights that goes from the origin to the destination.
// Otherwise returns a possibly empty list of flights [f_1, ..., f_n]
// that
//
//     represents a path from the origin to the destination
//     allows at least 30 minutes for every layover
//     minimizes the aggravation function
// 
//         a([f_1, f_2, ... f_n])
//             = \sum_{i=1}^n     b (f_i)
//               \sum_{i=1}^{n-1} c (f_i, f_{i+1})
//
// where b(f) is the flight time defined as
// the least non-negative integer k such that
//
//     k = (f.arrivesAt() - f.departsAt()) mod 1440
//
// and
//
//     c(f1,f2) = L + |60 - L|
//
// is the layover distress defined in terms of the layover time L,
// which is the least non-negative integer L such that
//
//     L = (f2.departsAt() - f1.arrivesAt()) mod 1440

import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class BestPath {
	public static List<Flight> bestPath(Set<Flight> flights, String src, String dst) {
		List<Flight> flts = new ArrayList<>();
		MyList<Flight> fList = MyLists.empty();
		for (Flight f : flights)
			fList.cons(f);
		MyList<Flight> rac = Schedules.fastestItinerary(src, dst, fList);
		try {
			print(rac);
		} catch (Exception ex) {
			throw new RuntimeException("No Flights available");
		}
		return flts;

	}

	public static void print(MyList<Flight> rac) {
		if (rac == null) {
			System.out.println("");

		} else if (!(rac.rest() == null)) {
			System.out.println(rac.first().toString());
			print(rac.rest());
		} else {
			System.out.println(rac.first().toString());
		}
	}

	public static void main(String[] args) {
		//bestPath (FlightExamples.panAmFlights, "LGA", "LHR");
		bestPath(FlightExamples.deltaFlights, "LGA", "LHR");
		bestPath(FlightExamples.deltaFlights, "DTW", "LHR");

	}

}