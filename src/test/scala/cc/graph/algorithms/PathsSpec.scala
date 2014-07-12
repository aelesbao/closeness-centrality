package cc.graph.algorithms

import cc.graph._

import org.scalatest.{FlatSpec, Matchers}

class PathsSpec extends FlatSpec with Matchers {
  /*
   * Example Graph:
   *    _ 2 _
   *   /  |  \
   * 1 -- 3 -- 4 -- 5
   *
   */
  val edges = Set((1, 2), (1, 3), (2, 3), (2, 4), (3, 4), (4, 5))
  val graph = Graph(edges)

  "Dijkstra's algorithm" should "calculate shortest path from source to all nodes" in {
    val dijkstra = new DijkstraImpl(graph)
    val distances = dijkstra.distancesFrom(2)
    val expectedDistances = Map(1 -> 1, 3 -> 1, 4 -> 1, 5 -> 2)
    distances should equal(expectedDistances)
  }

  it should "validate that the source node exists" in {
    val dijkstra = new DijkstraImpl(graph)
    the [AssertionError] thrownBy {
      dijkstra.distancesFrom(0)
    } should have message "assumption failed: Source is not known!"
  }
}
