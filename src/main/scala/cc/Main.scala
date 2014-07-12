package cc

import cc.graph._
import cc.graph.algorithms.{ClosenessCentralityWithDijkstra, DijkstraImpl, ClosenessCentrality}

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val graph = loadGraph(getClass.getResource("/edges.txt"))
    val closenessCentrality = new ClosenessCentralityWithDijkstra(graph)
    println(s"Nodes closeness ${closenessCentrality.verticesCloseness}")
  }

  def loadGraph(path: java.net.URL) = {
    val source = Source.fromURL(path)
    val lines = source.getLines().map(_.trim.split(" "))
    val edges = lines.map(l => l(0) -> l(1)).toIterable
    Graph(edges)
  }
}
