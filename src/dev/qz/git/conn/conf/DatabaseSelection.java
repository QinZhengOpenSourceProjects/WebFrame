package dev.qz.git.conn.conf;

/**
 * ----------------------------------------------------------------------------------
 * database selection factory return only one instance of database selection instance
 * ----------------------------------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * 
 * gen the enum value loading the conf file
 * 
 */

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dev.qz.git.conn.enums.DBSelection;

public class DatabaseSelection {

	private Properties properties = new Properties();

	private String PropFilePath = null;

	private String strPGSQL = null;

	private String strMYSQL = null;

	private String strORACLE = null;

	private static class DatabaseSelectionFactory {

		private static final DatabaseSelection INSTANCE = new DatabaseSelection();

	}

	public static final DatabaseSelection getInstance() {

		return DatabaseSelectionFactory.INSTANCE;

	}

	private void InitParam() {

		strMYSQL = "MYSQL";

		strPGSQL = "PGSQL";

		strORACLE = "ORACLE";

		PropFilePath = "db.properties";

	}

	//private constructor load the result
	private DatabaseSelection() {

		InitParam();

		try {

			InputStream inputStream = new BufferedInputStream(this.getClass()
					.getClassLoader().getResourceAsStream(PropFilePath));

			properties.load(inputStream);

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	//get the database conf result
	public DBSelection getDBType() {

		if (!properties.isEmpty()) {

			String type = properties.getProperty("DB", "PGSQL");

			if (type.equals(strMYSQL)) {

				return DBSelection.MYSQL;

			}

			if (type.equals(strPGSQL)) {

				return DBSelection.PGSQL;

			}

			if (type.equals(strORACLE)) {

				return DBSelection.ORACLE;

			}

		}

		return null;

	}

}
