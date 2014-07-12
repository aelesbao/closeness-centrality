package cc.graph.algorithms

import cc.graph.Graph

import scala.collection.mutable.PriorityQueue

trait ClosenessCentrality[V] { this: ShortestPath[V] =>
  def getNodeScore(node: V): Double = {
    val distances = distancesFrom(node)
    distances.values.sum.toDouble / distances.size
  }

  lazy val verticesCloseness: IndexedSeq[(V, Double)] = {
    implicit val ordering = new Ordering[(V, Double)] {
      def compare(a: (V, Double), b: (V, Double)) = b._2.compare(a._2)
    }
    val q = new PriorityQueue[(V, Double)]
    graph.vertices.foreach(v => q.enqueue(v -> getNodeScore(v)))
    q.dequeueAll
  }
}

class ClosenessCentralityWithDijkstra[V](val graph: Graph[V])
  extends ClosenessCentrality[V]
  with Dijkstra[V]