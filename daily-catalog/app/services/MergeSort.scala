package services

import math.Ordering

object MergeSort {


  def mergSort[T](el: List[T])(implicit  ord: Ordering[T]): List[T] = {
    val len = el.length / 2
    if (len == 0) el
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (ord.lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, sec) = el splitAt (len)
      merge(mergSort(fst), mergSort(sec))
    }
  }

  def main(args: Array[String]): Unit = {
    val nums = List(2, -4, 5, 7)
    // val mList = mergSort(nums)((x:Int, y:Int) => x < y)
    // Criteria for for implici
    // 1.is marked as implicit
    // 2.has type compatible with T
    // 3.is visible at any point of function call, or is define in a companion object associated with T
    val mList = mergSort(nums)
    println(mList)
    val alpha = List("a", "z", "d", "v")
    val sList = mergSort(alpha)
    print(sList)
  }

}
