
public class MyLists<E> {

	public static <E> MyList<E> empty() {
		return new MyListImpl<E>();
	}

}
