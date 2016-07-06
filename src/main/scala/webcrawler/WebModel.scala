package webcrawler

object WebModel {

  case class Url(value: String) extends AnyVal
  case class Data(value: String)

  case class Page(url: Url, data: Data)

}
