package ni.org.ics.smil.cssfv.api.repository;

import ni.org.ics.smil.cssfv.api.entity.catalogos.CatMuestra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Miguel Salinas on 14/12/2020.
 */
public interface CatMuestraRepository extends JpaRepository<CatMuestra, Long> {

	List<CatMuestra> findByNombre(String nombre);
}