import java.io.*;
import java.util.HashMap;

public class Inventario implements Serializable {
    private static final String ARCHIVO_INVENTARIO = "inventario.dat"; // Nombre del archivo donde se guardará el inventario
    private HashMap<Producto, Integer> productos;

    public Inventario() {
        productos = cargarInventario(); // Carga el inventario desde el archivo al iniciar el objeto Inventario
    }

    /**
     * Muestra todos los productos almacenados en el inventario, junto con su cantidad disponible.
     * Si el inventario está vacío, muestra un mensaje indicándolo.
     */
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return; // Sale del método si el inventario está vacío
        }

        // Muestra los detalles de cada producto en el inventario
        for (Producto p : productos.keySet()) {
            p.mostrarProducto(); // Muestra los detalles del producto
            System.out.println("Cantidad disponible: " + productos.get(p)); // Muestra la cantidad disponible
            System.out.println("---------------------------"); // Separador visual
        }
    }

    /**
     * Abastece el inventario con un producto nuevo o incrementa la cantidad de un producto existente.
     * Guarda automáticamente los cambios en el archivo después de la actualización.
     */
    public void abastecerInventario(Producto producto, int cantidad) {
        productos.put(producto, productos.getOrDefault(producto, 0) + cantidad); // Agrega la cantidad al producto existente o lo crea si no existe
        guardarInventario(); // Guarda los cambios en el archivo
    }

    /**
     * Guarda el estado actual del inventario en un archivo para persistencia de datos.
     * Si ocurre un error al guardar el inventario, muestra el mensaje de error.
     */
    private void guardarInventario() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_INVENTARIO))) {
            salida.writeObject(productos); // Escribe el inventario en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario: " + e.getMessage()); // Manejo de errores en caso de fallo al guardar
        }
    }

    /**
     * Busca un producto en el inventario usando su código único.
     * Devuelve el producto si se encuentra, o null si no se encuentra en el inventario.
     */
    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto p : productos.keySet()) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p; // Devuelve el producto si su código coincide
            }
        }
        return null; // Si no encuentra el producto, devuelve null
    }

    /**
     * Carga el inventario desde un archivo en el que se almacenaron previamente los productos.
     * Si el archivo no existe o está corrupto, devuelve un inventario vacío.
     */
    @SuppressWarnings("unchecked")
    private HashMap<Producto, Integer> cargarInventario() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO_INVENTARIO))) {
            return (HashMap<Producto, Integer>) entrada.readObject(); // Lee el inventario desde el archivo
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>(); // Devuelve un inventario vacío en caso de error
        }
    }
}
