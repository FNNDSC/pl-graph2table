

class Edge {
	private Vertex src;
	private Vertex dst;
	private int weight;
	private int dep;
	private int arr;
	Edge(Vertex src, Vertex dst, int weight,int dep, int arr){
		this.src=src;
		this.dst=dst;
		this.weight=weight;
		this.dep=dep;
		this.arr=arr;
	}
	public int getDep() {
		return dep;
	}
	public void setDep(int dep) {
		this.dep = dep;
	}
	public int getArr() {
		return arr;
	}
	public void setArr(int arr) {
		this.arr = arr;
	}
	public Vertex getSrc() {
		return src;
	}
	public void setSrc(Vertex src) {
		this.src = src;
	}
	public Vertex getDst() {
		return dst;
	}
	public void setDst(Vertex dst) {
		this.dst = dst;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
