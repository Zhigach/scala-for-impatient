package hostmann.inout

import scala.io.Source

object CountLongWords {
    def count = {
        val source = Source.fromFile("/home/zal/IdeaProjects/ScalaProjects/test/hello-world/src/main/scala/hortsmann/inout/testFile.txt")
        val q = "[a-zA-Z]{12}".r.findAllIn(source.mkString).size
        println(q)
    }

}
