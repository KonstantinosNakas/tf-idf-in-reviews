import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.ArrayList;

public class Collection {
	
	private String inputFile;
	private String outputFile;
	private ArrayList<Document> texts = new ArrayList<Document>();
	private HashMap<String, Integer> DF = new HashMap<String, Integer>();
	

	public Collection (String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}
	
	public void process() {
		Scanner inputStream = null;
		PrintWriter outputStream = null;
		try{
			inputStream = new Scanner(new FileInputStream(inputFile));
			outputStream = new PrintWriter(new FileOutputStream(outputFile));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening files.");
			System.exit(0);
		}
		String line = null;
		while (inputStream.hasNextLine( )){
			line = inputStream.nextLine( );
			Document doc = new Document(line);
			doc.process(DF);
			texts.add(doc);
		}
		for (int i=0; i<texts.size(); i++) {
			texts.get(i).computeTFIDF(DF);
			outputStream.println(texts.get(i).toString());
			outputStream.println("");
		}
		
		inputStream.close( );
		outputStream.close( );
	}
	
}
