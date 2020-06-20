//Cargamos librerias a utilizar
import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, OneHotEncoder}
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.classification.LinearSVC
import org.apache.log4j._

//Reducción de errores
Logger.getLogger("org").setLevel(Level.ERROR)

//Creamos nuestra sesion de spark
val spark = SparkSession.builder().getOrCreate()

// Cargamos nuestro dataset
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Creamos un vector donde almacenaremos las columnas que vamos a utilizar con el nombre de features
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("features")

//Aplicamos indexer la columna Y para que los valores de si y no los tome como valores numericos y podamos trabajar con ellos
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")

//Dividimos nuestro dataset en 70 y 30
val Array(training, test) = data.randomSplit(Array(0.7, 0.3), seed = 11L)

//Generamos nuestro prediccion con LinearSVC
val lsvc = new LinearSVC().setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setMaxIter(10).setRegParam(0.1)

//se crea un nuevo pipeline
val pipeline = new Pipeline().setStages(Array(labelIndexer,assembler, lsvc))

// ajustamos el modelo
val model = pipeline.fit(training)

// usamos el modelo con el 30% de los datos
val result = model.transform(test)

//Guardamos resultados de las variables seleccionadas
val predictionAndLabelsrdd = result.select("prediction", "label")

//Muestra predicciones
predictionAndLabelsrdd.show(5)

// resultados en el conjunto Text 
val predictionAndLabelsrdd = result.select($"prediction", $"label").as[(Double, Double)].rdd


//inicialice un objeto multiclassMetrics
val metrics = new MulticlassMetrics(predictionAndLabelsrdd)

// imprime la precision del algoritmo
println(s"Accuaracy Test = ${(metrics.accuracy)}")

//Tiempo de ejecucion
val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d

//Memoria usada
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)
