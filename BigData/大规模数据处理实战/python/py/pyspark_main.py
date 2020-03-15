from pyspark.sql import SparkSession
from pyspark.sql.functions import *

if __name__ == "__main__":
    spark = SparkSession.builder.appName(appName).getOrCreate()
    lines = spark.read.text("sample.txt")
    wordCounts = lines.select(explode(split(lines.value, " ")).alias("word")).groupBy("word").count()
    wordCounts.show()

    spark.stop()
