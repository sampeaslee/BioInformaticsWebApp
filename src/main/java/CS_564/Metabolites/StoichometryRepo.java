package CS_564.Metabolites;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoichometryRepo extends JpaRepository<Stoichometry, Integer> {
    
    
    @Query(value = " select * from stoichometry ;", nativeQuery = true)
    public ArrayList<Stoichometry> queryExample();
    
}

