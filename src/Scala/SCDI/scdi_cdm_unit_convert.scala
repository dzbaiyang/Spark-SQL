package SCDI
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object scdi_cdm_unit_convert {
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



    spark.sql("insert into hive.cdm_unit_convert_sum \n" +
      "select \n\ta.sku_code,\n\t" +
      "sum(a.conversion_factor) as conversion_factor\n" +
      " from hive.cdm_unit_convert a\n" +
      "group by a.sku_code").show()



    //通过引入隐式转换可以将RDD的操作添加到DataFrame上
    import spark.implicits._




    // 关闭会话
    spark.close()
  }
}
