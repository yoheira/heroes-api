/**
 * 
 */
package itli.diplomado.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import itli.diplomado.heroes.models.Poder;

/**
 * @author enunezt
 *
 */
@Repository
public interface PoderRepository extends JpaRepository<Poder, Integer> {

}
