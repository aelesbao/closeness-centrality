package cc.graph

case class Edge[V](val from: V, val to: V) {
  def invert = Edge(to, from)
  def looped = (from == to)
}

/* Class Graph[V] describes a graph with vertices of type V, with connecting
 * direction, unweighted edges.
 */
class Graph[V](edges: Map[V, Set[V]] = Map.empty[V, Set[V]]) {
  lazy val vertices = edges.foldLeft(Set.empty[V])((set, map) => set ++ map._2 + map._1)

  /* +(edge)
   * A copy of the graph with a new edge.
   */
  def +(edge: Edge[V]): Graph[V] = {
    assume(!edge.looped, "Cannot add a looped edge")
    val newEdges = edges + edge + edge.invert
    new Graph(newEdges)
  }

  /* ++(edges)
   * Returns a new graph containing the edges from the left hand operand
   * and the right hand operand.
   */
  def ++(edges: Iterable[Edge[V]]): Graph[V] = {
    edges.foldLeft(this)((graph, edge) => graph + edge)
  }

  def size = vertices.size

  def apply(node: V) = edges(node)

  override def toString() = s"Graph(${edges})"

  implicit def buildEdges(edge: Edge[V]): (V, Set[V]) = {
    val nodes = edges.getOrElse(edge.from, Set.empty)
    edge.from -> (nodes + edge.to)
  }
}

object Graph {
  def apply[V](edges: Iterable[Edge[V]]) = new Graph[V] ++ edges
}
