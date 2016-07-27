package dev.qz.git.sql.helper;

import java.util.Vector;

public class SqlHelper {

	private StringBuilder sqlBuilder = null;

	public void DeleteHelper() {

	}

	public String QueryHelper(String tableName, String primary,
			Vector<String> columnVector) {

		sqlBuilder = new StringBuilder();

		sqlBuilder.append("select * from ");

		sqlBuilder.append(tableName);

		if (primary != null || columnVector.size() > 0) {

			sqlBuilder.append(" where ");

		}

		for (int i = 0; i < columnVector.size(); i++) {

			sqlBuilder.append(columnVector.get(i));

			sqlBuilder.append("=?");

			sqlBuilder.append(" and ");

		}

		if (primary != null) {

			sqlBuilder.append(primary);

			sqlBuilder.append("=?");

			sqlBuilder.append(" and ");

		}

		sqlBuilder.replace(sqlBuilder.lastIndexOf(" and "),
				sqlBuilder.lastIndexOf(" and ") + " and ".length(), "");

		return sqlBuilder.toString();

	}

	public String InsertHelper(String tableName, String primary,
			Vector<String> columnVector, int count) {

		sqlBuilder = new StringBuilder();

		sqlBuilder.append("insert into ");

		sqlBuilder.append(tableName);

		setColumnInfo(primary, columnVector);

		sqlBuilder.append(" values");

		setValueForPreparedStmt(count);

		return sqlBuilder.toString();

	}

	public String UpdateHelper(String tableName, String primary,
			Vector<String> columnVector) {

		sqlBuilder = new StringBuilder();

		sqlBuilder.append("update ");

		sqlBuilder.append(tableName);

		sqlBuilder.append(" set ");

		for (int i = 0; i < columnVector.size(); i++) {

			sqlBuilder.append(columnVector.get(i) + "=?,");

		}

		sqlBuilder.replace(sqlBuilder.lastIndexOf(","),
				sqlBuilder.lastIndexOf(",") + 1, "");

		sqlBuilder.append(" where ");

		sqlBuilder.append(primary);

		sqlBuilder.append("=?");

		return sqlBuilder.toString();

	}

	private void setColumnInfo(String priamry, Vector<String> columnVector) {

		sqlBuilder.append("(");

		sqlBuilder.append(priamry + ",");

		for (int i = 0; i < columnVector.size(); i++) {

			sqlBuilder.append(columnVector.get(i) + ",");

		}

		sqlBuilder.append(")");

		sqlBuilder.replace(sqlBuilder.lastIndexOf(","),
				sqlBuilder.lastIndexOf(",") + 1, "");

	}

	private void setValueForPreparedStmt(int count) {

		sqlBuilder.append("(");

		for (int i = 0; i < count; i += 1) {

			sqlBuilder.append("?,");

		}

		sqlBuilder.append(")");

		sqlBuilder.replace(sqlBuilder.lastIndexOf(","),
				sqlBuilder.lastIndexOf(",") + 1, "");

	}

}
