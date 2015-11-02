name := "Armor"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  "mysql" % "mysql-connector-java" % "5.1.29",
  cache
)     

play.Project.playScalaSettings
