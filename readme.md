# Shitty webcrawler I wrote in 1 hour

Run by running **WebCrawlerMain**

The first crawling page and duration of crawling can be set like:

    val crawledPages = Future {

      webCrawler.crawlDeep(Url("https://dennikn.sk/"))
    }
    Try(Await.result(crawledPages, 10 seconds))

It will output all urls it crawled to the console.