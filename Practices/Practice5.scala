package org.apache.spark.examples.ml
//Paso 1: carga de paquetes y API necesarios
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

/**
* Un ejemplo de clasificación de perceptrón multicapa.
*/
object MultilayerPerceptronClassifierExample
{
//Paso 2: crear una sesión de Spark
//Donde la clase es la siguiente:
def main(args: Array[String]): Unit = {
val spark =
SparkSession.builder.appName(&quot;MultilayerPerceptronClassifierExample&quot;).getO
rCreate()

//Paso 3: cargar y analizar el conjunto de datos

// Cargue los datos almacenados en formato LIBSVM como un
DataFrame.
//Aquí el almacén de Spark está configurado en &quot;/usr/local/spark-2.3.4-
bin-hadoop2.6/data/mllib/s&quot;, pero debe configurar su ruta en consecuencia
val data = spark.read.format(&quot;libsvm&quot;).load(&quot;/usr/local/spark-2.3.4-bin-
hadoop2.6/data/mllib/sample_multiclass_classification_data.txt&quot;)

//Paso 4: preparación del conjunto de entrenamiento y prueba
//Prepare el tren y el conjunto de prueba: entrenamiento =&gt; 60%, prueba
=&gt; 40% y semilla =&gt; 12345L
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

/*Paso 5: especifique las capas para la red neuronal
Especifique las capas para la red neuronal de la siguiente manera:
capa de entrada =&gt; tamaño 4(características),
dos capas intermedias (es decir, capa oculta)
de tamaño 5 y 4 y salida =&gt; tamaño 3 (clases). */
val layers = Array[Int](4, 5, 4, 3)

// Paso 6: cree el entrenador MultilayerPerceptronClassifier y establezca
sus parámetros

val trainer = new
MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1
234L).setMaxIter(100)

/*Paso 7: Entrene el modelo de clasificación de perceptrón multicapa
utilizando el estimador
Entrene el modelo de clasificación de perceptrón multicapa utilizando el
estimador anterior (es decir, entrenador) */
val model = trainer.fit(train)
//Paso 8: calcule la precisión en el conjunto de prueba
val result = model.transform(test)
val predictionAndLabels = result.select(&quot;prediction&quot;, &quot;label&quot;)
//Paso 9: evalúe el modelo para el rendimiento de predicción
val evaluator = new
MulticlassClassificationEvaluator().setMetricName(&quot;accuracy&quot;)

/*Paso 11: ajuste ficticio en caso de ser necesario
si el rendimiento del clasificador es bastante bajo. Una razón es que el
Perceptron es muy superficial
el tamaño del conjunto de datos también es más pequeño; por lo tanto,
debemos seguir intentando profundizar
al menos aumentando el tamaño de las capas ocultas.*/

println(s&quot;Test set accuracy =
${evaluator.evaluate(predictionAndLabels)}&quot;)
// $example off$

//Paso 10: detener la sesión de Spark
spark.stop()
}
}