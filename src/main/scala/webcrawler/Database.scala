package webcrawler

import webcrawler.WebModel._

trait Database {

  def savePage(page: Page): Unit

  def getPage(url: Url): Page

  def getAllPages(): List[Url]
}

object InMemoryDatabase extends Database {

  var pages = Map[Url, Page]()

  override def savePage(page: Page) = {
    pages = pages + (page.url -> page)
  }

  override def getPage(url: Url) = pages(url)

  override def getAllPages() = pages.keys.toList
}

trait WithDatabase {
  def db: Database
}

trait WithInMemoryDatabase extends WithDatabase {
  val db = InMemoryDatabase
}