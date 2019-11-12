# scala-grpc-sample-server

by netty

## run server

```sh
$ sbt run
```

## grpc request

### install client(Mac)

```sh
$ brew install grpcurl
```

### request

```sh
$ sh ./sample/test.sh
```

or

```sh
$ grpcurl -plaintext -d '{"name": "F@N"}' -import-path ./src/main/protobuf -proto hello.proto localhost:50051 com.example.protos.Greeter/SayHello
```
