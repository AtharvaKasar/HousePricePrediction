import java.util.*;
import java.io.*;

public class LinearRegressionFunction implements Function<Double[], Double>{
   private final double[] thetaVector;

   LinearRegressionFunction(double[] thetaVector) {
      this.thetaVector = Arrays.copyOf(thetaVector, thetaVector.length);
   }

   public double apply(Double[] featureVector) {
     
	   assert featureVector[0] == 1.0;

      // simple, sequential implementation
      double prediction = 0;
      for (int j = 0; j < thetaVector.length; j++) {
    	// System.out.println("THETA VECTOR [j] " + thetaVector[j]);
    	// System.out.println("FEATURE VECTOR [j] " + featureVector[j]);
    	// System.out.println("MULT " + thetaVector[j] * featureVector[j]);
        // System.out.println("J's " + thetaVector[j] * featureVector[j]);
         
    	  prediction += thetaVector[j] * featureVector[j];
    	 // System.out.println("PRediction " + prediction);
      }
      return prediction;
   }

   public double[] getThetas() {
      return Arrays.copyOf(thetaVector, thetaVector.length);
   }
   
   public static double cost(Function<Double[], Double> targetFunction,
           List<Double[]> dataset,
           List<Double> labels) {
	int m = dataset.size();
	System.out.println("M: " + m);
	double sumSquaredErrors = 0;
	
	// calculate the squared error ("gap") for each training example and add it to the total sum
	for (int i = 0; i < m; i++) {
	// get the feature vector of the current example
	Double[] featureVector = dataset.get(i);
	// predict the value and compute the error based on the real value (label)
	double predicted = targetFunction.apply(featureVector);
	//System.out.println("COST PREDICTION: " + predicted);
	double label = labels.get(i);
	//System.out.println("COST LABEL: " + label);
	double gap = predicted - label;
	//System.out.println("GAP: " + gap);
	//System.out.println("SQUARED: " + Math.pow(gap, 2));
	sumSquaredErrors += Math.pow(gap, 2);
	}

	// calculate and return the mean value of the errors (the smaller the better)
	return (1.0 / (2 * m)) * sumSquaredErrors;
	}
   
   //                 TRAIN
   public static LinearRegressionFunction train(LinearRegressionFunction targetFunction,
           List<Double[]> dataset,
           List<Double> labels,
           double alpha) {
				int m = dataset.size();
				double[] thetaVector = targetFunction.getThetas();
				double[] newThetaVector = new double[thetaVector.length];
				
				// compute the new theta of each element of the theta array
				for (int j = 0; j < thetaVector.length; j++) {
				// summarize the error gap * feature
				double sumErrors = 0;
				for (int i = 0; i < m; i++) {
				Double[] featureVector = dataset.get(i);
				double error = targetFunction.apply(featureVector) - labels.get(i);
				sumErrors += error * featureVector[j];
				}
				
				// compute the new theta value
				double gradient = (1.0 / m) * sumErrors;
				System.out.println("GRADIENT: " + gradient);
				newThetaVector[j] = thetaVector[j] - alpha * gradient;
				System.out.println(newThetaVector[j]);
				}
				
				

	return new LinearRegressionFunction(newThetaVector);
	}

   //                 END TRAIN  
   
   public static void main (String[] args) throws FileNotFoundException{
	
	
	   
	   
	   
	   // the theta vector used here was output of a train process
	   double[] thetaVector = new double[] { 1.0, 2.0, 300.0};
	   LinearRegressionFunction targetFunction = new LinearRegressionFunction(thetaVector);
	   
	   //inputting stuff into list
	   List<Double[]> realDataSet = new ArrayList<>();
	   List<Double> labels = new ArrayList<>();
	   
	   
	   Scanner in = new Scanner(new File("LinearRegressionFunction.in"));
	   int n = in.nextInt();
	   for(int i = 0;i<n;i++) {
		   Double sqFt = (double)in.nextInt();
		   Double bedrooms = (double)in.nextInt();
		   Double price = (double)in.nextInt();
		   Double[] temp = new Double[3];
		   temp[0] = 1.0;
		   temp[1] = bedrooms;
		   temp[2] = sqFt;
		   realDataSet.add(temp);
		   labels.add(price);
	   }
	   
	   
	  
	
	

	   
	   double predictedCost = cost(targetFunction, realDataSet, labels);
	   System.out.println("Predicted Cost/Error: " + predictedCost);

	   // create the feature vector function with x0=1 (for computational reasons) and x1=house-size
	   Double[] featureVector = new Double[] { 1.0,3.0,  1330.0 };

	   // make the prediction
	   double predictedPrice = targetFunction.apply(featureVector);
	   
	   System.out.println("Initial Predicted Price: " + predictedPrice);
	   
	   
	   //               EXECUTION OF TRAIN
	   LinearRegressionFunction newFunction = train(targetFunction, realDataSet,labels, 0.00000005);
	   
	   thetaVector = newFunction.getThetas();
	   for(int i = 0; i<newFunction.getThetas().length;i++) {
		   System.out.println(i + " " + thetaVector[i]);
	   }
	   //                  END OF EXECUTION OF TRAIN
	   
	   //newFunction = new LinearRegressionFunction(thetaVector);
	   
	   System.out.println("NEW PRICE");
	   double newPrice = newFunction.apply(featureVector);
	   System.out.println(newPrice);
	   
	   in.close();
	   
	   PrintWriter out = new PrintWriter(new File("LinearRegressionFunction.out"));
	    //System.out.println(newPrice);
	    out.println("$" + newPrice);
	    out.close();

	   
   }




}
