package my.foo.patterns.creational

object AbstractFactoryTest extends App with AbstractFactoryGenerator {
  val shape = getFactoryByType(CircleFactoryType).createInstance("")
  println(shape)
}

sealed class AbstractFactoryType
case object CircleFactoryType extends AbstractFactoryType
case object RectangleFactoryType extends AbstractFactoryType

trait AbstractFactoryGenerator {
  def getFactoryByType(factoryType: AbstractFactoryType): ShapeAbstractFactory = {
    factoryType match {
      case CircleFactoryType => CircularFactory
      case RectangleFactoryType => RectangularFactory
    }
  }
}

sealed trait Shape {
  //  def draw
}

trait ShapeAbstractFactory {
  def createInstance(shapeType: String): Shape
}

object CircularFactory extends ShapeAbstractFactory {
  def createInstance(circleType: String): Shape = Ball()
  private[CircularFactory] case class Ball extends Shape
  private[CircularFactory] case class Moon extends Shape
  private[CircularFactory] case class CropCircle extends Shape
}

object RectangularFactory extends ShapeAbstractFactory {
  def createInstance(rectangleType: String): Shape = Box()
  private[RectangularFactory] case class Box extends Shape
  private[RectangularFactory] case class Cube extends Shape
  private[RectangularFactory] case class Cuboid extends Shape
}