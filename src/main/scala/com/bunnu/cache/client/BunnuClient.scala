package com.bunnu.cache.client

import akka.actor.ActorSystem
import com.bunnu.cache.messages.GetRequest
import akka.util.Timeout

import scala.concurrent.duration._
import akka.pattern.ask
import com.typesafe.config.ConfigFactory


/**
  * Created by nmupp on 12/29/16.
  */
class BunnuClient {
  private implicit val timeOut = Timeout(5 seconds)
  private val config = ConfigFactory.load.getConfig("BunnuCacheServiceLookup")
  private implicit val system = ActorSystem("LocalSystem",config)
  private val remoteDb = system.actorSelection(s"akka.tcp://BunnuCacheService@127.0.0.1:2552/user/BunnuDB")

  def get(key:String) = {
    remoteDb ? GetRequest(key)
  }

}
