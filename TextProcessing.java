
public class TextProcessing {
	
	public static void main(String[] args) {
		
		String inputFile = "reviews.txt";
		String outputFile = "TF-IDF.txt";
		
		Collection myColl = new Collection (inputFile,outputFile);
		myColl.process();
		
	}
	
}
