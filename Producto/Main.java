import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        
        // Menú de opciones
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar producto nuevo");
            System.out.println("2. Abastecer inventario");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Comparar productos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            //Opciones del menú
            try {
                if (opcion == 1) {
                    System.out.print("Ingrese descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese código: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Ingrese tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Ingrese costo: ");
                    double costo = scanner.nextDouble();
                    System.out.print("Ingrese impuesto: ");
                    double impuesto = scanner.nextDouble();
                    System.out.print("Ingrese cantidad inicial: ");
                    int cantidad = scanner.nextInt();

                    // Agregar un producto nuevo al inventario
                    Producto producto = new Producto(descripcion, codigo, tipo, costo, impuesto);
                    inventario.abastecerInventario(producto, cantidad);

                } else if (opcion == 2) {
                    System.out.print("Ingrese el código del producto a abastecer: ");
                    String codigo = scanner.nextLine();
                    Producto productoExistente = inventario.buscarProductoPorCodigo(codigo);

                    if (productoExistente != null) {
                        System.out.print("Ingrese la cantidad a añadir: ");
                        int cantidad = scanner.nextInt();
                        inventario.abastecerInventario(productoExistente, cantidad);
                        System.out.println("Producto abastecido con éxito.");
                    } else {
                        System.out.println("El producto no existe. ¿Desea crearlo? (S/N)");
                        String respuesta = scanner.nextLine();
                        
                        if (respuesta.equalsIgnoreCase("S")) {
                            System.out.print("Ingrese descripción: ");
                            String descripcion = scanner.nextLine();
                            System.out.print("Ingrese tipo: ");
                            String tipo = scanner.nextLine();
                            System.out.print("Ingrese costo: ");
                            double costo = scanner.nextDouble();
                            System.out.print("Ingrese impuesto: ");
                            double impuesto = scanner.nextDouble();
                            System.out.print("Ingrese cantidad inicial: ");
                            int cantidad = scanner.nextInt();

                            // Crear un nuevo producto y abastecerlo
                            Producto nuevoProducto = new Producto(descripcion, codigo, tipo, costo, impuesto);
                            inventario.abastecerInventario(nuevoProducto, cantidad);
                            System.out.println("Producto creado y abastecido con éxito.");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                    }

                } else if (opcion == 3) {
                    // Mostrar todo el inventario
                    inventario.mostrarInventario();

                } else if (opcion == 4) {
                    // Comparar productos por precio
                    System.out.print("Ingrese el código del primer producto: ");
                    String codigo1 = scanner.nextLine();
                    Producto p1 = inventario.buscarProductoPorCodigo(codigo1);

                    System.out.print("Ingrese el código del segundo producto: ");
                    String codigo2 = scanner.nextLine();
                    Producto p2 = inventario.buscarProductoPorCodigo(codigo2);

                    if (p1 != null && p2 != null) {
                        System.out.println(compararProductos(p1, p2)); // Comparar los dos productos
                    } else {
                        System.out.println("Uno o ambos productos no existen en el inventario.");
                    }

                } else if (opcion == 5) {
                    break; // Salir del programa
                }
            } catch (Exception e) {
                System.out.println("Error: entrada inválida. Intente de nuevo.");
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        }
        scanner.close();
    }


    public static String compararProductos(Producto p1, Producto p2) {
        double precio1 = p1.calcularPrecio(); // Obtener precio del primer producto
        double precio2 = p2.calcularPrecio(); // Obtener precio del segundo producto

        if (precio1 > precio2) {
            return "El producto más caro es: " + p1.getDescripcion() + " ($" + precio1 + ")";
        } else if (precio2 > precio1) {
            return "El producto más caro es: " + p2.getDescripcion() + " ($" + precio2 + ")";
        } else {
            return "Ambos productos tienen el mismo precio ($" + precio1 + ")";
        }
    }
}
