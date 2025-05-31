import java.util.*;

class Calculadora {
    private String[][] variables; // Matriz de variables [nombre, valor]

    public Calculadora(int cantidadVariables) {
        variables = new String[cantidadVariables][2]; // Columna 0: Nombre, Columna 1: Valor
    }

    public void asignarValor(String nombre, double valor) {
        for (int i = 0; i < variables.length; i++) {
            if (variables[i][0] == null) {
                variables[i][0] = nombre;
                variables[i][1] = String.valueOf(valor);
                break;
            }
        }
    }

    public double obtenerValor(String nombre) {
        for (String[] var : variables) {
            if (var[0] != null && var[0].equals(nombre)) {
                return Double.parseDouble(var[1]);
            }
        }
        return 0;
    }
}

class Graficador {
    private double[][] valores; // Matriz para almacenar (x, f(x))

    public Graficador(int puntos) {
        valores = new double[puntos][2];
    }

    public void calcularValores(String funcion) {
        for (int i = 0; i < valores.length; i++) {
            valores[i][0] = i - valores.length / 2; // Valores de x
            valores[i][1] = evaluarFuncion(funcion, valores[i][0]); // Evaluar f(x)
        }
    }

    private double evaluarFuncion(String funcion, double x) {
        if (funcion.equals("x^2")) return Math.pow(x, 2);
        if (funcion.equals("x^3")) return Math.pow(x, 3);
        if (funcion.equals("sqrt(x)")) return Math.sqrt(Math.abs(x)); // Evita valores negativos
        return 0;
    }

    public void mostrarTabla() {
        System.out.println("Tabla de valores:");
        System.out.println(" x | f(x) ");
        System.out.println("----------------");
        for (double[] punto : valores) {
            System.out.printf(" %2.0f | %5.2f %n", punto[0], punto[1]);
        }
    }
}

class Contactos {
    private String[][] listaContactos;
    private int totalContactos;

    public Contactos(int maxContactos) {
        listaContactos = new String[maxContactos][2]; // Columna 0: Nombre, Columna 1: Teléfono
        totalContactos = 0;
    }

    public void agregarContacto(String nombre, String telefono) {
        if (totalContactos < listaContactos.length) {
            listaContactos[totalContactos][0] = nombre;
            listaContactos[totalContactos][1] = telefono;
            totalContactos++;
        } else {
            System.out.println("¡Mijo, la agenda está llena!");
        }
    }

    public void listarContactos() {
        System.out.println("Lista de Contactos:");
        for (int i = 0; i < totalContactos; i++) {
            System.out.println(listaContactos[i][0] + " - Tel: " + listaContactos[i][1]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // **1. Calculadora con matrices**
        System.out.println("¡Bienvenido a la calculadora de expresiones!");
        Calculadora calc = new Calculadora(3);

        for (int i = 0; i < 3; i++) {
            System.out.print("Variable " + (i + 1) + " - Nombre: ");
            String nombre = sc.next();
            System.out.print("Valor: ");
            double valor = sc.nextDouble();
            calc.asignarValor(nombre, valor);
        }

        System.out.print("\nIngrese nombre de variable a consultar: ");
        String consulta = sc.next();
        System.out.println("Valor obtenido: " + calc.obtenerValor(consulta));

        // **2. Graficador de funciones**
        System.out.println("\nGraficador de funciones:");
        System.out.print("Ingrese la función (ej: x^2, x^3, sqrt(x)): ");
        String funcion = sc.next();
        Graficador graf = new Graficador(10);
        graf.calcularValores(funcion);
        graf.mostrarTabla();

        // **3. Gestión de Contactos con Matrices**
        System.out.println("\nGestión de contactos:");
        Contactos agenda = new Contactos(5);

        agenda.agregarContacto("Juan Pérez", "3123456789");
        agenda.agregarContacto("Ana Gómez", "3156789012");
        agenda.agregarContacto("Carlos Ramírez", "3109876543");
        agenda.listarContactos();
    }
}