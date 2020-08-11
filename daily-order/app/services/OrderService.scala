package services

import javax.inject.{Inject, Singleton}

import models.Order

@Singleton
class OrderService {

  def createOrder(order: Order): Order = {
    Order(1, 2, "2Kg", None, "ready", None, true)
  }

  def placeOrder(): Order = {
    println("I am placing order")
    Order(1, 2, "2Kg", None, "ready", None, true)
  }

}
