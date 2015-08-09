package edu.tju.ina.seeworld.logic.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.EssentialUser;

public class SeeworldUserDetailsService implements UserDetailsService {

	private IUserDAO userDao;
	private VOPOTransformator vOPOTransformator;
	public static String USERNAME_IN_SESSION = "usernameInSession";
	public static String PASSWORD_IN_SESSION = "passwordInSession";

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException{
		List<EssentialUser> user =new ArrayList<EssentialUser>(0);
		try {
			user =vOPOTransformator.transferUserToEssentialUserList(userDao.findByUserName(username));
		} catch (SeeWorldException e) {
			//TODO handle exception
			e.printStackTrace();
		}
		if (user.size() == 0) {
			throw new UsernameNotFoundException("User with name " + username
					+ " is not found!");
		}
		return user.get(0);
	}

}
