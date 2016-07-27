package dev.qz.git.action.abs;

/**
 * 
 * -------------------
 * interface of action
 * -------------------
 * 
 * @author Qin Zheng
 *
 */

/*
 * 
 *interface of the basic action
 *Execute() method is the default invoke method. 
 *It can not be changed temporary
 *
 */

public interface ActionAbs {

	// default invoked method
	public Object Execute();

}
