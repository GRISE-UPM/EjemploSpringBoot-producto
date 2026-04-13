package es.upm.fi.productos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.upm.fi.productos.model.Producto;
import es.upm.fi.productos.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	// Todos los productos
	@GetMapping("/todos")
	List<Producto> obtenerTodosProductos() {
		
		return productoService.obtenerTodosProductos();
		
	}
	
    @GetMapping("/todosPageable")
    // Ejemplo: GET /productos/todos?page=0&size=5&sort=nombre,asc
    // Ejemplo: GET /productos/todos?nombre=leche&page=0&size=5&sort=nombre,asc
    public Page<Producto> obtenerTodosProductos(
            @RequestParam(required = false) String nombre,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        return productoService.obtenerTodosProductos(nombre, pageable);
        
    }
    
    /* Formato de respuesta de Page<Producto> (Spring Data JPA):

    {
      "content": [                 // Lista de elementos de la página actual
        {
          "id": 1,
          "nombre": "Producto A"
        }
      ],
      "pageable": {               // Información interna de paginación
        "pageNumber": 0,          // Número de página actual (empieza en 0)
        "pageSize": 2,            // Tamaño de página solicitado
        "offset": 0,              // Índice del primer elemento (page * size)
        "paged": true,            // Indica si está paginado
        "unpaged": false,
        "sort": {                 // Información de ordenación
          "empty": true,
          "unsorted": true,
          "sorted": false
        }
      },
      "totalElements": 3,         // Total de elementos en la base de datos
      "totalPages": 2,            // Número total de páginas
      "last": false,              // Indica si es la última página
      "first": true,              // Indica si es la primera página
      "size": 2,                  // Tamaño de página
      "number": 0,                // Página actual
      "numberOfElements": 2,      // Elementos en esta página
      "sort": {                   // Ordenación global
        "empty": true,
        "unsorted": true,
        "sorted": false
      },
      "empty": false              // Indica si la página está vacía
    }
    */
    
    @GetMapping("/{id}")
    // Ejemplo: GET /productos/{1}
    Optional<Producto> obtenerProducto(@PathVariable long id) {
    	
    	return productoService.obtenerProducto(id);
    	
    }
    
	@PostMapping("/anadir")
	@ResponseStatus(HttpStatus.CREATED)
	void anadirProducto(@RequestBody Producto producto) throws Exception {
		
		productoService.anadirProducto(producto);
		
	}
	
	/* Se puede probar usando curl
	  
    curl -i 
         -X POST http://localhost:8080/productos/anadir \
         -H "Content-Type: application/json" \
         -d '{"nombre":"Aceite"}'
	 */
	
}

