import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object SparkSession_Builder_01 {
  def main(args: Array[String]): Unit = {

    //TODO Spark环境
    //TODO Spark的构造过程比较繁琐，所以不能直接构建
    //TODO builder构建器设计模式,这是目前比较主流的构建方法
    //    SparkSession.builder().master("local[*]").appName("SparkSQL")

    val conf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val spark: SparkSession = SparkSession.builder().config(conf).getOrCreate()
    //日志输出
    spark.sparkContext.setLogLevel("ERROR")

    //通过引入隐式转换可以将RDD的操作添加到DataFrame上
    import spark.implicits._

    //通过spark.read操作读取JSON数据
    val df = spark.read.json("C:\\Users\\MLT\\Desktop\\Bob\\00_Idea_Project\\Spark-SQL\\src\\Scala\\user.json")
    df.createOrReplaceTempView("users")
    //通过spark.sql方法来运行正常的SQL语句
    spark.sql("with test as " +
        "(select * from test) " +
      (", test1 as (select * from test union all select * from test)")).show()
    
    // 关闭会话
    spark.close()

  }
}
