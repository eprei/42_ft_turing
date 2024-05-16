val scala3Version = "3.4.1"
val circeVersion = "0.14.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "ft_turing",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scala-lang" %% "toolkit" % "0.4.0",
    libraryDependencies += "com.lihaoyi" %% "upickle" % "3.3.0",
      libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.9.3",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.5",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
  )


