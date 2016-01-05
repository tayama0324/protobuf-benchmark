import com.google.protobuf.CodedOutputStream
import com.trueaccord.scalapb.GeneratedMessage
import java.io.ByteArrayOutputStream
import test.Test2
import test.Test2Sint

/**
 * Created by takashi_tayama on 2016/01/04.
 */
package object Util {

  def printMessage(message: GeneratedMessage) = {
    val bytes = message.toByteArray
    println(s"${message.toString} -> ${bytes.map(_.formatted("%02x")).mkString(" ")} (${bytes.length} bytes)")
  }

  def test2() = {
    def computeLength(message: GeneratedMessage): Int = {
      val bs = new ByteArrayOutputStream()
      val os = CodedOutputStream.newInstance(bs)

      0 until (1 << 23) foreach { _ =>
        os.writeRawVarint32(message.serializedSize)
        os.writeRawBytes(message.toByteArray)
      }
      os.flush()
      bs.size()
    }

    println("Test2: " + computeLength(Test2(-1, -2)))
    println("Test2Sint: " + computeLength(Test2Sint(-1, -2)))
  }
}
