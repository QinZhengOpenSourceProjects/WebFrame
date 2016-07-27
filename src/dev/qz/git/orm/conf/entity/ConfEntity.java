package dev.qz.git.orm.conf.entity;

import java.util.Vector;

public class ConfEntity {

	private Class<?> className;

	private String tableNameString;

	private String PrimaryString;

	private Vector<String> columnVector;

	public String getPrimaryString() {

		return PrimaryString;

	}

	public void setPrimaryString(String PrimaryString) {

		this.PrimaryString = PrimaryString;

	}

	public Class<?> getClassName() {

		return className;

	}

	public void setClassName(Class<?> className) {

		this.className = className;

	}

	public String getTableNameString() {

		return tableNameString;

	}

	public void setTableNameString(String tableNameString) {

		this.tableNameString = tableNameString;

	}

	public Vector<String> getColumnVector() {

		return columnVector;

	}

	public void setColumnVector(Vector<String> columnVector) {

		this.columnVector = columnVector;

	}

}
