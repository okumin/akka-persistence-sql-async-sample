name := "akka-persistence-sql-async-sample"

version := "1.0"

scalaVersion := "2.11.7"

lazy val loggingDebug = (project in file("logging-debug"))
  .settings(commonSettings: _*)
  .settings(
    name := "logging-debug"
  )

lazy val loggingWarning = (project in file("logging-warning"))
  .settings(commonSettings: _*)
  .settings(
    name := "logging-warning"
  )

lazy val commonSettings = Seq(
  scalaVersion := "2.11.7",
  libraryDependencies ++= Seq(
    "com.okumin" %% "akka-persistence-sql-async" % "0.3.1",
    "com.github.mauricio" %% "mysql-async" % "0.2.16",
    "com.typesafe.akka" %% "akka-cluster" % "2.4.0"
  )
)
