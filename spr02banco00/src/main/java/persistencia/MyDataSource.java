package persistencia;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public abstract class MyDataSource {

	private static DataSource ds;
	
	private MyDataSource(){}
	
	public static DataSource getDataSource(){
		if (ds == null) {
			BasicDataSource bds = new BasicDataSource();
			Properties prop = new Properties();
			try (FileReader fr = new FileReader("src/main/resources/bbdd.properties")){
				prop.load(fr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			bds.setDriverClassName(prop.getProperty("bbdd.driver"));
			bds.setUrl(prop.getProperty("bbdd.url"));
		
			bds.setUsername(prop.getProperty("bbdd.user"));
			bds.setPassword(prop.getProperty("bbdd.pass"));
			ds = bds;
		}
		return ds;
	}
}
