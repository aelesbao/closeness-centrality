package cc

import cc.graph._

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val edges = loadEdges(getClass.getResource("/edges.txt"))
    val graph = Graph(edges)
    val dijkstra = new Dijkstra(graph)
    for (node <- graph.vertices) {
      val distances = dijkstra.distancesFrom(node)
      println(s"Distances for $node: $distances")
    }
  }

  def loadEdges(path: java.net.URL) = {
    val source = Source.fromURL(path)
    val lines = source.getLines().map(_.trim.split(" "))
    lines.map(l => Edge(l(0), l(1))).toIterable
  }
}
