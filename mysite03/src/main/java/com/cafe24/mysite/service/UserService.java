package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService 
//implements UserDetailsService 
{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UserVo userVo = userDao.get(username);
//
//		String role = "ROLE_USER";
//
//		SecurityUser securityUser = new SecurityUser();
//		if (userVo != null) {
//			securityUser.setName(userVo.getName());	
//			securityUser.setUsername(userVo.getEmail());
//			securityUser.setPassword(userVo.getPassword());
//
//			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//			authorities.add(new SimpleGrantedAuthority(role));
//			securityUser.setAuthorities(authorities);
//		}
//
//		return securityUser;
//	}

	public Boolean existEmail(String email) {
		UserVo userVo = userDao.get(email);
		return userVo != null;
	}

	public UserService() {
		System.out.println("userService 생성");
	}

	public Boolean join(UserVo userVo) {
		userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
		return userDao.insert(userVo);
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo.getEmail(), userVo.getPassword());
	}

	public UserVo getUser(long no) {

		return userDao.get(no);
	}

	public Boolean update(UserVo updateUserVo) {
		return userDao.update(updateUserVo);
	}

}
