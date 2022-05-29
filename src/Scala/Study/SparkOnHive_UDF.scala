package Study

import org.apache.spark.sql.SparkSession


object SparkOnHive_UDF {
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

//    TODO UDF : 在SQL中使用自定义的函数，完成逻辑的处理
    spark.udf.register("Conact",(name:String) => {
      "Fuck: " + name
    })

    spark.sql("select id , trim(Conact(name)) as Conact_name , create_date from dim_user").show()

    // 关闭会话
    spark.close()
  }
}
