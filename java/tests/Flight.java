/**
 * Represents schedule information for (one leg of) an airplane flight.
 */

public interface Flight extends Comparable<Flight> {

    /**
     * @return         the name of the Flight
     */

    public String name();

    /**
     * @return         the name of the airport from which the flight departs
     */

    public String from();

    /**
     * @return         the name of the airport at which the flight arrives
     */

    public String to();

    /**
     * @return         the time (in UTC) at which the flight departs,
     *                 represented as minutes after midnight; thus
     *                 0 represents midnight in Greenwich, and
     *                 1234 represents 8:34pm in Greenwich.
     */

    public int departsAt();

    /**
     * @return         the time (in UTC as above) at which the flight arrives
     */

    public int arrivesAt();
}
