package com.felipecsl.elifut

import akka.actor.{Actor, ActorLogging}
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class ItemsRequestingActor(
    sendAndReceive: HttpRequest => Future[HttpResponse],
    implicit val ec: ExecutionContext
) extends Actor with ActorLogging {

  private val baseUrl = "https://www.easports.com/fifa/ultimate-team/api/fut/item"

  def receive: PartialFunction[Any, Unit] = {
    case page =>
      val uri = baseUrl + "?jsonParamObject=%7B\"page\":" + page + "%7D"
      val s = sender()
      sendAndReceive(HttpRequest(uri = uri)).onComplete {
        case Success(response) => s ! response
        case Failure(exception) => s ! exception
      }
  }
}