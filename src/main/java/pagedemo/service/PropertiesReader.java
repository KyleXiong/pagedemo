package pagedemo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public static void main(String[] args) {
		Properties props = new Properties();
		
		InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			//InputStream input = new FileInputStream("D:\\kyle\\workspace\\pagedemo\\src\\main\\java\\jdbc.properties");
			props.load(in);
			
			System.out.println(props.get("url"));
			System.out.println(props.get("name"));
			System.out.println(props.get("password"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
