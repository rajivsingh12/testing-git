package my.foo.patterns.creational

object BuilderTest extends App {
  val x = new ClassBuilder().build
  println(x.returnSome)

  SimpleCaseClass() withIntParam(0) withStringParam("") returnSome
  
}

trait ClassesTrait {
  def returnSome(): String
}

//object ClassBuilder { //Not thread-safe
class ClassBuilder {
  var intParam = 0
  var stringParam = ""

  def build = new SimpleClass

  def withIntParam(intParam: Int) = {
    this.intParam = intParam
    this
  }

  def withStringParam(stringParam: String) = {
    this.stringParam = stringParam
    this
  }

  private[patterns] class SimpleClass extends ClassesTrait {
    val intParam: Int = this.intParam
    val stringParam: String = this.stringParam
    override def returnSome(): String = "Hey I am initizialized with intParam : " + intParam + " stringParam " + stringParam
  }

}

case class SimpleCaseClass(intParam: Int, stringParam: String) extends ClassesTrait {
  override def returnSome(): String = "Hey I am initizialized with intParam : " + intParam + " stringParam " + stringParam

  def this() = this(0, "")
  def withIntParam(intParam: Int) = this.copy(intParam)
  def withStringParam(stringParam: String) = this.copy(stringParam=stringParam)
}

object SimpleCaseClass {
  def apply() = new SimpleCaseClass 
}

class TypeSafeBuilderPattern {
  //BuilderPattern above find builder errors like uninitialized instances or wrong types only at run time and not at compile time.   
}