package cc

import cc.facebook.{ FacebookClient, FacebookFriendsGraph }
import cc.graph._
import cc.graph.algorithms.ClosenessCentralityWithDijkstra

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    printCloseness(loadGraphFromFile(getClass.getResource("/edges.txt")))
    printCloseness(loadFacebookGraph)
  }

  def printCloseness[V](graph: Graph[V]) {
    val closenessCentrality = new ClosenessCentralityWithDijkstra(graph)

    println(s"Nodes Closeness Centrality")
    for ((node, score) <- closenessCentrality.verticesCloseness) {
      println(s"  $node: $score")
    }
  }

  def loadGraphFromFile(path: java.net.URL) = {
    val source = Source.fromURL(path)
    val lines = source.getLines().map(_.trim.split(" "))
    val edges = lines.map(l => l(0) -> l(1)).toIterable
    Graph(edges)
  }

  def loadFacebookGraph() = {
    val accessToken = sys.env("ACCESS_TOKEN")
    val appSecret = sys.env("APP_SECRET")
    val client = new FacebookClient(accessToken, appSecret)
    FacebookFriendsGraph(client)
  }
}
