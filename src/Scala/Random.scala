import scala.util.Random


object Random {
  def main(args: Array[String]) {
    val wordPerMessage = 4
    var i = 0
    while (i < 10) {
      /*
        1.the (1 to 1) is meaning that only have one circulation.
        */
      (1 to 1).foreach { messageNum => {
        //[There's only three cycle]
        val str: Seq[String] = (1 to wordPerMessage).map(x => scala.util.Random.nextInt(10).toString)
        val str1 = str.mkString(" ") //separate str1 with space
        println(str)
      }
      }
      i = i + 1

    }
  }
}
