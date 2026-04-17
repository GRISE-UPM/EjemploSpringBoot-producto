package es.upm.fi.productos.controller;

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

import es.upm.fi.productos.dto.ProductoDto;
import es.upm.fi.productos.mapper.ProductoMapper;
import es.upm.fi.productos.model.Producto;
import es.upm.fi.productos.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	ProductoService productoService;
	ProductoMapper productoMapper;

	public ProductoController(ProductoService productoService, ProductoMapper productoMapper) {
		this.productoService = productoService;
		this.productoMapper = productoMapper;
	}

	// Todos los productos
	@GetMapping("/todos")
	List<ProductoDto> obtenerTodosProductos() {
		
		List<Producto> productos = productoService.obtenerTodosProductos();
		return productoMapper.toDtoList(productos);
		
	}
	
  @GetMapping("/todosPageable")
  // Ejemplo: GET /productos/todos?page=0&size=5&sort=nombre,asc
  // Ejemplo: GET /productos/todos?nombre=leche&page=0&size=5&sort=nombre,asc
  public Page<ProductoDto> obtenerTodosProductos(
          @RequestParam(required = false) String nombre,
          @PageableDefault(size = 10, sort = "id") Pageable pageable) {

      Page<Producto> productos = productoService.obtenerTodosProductos(nombre, pageable);
      return productos.map(productoMapper::toDto);
      
  }
       
  @GetMapping("/{id}")
  // Ejemplo: GET /productos/1
  ProductoDto obtenerProducto(@PathVariable long id) {
    
    Optional<Producto> producto = productoService.obtenerProducto(id);
    return productoMapper.toDto(producto);
    
  }
    
	@PostMapping("/anadir")
	@ResponseStatus(HttpStatus.CREATED)
	void anadirProducto(@RequestBody Producto producto) throws Exception {
		
		productoService.anadirProducto(producto);
		
	}
	
}

