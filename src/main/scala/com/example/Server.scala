package com.example

import scala.concurrent.{Future, ExecutionContext}
import java.util.logging.Logger
import com.example.protos.hello._
import io.grpc.{Server, ServerBuilder}

object HelloWorldServer {
  private val logger = Logger.getLogger(classOf[HelloWorldServer].getName)

  def main(args: Array[String]): Unit = {
    val server = new HelloWorldServer(ExecutionContext.global)
    server.start()
    server.blockUntilShutdown()
  }

  private val port = 50051
}

@SuppressWarnings(
  Array("org.wartremover.warts.Var",
        "org.wartremover.warts.Null",
        "org.wartremover.warts.StringPlusAny",
        "org.wartremover.warts.NonUnitStatements"))
class HelloWorldServer(executionContext: ExecutionContext) { self =>
  private[this] var server: Server = null

  private def start(): Unit = {
    server = ServerBuilder
      .forPort(HelloWorldServer.port)
      .addService(GreeterGrpc.bindService(new GreeterImpl, executionContext))
      .build
      .start
    HelloWorldServer.logger.info(
      "Server started, listening on " + HelloWorldServer.port)
    sys.addShutdownHook {
      System.err.println(
        "*** shutting down gRPC server since JVM is shutting down")
      self.stop()
      System.err.println("*** server shut down")
    }
  }

  private def stop(): Unit = {
    if (server != null) {
      server.shutdown()
    }
  }

  private def blockUntilShutdown(): Unit = {
    if (server != null) {
      server.awaitTermination()
    }
  }

  private class GreeterImpl extends GreeterGrpc.Greeter {
    override def sayHello(req: HelloRequest) = {
      val reply = HelloReply(message = "Hello " + req.name)
      Future.successful(reply)
    }
  }

}
