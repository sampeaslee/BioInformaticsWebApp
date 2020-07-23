package CS_564.Metabolites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserLoginRepo extends JpaRepository<UserLogin, Integer> {

	@Query(value = "select * from userlogin", nativeQuery = true)
 	public List<UserLogin> getUserLogin();
}
