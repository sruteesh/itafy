import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "itafy"
    val appVersion      = "0.1"

    val appDependencies = Seq(
      javaCore,
      "org.twitter4j" % "twitter4j-core" % "3.0.5",
      "org.twitter4j" % "twitter4j-stream" % "3.0.5",
      "org.mongodb" % "mongo-java-driver" % "2.11.1",
      "org.jongo" % "jongo" % "0.4",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.2.1" % "optional",
      "org.apache.lucene" % "lucene-core" % "4.6.0",
      "org.apache.lucene" % "lucene-analyzers-common" % "4.6.0",
      "org.apache.lucene" % "lucene-queryparser" % "4.6.0"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      javacOptions ++= Seq("-source", "1.6", "-target", "1.6", "-encoding", "UTF-8")
    )

}
