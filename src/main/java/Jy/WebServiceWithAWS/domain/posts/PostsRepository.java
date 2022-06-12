package Jy.WebServiceWithAWS.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("select p from Posts p ORDER BY p.id desc")
    List<Posts> findAllDesc();
}
