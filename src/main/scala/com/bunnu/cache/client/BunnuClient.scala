package com.bunnu.cache.client

import java.util.logging.Logger

import akka.actor.{ActorSystem, Status}
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


/**
  * Created by nmupp on 12/29/16.
  */
object BunnuClient extends App {
  private implicit val timeOut = Timeout(5 seconds)
  private val logger = Logger.getLogger("BunnuClient")
  private implicit val system = ActorSystem("LocalSystem", ConfigFactory.load.getConfig("BunnuCacheServiceLookup"))
  private val remoteDb = system.actorSelection("akka.tcp://BunnuDB@127.0.0.1:2552/user/BunnuDB")
  private implicit val executionContext = ExecutionContext.global

  val future = remoteDb ? "unknown message"
  future.onSuccess {
    case Status.Success => logger.info("Cache the value successfully")
    case s:Status.Failure => println(s"Failed to cache the value ${s.cause.getMessage}")
  }
}
