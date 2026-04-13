package es.upm.fi.productos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.upm.fi.productos.model.Producto;

public interface ProductosRepository extends JpaRepository <Producto, Long>{

	// Este método no tiene implementación explícita porque Spring Data JPA la genera automáticamente.
	// A partir del nombre del método, Spring interpreta la consulta:
	// - findBy → indica que se realizará una búsqueda
	// - Nombre → campo de la entidad Producto sobre el que se filtra
	// - Containing → búsqueda parcial (equivalente a LIKE %valor%)
	// - IgnoreCase → ignora mayúsculas y minúsculas
	// Además, al recibir un objeto Pageable, Spring aplica paginación automáticamente.
	// En tiempo de ejecución, Spring crea una implementación que ejecuta una consulta similar a:
	// SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))
	// Este tipo de métodos se conoce como "query method" o método derivado.
	Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
	
}

