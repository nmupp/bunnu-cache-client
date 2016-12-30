package com.bunnu.cache.client

import akka.actor.Status
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

import scala.concurrent.ExecutionContext

/**
  * Created by nmupp on 12/30/16.
  */
class BunnuClientSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {

  implicit val context = ExecutionContext.global

  describe("cache client") {
    it("cache the value in db server") {
      val client = new BunnuClient
      val future = client.get("unknown message")
      future.onSuccess {
        case result:Status.Failure => result.cause should equal("Unknown message")
      }
      Thread.sleep(7000)
    }
  }

}
