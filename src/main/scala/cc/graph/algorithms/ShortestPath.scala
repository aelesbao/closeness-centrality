package cc.graph.algorithms

import scala.collection.mutable.{Map, PriorityQueue, Set}

abstract class ShortestPath[V] {
  case class Pair(val key: V, val priority: Int) extends Ordered[Pair] {
    def compare(that: Pair) = this.priority - that.priority
  }

  /* distancesFrom(source):
   * Calculates the shortest paths to all nodes from 'source'.
   * Returns a Map of distances of the form (node -> distance from source).
   */
  def distancesFrom(source: V): Map[V, Int]
}

class Dijkstra[V](graph: Graph[V]) extends ShortestPath[V] {
  def distancesFrom(source: V) = {
    assume(graph.contains(source), "Source is not known!")

    val distances = Map[V, Int]()
    val visited = Set.empty[V]

    val Q = new PriorityQueue[Pair]()
    Q.enqueue(Pair(source, 0))

    while (!Q.isEmpty) {
      /* Extract nearest vertex to computed set, and declare it as visited.
       * Update the distances to this node's neighbours, and update Q for
       * next iteration. */
      val node = Q.dequeue.key
      visited += node

      for (v <- graph(node)) {
        if (!visited.contains(v)) {
          val vNewDist = (distances.getOrElse(node, 0) + 1).toByte
          if (!distances.isDefinedAt(v) || vNewDist < distances(v)) {
            distances += v -> vNewDist
            Q.enqueue(Pair(v, vNewDist))
          }
        }
      }
    }

    distances
  }
}
