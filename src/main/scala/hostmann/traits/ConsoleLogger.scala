package hostmann.traits

trait ConsoleLogger extends Logger {
    def log(msg: String): Unit = println(msg)
}
