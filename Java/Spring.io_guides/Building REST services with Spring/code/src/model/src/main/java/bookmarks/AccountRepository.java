package bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Created by xjsaber on 2016/7/4.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
