import sbt._
import Keys._

object Dependencies {
    val jflex = "edu.umass.cs.iesl" % "jflex-scala" % "1.6.1" % "compile"

    val commonDeps = Seq(
        jflex
    )
}

object BuildSbtJFlex extends Build {
    import Dependencies._

    val sbtAntlr = Project(
        id = "sbt-jflex-scala",
        base = file("."),

        settings = Defaults.defaultSettings ++ Seq(
            organization := "edu.umass.cs.iesl",
            version := "0.1",
            sbtPlugin := true,
            publishMavenStyle := true,

            scalacOptions := Seq("-deprecation", "-unchecked"),

            libraryDependencies ++= commonDeps,

            publishTo <<= version { (v: String) =>
              val nexus = "https://oss.sonatype.org/"
              if (v.trim.endsWith("SNAPSHOT")) 
                Some("snapshots" at nexus + "content/repositories/snapshots") 
                else
                  Some("releases"  at nexus + "service/local/staging/deploy/maven2")
                },
                publishArtifact in Test := false,
                pomIncludeRepository := { _ => false },
                pomExtra := (
                  <url>http://github.com/strubell/sbt-jflex-scala</url>
                  <licenses>
                    <license>
                      <name>Apache 2</name>
                      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
                      <distribution>repo</distribution>
                    </license>
                  </licenses>
                  <scm>
                    <url>git@github.com:strubell/sbt-jflex-scala.git</url>
                    <connection>scm:git@github.com:strubell/sbt-jflex-scala.git</connection>
                  </scm>
                  <developers>
                    <developer>
                      <id>strubell</id>
                      <name>Emma Strubell</name>
                      <url>http://cs.umass.edu/~strubell/</url>
                    </developer>
                  </developers>
                )

        )
    )
}
