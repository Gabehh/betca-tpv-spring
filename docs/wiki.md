# Tecnología Spring: Proyecto TPV - Back-end
### Back-end con Tecnologías de Código Abierto (SPRING)
### [Máster en Ingeniería Web por la U.P.M.](http://miw.etsisi.upm.es)
---

# Curso 2018-19. Enunciado de prácticas

## 1. Article
> **Autor: xxx**  
**GitHub: xxx**
>* Permitir la creación, lectura, modificación de artículos.
>* Debe permitir la creación de artículos sin `code`, se asignará uno automáticamente a partir del `840000000000x` y hasta el `840000099999x` (EAN13). **OJO** habrá que consultar la BD y controlar el límite.
>* Realizar una búsqueda de aquellos artículos que no estén totalmente definidos.    

[Más información...](./Article)

## 2. Articles Family View
> **Autor: xxx**  
**GitHub: xxx**
>* Permitir la búsqueda de productos mediante agrupaciones jerárquicas. Un artículo puede pertenecer a varias familias. Aquí se utiliza el **patrón Composite**.  
>* Esta parte sólo hace consulta. Incluir en el fichero `db.yml` una población de pruebas.
>* El primer nivel muestra el contenido de `root`. Si se pulsa sobre uno de tipo `ARTICLE` se añade al carro de la compra. 
Si se pulsa sobre un `SIZES` se muestra dialogo con todas las tallas con su stock que son de tipo `ARTICLE`, al elegir uno se cierra el dialog y se añade al carro de la compra. Si se pulsa sobre un tipo `ARTICLES`, se cambia el contenido de la familia

[Más información...](./Articles-Family-View)

## 3. Articles Family Creator
> **Autor: xxx**  
**GitHub: xxx**
>* Permitir realizar una creación rápida de familias de artículos, incluyendo la creación de artículos con asignación automática de código. Ver práctica. `Article`  
>* Las tallas podrían ser 0..60, o "XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL", "Especial".

[Más información...](./Articles-Family-Creator)

## 4. Articles Family CRUD
> **Autor: xxx**  
**GitHub: xxx**
>* Permitir realizar un CRUD de las familias.

[Más información...](./Articles-Family-CRUD)

## 5. Articles Query
> **Autor: xxx**  
**GitHub: xxx**
>* Crear un componente avanzado para la la búsqueda de productos, mediante filtros, para localizar un producto con rapidez y pueda ser utilizado en muchos puntos de la aplicación.

[Más información...](./Articles-Query)

## 6. Articles Tracking 
> **Autor: xxx**  
**GitHub: xxx**
>* Facilitar el seguimiento de artículos por falta de stock de los clientes. Cuando se cree un ticket con una compra no entregada, 
se habilitará la referencia del ticket para que el cliente pueda acceder al ticket vía Internet para comprobar su estado.
>* Añadir el envío de un email al cliente, cuando el ticket alcance el estado de `IN_STOCK` en alguna compra, indicando el estado de lo llegado.
>* Cuando todo el ticket tenga el estado de `COMMIT`, ya no se podrá consultar más.
 
[Más información...](./Articles-tracking)

## 7. Budget
> **Autor: xxx**  
**GitHub: xxx**
>* Gestión de presupuestos. Será un carro de la compra realizando un **budge** .
>* Se genera un PDF de presupuesto.
 
[Más información...](./Budget)

## 8. Cash Movements
> **Autor: xxx**  
**GitHub: xxx**
>* Registrar movimientos de caja: ingresos o gastos de proveedores.
>* Integrado con el proceso de cierre de caja.

[Más información...](./Cash-Movements)

## 9. Invoice
> **Autor: xxx**  
**GitHub: xxx**
>* Implementar la creación de facturas a partir de un ticket, en el proceso de creación del ticket o posteriormente. Se deberá asegurar que el usuario tiene los datos necesarios (DNI, nombre de usuario y dirección completa).
>* Ampliar el servicios de Pdf para crear la factura.
>* Se debe sincronizar con la práctica de `Ticket` ya que si un Ticket con factura existe devolución, se debe crear una segunda factura negativa del mismo `Ticket`.  

[Más información...](./Invoice)

## 10. Invoice Update
> **Autor: xxx**  
**GitHub: xxx**
>* Permitir la búsqueda de facturas por móvil, o entre un rango de fechas.
>* Permitir la rectificación de facturas.
>* Permitir la reimpresión de facturas.

[Más información...](./Invoice-Update)

## 11. Offers
>* **Autor: xxx**   
**GitHub: xxx**
>* Crear y consultar ofertas, sobre un conjunto de artículos. La oferta tendrá un código no predecible para su comprobación.
>* Permite consultar de un artículo si se le aplica alguna oferta.

[Más información...](./Offers)

## 12. Operator Manager 
> **Autor: xxx**  
**GitHub: xxx**
>* Gestionar a los vendedores (operator & Manager).
>* Gestiona de ficha de entrada y salida por día.
>* Da un informe mensual de días y horas.

[Más información...](./Operator-Manager)

## 13. Orders 
> **Autor: xxx**  
**GitHub: xxx**
>* Gestionar la tramitación de pedidos. Se organizarán por proveedor. 
>* Permitir crear un pedido a partir de algún pedido anterior.

