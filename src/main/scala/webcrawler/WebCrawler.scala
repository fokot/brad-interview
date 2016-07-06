package webcrawler

import org.htmlcleaner.HtmlCleaner
import webcrawler.WebModel._

import scala.io.Source

trait WebCrawler {

  def crawl(url: Url): List[Url]

  def crawlDeep(url: Url): Unit
}


// this can be implemented as thread or actor pool
// so multiple requests can be run in parallel and it will be throttled
class SimpleWebCrawler extends WebCrawler {
  self: WithDatabase =>

  override def crawl(url: Url) = {
    val pageData = Source.fromURL(url.value).getLines().mkString

    db.savePage(Page(url, Data(pageData)))

    val cleaner = new HtmlCleaner
    val props = cleaner.getProperties
    val rootNode = cleaner.clean(pageData)

    import collection.JavaConverters._

    val l: List[String] = rootNode.getElementsByName("a", true).toList.map(_.getAttributeByName("href"))
    l.map(Url)
  }

  // this would be better to send to queue or url than to crawl recursivelly
  override def crawlDeep(url: Url) =
    crawl(url)
      .filterNot(db.getAllPages().contains(_))
      .foreach(crawlDeep)
}           g