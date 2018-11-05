package com.redislabs.provider.redis.df

import com.redislabs.provider.redis.util.Person._
import org.apache.spark.SparkException
import org.apache.spark.sql.redis._
import org.scalatest.Matchers
import com.redislabs.provider.redis.util.TestUtils._

/**
  * @author The Viet Nguyen
  */
trait BinaryDataframeSuite extends RedisDataframeSuite with Matchers {

  test("save and load dataframe with binary mode") {
    val tableName = generateTableName(TableNamePrefix)
    val df = spark.createDataFrame(data)
    df.write.format(RedisFormat)
      .option(SqlOptionModel, SqlOptionModelBinary)
      .option(SqlOptionTableName, tableName)
      .save()
    val loadedDf = spark.read.format(RedisFormat)
      .option(SqlOptionModel, SqlOptionModelBinary)
      .option(SqlOptionTableName, tableName)
      .load()
      .cache()
    verifyDf(loadedDf)
  }

  test("save with binary mode and load dataframe") {
    val tableName = generateTableName(TableNamePrefix)
    val df = spark.createDataFrame(data)
    df.write.format(RedisFormat)
      .option(SqlOptionModel, SqlOptionModelBinary)
      .option(SqlOptionTableName, tableName)
      .save()
    intercept[SparkException] {
      spark.read.format(RedisFormat)
        .option(SqlOptionTableName, tableName)
        .load()
        .show()
    }
  }

  test("save and load with binary mode dataframe") {
    val tableName = generateTableName(TableNamePrefix)
    val df = spark.createDataFrame(data)
    df.write.format(RedisFormat)
      .option(SqlOptionTableName, tableName)
      .save()
    intercept[SparkException] {
      spark.read.format(RedisFormat)
        .option(SqlOptionModel, SqlOptionModelBinary)
        .option(SqlOptionTableName, tableName)
        .load()
        .show()
    }
  }
}
