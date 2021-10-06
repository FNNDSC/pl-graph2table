import java.util.ArrayList;

class Vertex {
	String airport;
	ArrayList<Flight> flst;
	int size;

	// Java constructor.
	public Vertex(String a, Flight f) {
		this.airport = a;
		this.flst = new ArrayList<Flight>();
		this.flst.add(f);
		this.size = flst.size();

	}

	// RETURNS: a string which represents the name of the airport.
	public String getAirport() {
		return airport;
	}

	// GIVEN: is a String which represents the name of the airport.
	public void setAirport(String airport) {
		this.airport = airport;
	}

	// RETURNS: an ArrayList of type Flight.
	public ArrayList<Flight> getFlst() {
		return flst;
	}

	// GIVEN: an ArrayList of type Flight
	public void setFlst(ArrayList<Flight> flst) {
		this.flst = flst;
	}

	// RETURNS: an integer which represents the size.
	public int getSize() {
		return size;
	}

	// GIVEN: an integer which represents the size.
	public void setSize(int size) {
		this.size = size;
	}

	public boolean isEqual(Vertex n) {
		if (this.airport == n.getAirport() && this.size == n.getSize() && this.flst.equals(n.getFlst())) {
			return true;
		} else
			return false;
	}

	public boolean equals(Object x) {
		if (x instanceof Vertex) {
			Vertex n = (Vertex) x;
			return isEqual(n);
		} else
			return false;
	}

	public void add(Flight f) {
		this.flst.add(f);
		this.size += 1;
	}
}
