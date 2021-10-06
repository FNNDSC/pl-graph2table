import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

/**
 * Defines some examples for testing and makes them available as
 * public members of the class.
 */

public class FlightExamples {

    public static Set<Flight> panAmFlights
        = Collections.unmodifiableSet(new HashSet<Flight>());

    public static Set<Flight> deltaFlights = initDeltaFlights();

    // Returns a list of flights extracted from schedules published
    // by Delta Airlines.

    private static Set<Flight> initDeltaFlights() {
        Set<Flight> result = new TreeSet<Flight>();

        // Java interprets a leading 0 to mean octal, so there aren't
        // any leading zeroes here.

        result.add (makeFlight ("Delta 0121", "LGA", "MSP",  660,  849));
        result.add (makeFlight ("Delta 2163", "MSP", "PDX",  900, 1142));
        result.add (makeFlight ("Delta 2079", "BOS", "DTW",  635,  779));
        result.add (makeFlight ("Delta 1523", "BOS", "DTW", 1318,   20));
        result.add (makeFlight ("Delta 0058", "BOS", "LHR",   44,  440));
        result.add (makeFlight ("Delta 2531", "BOS", "LAX",  797, 1220));
        result.add (makeFlight ("Delta 2532", "BOS", "LAX", 1370,  355));
        result.add (makeFlight ("Delta 1959", "BOS", "MSP",  650,  857));
        result.add (makeFlight ("Delta 1894", "BOS", "MSP",  835, 1050));
        result.add (makeFlight ("Delta 2391", "BOS", "MSP", 1295,   65));
        result.add (makeFlight ("Delta 2734", "BOS", "LGA",  660,  750));
        result.add (makeFlight ("Delta 3550", "BZN", "LAX", 1220, 1382));
        result.add (makeFlight ("Delta 1601", "DEN", "DTW",  785,  971));
        result.add (makeFlight ("Delta 0916", "DEN", "DTW", 1412,  139));
        result.add (makeFlight ("Delta 0010", "DEN", "LHR", 1230,  585));
        result.add (makeFlight ("Delta 5703", "DEN", "LAX",  844, 1035));
        result.add (makeFlight ("Delta 5743", "DEN", "LAX",   34,  211));
        result.add (makeFlight ("Delta 2437", "DTW", "BOS",  825,  946));
        result.add (makeFlight ("Delta 0158", "DTW", "BOS", 1020, 1135));
        result.add (makeFlight ("Delta 1700", "DTW", "BOS", 1360,   42));
        result.add (makeFlight ("Delta 1511", "DTW", "DEN",  810, 1011));
        result.add (makeFlight ("Delta 1645", "DTW", "DEN", 1031, 1238));
        result.add (makeFlight ("Delta 1706", "DTW", "LAX",  800, 1125));
        result.add (makeFlight ("Delta 0249", "DTW", "MSP",  900, 1027));
        result.add (makeFlight ("Delta 2359", "DTW", "MSP", 1035, 1160));
        result.add (makeFlight ("Delta 2476", "DTW", "MSP",   70,  198));
        result.add (makeFlight ("Delta 0059", "LHR", "BOS",  560, 1046));
        result.add (makeFlight ("Delta 4378", "LHR", "BOS", 1005,   20));
        result.add (makeFlight ("Delta 0011", "LHR", "DEN",  775,  140));
        result.add (makeFlight ("Delta 0302", "LAX", "BOS",  985, 1334));
        result.add (makeFlight ("Delta 5732", "LAX", "BZN",   30,  198));
        result.add (makeFlight ("Delta 4574", "LAX", "DEN", 1055, 1207));
        result.add (makeFlight ("Delta 5700", "LAX", "DEN",   10,  165));
        result.add (makeFlight ("Delta 2077", "LAX", "PDX", 1055, 1209));
        result.add (makeFlight ("Delta 1728", "MSP", "BOS",  960, 1131));
        result.add (makeFlight ("Delta 2305", "MSP", "BZN",  141,  313));
        result.add (makeFlight ("Delta 1609", "MSP", "DEN", 1235, 1372));
        result.add (makeFlight ("Delta 1836", "MSP", "DTW",  744,  855));
        result.add (makeFlight ("Delta 1734", "MSP", "DTW", 1075, 1181));
        result.add (makeFlight ("Delta 0592", "MSP", "LGA", 1050, 1217));
        result.add (makeFlight ("Delta 2734", "LGA", "BOS",  660,  728));
        result.add (makeFlight ("Delta 1294", "LGA", "DEN",  790, 1074));
        result.add (makeFlight ("Delta 0879", "LGA", "DTW",  850,  980));
        result.add (makeFlight ("Delta 1422", "LGA", "MSP",  900, 1102));
        result.add (makeFlight ("Delta 0950", "PDX", "LAX",  858, 1015));
        result.add (makeFlight ("Delta 2077", "PDX", "LAX", 1245, 1394));
        result.add (makeFlight ("Delta 2831", "PDX", "LAX", 1426,  145));
        result.add (makeFlight ("Delta 2167", "PDX", "MSP", 1320,   80));

        return Collections.unmodifiableSet(result);
    }

    // Help method to make the lines shorter and easier to read.

    private static Flight makeFlight (String name, String from, String to,
                                      int departs, int arrives) {
        return Flights.makeFlight (name, from, to, departs, arrives);
    }
    public static void main(String [] args) {
    	
    	 List<Flight> fList=BestPath.bestPath (FlightExamples.deltaFlights, "DTW", "LHR");
    	 for(Flight f:fList) 
    		 System.out.println(f);
    	
    }
}
