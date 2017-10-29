package onextent.akka.quickstart

import onextent.akka.quckstart.teststuff.Fraction
import org.scalatest.prop.PropertyChecks
import org.scalatest._

class SomePropSpec extends FlatSpec with Matchers with PropertyChecks {

  // ejs docs say a hundred pairs will be tested but logging shows 10

  forAll { (n: Int, d: Int) =>
    whenever(
      d != 0 && d != Integer.MIN_VALUE
        && n != Integer.MIN_VALUE) {

      val f = new Fraction(n, d)

      if (n < 0 && d < 0 || n > 0 && d > 0)
        f.numer should be > 0
      else if (n != 0)
        f.numer should be < 0
      else
        f.numer should equal (0)

      f.denom should be > 0
    }
  }

  val invalidCombos =
    Table(
      ("n", "d"),
      (Integer.MIN_VALUE, Integer.MIN_VALUE),
      (1, Integer.MIN_VALUE),
      (Integer.MIN_VALUE, 1),
      (Integer.MIN_VALUE, 0),
      (1, 0)
    )

  forAll(invalidCombos) { (n: Int, d: Int) =>
    an [IllegalArgumentException] should be thrownBy {
      new Fraction(n, d)
    }

  }

}
