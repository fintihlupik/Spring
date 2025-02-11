package es.cursosprhib.mediosdepago.persistencia;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;

public interface ExtractoDao extends JpaRepository<Extracto, Integer>{
	
	@Query("select e from Extracto e "
			+ "join fetch e.movimientos m "
			+ "join fetch m.tipo tm "
			+ "join fetch e.cuenta c "
			+ "join fetch c.tarjetas t "
			+ "where e.cuenta = ?1 and e.anyo = ?2 and e.mes = ?3")
	Extracto findExtracto(Cuenta cuenta, int anyo, int mes);

}
