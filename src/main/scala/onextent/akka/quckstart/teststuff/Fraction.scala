package onextent.akka.quckstart.teststuff

class Fraction(n: Int, d: Int) {

  require(d != 0)
  require(d != Integer.MIN_VALUE)
  require(n != Integer.MIN_VALUE)

  val numer: Int = if (d < 0) -1 * n else n
  val denom: Int = d.abs

  override def toString: String = numer + " / " + denom
}
