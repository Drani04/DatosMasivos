# Unit 4

## Index

- Introduction
- Theoretical framework
- Support Vector Machine	
- Decision trees
- Logistic Regresion
- Multilayer Perceptron
- Implementation
- Codes
- Results
- Conclusion
- References
- Colaborators

## Introduction

This project aims to use the scala programming language with the spark platform to test four data classification models which are; Support Vector Machine, Decision Three, Logistic Regression and Multilayer Perceptron, in order to see their performance when working on a dataset in common so we can see if one is better than the other or if they all serve equally.

## Theoretical framework
## Support Vector Machine	
They can be used for both regression and classification.
It is called "machine" in Spanish for the part "machine" learning. The support vectors are the points that define the maximum margin of separation of the hyperplane that separates the classes. They are called vectors, instead of points, because these "points" have as many elements as there are dimensions in our input space. That is, these multi-dimensional points are represented with an n-dimensional vector. [1]
The advantages of support vector machines are:
Effective in large spaces.
It remains effective in cases where the number of dimensions is greater than the number of samples.
It uses a subset of training points in the decision function (called support vectors), so it is also memory efficient.
Versatile: Different Kernel functions can be specified for the decision function. Common cores are provided, but it is also possible to specify custom cores.
The disadvantages of support vector machines include:
If the number of features is much greater than the number of samples, avoid over-tuning when choosing the core functions and the regularization term is crucial.
SVMs do not directly provide probability estimates, they are calculated using costly five-time cross-validation (see Scores and Odds, below).

## Decision trees


A decision tree is a graphical and analytical way of representing all the events that can arise from a decision made at a certain moment. They help us make the most "right" decision, from a probabilistic point of view, before a range of possible decisions.
These trees allow you to examine the results and visually determine how the model flows.
The decision trees feature in SPSS creates classification and decision trees to identify groups, discover relationships between groups, and predict future events. [2]
Decision trees are a data mining technique.
Prepare, probe and explore the data to get the information hidden in it. The solution to prediction, classification and segmentation problems is addressed.
Data mining techniques come from Artificial Intelligence and Statistics.
These techniques are nothing more than algorithms, more or less sophisticated, that are applied to a set of data to obtain results.
The most representative techniques are: neural networks, linear regression, decision trees, statistical models, grouping or clustering, and association rules. [3]
Decision trees create a classification model based on flow charts. They classify cases into groups or predict values of a dependent variable based on values of independent variables.

## Logistic Regresion


Logistic regression is a statistical instrument for multivariate analysis, for both explanatory and predictive use.
Its use is useful when you have a dichotomous dependent variable (an attribute whose absence or presence we have scored with the values zero and one, respectively) and a set of predictor or independent variables, which can be quantitative (called variables or covariates) or categorical.
In the latter case, it is required that they be transformed into “dummy” variables, that is, simulated variables.
The purpose of the analysis is to: predict the probability that a certain “event” will occur to someone:
for example, being unemployed = 1 or not = 0, being poor = 1 or not poor = 0, receiving a sociologist = 1 or not receiving = 0).
Determine which variables weigh the most to increase or decrease the probability that the event in question will happen to someone.
This assignment of probability of occurrence of the event to a certain subject, as well as the determination of the weight that each of the variables dependent on this probability, are based on the characteristics of the subjects to whom, effectively, these occur or not. events. [4]
For its part, the identification of the best logistic regression model is made by comparing models using the likelihood ratio, which indicates from the sample data how much more likely a model is than the other. The difference in the likelihood ratios between two models is distributed according to the Chi-square law with the degrees of freedom corresponding to the difference in the number of variables between the two models. If it cannot be demonstrated from this coefficient that one model is better than the other, it will be considered as the most suitable, the simplest. [5]

## Multilayer Perceptron

The Multilayer Perceptron is a Network of Artificial Neurons widely used at present for the resolution of problems of various kinds, among which you can find classification, regression and time series prediction problems.
The Multilayer Perceptron is a type of Network that is composed of several perceptrons and that allows more than two classes to be properly classified. These neurons are organized by layers, which transmit information from layer to layer.
The characteristic of this type of Network is that its connections are made from back to front and that also the neurons of the same layer are not related to each other, characteristics that do not occur in other types of Neural Networks such as Kohonen.

## Implementation

- We use scala as a programming language since it offers us many libraries that facilitate the handling of datasets, in addition to being a widely recognized language and easy to learn.

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/scala.png)

