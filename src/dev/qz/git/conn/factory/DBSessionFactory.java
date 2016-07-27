package dev.qz.git.conn.factory;

/**
 * 
 * -----------------------------------------------------------------------
 * configure factory and return implements instance of session of database
 * -----------------------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dev.qz.git.common.exception.KeyObjectNullException;
import dev.qz.git.conn.conf.DatabaseSelection;
import dev.qz.git.conn.enums.DBSelection;
import dev.qz.git.conn.impl.DBSessionImpl;

public class DBSessionFactory {

	private Connection connection = null;

	private DBSelection dbSelection = null;

	private static class DBSessionFactoryGen {

		private static final DBSessionFactory INSTANCE = new DBSessionFactory();

	}

	public static final DBSessionFactory getInstance() {

		return DBSessionFactoryGen.INSTANCE;

	}

	private DBSessionFactory() {

		Context envContext = null;

		try {

			envContext = (Context) new InitialContext()
					.lookup("java:/comp/env");

		} catch (NamingException e1) {

			// TODO Auto-generated catch block
			e1.printStackTrace();

			return;

		}

		if (envContext != null) {

			DataSource dataSource = null;

			try {

				dbSelection = DatabaseSelection.getInstance().getDBType();

				if (dbSelection == DBSelection.PGSQL) {

					dataSource = (DataSource) envContext.lookup("jdbc/pgsqlds");

				}

				if (dbSelection == DBSelection.MYSQL) {

					dataSource = (DataSource) envContext.lookup("jdbc/mysqlds");

				}

				if (dbSelection == DBSelection.ORACLE) {

					dataSource = (DataSource) envContext
							.lookup("jdbc/oracleds");

				}

			} catch (NamingException e1) {

				// TODO Auto-generated catch block
				e1.printStackTrace();

				return;

			}

			if (dataSource == null) {

				try {

					throw new KeyObjectNullException(
							"datasource is null pls check the conf node in any contex.xml");

				} catch (KeyObjectNullException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

					return;

				}

			}

			try {

				connection = dataSource.getConnection();

			} catch (SQLException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				return;

			}

		} else {

			try {

				throw new KeyObjectNullException(
						"the env context is null ,pls check the conf file");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	}

	public DBSessionImpl getDBSession(boolean transactionConf) {

		if (connection == null) {

			try {

				throw new KeyObjectNullException(
						"the connection can not be init correctly pls check");

			} catch (KeyObjectNullException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				return null;

			}

		} else {

			return new DBSessionImpl(connection, transactionConf);

		}

	}

}
