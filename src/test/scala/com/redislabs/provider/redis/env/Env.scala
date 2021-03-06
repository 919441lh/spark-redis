package com.redislabs.provider.redis.env

import com.redislabs.provider.redis.RedisConfig
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

trait Env {

  val conf: SparkConf
  var spark: SparkSession = _
  var sc: SparkContext = _

  val redisHost = "127.0.0.1"
  val redisPort = 6379
  val redisAuth = "passwd"
  val redisConfig: RedisConfig
}

