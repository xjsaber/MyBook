package readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xjsaber on 2017/4/28.
 * Persist readers via JPA
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {

}
