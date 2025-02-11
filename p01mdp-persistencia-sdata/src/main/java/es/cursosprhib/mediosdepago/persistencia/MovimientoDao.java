package es.cursosprhib.mediosdepago.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cursosprhib.mediosdepago.modelo.Movimiento;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer>{

}
