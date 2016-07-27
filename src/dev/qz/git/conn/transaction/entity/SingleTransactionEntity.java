package dev.qz.git.conn.transaction.entity;

/**
 * ----------------------------------------------------------
 * transaction entity,contains single transaction unit vector
 * ----------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

import dev.qz.git.conn.transaction.conf.OpsSelection;

public class SingleTransactionEntity {

	private String preparedStmtString = null;

	private Object[] preparedStmtParamsObjects = null;

	private OpsSelection opsTypeEnums = null;

	public String getPreparedStmtString() {

		return preparedStmtString;

	}

	public void setPreparedStmtString(String preparedStmtString) {

		this.preparedStmtString = preparedStmtString;

	}

	public Object[] getPreparedStmtParamsObjects() {

		return preparedStmtParamsObjects;

	}

	public void setPreparedStmtParamsObjects(Object[] preparedStmtParamsObjects) {

		this.preparedStmtParamsObjects = preparedStmtParamsObjects;

	}

	public OpsSelection getOpsTypeEnums() {

		return opsTypeEnums;

	}

	public void setOpsTypeEnums(OpsSelection opsTypeEnums) {

		this.opsTypeEnums = opsTypeEnums;

	}

}
