name := "my-project"
version := "0.0.0"
scalaVersion := "2.11.11"

val SparkVersion = "2.3.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"          % SparkVersion % "provided",
  "org.apache.spark" %% "spark-sql"           % SparkVersion % "provided",
  "org.scalatest"    %% "scalatest"           % "3.0.5" % "test",
  "com.holdenkarau"  %% "spark-testing-base"  % s"${SparkVersion}_0.10.0" % "test"
)

parallelExecution in Test := false
fork in Test := true
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
