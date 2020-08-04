package CS_564.Metabolites;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUSerDetailService implements UserDetailsService {
	
	@Autowired
	UserLoginRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
		
		ArrayList<UserLogin> user = (ArrayList<UserLogin>)userRepo.getUserLogin();
		
		if (user == null)
		{
			throw new UsernameNotFoundException("User Does Not Exist");
		}
		
		return new MyUserDetails(user.get(0));
	}
}
