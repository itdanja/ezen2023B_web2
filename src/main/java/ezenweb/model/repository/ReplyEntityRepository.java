package ezenweb.model.repository;

import ezenweb.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyEntityRepository
    extends JpaRepository<ReplyEntity , Integer> {

}
