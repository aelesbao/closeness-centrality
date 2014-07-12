package cc

import cc.graph._

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val graph = loadGraph(getClass.getResource("/edges.txt"))
    val dijkstra = new Dijkstra(graph)
    for (node <- graph.vertices) {
      val distances = dijkstra.distancesFrom(node)
      println(s"Distances for $node: $distances")
    }
  }

  def loadGraph(path: java.net.URL) = {
    val source = Source.fromURL(path)
    val lines = source.getLines().map(_.trim.split(" "))
    val edges = lines.map(l => l(0) -> l(1)).toIterable
    Graph(edges)
  }
}
