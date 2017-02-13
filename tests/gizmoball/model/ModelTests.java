package gizmoball.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.*;

import gizmoball.model.BuildModel;
import gizmoball.model.Model;
import gizmoball.model.SyntaxError;

public class ModelTests {
	
	BuildModel model;
	
	@Before
	public void initialise() {
		model = new Model(20, 20);
	}
	
	@Test
	public void testLoadModel() throws IOException, SyntaxError {
		String modelString = "";
		try {
			FileReader modelFileReader = new FileReader("sampleModelFile.gzm");
			BufferedReader modelBufferedReader = new BufferedReader(modelFileReader);
			String line = null;
			while((line = modelBufferedReader.readLine()) != null) {
				modelString += line;
			}
			modelBufferedReader.close();
			
			InputStream modelInputStream = new ByteArrayInputStream(modelString.getBytes(StandardCharsets.UTF_8));
			model.load(modelInputStream);
			//assertTrue(true);
		//} catch (FileNotFoundException e) {
			//assertFalse(true);
		//}
		//catch (IOException e) {
			//assertFalse(true);
		}
		catch(SyntaxError e) {
			System.out.println(e.getMessage());
			//assertFalse(true);
		}
	}
	
	@Test
	public void testSaveModel() {
		model.addBall("B0");
		model.addCircle("C1");
		model.addSquare("S2");
		model.addTriangle("T3");
		model.addRightFlipper("RF0");
		model.addLeftFlipper("LF0");
		System.out.println(model.save().toString());
	}
	
}
