package assignmentspring.demo.repository;

import assignmentspring.demo.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyCoinRepository extends JpaRepository<Coin, Long> {
    List<Coin> findAllByNameAndStatus(String name, int status);
    List<Coin> findAllByIdAndStatus(Long marketId, int status);
}
