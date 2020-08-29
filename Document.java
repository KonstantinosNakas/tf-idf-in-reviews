import java.util.HashMap;
import java.lang.Math;

public class Document {

	private String text;
	private String[] words;
	private HashMap<String, Integer> TF = new HashMap<String, Integer>();
	private HashMap<String, Double> TF_IDF = new HashMap<String, Double>();
	
	public Document(String text) {
		this.text = text;
	}
	
	public void process(HashMap<String, Integer> DF) {
		words = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		int val;
		for (int i=0; i<words.length; i++) {
			if (!TF.containsKey(words[i])){ 
				TF.put(words[i],1); 
				if (!DF.containsKey(words[i])){ 
					DF.put(words[i],1); 
				}else {
					val = DF.get(words[i]);
					val++;
					DF.remove(words[i]);
					DF.put(words[i],val);
				}
			}else {
				val = TF.get(words[i]);
				val++;
				TF.remove(words[i]);
				TF.put(words[i],val);
			}
		}
	}
	
	public void computeTFIDF(HashMap<String, Integer> DF) {
		for (int i=0; i<words.length; i++) {
			double val;
			if (!TF_IDF.containsKey(words[i])) {
				val =  TF.get(words[i])*Math.log(15/DF.get(words[i]));
				TF_IDF.put(words[i],val);
			}
		}
	}
	
	public String toString() {
		String s = "";
		for (int i=0; i<words.length; i++) {
			s = s + words[i] + ":" + TF_IDF.get(words[i]) + " ";
		}
		return s;
	}
	
}
