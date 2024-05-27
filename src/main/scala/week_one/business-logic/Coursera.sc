case class Experience(
                       duration: Int,
                       definition: Double,
                       network: Network
                     )

enum Network:
  case Mobile, Fixed

val lowQuality = 0.3
val highQuality = 0.6

val thirtyMinutes = 30 * 60

val highQualityMobile: Experience =
  Experience(thirtyMinutes, highQuality, Network.Mobile)

val lowQualityFixed =
  Experience(thirtyMinutes, lowQuality, Network.Fixed)

val dataCenterEnergy = 0.000072

val conversionFactor = 0.5

def networkEnergy(network: Network): Double =
  network match
    case Network.Fixed => 0.00043
    case Network.Mobile => 0.00088

def footprint(experience: Experience): Double =
  val megabytes = experience.duration * experience.definition
  val energy = dataCenterEnergy + networkEnergy(experience.network)
  energy * megabytes * conversionFactor

footprint(lowQualityFixed)

footprint(highQualityMobile)