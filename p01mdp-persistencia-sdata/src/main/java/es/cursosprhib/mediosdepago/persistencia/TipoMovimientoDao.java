package es.cursosprhib.mediosdepago.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cursosprhib.mediosdepago.modelo.TipoMovimiento;

public interface TipoMovimientoDao extends JpaRepository<TipoMovimiento, Integer>{

}
