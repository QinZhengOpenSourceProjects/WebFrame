package dev.qz.git.action.function;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import dev.qz.git.action.abs.ActionAbs;
import dev.qz.git.conn.factory.DBSessionFactory;
import dev.qz.git.conn.transaction.conf.OpsSelection;
import dev.qz.git.conn.transaction.impl.DBSessionTransaction;
import dev.qz.git.context.impl.ContextImpl;
import dev.qz.git.orm.entity.UserInfo;
import dev.qz.git.orm.mapping.OrmMapping;

public class Login implements ActionAbs {

	@Override
	public Object Execute() {
		// TODO Auto-generated method stub

		// Trans();

		ORMQuery();

		return "Success";

	}
	

	public void ORMInsert() {

		OrmMapping ormMapping = new OrmMapping();

		UserInfo userInfo = new UserInfo();

		userInfo.setNameString("kobei");

		userInfo.setPasswordString("123");

		ormMapping.ORMInsert(userInfo);

	}

	public void ORMUpdate() {

		OrmMapping ormMapping = new OrmMapping();

		UserInfo userInfo = new UserInfo();

		userInfo.setNameString("kobei");

		userInfo.setPasswordString("11");

		ormMapping.ORMUpdate(userInfo);

	}

	public void ORMQuery() {

		OrmMapping ormMapping = new OrmMapping();

		UserInfo userInfo = new UserInfo();
		
		userInfo.setNameString("kobei");

		userInfo.setPasswordString("11");

		Vector<Object> vector = ormMapping.ORMQuery(userInfo);

		for (int i = 0; i < vector.size(); i++) {

			UserInfo info = (UserInfo) vector.get(i);

			System.out.println(info.getNameString() + "|"
					+ info.getPasswordString());

		}

	}

	public void Trans() {

		HttpServletRequest httpServletRequest = (HttpServletRequest) ContextImpl
				.getThreadLocalMap("request");

		DBSessionTransaction dbSessionTransaction = new DBSessionTransaction();

		dbSessionTransaction.addSingleTransactionEntity(
				"insert into userinfo values(?,?)", new Object[] {
						httpServletRequest.getParameter("name"),
						httpServletRequest.getParameter("password") },
				OpsSelection.insertOps);

		dbSessionTransaction.addSingleTransactionEntity(
				"update userinfo set password =? where name=?", new Object[] {
						"111", httpServletRequest.getParameter("name") },
				OpsSelection.updateOps);

		dbSessionTransaction.executeTransaction();

	}

	public String LoginDo() {

		HttpServletRequest httpServletRequest = (HttpServletRequest) ContextImpl
				.getThreadLocalMap("request");

		if (DBSessionFactory
				.getInstance()
				.getDBSession(false)
				.queryOps(
						"select * from userinfo where name=? and password=?",
						new Object[] { httpServletRequest.getParameter("name"),
								httpServletRequest.getParameter("password") })
				.size() > 0) {

			return "Success";

		} else {

			return "Fail";

		}

	}

}
