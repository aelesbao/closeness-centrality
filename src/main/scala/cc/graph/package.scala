package cc

package object graph {
  import scala.language.implicitConversions

  implicit def tupleToEdge[V](t: (V, V)) = Edge[V](t._1, t._2)
  implicit def iterableToEdges[V](ts: Iterable[(V, V)]) = ts.map(t => Edge[V](t._1, t._2))
}
