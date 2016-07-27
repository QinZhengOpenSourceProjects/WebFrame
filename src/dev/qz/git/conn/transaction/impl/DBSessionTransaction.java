package dev.qz.git.conn.transaction.impl;

/**
 * 
 * --------------------------------------------------------------------
 * transaction session class ,provide transaction function in framework
 * --------------------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * 
 * single transaction entity to provide the model
 * vector<transactionentity> for loop to implement the transaction
 * 
 */

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import dev.qz.git.conn.factory.DBSessionFactory;
import dev.qz.git.conn.impl.DBSessionImpl;
import dev.qz.git.conn.transaction.conf.OpsSelection;
import dev.qz.git.conn.transaction.entity.SingleTransactionEntity;

public class DBSessionTransaction {

	private Vector<SingleTransactionEntity> singleTransactionEntities = null;

	public DBSessionTransaction() {

		singleTransactionEntities = new Vector<SingleTransactionEntity>();

	}

	public void addSingleTransactionEntity(String preparedStmtString,
			Object[] params, OpsSelection opsType) {

		SingleTransactionEntity singleTransactionEntity = new SingleTransactionEntity();

		singleTransactionEntity.setPreparedStmtString(preparedStmtString);

		singleTransactionEntity.setPreparedStmtParamsObjects(params);

		singleTransactionEntity.setOpsTypeEnums(opsType);

		singleTransactionEntities.addElement(singleTransactionEntity);

	}

	public int executeTransaction() {

		int result = 0;

		DBSessionImpl tmpDbSession = DBSessionFactory.getInstance()
				.getDBSession(true);

		tmpDbSession.beginTransaction();

		for (SingleTransactionEntity singleTransactionEntity : singleTransactionEntities) {

			try {
				tmpDbSession
						.getClass()
						.getMethod(
								singleTransactionEntity.getOpsTypeEnums()
										.toString(), String.class,
								Object[].class)
						.invoke(tmpDbSession,
								singleTransactionEntity.getPreparedStmtString(),
								singleTransactionEntity
										.getPreparedStmtParamsObjects());

			} catch (IllegalAccessException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				tmpDbSession.rollBack();

				result = -1;

				return result;

			} catch (IllegalArgumentException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				tmpDbSession.rollBack();

				result = -1;

				return result;

			} catch (InvocationTargetException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				tmpDbSession.rollBack();

				result = -1;

				return result;

			} catch (NoSuchMethodException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				tmpDbSession.rollBack();

				result = -1;

				return result;

			} catch (SecurityException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

				tmpDbSession.rollBack();

				result = -1;

				return result;

			}

		}

		tmpDbSession.commitTransaction();

		tmpDbSession.close();

		return result;

	}
}