- We use spark as a framework since it is compatible with scala and offers us ease of operating with large amounts of data and is easy to use.

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/spark.png)

- As an operating system you use Windows 7 since it is the one we had but it can also be used in Linux.

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/w7.png)

## Codes
## SVM

We load libraries to use
```
import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, OneHotEncoder}
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.classification.LinearSVC
import org.apache.log4j._
```
Error reduction
```
Logger.getLogger("org").setLevel(Level.ERROR)
```
We create our spark session
```
val spark = SparkSession.builder().getOrCreate()
```
We load our dataset
```
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")
```
We create a vector where we will store the columns that we are going to use with the name of features
```
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
```
We apply indexing column Y so that the values ​​of yes and do not take them as numerical values ​​and we can work with them
```
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")
```
We divide our dataset into 70 and 30
```
val Array(training, test) = data.randomSplit(Array(0.7, 0.3), seed = 11L)
```
We generate our prediction with LinearSVC
```
val lsvc = new LinearSVC().setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setMaxIter(10).setRegParam(0.1)
```
A new pipeline is created
```
val pipeline = new Pipeline().setStages(Array(labelIndexer,assembler, lsvc))
```
we fit the model
```
val model = pipeline.fit(training)
```
we use the model with 30% of the data
```
val result = model.transform(test)
```
We save results of the selected variables
```
val predictionAndLabelsrdd = result.select("prediction", "label")
```
Show predictions
```
predictionAndLabelsrdd.show(5)
```
results in the Text set
```
val predictionAndLabelsrdd = result.select($"prediction", $"label").as[(Double, Double)].rdd
```
initialize a multiclassMetrics object
```
val metrics = new MulticlassMetrics(predictionAndLabelsrdd)
```
prints the precision of the algorithm
```
println(s"Accuaracy Test = ${(metrics.accuracy)}")
```
Execution time
```
val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d
```
Used memory
```
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)
```





## Results


In our first model (SVM) the following results were obtained:

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/LSVM.png)

As we can see, the efficiency and error remain constant while the execution time varies, in addition to the fact that the memory space it uses is very small.

In our second model (DT) the following results were obtained:

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/DT.png)

As we can see the efficiency and the error undergo a very minimal change on some occasions and the execution time also varies as does the memory it uses.

In our following model (LR) the following results were obtained:

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/LR.png)

Al igual que SVM conseguimos una eficiencia del 88% con una probabilidad de error del 11%, la ejecución al igual que en los otros 2 sigue variando y la memoria que utiliza también.

In our latest model (MLP) the following results were obtained:

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/MLP.png)

Same situation, efficiency and error remain at 88% and 11% while execution is variant as well as memory.


To understand these tables more simply, we put them together into one with the average of each model in the different characteristics:

![img](https://github.com/Drani04/DatosMasivos/blob/Unit-4/Images/Promedios.png)

We can see that although DT had more efficiency in some cases with 1 hundredth when averaging the values, all the models achieved the same amount of efficiency and error, however, at runtime it shows us that MLP is a slightly longer model and heavy so it takes 3 seconds longer than the others, in addition to using more memory than the others this due to its layer structure, where it processes a lot of information and therefore is more robust and time consuming.


## Conclusion

As we could see the four models are functional for this case, they had almost the same efficiency at all times, not counting the thousandths of difference, the loading time was not very high, and the memory space did not vary too much, however they all have its advantages and disadvantages, so it is good that we know more options when working with datasets

## References
[1]https://iartificial.net/maquinas-de-vectores-de-soporte-svm/#Formas_equivocadas_de_clasificar, 
[2]Berlanga, V., Rubio Hurtado, M. J., & Vilà Baños, R. (2013). Cómo aplicar árboles de decisión en SPSS. REIRE. Revista d'Innovació i Recerca en Educació, 2013, vol. 6, num. 1, p. 65-79.
[3]http://diposit.ub.edu/dspace/bitstream/2445/43762/1/618361.pdf
[4]Chitarroni, H. (2002). La regresión logística.
[5]Peláez, I. M. (2016). Modelos de regresión: lineal simple y regresión logística. Revista Seden.

## Colaborators
- [Hernandez Marín Betzibeth Magdalena](https://github.com/BetzibethHrdz)
- [Luis Daniel Lopez Valencia](https://github.com/Drani04)
