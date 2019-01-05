package org;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserDao extends BaseDao<User> {
	
	private static Logger logger = LogManager.getLogger(UserDao.class);
	
	public UserDao() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}
}