import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L; // Versión para evitar problemas de compatibilidad
    private String descripcion;
    private String codigo;
    private String tipo;
    private double costo;
    private double impuesto;

    public Producto(String descripcion, String codigo, String tipo, double costo, double impuesto) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.tipo = tipo;
        this.costo = costo;
        this.impuesto = impuesto;
    }

    public String getDescripcion() {
        return descripcion; // Retorna la descripción del producto
    }

    public String getCodigo() {
        return codigo; // Retorna el código del producto
    }

    public String getTipo() {
        return tipo; // Retorna el tipo del producto
    }

    public double getCosto() {
        return costo; // Retorna el costo base del producto
    }

    public double getImpuesto() {
        return impuesto; // Retorna el porcentaje de impuesto sobre el producto
    }

    /**
     * Método para calcular el precio total del producto, incluyendo el impuesto.
     * @return El precio total del producto con el impuesto.
     */
    public double calcularPrecio() {
        return costo + (costo * (impuesto / 100)); // Cálculo: costo + impuesto sobre el costo
    }

    /**
     * Método para mostrar los detalles del producto, como la descripción, código, tipo, costo, impuesto y precio con impuesto.
     */
    public void mostrarProducto() {
        System.out.println("Descripción: " + descripcion);
        System.out.println("Código: " + codigo);
        System.out.println("Tipo: " + tipo);
        System.out.println("Costo: " + costo);
        System.out.println("Impuesto: " + impuesto + "%");
        System.out.println("Precio con impuesto: " + calcularPrecio());
    }

    /**
     * Compara el producto actual con otro objeto para ver si son iguales.
     * Los productos se consideran iguales si tienen el mismo código.
     * @param obj El objeto a comparar con el producto actual.
     * @return true si los productos tienen el mismo código, false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Si ambos objetos son el mismo, son iguales
        if (obj == null || getClass() != obj.getClass()) return false; // Si el objeto es null o no es de la misma clase, no son iguales
        Producto producto = (Producto) obj;
        return codigo.equals(producto.codigo); // Compara por código
    }

    /**
     * Retorna el código hash del producto basado en su código.
     * @return El código hash generado a partir del código del producto.
     */
    @Override
    public int hashCode() {
        return codigo.hashCode(); // Usa el código del producto para generar el código hash
    }
}
