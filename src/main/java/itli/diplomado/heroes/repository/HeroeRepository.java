package itli.diplomado.heroes.repository;

import itli.diplomado.heroes.models.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Integer> {

}
