import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object SparkOnHive {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]" ).appName("SparkApp").enableHiveSupport().getOrCreate()
    val sparkSession=SparkSession.builder()
          .config("spark.sql.warehouse.dir", "/data/hive/data")
          .getOrCreate()
    //日志输出
    spark.sparkContext.setLogLevel("ERROR")
        val gg =sparkSession.sql("show databases")
        val ug =sparkSession.sql("use hive")
        val sh =sparkSession.sql("show tables")


       val in = sparkSession.sql("insert into test values (8,'fakebob','20220425')")
    spark.sql("select * from test").show()
//        val nums = 1.to(100)
//        for(in <- nums)
//          print(in)

//    spark.sql("with test1 as " +
//      "(select a.id,a.name,a.create_date from test a left join test b on a.id = b.id)" +
//      "select 'AFlag' as flag,a.id,a.name,a.create_date from test1 a" +
//      " union all" +
//      " select 'BFlag' as flag,a.id,a.name,a.create_date from test1 a").show()


    //通过引入隐式转换可以将RDD的操作添加到DataFrame上
    import spark.implicits._




    // 关闭会话
    spark.close()


  }
}
