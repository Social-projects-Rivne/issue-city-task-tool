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

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class BawlUserDetailsService implements UserDetailsService {

    public static final Logger LOG=Logger.getLogger(BawlUserDetailsService.class);

    private static final EnumMap<UserRole, List<SimpleGrantedAuthority>> AUTHORITIES_BY_ROLE = new EnumMap(UserRole.class);

    public static final SimpleGrantedAuthority USER_NOT_CONFIRMED = new SimpleGrantedAuthority("USER_NOT_CONFIRMED");

    public static final SimpleGrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");

    public static final SimpleGrantedAuthority ROLE_MANAGER = new SimpleGrantedAuthority("ROLE_MANAGER");

    public static final SimpleGrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    static {
            AUTHORITIES_BY_ROLE.put(UserRole.USER_NOT_CONFIRMED, Arrays.asList(USER_NOT_CONFIRMED));
            AUTHORITIES_BY_ROLE.put(UserRole.USER, Arrays.asList(ROLE_USER));
            AUTHORITIES_BY_ROLE.put(UserRole.MANAGER, Arrays.asList(ROLE_MANAGER, ROLE_USER));
            AUTHORITIES_BY_ROLE.put(UserRole.ADMIN, Arrays.asList(ROLE_MANAGER, ROLE_ADMIN, ROLE_USER));
    }

    @Autowired
	private UserDao userDao;
 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {

			UserModel domainUser = userDao.findByLogin(username);
			final List<? extends GrantedAuthority> authorities = AUTHORITIES_BY_ROLE.get(domainUser.getRole());
			return new User(domainUser.getLogin(), domainUser.getPassword(), true, true, true, true, authorities);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}