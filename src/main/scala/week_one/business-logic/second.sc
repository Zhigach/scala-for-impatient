enum Shape:
  case Diamond, Oval, Squiggle

enum Color:
  case Red, Green, Purple

enum Shading:
  case Open, Striped, Solid

enum Number:
  case One, Two, Three

case class Card(shape: Shape, number: Number, color: Color, shading: Shading)

val deck = List(
  Card(Shape.Diamond, Number.One, Color.Purple, Shading.Solid),
  Card(Shape.Squiggle, Number.Two, Color.Red, Shading.Open),
  Card(Shape.Oval, Number.Three, Color.Green, Shading.Solid)
)

def checkShapeProperty(card1: Card, card2: Card, card3: Card): Boolean =
  def allSame =
    card1.shape == card2.shape && card2.shape == card3.shape
  def allDifferent =
    card1.shape != card2.shape &&
      card1.shape != card3.shape &&
      card2.shape != card3.shape
  allSame || allDifferent

def checkNumberProperty(card1: Card, card2: Card, card3: Card): Boolean =
  def allSame =
    card1.number == card2.number && card2.number == card3.number
  def allDifferent =
    card1.number != card2.number &&
      card1.number != card3.number &&
      card2.number != card3.number
  allSame || allDifferent

def checkShadingProperty(card1: Card, card2: Card, card3: Card): Boolean =
  def allSame =
    card1.shading == card2.shading && card2.shading == card3.shading
  def allDifferent =
    card1.shading != card2.shading &&
      card1.shading != card3.shading &&
      card2.shading != card3.shading
  allSame || allDifferent

def checkColorProperty(card1: Card, card2: Card, card3: Card): Boolean =
  def allSame =
    card1.color == card2.color && card2.color == card3.color
  def allDifferent =
    card1.color != card2.color &&
      card1.color != card3.color &&
      card2.color != card3.color
  allSame || allDifferent


def isValidSet(card1: Card, card2: Card, card3: Card): Boolean =
  checkShapeProperty(card1, card2, card3) &&
    checkNumberProperty(card1, card2, card3) &&
    checkShadingProperty(card1, card2, card3) &&
    checkColorProperty(card1, card2, card3)

isValidSet(
  Card(Shape.Diamond, Number.One, Color.Purple, Shading.Solid),
  Card(Shape.Squiggle, Number.Two, Color.Red, Shading.Open),
  Card(Shape.Oval, Number.Three, Color.Green, Shading.Solid)
)