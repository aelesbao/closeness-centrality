package cc.graph.algorithms

import cc.graph._

import org.scalatest.{FlatSpec, Matchers}

class CentralitySpec extends FlatSpec with Matchers {
  /*
   * Example Graph:
   *    _ 2 _
   *   /  |  \
   * 1 -- 3 -- 4 -- 5
   *      |       /
   *      6 -----
   */
  val edges = Set((1, 2), (1, 3), (2, 3), (2, 4), (3, 4), (3, 6), (4, 5), (5, 6))
  val graph = Graph(edges)

  "Closeness Centrality algorithm" should "calculate a node closeness" in {
    val closenessCentrality = new ClosenessCentralityWithDijkstra(graph)
    val score = closenessCentrality.getNodeScore(3)
    score should be(1.2)
  }

  it should "order graph's vertices by closeness" in {
    val closenessCentrality = new ClosenessCentralityWithDijkstra(graph)
    val closeness = closenessCentrality.verticesCloseness
    closeness should contain inOrder ((3,1.2), (4,1.4), (2,1.6), (6,1.6), (5,1.8), (1,1.8))
  }
}
