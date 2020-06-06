import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

// Load the data stored in LIBSVM format as a DataFrame.
val data = spark.read.format(&quot;libsvm&quot;).load(&quot;data/mllib/sample_libsvm_data.txt&quot;)
// Index labels, adding metadata to the label column.
// Fit on whole dataset to include all labels in index.
val labelIndexer = new StringIndexer().setInputCol(&quot;label&quot;).setOutputCol(&quot;indexedLabel&quot;).fit(data)
// Automatically identify categorical features, and index them.
val featureIndexer = new
VectorIndexer().setInputCol(&quot;features&quot;).setOutputCol(&quot;indexedFeatures&quot;).setMaxCategories(4)
// features with &gt; 4 distinct values are treated as continuous..fit(data)
// Split the data into training and test sets (30% held out for testing).
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
// Train a DecisionTree model.
val dt = new DecisionTreeClassifier().setLabelCol(&quot;indexedLabel&quot;).setFeaturesCol(&quot;indexedFeatures&quot;)

// Convert indexed labels back to original labels.
val labelConverter = new
IndexToString().setInputCol(&quot;prediction&quot;).setOutputCol(&quot;predictedLabel&quot;).setLabels(labelIndexer.labels)

// Chain indexers and tree in a Pipeline.val pipeline = new Pipeline().setStages(Array(labelIndexer,
featureIndexer, dt, labelConverter))
// Train model. This also runs the indexers.
val model = pipeline.fit(trainingData)
// Make predictions.
val predictions = model.transform(testData)

// Select example rows to display.
predictions.select(&quot;predictedLabel&quot;, &quot;label&quot;, &quot;features&quot;).show(5)
// Select (prediction, true label) and compute test error.
val evaluator = new
MulticlassClassificationEvaluator().setLabelCol(&quot;indexedLabel&quot;).setPredictionCol(&quot;prediction&quot;).setMetric
Name(&quot;accuracy&quot;)
val accuracy = evaluator.evaluate(predictions)
println(s&quot;Test Error = ${(1.0 - accuracy)}&quot;)
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s&quot;Learned classification tree model:\n ${treeModel.toDebugString}&quot;)