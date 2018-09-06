package org.ludwiggj.serialization.shenanigans

import java.io._
import java.util.Base64

import org.ludwiggj.serialization.shenanigans.batch.{BatchRunnerModule, LazyLoggingStub}
import org.ludwiggj.serialization.shenanigans.lazarus.{LazarusLazyLoggingStub, LazarusLazyLoggingStub2, LazarusLazyLoggingStub3}

object SerializationWorkout {

  def serializeRoundTrip(obj: Object): Object = {
    def convertToByteString(obj: Object): String = {
      val bos: ByteArrayOutputStream = new ByteArrayOutputStream()
      val out: ObjectOutput = new ObjectOutputStream(bos)
      out.writeObject(obj)
      val byteArray = bos.toByteArray()
      Base64.getEncoder().encodeToString(byteArray)
    }

    def convertFromByteString(byteString: String): Object = {
      val bytes = Base64.getDecoder().decode(byteString)
      val bis: ByteArrayInputStream = new ByteArrayInputStream(bytes)
      val in: ObjectInput = new ObjectInputStream(bis)
      in.readObject()
    }

    try {
      println(s"\n         Object type: [${obj.getClass}]")
      println(s"      Initial object: [$obj]")

      val serialisedString = convertToByteString(obj)
      println(s"   Serialized object: [$serialisedString]")

      val reconstitutedObj = convertFromByteString(serialisedString)
      println(s"Reconstituted object: [$reconstitutedObj]\n\n\n\n\n")

      reconstitutedObj
    } catch {
      case e: Exception => {
        println("Darn:\n" + e)
        e.printStackTrace()
        null
      }
    }
  }

  trait EmptyTrait

  trait SerializableEmptyTrait extends scala.Serializable

  trait AnotherSerializableEmptyTrait extends SerializableEmptyTrait

  trait SerializableTrait extends scala.Serializable {
    def doIt(): Unit
  }

  trait SerializableTrait2 extends SerializableTrait {
    override def doIt() = {
      println("Yowzers")
    }
  }

  def main(args: Array[String]): Unit = {
    def stringy: Unit = {
      serializeRoundTrip("This is round trip 1")
    }

    def mt: Unit = {
      object Mt extends EmptyTrait
      // TODO NOTE: This trait isn't serializable
      serializeRoundTrip(Mt)
    }

    def serializableMt = {
      object SerializableMt extends SerializableEmptyTrait
      serializeRoundTrip(SerializableMt)
    }

    def anotherSerializableMt: Unit = {
      object AnotherSerializableMt extends AnotherSerializableEmptyTrait
      serializeRoundTrip(AnotherSerializableMt)
    }

    def serializableT: Unit = {
      object SerializableT extends SerializableTrait {
        override def doIt(): Unit = {
          println("Yo")
        }
      }
      serializeRoundTrip(SerializableT)
    }

    def serializableT2: Unit = {
      object SerializableT2 extends SerializableTrait2 {
        override def doIt(): Unit = {
          println("YoYo")
        }
      }
      serializeRoundTrip(SerializableT2)
    }

    def lazyLoggingStub: Unit = {
      object LazyLoggingStubby extends LazyLoggingStub
      serializeRoundTrip(LazyLoggingStubby)
    }

    def batchRunner: Unit = {
      object BatchRunner extends BatchRunnerModule with LazyLoggingStub
      // This is OK
      serializeRoundTrip(BatchRunner)

      // TODO NOTE: This isn't serializable!
      serializeRoundTrip(BatchRunner.logger)
    }

    def lazarusLazyLoggingStub: Unit = {
      object LazarusLazyLoggingStubby extends LazarusLazyLoggingStub

      println(s"Logger in [${LazarusLazyLoggingStubby.logger}]")

      val reconstituted = serializeRoundTrip(LazarusLazyLoggingStubby)

      // TODO NOTE: Logger NOT reconstituted
      println(s"Logger out [${reconstituted.asInstanceOf[LazarusLazyLoggingStub].logger}]")
    }

    def lazarusLazyLoggingStub2: Unit = {
      object LazarusLazyLoggingStubby2 extends LazarusLazyLoggingStub2

      println(s"Logger in [${LazarusLazyLoggingStubby2.logger}]")

      val reconstituted = serializeRoundTrip(LazarusLazyLoggingStubby2)

      // TODO NOTE: Logger NOT reconstituted
      println(s"Logger out [${reconstituted.asInstanceOf[LazarusLazyLoggingStub2].logger}]")
    }

    def lazarusLazyLoggingStub3: Unit = {
      val lazarus3LazyLoggingStubby = new LazarusLazyLoggingStub3

      println(s"Logger in [${lazarus3LazyLoggingStubby.logger}]")

      val reconstituted = serializeRoundTrip(lazarus3LazyLoggingStubby)

      // TODO NOTE: Logger IS reconstituted!
      println(s"Logger out [${reconstituted.asInstanceOf[LazarusLazyLoggingStub3].logger}]")
    }

    // Start here
    stringy
    mt
    serializableMt
    anotherSerializableMt
    serializableT
    serializableT2
    lazyLoggingStub
    batchRunner
    lazarusLazyLoggingStub
    lazarusLazyLoggingStub2
    lazarusLazyLoggingStub3
  }
}