package serial;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;

public class JSONGenerator {
	
	public static void generate(){
		Random random = new Random();
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		
		for(int i = -1; i < random.nextInt(5); i++){
			JSONObject shape = new JSONObject();
			shape.put("Name","Circle");
			shape.put("Radius", random.nextInt(20));
			list.add(shape);
		}
		for(int i = -1; i < random.nextInt(5); i++){
			JSONObject shape = new JSONObject();
			shape.put("Name","Ellipse");
			shape.put("Radius", random.nextInt(20));
			shape.put("OtherRadius", random.nextInt(20));
			list.add(shape);
		}
		for(int i = -1; i < random.nextInt(5); i++){
			JSONObject shape = new JSONObject();
			shape.put("Name","Square");
			shape.put("Width", random.nextInt(20));
			list.add(shape);
		}
		for(int i = -1; i < random.nextInt(5); i++){
			JSONObject shape = new JSONObject();
			shape.put("Name","Rectangle");
			shape.put("Width", random.nextInt(20));
			shape.put("Height", random.nextInt(20));
			list.add(shape);
		}
		for(int i = -1; i < random.nextInt(5); i++){
			JSONObject shape = new JSONObject();
			shape.put("Name","Triangle");
			shape.put("Width", random.nextInt(20));
			shape.put("Height", random.nextInt(20));
			list.add(shape);
		}
		for(int i = -1; i < random.nextInt(5); i++){
			JSONObject shape = new JSONObject();
			shape.put("Name","ScaleneTriangle");
			shape.put("Width", 3);
			shape.put("Height", 6);
			shape.put("Length", 7);
			list.add(shape);
		}
		
		
	    try {
	    	Writer writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("JSON.txt"), "utf-8"));
	    	for(JSONObject obj : list){
	    		StringWriter out = new StringWriter();
	    		obj.writeJSONString(out);
	    	    String jsonText = out.toString();
	    	   writer.write(jsonText + '\n');
	    	}
	    	writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
