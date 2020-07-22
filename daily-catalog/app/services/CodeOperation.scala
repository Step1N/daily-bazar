package services

import scala.annotation.tailrec

object CodeOperation {

  def fib(n: Int) = {
    @tailrec
    def fibRec(n: Int, a: Int, b: Int): Int = n match {
      case 0 => a
      case _ => fibRec(n - 1, b, a + b)
    }

    fibRec(n, 0, 1)
  }

  def main(args: Array[String]): Unit = {
    println(fib(5))
  }
}
