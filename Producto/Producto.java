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
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCosto() {
        return costo;
    }

    public double getImpuesto() {
        return impuesto;
    }

    // Método para calcular el precio total del producto, incluyendo el impuesto
    public double calcularPrecio() {
        return costo + (costo * (impuesto / 100)); // Cálculo: costo + impuesto sobre el costo
    }

    // Método para mostrar los detalles del producto
    public void mostrarProducto() {
        System.out.println("Descripción: " + descripcion);
        System.out.println("Código: " + codigo);
        System.out.println("Tipo: " + tipo);
        System.out.println("Costo: " + costo);
        System.out.println("Impuesto: " + impuesto + "%");
        System.out.println("Precio con impuesto: " + calcularPrecio());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo.equals(producto.codigo); // Considera iguales los productos con el mismo código
    }

    @Override
    public int hashCode() {
        return codigo.hashCode(); // Usa el código del producto para el hash
    }
}
