package es.upm.fi.productos.service;

import org.springframework.stereotype.Service;

import es.upm.fi.productos.model.Producto;

@Service
public class OfertasService {
	
	public float obtenerDescuento(Producto producto) {
		
		if("Gasolina 95".equals(producto.getNombre()))
			return 0.1F;
		
		return 0;
		
	}

}