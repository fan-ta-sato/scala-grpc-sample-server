#!/bin/sh

GRPCURL=/usr/local/bin/grpcurl

SRC_ROOT=$HOME/Git/GitHub/fan-ta-sato/proto-sample
PROTO_DIR=$SRC_ROOT/src/main/protobuf

REQ=$SRC_ROOT/sample/hello.proto.txt
SERVER=localhost:50051

cat $REQ | $GRPCURL -plaintext -d @ -import-path $PROTO_DIR -proto hello.proto $SERVER com.example.protos.Greeter/SayHello

