package my.foo.patterns.creational

object FactoryTest extends App {
  val ball = BallFactory.getOnebySide()
  println(ball.name)
}

abstract sealed class AbstractBall(val name: String)
case class LeftBall extends AbstractBall("the_left_ball")
case class RightBall extends AbstractBall("the_right_ball")
case class BadBall extends AbstractBall("no_ball")

object BallFactory {
  def getOnebySide(side: String = ""): AbstractBall = {
    side match {
      case "left" => LeftBall()
      case "right" => RightBall()
      case _ => BadBall()
    }
  }
}