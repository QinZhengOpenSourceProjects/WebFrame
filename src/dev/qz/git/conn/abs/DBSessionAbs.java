package dev.qz.git.conn.abs;

/**
 * 
 * -------------------------------------------------------------------------------
 * system session with database,not the httpsession define class,read with caution
 * -------------------------------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * 
 * this interface defines the basic CRUD method prototype
 * 
 */

import java.util.List;
import java.util.Map;

public interface DBSessionAbs {

	// query database using preparedstatement with params and return the list of
	// row key value hashmap
	public List<Map<String, Object>> queryOps(String preparedStmtString,
			Object[] params);

	// insert data into database using preparedstatement with params the int
	// return value is status code
	public int insertOps(String preparedStmtString, Object[] params);

	// update data of database using preparedstatement with params the int
	// return value is status code
	public int updateOps(String preparedStmtString, Object[] params);

	// delete data from database using preparedstatement with params the int
	// return value is status code
	public int deleteOps(String preparedStmtString, Object[] params);

}
