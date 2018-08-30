name := "scalaScratch"

version := "0.1"

scalaVersion := "2.12.6"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.mockito" % "mockito-core" % "2.8.9"
)