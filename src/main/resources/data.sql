-- Usar comillas, no apostrofes
--
-- Se está usando @GeneratedValue(strategy = GenerationType.IDENTITY)
-- En este caso, los id no se deben incluir en el INSERT, ya que los genera la misma BD
INSERT INTO Productos (nombre) VALUES ('Limpiaparabrisas');
INSERT INTO Productos (nombre) VALUES ('Gasolina 95');
INSERT INTO Productos (nombre) VALUES ('Gasolina 98');

-- Si no se especifica GenerationType.IDENTITY, habria que incluir los id
-- INSERT INTO Productos VALUES (1, 'Limpiaparabrisas');

-- Pero esto genera un problema: en este caso, Hibernate utiliza una estrategia
-- automática (AUTO), gestionando internamente la generación de identificadores.
-- Hibernate no tiene en cuenta inserciones manuales realizadas directamente en 
-- la base de datos (por ejemplo, mediante data.sql) y comienza a contar en 1.
-- Esto puede provocar inconsistencias, ya que Hibernate puede generar IDs que ya
-- existen, produciendo errores de clave primaria duplicada.
-- Con GenerationType.IDENTITY, la generación del ID se delega en la base de datos,
-- que mantiene correctamente el autoincremento y evita conflictos de duplicidad.