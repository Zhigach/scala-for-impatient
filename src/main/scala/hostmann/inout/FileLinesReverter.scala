package hostmann.inout

import scala.io.Source

object FileLinesReverter {
    def reverse = {
        val source = Source.fromFile("/home/zal/IdeaProjects/ScalaProjects/test/hello-world/src/main/scala/hortsmann/inout/testFile.txt")
        val out = new java.io.PrintWriter("testOut.txt")
        val lines = source.getLines.toArray
        for (i <- lines.indices.reverse) out.println(lines(i))

        source.close
        out.close()
    }
}
