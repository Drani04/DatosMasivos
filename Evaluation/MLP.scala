//Cargamos las librerias que utilizaremos
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.linalg.Vectors
    
//Disminuye errores
Logger.getLogger("org").setLevel(Level.ERROR)
    
//Creamos sesion de spark
val spark = SparkSession.builder().getOrCreate()
    
//Cargamos nuestro dataset
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Creamos un vector donde seleccionaremos las columnas a utilizar de nuestro dataset
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(data)
  
//Indexamos la columna "y" para poder utilizar los si y no como 0 y 1   
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val dataIndexed = labelIndexer.fit(features).transform(features)
  
//Dividimos nuestro dataset en porciones de 70% y 30% para el entrenamiento y prueba de nuestro modelo
val split = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 1234L)

//Asignamos 70% del total al entrenamiento
val train = split(0)

//Asignamos el 30% del total a las pruebas
val test = split(1)

//Asignamos el valor a las capas de nuestro modelo
val layers = Array[Int](5, 2, 3, 2)

//Creamos un entrenador para nuestro modelo con sus parametros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//Entrenamos nuestro modelo con el entrenamiento creado arriba
val model = trainer.fit(train)

//Proyecta resultados de nuestro modelo
val result = model.transform(test)
    
// creamos predicciones con columnas prediction y label
val predictionAndLabels = result.select("prediction", "label")

//visualizamos
predictionAndLabels.show(10)

//muestra la eficiencia del modelo
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Accuracy test = ${evaluator.evaluate(predictionAndLabels)}")

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