package cc

object Main {
  def main(args: Array[String]) {
    println("Following is the content read:")

    DataSource.load.foreach{
      print
    }
  }
}
