package my.foo.patterns.creational

object SingletonTest extends App {
	val x = Just1Apple
	val y = Just1Apple
	x match {
	  case Just1Apple => println("equals")
	  case _ => println("not equals")
	}
}

case object Just1Apple {
  println("I am an Singleton Apple")
}