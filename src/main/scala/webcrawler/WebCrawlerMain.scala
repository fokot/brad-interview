package webcrawler

import webcrawler.WebModel.Url

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Try

object WebCrawlerMain extends App {

  val webCrawler: WebCrawler = new SimpleWebCrawler with WithInMemoryDatabase

  val crawledPages = Future {

    webCrawler.crawlDeep(Url("https://dennikn.sk/"))
  }


  Try(Await.result(crawledPages, 10 seconds))

  println("finito")

  InMemoryDatabase.getAllPages().foreach(println)
}
