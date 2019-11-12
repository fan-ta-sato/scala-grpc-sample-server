import sbt._

object Versions {
  val Scala = "2.13.0"
  val ScalaPB = "0.9.4"
  val GrpcNetty = "1.25.0"
}

object Dependencies {
  lazy val grpc = Seq(
    Libraries.grpcNetty,
    Libraries.scalapbRuntimeGrpc,
  )
}

object Libraries {
  lazy val grpcNetty = "io.grpc" % "grpc-netty" % Versions.GrpcNetty
  lazy val scalapbRuntimeGrpc =  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % Versions.ScalaPB
}
