package my.foo.patterns.structural

object DecoratorTest extends App {

  /* import java_style._
  val text = new TextField(10, 20)
  val clientDesign1 = new BorderDecorator(new TextDecorator(new ScrollDecorator(text)))
  clientDesign1.draw

  val circle = new TextField(10, 20)
  val clientDesign2 = new BorderDecorator(new TextDecorator(new ScrollDecorator(circle)))
  clientDesign2.draw*/

  //Scala traits mixins alternative is clean  
  import scala_mixin_style._
  val textClient = new TextField(10, 20) with BorderDecorator with TextDecorator with ScrollDecorator
  textClient.draw
}

trait Widget {
  def draw()
}

class TextField(x_axis: Int, y_axis: Int) extends Widget {
  override def draw() = {
    println("Creating TextField: " + "x_axis " + x_axis + " y_axis " + y_axis)
  }
}

class Circle(center_x_axis: Int, center_y_axis: Int, radius: Int) extends Widget {
  override def draw() = {
    println("Creating Circle: " + "center_x_axis " + center_x_axis + " center_y_axis " + center_y_axis + " radius " + radius)
  }
}

//Java style decorators
package java_style {

  abstract class Decorator(wid: Widget) extends Widget {
    def draw() = wid.draw
  }

  class BorderDecorator(wid: Widget) extends Decorator(wid) {
    override def draw() = {
      super.draw
      println("BorderDecoration Done")
    }
  }

  class TextDecorator(wid: Widget) extends Decorator(wid) {
    override def draw() = {
      super.draw
      println("TextDecoration Done")
    }
  }

  class ScrollDecorator(wid: Widget) extends Decorator(wid) {
    override def draw() = {
      super.draw
      println("ScrollDecoration Done")
    }
  }

}

package scala_mixin_style {

  trait BorderDecorator extends Widget {
    abstract override def draw() = {
      super.draw
      println("BorderDecoration Done")
    }
  }

  trait TextDecorator extends Widget {
    abstract override def draw() = {
      super.draw
      println("TextDecoration Done")
    }
  }

  trait ScrollDecorator extends Widget {
    abstract override def draw() = {
      super.draw
      println("ScrollDecoration Done")
    }
  }

}