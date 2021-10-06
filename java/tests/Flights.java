/**
 * Implements a static factory method for creating flights.
 */

public final class Flights {

    private Flights () { }    // so no objects of this class can be created

    /**
     * Static factory method for creating flights.
     *
     * @param name     the name of the flight
     * @param from     the name of the airport from which the flight departs
     * @param to       the name of the airport at which the flight arrives
     * @param departs  the time of departure (in minutes after midnight UTC)
     * @param arrives  the time of arrival (in minutes after midnight UTC)
     * @return         a flight object that encapsulates the given information
     */

    public static Flight makeFlight (String name, String from, String to,
                                     int departs, int arrives) {

        // Preconditions don't need to be checked as they are here,
        // but checking preconditions that are cheap to check makes
        // programs easier to debug.

        if ((0 <= departs) &&
            (departs <  1440) &&
            (0 <= arrives) &&
            (arrives <= 1440)) {

            // Using two different classes to implement flights protects
            // against the common mistake of thinking every flight is
            // an instance of the same class.

            counter = counter + 1;

            if ((counter % 2) == 1)
                return new Flight0 (name, from, to, departs, arrives);
            else
                return new Flight1 (name, from, to, departs, arrives);
        }
        else throw new IllegalArgumentException ("bad UTC time");
    }

    private static int counter = 0;  // how many flights have been created

    private static class FlightBase implements Flight {

        private String name;  // name of this flight
        private String from;  // name of airport from which this flight departs
        private String to;    // name of airport at which this flight arrives
        private int departs;  // time (in UTC) at which this flight departs
        private int arrives;  // time (in UTC) at which this flight arrives

        private int h = 0;    // nonzero value is cached hash value

        FlightBase (String name, String from, String to,
                    int departs, int arrives) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.departs = departs;
            this.arrives = arrives;
        }

        /**
         * @return         {@inheritDoc}
         */

        public String name() { return name; }

        /**
         * @return         {@inheritDoc}
         */

        public String from() { return from; }

        /**
         * @return         {@inheritDoc}
         */

        public String to() { return to; }

        /**
         * @return         {@inheritDoc}
         */

        public int departsAt() { return departs; }

        /**
         * @return         {@inheritDoc}
         */

        public int arrivesAt() { return arrives; }

        /**
         * {@inheritdoc}
         */

        public boolean equals (Object x) {
            if (x == null)
                return false;
            else if (! (x instanceof Flight))
                return false;
            else {
                Flight f2 = (Flight) x;
                return (this.name().equals(f2.name()))
                    && (this.from().equals(f2.from()))
                    && (this.to().equals(f2.to()))
                    && (this.departsAt() == f2.departsAt())
                    && (this.arrivesAt() == f2.arrivesAt());
            }
        }

        /**
         * {@inheritdoc}
         */

        public String toString() {
            String s = name + ": " + from + " to " + to;
            return s + " lv " + departs + " ar " + arrives;
        }

        /**
         * {@inheritdoc}
         */

        public int hashCode() {
            if (this.h == 0)
                this.h = toString().hashCode();
            return this.h;
        }

        /**
         * {@inheritdoc}
         */

        public int compareTo (Flight f2) {
	    if (this == f2)
		return 0;
	    int result = this.name().compareTo(f2.name());
	    if (result != 0)
		return result;
	    result = this.from().compareTo(f2.from());
	    if (result != 0)
		return result;
	    result = this.to().compareTo(f2.to());
	    if (result != 0)
		return result;
	    result = this.departsAt() - f2.departsAt();
	    if (result != 0)
		return result;
	    result = this.arrivesAt() - f2.arrivesAt();
	    return result;
	}
    }

    private static class Flight0 extends FlightBase {

        Flight0 (String name, String from, String to,
                 int departs, int arrives) {
            super (name, from, to, departs, arrives);
        }
    }

    private static class Flight1 extends FlightBase {

        Flight1 (String name, String from, String to,
                 int departs, int arrives) {
            super (name, from, to, departs, arrives);
        }
    }
}
