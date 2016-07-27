package dev.qz.git.orm.mapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import dev.qz.git.conn.factory.DBSessionFactory;
import dev.qz.git.conn.impl.DBSessionImpl;
import dev.qz.git.orm.conf.ORMConfParser;
import dev.qz.git.orm.conf.entity.ConfEntity;
import dev.qz.git.sql.helper.SqlHelper;

public class OrmMapping {

	private Map<String, ConfEntity> mappers = null;

	private DBSessionImpl dbSessionImpl = null;

	public OrmMapping() {

		mappers = new ORMConfParser().load();

		dbSessionImpl = DBSessionFactory.getInstance().getDBSession(false);

	}

	public Vector<Object> ORMQuery(Object object) {

		ConfEntity confEntity = null;

		confEntity = mappers.get(object.getClass().getName());

		Vector<String> columnVector = confEntity.getColumnVector();

		Vector<String> notNullColumn = new Vector<String>();

		Vector<Object> notNullResult = new Vector<Object>();

		Object primaryString = "";

		Object[] params = null;

		try {

			for (int i = 0; i < columnVector.size(); i++) {

				Object reString = object.getClass()
						.getMethod("get" + confEntity.getColumnVector().get(i))
						.invoke(object);

				if (reString != null) {

					notNullColumn.addElement(confEntity.getColumnVector()
							.get(i));

					notNullResult.addElement(reString.toString());

				}

			}

			primaryString = object.getClass()
					.getMethod("get" + confEntity.getPrimaryString())
					.invoke(object);

			if (primaryString == null) {

				params = new Object[notNullResult.size()];

				for (int i = 0; i < notNullColumn.size(); i++) {

					params[i] = notNullResult.get(i);

				}

			}

			else {

				params = new Object[notNullResult.size() + 1];

				for (int i = 0; i < notNullColumn.size(); i++) {

					params[i] = notNullResult.get(i);

				}

				params[notNullResult.size()] = primaryString;

			}

		} catch (IllegalAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InvocationTargetException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (NoSuchMethodException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SecurityException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		System.out.println(new SqlHelper().QueryHelper(confEntity.getTableNameString(),
				confEntity.getPrimaryString(), notNullColumn));

		List<Map<String, Object>> resList = dbSessionImpl.queryOps(
				new SqlHelper().QueryHelper(confEntity.getTableNameString(),
						confEntity.getPrimaryString(), notNullColumn), params);

		Vector<Object> queryObjects = new Vector<Object>();

		if (resList != null) {

			for (int i = 0; i < resList.size(); i++) {

				try {

					Object singleObject = confEntity.getClassName()
							.newInstance();

					Method[] methods = singleObject.getClass().getMethods();

					for (Method method : methods) {

						if (method.getName().equals(
								"set" + confEntity.getPrimaryString())) {

							Class<?>[] paramType = method.getParameterTypes();

							singleObject
									.getClass()
									.getMethod(
											"set"
													+ confEntity
															.getPrimaryString(),
											paramType)
									.invoke(singleObject,
											resList.get(i).get(
													confEntity
															.getPrimaryString()
															.toUpperCase()));

						}

					}

					for (int j = 0; j < columnVector.size(); j++) {

						for (Method method : methods) {

							if (method.getName().equals(
									"set" + columnVector.get(j))) {

								Class<?>[] paramType = method
										.getParameterTypes();

								singleObject
										.getClass()
										.getMethod("set" + columnVector.get(j),
												paramType)
										.invoke(singleObject,
												resList.get(i).get(
														columnVector.get(j)
																.toUpperCase()));

							}

						}

					}

					queryObjects.addElement(singleObject);

				} catch (InstantiationException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (IllegalAccessException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (NoSuchMethodException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (SecurityException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (IllegalArgumentException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (InvocationTargetException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			}

		}

		return queryObjects;

	}

	public void ORMUpdate(Object object) {

		ConfEntity confEntity = null;

		confEntity = mappers.get(object.getClass().getName());

		Vector<String> columnVector = confEntity.getColumnVector();

		Object[] params = new Object[columnVector.size() + 1];

		try {

			for (int i = 0; i < columnVector.size(); i++) {

				params[i] = object.getClass()
						.getMethod("get" + columnVector.get(i)).invoke(object);

			}

			params[columnVector.size()] = object.getClass()
					.getMethod("get" + confEntity.getPrimaryString())
					.invoke(object);

		} catch (NoSuchMethodException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SecurityException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IllegalAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InvocationTargetException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		dbSessionImpl.updateOps(new SqlHelper().UpdateHelper(
				confEntity.getTableNameString(), confEntity.getPrimaryString(),
				columnVector), params);

	}

	public void ORMInsert(Object object) {

		ConfEntity confEntity = null;

		confEntity = mappers.get(object.getClass().getName());

		Vector<String> columnVector = confEntity.getColumnVector();

		Object[] params = new Object[columnVector.size() + 1];

		try {

			params[0] = object.getClass()
					.getMethod("get" + confEntity.getPrimaryString())
					.invoke(object);

			for (int i = 1; i < columnVector.size() + 1; i++) {

				params[i] = object.getClass()
						.getMethod("get" + columnVector.get(i - 1))
						.invoke(object);

			}

		} catch (NoSuchMethodException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SecurityException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IllegalAccessException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InvocationTargetException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		dbSessionImpl.insertOps((new SqlHelper().InsertHelper(
				confEntity.getTableNameString(), confEntity.getPrimaryString(),
				columnVector, columnVector.size() + 1)), params);

	}
}
