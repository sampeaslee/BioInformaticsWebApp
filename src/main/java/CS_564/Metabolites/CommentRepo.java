package CS_564.Metabolites;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * FROM comment WHERE id = :id",  nativeQuery = true)
    public  List<Comment> findAllComment(@Param("id") String id);
}