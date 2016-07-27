package dev.qz.git.conn.impl;

/**
 * ---------------------------------------------------------------------------------------------
 * the key implements of session with database,CRUD methods are defined here,get through factory
 * ---------------------------------------------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * 
 * CRUD methods define for the improvement of reuse of database ops code
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dev.qz.git.common.exception.KeyObjectNullException;
import dev.qz.git.conn.abs.DBSessionAbs;

public class DBSessionImpl implements DBSessionAbs {

	private Connection connection = null;

	private boolean transactionConf = false;

	public DBSessionImpl(Connection connection, boolean transactionConf) {

		this.connection = connection;

		this.transactionConf = transactionConf;

	}

	// update database data
	@Override
	public int updateOps(String preparedStmtString, Object[] params) {

		int result = 0;

		PreparedStatement preparedStatement = null;

		if (connection != null) {

			try {

				preparedStatement = connection
						.prepareStatement(preparedStmtString);

				int loopCount = 0;

				for (Object param : params) {

					loopCount++;

					preparedStatement.setObject(loopCount, param);

				}

				result = preparedStatement.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				result = -1;

				if (transactionConf) {

					this.rollBack();

				}

				this.close(connection, preparedStatement, null);

			}

		} else {

			try {

				throw new KeyObjectNullException(
						"database connection is null in update method,pls check");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				result = -1;

				if (transactionConf) {

					this.rollBack();

				}

			}

		}

		return result;

	}

	// basic result query;
	@Override
	public List<Map<String, Object>> queryOps(String preparedStmtString,
			Object[] params) {

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		ResultSetMetaData resultSetMetaData = null;

		List<Map<String, Object>> resultList = null;

		if (connection != null) {

			try {

				preparedStatement = connection
						.prepareStatement(preparedStmtString);

				int count = 0;

				for (Object paramObject : params) {

					count++;

					preparedStatement.setObject(count, paramObject);

				}

				resultSet = preparedStatement.executeQuery();

				resultSetMetaData = resultSet.getMetaData();

				resultList = new LinkedList<Map<String, Object>>();

				while (resultSet.next()) {

					Map<String, Object> tmpMap = new HashMap<String, Object>();

					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {

						tmpMap.put(resultSetMetaData.getColumnName(i + 1),
								resultSet.getObject(i + 1));

					}

					resultList.add(tmpMap);

				}

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				if (transactionConf) {

					this.rollBack();

				}

				this.close(connection, preparedStatement, null);

			}

		} else {

			try {

				throw new KeyObjectNullException(
						"connection is null in query ops,pls check");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				if (transactionConf) {

					this.rollBack();

				}

			}

		}

		return resultList;

	}

	// basic delete ops
	@Override
	public int deleteOps(String preparedStmtString, Object[] params) {

		int result = 0;

		PreparedStatement preparedStatement = null;

		if (connection != null) {

			try {
				preparedStatement = connection
						.prepareStatement(preparedStmtString);

				int count = 0;

				for (Object param : params) {

					count++;

					preparedStatement.setObject(count, param);

				}

				result = preparedStatement.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				result = -1;

				if (transactionConf) {

					this.rollBack();

				}

				this.close(connection, preparedStatement, null);

			}

		}

		else {

			try {

				throw new KeyObjectNullException(
						"connection is null in insert method,pls check");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				result = -1;

				if (transactionConf) {

					this.rollBack();

				}

			}

		}

		return result;

	};

	// basic row record insert
	@Override
	public int insertOps(String preparedStmtString, Object[] params) {

		int result = 0;

		PreparedStatement preparedStatement = null;

		if (connection != null) {

			try {

				preparedStatement = connection
						.prepareStatement(preparedStmtString);

				int loopCount = 0;

				for (Object param : params) {

					loopCount++;

					preparedStatement.setObject(loopCount, param);

				}

				result = preparedStatement.executeUpdate();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				result = -1;

				if (transactionConf) {

					this.rollBack();

				}

				this.close(connection, preparedStatement, null);

			}

		} else {

			try {

				throw new KeyObjectNullException(
						"connection is null in insert method,pls check");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				result = -1;

				if (transactionConf) {

					this.rollBack();

				}

			}

		}

		return result;

	}

	// for other class easy ops
	public void close() {

		close(connection, null, null);

	}

	// in exception condition,close all the possible bad connections ,prepared
	// statements,result sets
	public void close(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {

		try {

			if (!connection.isClosed()) {

				connection.close();

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {

			if (preparedStatement != null) {

				if (!preparedStatement.isClosed()) {

					preparedStatement.close();

				}

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		try {

			if (resultSet != null) {

				if (!resultSet.isClosed()) {

					resultSet.close();

				}

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	// set transaction start method if in transaction mode
	public void beginTransaction() {

		if (transactionConf) {

			try {

				this.connection.setAutoCommit(false);

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	}

	// set commit transaction method
	public void commitTransaction() {

		try {

			if (!this.connection.getAutoCommit()) {

				this.connection.commit();

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	// set exception roll back method
	public void rollBack() {

		try {

			if (!this.connection.getAutoCommit()) {

				this.connection.rollback();

				this.connection.setAutoCommit(true);

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
