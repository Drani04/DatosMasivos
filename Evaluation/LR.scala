// Cargamos librerias
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.Pipeline
    
// Disminución de errores
Logger.getLogger("org").setLevel(Level.ERROR)
    
// Creamos sesion de spark
val spark = SparkSession.builder().getOrCreate()
    
// Cargamos nuestro dataset
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")
 
//Creamos un vector donde colocaremos las columnas que queremos utilizar
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
    
//Indexamos la columna Y para que los valores de si y no tomen valor de 1 y 0
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val dataIndexed = labelIndexer.fit(data).transform(data)
    
// Dividimos los datos en 70% y 30% para el entrenamiento y prueba
val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 12345)

// creamos nuestro logistic regresion
val lr = new LogisticRegression()

// creamos nuestro pipeline
val pipeline = new Pipeline().setStages(Array(assembler,lr))

// creamos nuestro modelo ingresandole el 70% de los datos
val model = pipeline.fit(training)

// generamos los resultados
val results = model.transform(test)

// Generamos predicciones
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd

//creamos nuestro objeto evaluador para las multiclases de prediction and labels
val metrics = new MulticlassMetrics(predictionAndLabels)
    
//Imprimimos la matriz de confusion 
println(metrics.confusionMatrix)

//Muestra la eficacia del modelo
println(s"Accuracy = ${(metrics.accuracy)}")
println(s"Test Error = ${(1.0 - metrics.accuracy)}")

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