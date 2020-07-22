package services

import scala.annotation.tailrec


trait Mix {
  var t = 0

  def s = 0

}

object PlayWithMixin extends Mix {

  def increaseVals(): Unit = {
    t = t + 1
    println(t)
  }

  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case x :: xs => x + sum(xs)
  }

  val reverse = (s: String) => s.reverse

  val toUpper = (s: String) => s.toUpperCase

  val appendBar = (s: String) => s + "bar"

  @tailrec
  def applyTransformation(initial: String, transformations: Seq[String => String]): String =
    transformations match {
      case head :: tail => applyTransformation(head(initial), tail)
      case Nil => initial
    }
  def applyTransformations(initial: String, transformations: Seq[String => String]) =
    transformations.foldLeft(initial) {(cur, transformation) => transformation(cur)}

  def main(args: Array[String]): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numberFunc = numbers.foldLeft(List[Int]())_
    val squares = numberFunc((xs, x) => xs:+ x*x)
    println(squares.toString())
    val list = List(1, 2, 3, 4)
    println(sum(list))
    println((applyTransformations("rest", List(reverse, toUpper, appendBar))))

  }
}
