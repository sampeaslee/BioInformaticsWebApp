package CS_564.Metabolites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EndMetaReactionRepo extends JpaRepository<EndMetaReaction, Integer> {
}