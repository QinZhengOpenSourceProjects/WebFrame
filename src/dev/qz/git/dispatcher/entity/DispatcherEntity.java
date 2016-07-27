package dev.qz.git.dispatcher.entity;

/**
 * 
 * ----------------------------------
 * define the conf file parser entity
 * ----------------------------------
 * 
 * @author Qin Zheng
 * 
 */

import java.io.Serializable;
import java.util.Map;

public class DispatcherEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<?> ActionClass;

	private Map<String, String> viewMap;
	

	public Class<?> getActionClass() {

		return ActionClass;

	}

	public void setActionClass(Class<?> actionClass) {

		ActionClass = actionClass;

	}

	public Map<String, String> getViewMap() {

		return viewMap;

	}

	public void setViewMap(Map<String, String> viewMap) {

		this.viewMap = viewMap;

	}

	public static long getSerialversionuid() {

		return serialVersionUID;

	}

}
