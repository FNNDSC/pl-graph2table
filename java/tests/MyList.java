interface MyList<E> {

	boolean isEmpty();

	E first();

	MyList<E> rest();

	MyList<E> cons(E x);
}