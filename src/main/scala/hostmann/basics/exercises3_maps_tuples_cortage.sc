val scores = Map("Alice" -> 10)
//scores("Alice") = 1 - forbidden
val mutableScores = scala.collection.mutable.Map("Bob" -> 12)
mutableScores.put("foo", 1)
mutableScores


val goods = Map("NVD" -> 150000.0, "Mercedes" -> 100500000.0, "Snickers" -> 15.5)

for ( (k, v) <- goods) yield (k, v * 0.9)
