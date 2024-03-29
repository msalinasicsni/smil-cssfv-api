package ni.org.ics.smil.cssfv.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ni.org.ics.smil.cssfv.api.entity.Muestra;
//import ni.org.ics.smil.cssfv.api.dto.MuestrasDTO;

public interface MuestraRepository extends JpaRepository<Muestra, Long> {
	
	@Query(value = "SELECT Count(*) FROM muestras a " + 
			"WHERE a.codigo_participante = :codigoParticipante " +
			"AND a.mx_id = :id " +
			"AND a.anulada = false ", nativeQuery=true)
	long countBycodigoParticipanteAndMxIdId(
			@Param("codigoParticipante") Integer codigoParticipante, 
			@Param("id") Long id);
	
	long countBycodigoParticipanteAndFechaRegistroBetween(Integer codigoParticipante, Date startDate, Date endDate);
	
	/*Obtener las ultimas 7 muestras tomadas*/
	@Query(value = "SELECT * FROM muestras "
			+ "ORDER BY fecha_registro DESC LIMIT 7", nativeQuery=true)
	List<Muestra> findTopMuestras();
	
	/*Obtener el registro by cod_lab_scan*/
	@Query(value = "SELECT * FROM muestras a "
			+ "WHERE a.cod_lab_scan = :codLabScan", nativeQuery=true)
	Muestra findMxByCodLabScan(@Param("codLabScan") String codLabScan);
	
	/*Obtener el registro by cod_lab*/
	@Query(value = "SELECT * FROM muestras a "
			+ "WHERE a.cod_lab = :codLab", nativeQuery=true)
	Muestra findMxByCodLab(@Param("codLab") String codLab);
	
	/*Verificar si existe muestra correspondiente al numero de envio*/
	@Query(value = "SELECT count(*) "
			+ "FROM muestras a "
			+ "WHERE DATE_FORMAT(a.fecha_envio, '%Y-%m-%d') = CURDATE() "
			+ "AND viaje = :viaje", nativeQuery=true)
	long countMuestraByViaje(@Param("viaje") Integer viaje);
}
