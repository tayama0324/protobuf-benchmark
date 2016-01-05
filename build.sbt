import com.trueaccord.scalapb.{ScalaPbPlugin => PB}


name := "protobuf-benchmark"

version := "1.0"

scalaVersion := "2.11.7"

PB.protobufSettings

PB.runProtoc in PB.protobufConfig := { args =>
  com.github.os72.protocjar.Protoc.runProtoc("-v300" +: args.toArray)
}

watchSources ++= (((sourceDirectory in Compile).value / "protobuf") ** "*.proto").get
