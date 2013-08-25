package my.foo.patterns.structural

object BridgeTest extends App {

  val sonyRemoteWithFavorite = new RemoteWithFavorite {
    val tvImplementor = new SonyTV()
  }
  
  val sharpRemoteWithNews = new RemoteWithNews {
    val tvImplementor = new SharpTV()
  }
  
  val panasonicRemoteWithFavorite = new  RemoteWithFavorite {
    val tvImplementor = new PanasonicTV()
  }
}

trait TV {
  def on
  def off
  def tuneN(n: Int)
}

class SonyTV extends TV {
  def on = println("Sony On")
  def off = println("Sony OFF")
  def tuneN(n: Int) = println("Sony tuned to " + n)
}

class SharpTV extends TV {
  def on = println("Sharp ON")
  def off = println("Sharp OFF")
  def tuneN(n: Int) = println("Sharp tuned to " + n)
}

class PanasonicTV extends TV {
  def on = println("Panasonic On")
  def off = println("Panasonic OFF")
  def tuneN(n: Int) = println("Panasonic tuned to " + n)
}

trait Remote {
  val tvImplementor: TV

  def switchOffTV = {
    tvImplementor.on
  }

  def switchOnTV = {
    tvImplementor.off
  }
  
  def setChannel(n: Int) = {
    tvImplementor.tuneN(n)
  }
}

abstract class RemoteWithFavorite extends Remote {
  var favorite: Int = 0
  def setFavoriteChannel(n: Int) = {favorite=n}
  def goToFavorite = setChannel(favorite)
}

abstract class RemoteWithNews extends Remote {
  var news: Int = 0
  def setNewsChannel(n: Int) = {news=n}
  def setNewsFavorite = setChannel(news)
}