[Más información...](./Orders)

## 14. Orders Entry
> **Autor: xxx**  
**GitHub: xxx**
>* Gestionar la entrada de pedidos. Se deberá ayudar para la comprobación de entrada y la inclusión en los diferentes stocks.
>* También deberá ayudar en la gestión de reservas, es decir, artículos reservados que acaban de entrar.
>* Relación alta con la práctica de `Orders`.

[Más información...](./Orders-Entry)

## 15. Provider
> **Autor: xxx**  
**GitHub: xxx**
>* Mostrar una lista de proveedores (**id-company**). Solo se mostraran los proveedores activos.  
>* Implementar un componente para la busqueda rápida de proveedores.  
>* Permitir la creación, lectura, modificación de proveedores. No se permite borrar, solo dejar inactivo al proveedor.  

[Más información...](./Provider)

## 16. Statistics
> **Autor: xxx**   
**GitHub: xxx**
>* Ofrecer un conjunto de estudios estadísticos, mostrando en gráficas, de diferentes estudios sobre las Bases de Datos. También se permitirá obtener los datos de forma numérica.
>* Los estudios estadísticos serán abiertos, como ejemplo, se podría estudiar las ventas de un producto a lo largo de un periodo de tiempo, la evolución de ventas en total a lo largo de un periodo...
>* Importante aplicar el patrón `Facade` para mostrar los datos.

[Más información...](./Statistics)

## 17. Stock Alarm
> **Autor: xxx**  
**GitHub: xxx**
>* Ampliar con la creación, lectura, modificación y borrado de alarmas por stock.  
>* Una alarma podría ser establecer unos valores mínimos (con nivel WARNING y CRITICAL) de un conjunto de artículos, de tal manera, 
que cuando se alcanza dicho valor, aparece en una lista de artículos críticos y se avise de alguna manera.

[Más información...](./Stock-Alarm)

## 18. Stock Manager
> **Autor: xxx**  
**GitHub: xxx**
>* Ayuda a gestionar el stok.  
>* Permite realizar busquedas variadas. Productos por debajo de un stock. El stock puede ser negativo, indicando que existen reservas del mismo.
>* Productos vendidos hoy.

[Más información...](./Stock-Manager)

## 19. Stock Prediction
> **Autor: xxx**  
**GitHub: xxx**
>* Ayuda a predecir el stock de futuro. 
>* Dado un producto, te indica que stock va haber a lo largo del tiempo.

[Más información...](./Stock-Prediction)

## 20. Ticket
> **Autor: xxx**   
**GitHub: xxx**
>* Se realizará una busqueda por `id` de ticket.
>* Facilitar lectura y modificación de tickets. La modificación de tickets se permitirá reducir la cantidad de compras de artículos, pudiéndolo dejar a 0,
 y además, hacer evolucionar el estado de una compra. El resto de aspectos del ticket deben ser invariantes.
>* Cuando se tenga que devolver dinero se realizará mediante un `Voucher`. Ver práctica de `Voucher`.

[Más información...](./Ticket)

## 21. Ticket Query
> **Autor: xxx**   
**GitHub: xxx**
>* Facilitar la busqueda avanzada de tickets.
>* Por el móvil.  
>* Por los tickets pendientes de entregar de algún artículo.  
>* Por los tickets pendientes de entregar por algun artículo de una lista.

[Más información...](./Ticket-Query)

## 22. User
> **Autor: xxx**  
**GitHub: xxx**
>* Añadir la creación, lectura, modificación de usuarios. No se pueden borrar, pero si dejarlos en modo inactivo.  
>* Implementar un componente para la creación rápida y actualización de usuarios.  
>* En el proceso de realizar el `check out`, permitir asociar el ticket a un usuario, realizando la creación rápida si no existe. 

[Más información...](./User)

## 23. User Advanced
> **Autor: xxx**  
**GitHub: xxx**
>* Crear un componente de busqueda avanzada de usuarios.
>* Permitir a un usuario cambiar su contraseña a traves de su perfil.
>* Permitir en la página de gestion de usuarios, permitir cambiar el rol de un usuario, cumpliendo una logica de autorizaciones.  
>* Un `admin` puede todo. Un `manager` puede modificar `manager`, `operator` y `customer`. Un `operator` no puede alterar roles.

[Más información...](./User-Advanced)

## 24. Voucher
> **Autor: xxx**  
**GitHub: xxx**
>* Realizar la creación y lectura. No se debe permitir el borrado ni cambiar su contenido.   
>* Permitir el consumo de un vale. Se debe asegurar previamente que el vale no ha sido ya consumido.  
>* Realizar una consulta de vales pendientes generados entre dos fechas.  

[Más información...](./Voucher)

## Ampliaciones

### Gestion de cuenta para pago al final de mes

### Gestion de Ticket Regalo

### Descuentos a clientes especiales

### Gestión de IVA trimestral

### Descuento de fidelización

### Asociación de tickets a vendedores. Dado un vendedor obtener producción

### Notas internas entre vendedores

### Gestión de ley de protección de datos

### Gestión de empleados

### Gestión de puntos de clientes

### Gestión de envíos

### Gestión de auditoria de stock

