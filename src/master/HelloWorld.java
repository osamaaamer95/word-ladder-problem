package master;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;

import com.google.gson.*;


public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		
		int count = 0;
		
		try {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(new FileReader("dictionary/dictionary.json"));
		JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
		Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
		for (Map.Entry<String, JsonElement> entry: entries) {
		    System.out.println(entry.getKey());
		    count++;
		}

		}
		catch(FileNotFoundException e) {
			
		}
		
		System.out.print(count);
	}

}
