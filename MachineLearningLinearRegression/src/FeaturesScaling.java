/*import java.util.*;

public class FeaturesScaling implements Function<Double[], Double>{

	// create the dataset
	ArrayList<Double[]> dataset = new ArrayList<>();
	dataset.add(new Double[] { 1.0,  90.0,  8100.0 });   // feature vector of house#1
	dataset.add(new Double[] { 1.0, 101.0, 10201.0 });   // feature vector of house#2
	dataset.add(new Double[] { 1.0, 103.0, 10609.0 });   // ...
	//...

	// create the labels
	List<Double> labels = new ArrayList<>();

	labels.add(249.0);        // price label of house#1
	labels.add(338.0);        // price label of house#2
	labels.add(304.0);        // ...
	//...

	// scale the extended feature list
	Function<Double[], Double[]> scalingFunc = FeaturesScaling.createFunction(dataset);
	List<Double[]>  scaledDataset  = dataset.stream().map(scalingFunc).collect(Collectors.toList());

	// create hypothesis function with initial thetas and train it with learning rate 0.1
	LinearRegressionFunction targetFunction =  new LinearRegressionFunction(new double[] { 1.0, 1.0, 1.0 });
	for (int i = 0; i < 10000; i++) {
	   targetFunction = Learner.train(targetFunction, scaledDataset, labels, 0.1);
	}


	// make a prediction of a house with size if 600 m2
	Double[] scaledFeatureVector = scalingFunc.apply(new Double[] { 1.0, 600.0, 360000.0 });
	double predictedPrice = targetFunction.apply(scaledFeatureVector);
	
	@Override
	public double apply(Double[] featureVector) {
		// TODO Auto-generated method stub
		return 0;
	}

}*/
