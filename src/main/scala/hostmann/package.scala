import sun.jvm.hotspot.opto.Block

package object hostmann {
    def runTime(block: => Unit): Unit = {
        val time = System.currentTimeMillis()
        block         
        println(f"Executed in ${(System.currentTimeMillis() - time)/1000.0} seconds")
    }
}
