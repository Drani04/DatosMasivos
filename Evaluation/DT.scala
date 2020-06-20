//Cargamos librerias a utilizar
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.log4j._
import org.apache.spark.ml.PipelineStage
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler

//Reducción de errores
Logger.getLogger("org").setLevel(Level.ERROR)

//Creamos nuestra sesion de spark
val spark = SparkSession.builder().getOrCreate()

//Importamos nuestro DataSet
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Creamos nuestro label indexer para comparar 
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)

//Inicializamos el vector asembler por datos de tipo numericos y agregamos la columna features como output 
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
val features = assembler.transform(data)

// Identifica categoricamente nuestro dataset en vector 
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(features)

//Dividimos nuestro dataset en 70% entrenamiento y 30% de prueba
val Array(trainingData, testData) = features.randomSplit(Array(0.7, 0.3))

//Creamos un objeto DecisionTree
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

//Rama de prediccion
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

//Juntamos los datos en un pipeline
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

//creamos modelo de entrenamiento
val model = pipeline.fit(trainingData)

//Transformacion de datos en el modelo
val predictions = model.transform(testData)

//Desplegamos predicciones
predictions.select("predictedLabel", "y").show(5)

//se genera el modelo de arbol
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

//Evaluamos la eficiencia del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

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