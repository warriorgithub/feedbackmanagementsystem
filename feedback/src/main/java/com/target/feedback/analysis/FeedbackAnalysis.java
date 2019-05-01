package com.target.feedback.analysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class FeedbackAnalysis {

	private  static DoccatModel model = null;
	private static final String POSITIVE_FEEDBACK=" Positive";
	private static final String NEGATIVE_FEEDBACK=" Negetive";
	private static final String POSITIVE__BIT="1";
	private static final String FILE_NAME="/Users/naveens/nonproject/target/feedback/src/main/java/com/target/feedback/analysis/data.txt";

	public static String validateComment(String comment) {
		trainModel();
		String response = classifyNewText(comment);
		if(response.equalsIgnoreCase(NEGATIVE_FEEDBACK)) {
			//makeSystemSmarter(NEGATIVE__BIT,comment);
		}else {
			//makeSystemSmarter(POSITIVE__BIT,comment);
		}
		return response;
	}

	private static void makeSystemSmarter(String bit,String comment) {
		String textToAppend = "\r\n"+bit+" "+comment;   
		FileOutputStream outputStream=null;
		try {
			outputStream = new FileOutputStream(FILE_NAME, true);
			byte[] strToBytes = textToAppend.getBytes();

			outputStream.write(strToBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	private  static void trainModel() {
		MarkableFileInputStreamFactory  dataIn = null;
		File file = new File(FILE_NAME);
		
		try {

			dataIn = new MarkableFileInputStreamFactory(
					new File(FILE_NAME));
			
			ObjectStream<String> lineStream = null;
			lineStream = new PlainTextByLineStream(dataIn, StandardCharsets.UTF_8);
			ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

			TrainingParameters tp = new TrainingParameters();
			tp.put(TrainingParameters.CUTOFF_PARAM, "2");
			tp.put(TrainingParameters.ITERATIONS_PARAM, "30");

			DoccatFactory df = new DoccatFactory();
			model = DocumentCategorizerME.train("en", sampleStream, tp, df);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dataIn != null) {
				try {
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	private static String classifyNewText(String text){
		DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);

		double[] outcomes = myCategorizer.categorize( text.split(" ") );
		String category = myCategorizer.getBestCategory(outcomes);

		if (category.equalsIgnoreCase(POSITIVE__BIT)){
			return POSITIVE_FEEDBACK;
		} else {
			return NEGATIVE_FEEDBACK;
		}

	}
	public static void main(String[] args) throws IOException { 
		  trainModel();
	 
	  
	  }
}




  


