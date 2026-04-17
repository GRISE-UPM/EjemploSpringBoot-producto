package es.upm.fi.productos.mapper;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import es.upm.fi.productos.dto.ProductoDto;
import es.upm.fi.productos.model.Producto;
import es.upm.fi.productos.service.OfertasService;

@Mapper(componentModel = "spring")
public abstract class ProductoMapper {

    @Autowired
    protected OfertasService ofertasService;
	
    public abstract ProductoDto toDto(Producto producto);

    public ProductoDto toDto(Optional<Producto> producto) {
        return producto.map(this::toDto).orElse(null);
    }

    public abstract List<ProductoDto> toDtoList(List<Producto> productos);
    
    @Mapping(
    		target = "descuento",
            source = "producto",
            qualifiedByName = "obtenerDescuento"
    )

	@Named("obtenerDescuento")
    float obtenerDescuento(Producto producto, OfertasService ofertasService) {
    	return ofertasService.obtenerDescuento(producto);
    }
    
}