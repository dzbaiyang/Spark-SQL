package GetAPI
import com.alibaba.fastjson.JSON
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils


object GetPost_HotVideo {
  def postResponse(url: String, params: String = null, header: String = null): String ={
    val httpClient = HttpClients.createDefault()    // 创建 client 实例
    val post = new HttpPost(url)    // 创建 post 实例

    // 设置 header
    if (header != null) {
      val json = JSON.parseObject(header)
      json.keySet().toArray.map(_.toString).foreach(key => post.setHeader(key, json.getString(key)))
    }

    if (params != null) {
      post.setEntity(new StringEntity(params, "UTF-8"))
    }

    val response = httpClient.execute(post)    // 创建 client 实例
    EntityUtils.toString(response.getEntity, "UTF-8")   // 获取返回结果
  }

  def main(args: Array[String]): Unit = {
    val url = "http://apis.juhe.cn/fapig/douyin/billboard"
    val postUrl = "http://apis.juhe.cn/fapig/douyin/billboard?key=8ca42647f0043d0dafcfdd4563f35ae3&type=hot_video"
    val params = """{"type":["hot_video"],"size":50}"""
    println("This post response: ")

    val header =
      """
        |{
        |    "Accept": "application/json;charset=UTF-8",
        |    "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
        |    "Connection": "keep-alive",
        |    "Content-Type": "application/json; charset=utf-8",
        |    "Authorization": "8ca42647f0043d0dafcfdd4563f35ae3"
        |}
      """.stripMargin

    // 需要token认证的情况，在header中加入Authorization
    println(postResponse(postUrl, params, header))

  }
}
