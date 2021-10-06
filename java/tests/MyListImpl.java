
public class MyListImpl<E> implements MyList<E> {

	MyList<E> rest;
	E first;

	// java constructor
	public MyListImpl() {
		rest = null;
		first = null;
	}

	// java constructor
	public MyListImpl(E f) {
		first = f;
		rest = null;
	}

	// java constructor
	public MyListImpl(E f, MyList<E> r) {
		first = f;
		rest = r;
	}

	// RETURNS: true iff first and rest are null.
	public boolean isEmpty() {
		if (rest == null && first == null)
			return true;
		return false;
	}

	// WHERE: this list is non-empty
	// RETURNS: first element of this list
	public E first() {
		return first;
	}

	// WHERE: this list is non-empty
	// RETURNS: rest of this list
	public MyList<E> rest() {
		return rest;
	}

	public MyList<E> cons(E x) {
		if (this.isEmpty()) {
			this.first = x;
		} else if (rest == null) {
			this.rest = new MyListImpl<E>(first);
			this.first = x;
		} else {
			rest = this.rest.cons(first);
			first = x;
		}
		MyList<E> newlist = new MyListImpl<E>(first, rest);
		return newlist;
	}

	@Override
	// RETURNS: a String.
	public String toString() {
		return "RacketLists [rest=" + rest + ", first=" + first + "]";
	}

}
