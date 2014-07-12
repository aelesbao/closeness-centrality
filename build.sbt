name := "Closeness Centrality"

version := "1.0"

scalaVersion := "2.11.1"

// set the main class for the main 'run' task
mainClass := Some("cc.Main")

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test"
)

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

// fork a new JVM for 'run' and 'test:run'
fork := true

sources in (Compile,doc) := Seq.empty