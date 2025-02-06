import java.io.*;
import java.util.HashMap;

public class Inventario implements Serializable {
    private static final String ARCHIVO_INVENTARIO = "inventario.dat"; // Nombre del archivo donde se guardará el inventario
    private HashMap<Producto, Integer> productos;

    // Constructor: Carga el inventario desde el archivo al iniciar
    public Inventario() {
        productos = cargarInventario(); 
    }

    // Método para mostrar los productos en el inventario
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        for (Producto p : productos.keySet()) {
            p.mostrarProducto();
            System.out.println("Cantidad disponible: " + productos.get(p));
            System.out.println("---------------------------");
        }
    }

    // Método para abastecer el inventario con un producto nuevo o aumentar la cantidad de uno existente
    public void abastecerInventario(Producto producto, int cantidad) {
        productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
        guardarInventario(); // Guardar cambios en el archivo después de actualizar el inventario
    }

    // Método para guardar el inventario en un archivo
    private void guardarInventario() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_INVENTARIO))) {
            salida.writeObject(productos);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage());
        }
    }
    // Método para buscar un producto por su código
public Producto buscarProductoPorCodigo(String codigo) {
    for (Producto p : productos.keySet()) {
        if (p.getCodigo().equalsIgnoreCase(codigo)) {
            return p; // Devuelve el producto si encuentra el código
        }
    }
    return null; // Devuelve null si no lo encuentra
}


    // Método para cargar el inventario desde un archivo
    @SuppressWarnings("unchecked")
    private HashMap<Producto, Integer> cargarInventario() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_INVENTARIO))) {
            return (HashMap<Producto, Integer>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>(); // Si hay error (archivo no existe o está corrupto), devolver un inventario vacío
        }
    }
}
