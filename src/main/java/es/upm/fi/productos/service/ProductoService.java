package es.upm.fi.productos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.upm.fi.productos.model.Producto;
import es.upm.fi.productos.repository.ProductosRepository;

@Service
public class ProductoService {

    private final ProductosRepository productosRepository;

    public ProductoService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    // Todos los productos
    public List<Producto> obtenerTodosProductos() {
        return new ArrayList<>(productosRepository.findAll());
    }

    public Page<Producto> obtenerProductos(String nombre, Pageable pageable) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            return productosRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        }
        return new PageImpl<>(List.of(), pageable, 0);
    }

    public Optional<Producto> obtenerProducto(long id) {
        return productosRepository.findById(id);
    }

    public void anadirProducto(Producto producto) throws ResponseStatusException {
        if (producto.getId() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Un producto nuevo no debe tener ID"
            );
        }
        productosRepository.save(producto);
    }
}
