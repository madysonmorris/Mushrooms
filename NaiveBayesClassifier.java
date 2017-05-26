/*
Naive Bayes Classifier for Mushroom Problem
CSCI 183 Final Project
Tiffany Palmer
Madyson Morris
Erin Gates
*/

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;


public class NaiveBayesClassifier {

    //public static final int FEATURES = 22;
    private int totalMushroomCount;
    public class CategoryStats {

        public int mushroomsInCategory;
        public Double priorProb;
        public Double baseAccum;

        // a treemap for each feature specification
        //public TreeMap<Character, Integer> featureTF;

        // each mushroom within this category's feature freq
        public ArrayList<TreeMap<Character, Integer>> featureList;

        public CategoryStats() {
            mushroomsInCategory = 0;
            //featureTF = new TreeMap<Character, Integer>();
            featureList = new ArrayList<TreeMap<Character,Integer>>();
        }  

    }

    private TreeMap<Boolean, CategoryStats> data;

    // constructor for NaiveBayesClassifier
    public NaiveBayesClassifier() {

        totalMushroomCount = 0;
        data = new TreeMap<Boolean, CategoryStats>();

    }

   public void trainMushroom(Mushroom shroom) {
      totalMushroomCount++;
      
      // Add mushroom classification category (poisonous=false, edible=true) to data 
      Boolean cat = shroom.getCategory();
      if (!data.containsKey(cat)) data.put(cat, new CategoryStats());
      CategoryStats currentCat = data.get(cat);
      
      currentCat.priorProb = ((double) currentCat.mushroomsInCategory)/((double) totalMushroomCount);
      currentCat.baseAccum = Math.log(currentCat.priorProb);
      data.put(cat, currentCat);
   
   
      char[] attributes = shroom.attributes;
      
      for (int i=0; i<attributes.length; i++) {
   	   char currFeature = attributes[i];
   	   
   	   int count = currentCat.featureList.get(i).containsKey(currFeature) ? currentCat.featureList.get(i).get(currFeature) : 0;
   	   currentCat.featureList.get(i).put(currFeature, count + 1);
      }  
   
    //currentCat.featureCount = currentCat.featureTF.size();
    }

   public Boolean classify(Character[] attributes) {

       Boolean answer = false;

       Boolean category = false;
       char search;
       double largeAccum = 0.0;
       double accum = 0.0;
       int attributeFreq = 0;
       
       Iterator<Boolean> itr = data.keySet().iterator();
       
       while (itr.hasNext()) {
   	   category = itr.next();
   	   CategoryStats currCat = data.get(category); // iterate through categories
   	   accum = currCat.baseAccum;
   	   for (int i = 0; i<attributes.length; i++) {
   	      search = attributes[i];
      	   if (currCat.featureList.get(i).containsKey(search)) {
      	       attributeFreq = currCat.featureList.get(i).get(search) + 1;
      	       accum += Math.log((double)attributeFreq/1.0);  //currCat.termCount);
	         }
   
         }
      	if (accum > largeAccum) {
      	    answer = category;
      	    largeAccum = accum;
	      }
    }
    
    return answer;
  }

}
