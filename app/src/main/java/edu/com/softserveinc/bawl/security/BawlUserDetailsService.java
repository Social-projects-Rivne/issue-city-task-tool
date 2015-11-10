package edu.com.softserveinc.bawl.security;
 
import edu.com.softserveinc.bawl.dao.UserDao;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
@Transactional(readOnly = true)
@Service
public class BawlUserDetailsService implements UserDetailsService {

    public static final Logger LOG=Logger.getLogger(BawlUserDetailsService.class);
	
	@Autowired
	private UserDao userDao;
 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {

			UserModel domainUser = userDao.findByLogin(username);
			final Collection<? extends GrantedAuthority> authorities = getAuthorities(domainUser.getRole());

			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			return new User(
					domainUser.getLogin(), 
					domainUser.getPassword(),
					enabled,
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,
					authorities);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
		final List<String> springGrantedAuthoritiesRoles = getSpringGrantedAuthoritiesRoles(role.id);
		List<GrantedAuthority> authList = getGrantedAuthorities(springGrantedAuthoritiesRoles);
		return authList;
	}
	
	public static List<String> getSpringGrantedAuthoritiesRoles(Integer role) {
		List<String> roles = new ArrayList<String>();
		switch (role) {
			case 0 : {
				roles.add("USER_NOT_CONFIRMED");
			}
			case 1 : {
				roles.add("ROLE_USER");
			}
			case 2 : {
				roles.add("ROLE_MANAGER");
				roles.add("ROLE_ADMIN");
			}
			case 3: {
				roles.add("ROLE_MANAGER");
			}
		}
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}