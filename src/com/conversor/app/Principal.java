package com.conversor.app;

import com.conversor.modelo.Conversor;
import com.conversor.servicio.ConsultarAPI;
import com.conversor.util.ConsolaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * Se encarga de mostrar el menú, recibir los datos del usuario
 * y realizar la conversión utilizando la clase ConsultarAPI.
 */
public class Principal {
    public static void main(String[] args) {

        // Inicializa el lector de consola y el servicio de API
        Scanner scanner = new Scanner(System.in);
        ConsultarAPI consulta = new ConsultarAPI();

        // Lista para almacenar el historial de conversiones
        List<String> historial = new ArrayList<>();

        // Limpia la pantalla al iniciar
        ConsolaUtil.limpiar();

        // Muestra un encabezado decorativo con ASCII Art
        ConsolaUtil.imprimirColor("""
                 ██████╗ ██████╗ ███╗   ██╗██╗   ██╗███████╗██████╗ 
                ██╔════╝██╔═══██╗████╗  ██║██║   ██║██╔════╝██╔══██╗
                ██║     ██║   ██║██╔██╗ ██║██║   ██║█████╗  ██████╔╝
                ██║     ██║   ██║██║╚██╗██║╚██╗ ██╔╝██╔══╝  ██╔══██╗
                ╚██████╗╚██████╔╝██║ ╚████║ ╚████╔╝ ███████╗██║  ██║
                 ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝  ╚═══╝  ╚══════╝╚═╝  ╚═╝
            """, ConsolaUtil.CYAN);

        // Mensaje de bienvenida
        ConsolaUtil.imprimirColor("🌎 BIENVENIDOS AL CONVERSOR DE MONEDAS - Challenge ONE\n", ConsolaUtil.GREEN);

        // Ciclo principal del menú
        while (true) {
    System.out.println("""
                       ╔══════════════════════════MENÚ PRINCIPAL ═══════════════════════════════╗         
                       ║      1️⃣  USD 🇺🇸 dólares estadounidenses a → CLP 🇨🇱 pesos chilenos       ║ 
                       ║      2️⃣  CLP 🇨🇱 pesos chilenos a → USD 🇺🇸 dólares estadounidenses       ║ 
                       ║      3️⃣  USD 🇺🇸 dólares estadounidenses a → ARS 🇦🇷 pesos argentinos     ║ 
                       ║      4️⃣  ARS 🇦🇷 pesos argentinos a → USD 🇺🇸 dólares estadounidenses     ║ 
                       ║      5️⃣  EUR 🇪🇺 euros a → USD 🇺🇸 dólares estadounidenses                ║                
                       ║      6️⃣  USD 🇺🇸 dólares estadounidenses a → EUR 🇪🇺 euros                ║    
                       ║      7️⃣  BRL 🇧🇷 reales brasileños a → CLP 🇨🇱 pesos chilenos             ║       
                       ║      8️⃣  MXN 🇲🇽 pesos mexicanos a  → CLP 🇨🇱 pesos chilenos              ║    
                       ║      9️⃣  🕓 Ver historial de conversiones                              ║
                       ║      🔟  ❌ Salir                                                      ║                                         
                       ╚═════════════════════════════════════════════════════════════════════════╝""");

     
        System.out.print("👉 Ingrese su opción: ");

        int opcion = scanner.nextInt();

        // Opción para salir del programa
        if (opcion == 10) {
            ConsolaUtil.imprimirColor("👋 Gracias por usar el conversor. ¡Hasta pronto!", ConsolaUtil.YELLOW);
            break;
        }
            // Mostrar historial
            if (opcion == 9) {
                ConsolaUtil.imprimirColor("\n📜 HISTORIAL DE CONVERSIONES\n", ConsolaUtil.CYAN);
                if (historial.isEmpty()) {
                    System.out.println("⚠️ No hay conversiones registradas aún.");
                } else {
                    historial.forEach(System.out::println);
                }
                System.out.println();
                continue;
            }
        String origen = "", destino = "";

        // Asigna las monedas según la opción elegida
        switch (opcion) {
            case 1 -> { origen = "USD"; destino = "CLP"; }
            case 2 -> { origen = "CLP"; destino = "USD"; }
            case 3 -> { origen = "USD"; destino = "ARS"; }
            case 4 -> { origen = "ARS"; destino = "USD"; }
            case 5 -> { origen = "EUR"; destino = "USD"; }
            case 6 -> { origen = "USD"; destino = "EUR"; }
            case 7 -> { origen = "BRL"; destino = "CLP"; }
            case 8 -> { origen = "MXN"; destino = "CLP"; }

            default -> {
                ConsolaUtil.imprimirColor("⚠️ Opción inválida. Intente nuevamente.", ConsolaUtil.RED);
                continue;
            }
        }

            // Solicita el monto a convertir con validación completa
            double monto = 0;
            while (true) {
                System.out.print("💰 Ingrese el monto a convertir: ");

                // Verifica si el usuario ingresó un número
                if (scanner.hasNextDouble()) {
                    monto = scanner.nextDouble();

                    // Verifica que el número sea mayor que cero
                    if (monto <= 0) {
                        ConsolaUtil.imprimirColor("⚠️ El monto debe ser mayor que cero. Intente nuevamente.", ConsolaUtil.RED);
                        continue; // vuelve a pedir el dato
                    }
                    break; // sale del bucle si el valor es válido

                } else {
                    // Maneja entradas no numéricas
                    ConsolaUtil.imprimirColor("⚠️ Entrada inválida solo numeros. Por favor ingrese un número válido.", ConsolaUtil.RED);
                    scanner.next(); // limpia la entrada incorrecta
                }
            }


        // Realiza la consulta a la API
        Conversor conversor = consulta.obtenerTasa(origen, destino);

        // Muestra el resultado o error
        if (conversor != null) {
            double resultado = conversor.getConversion_rate() * monto;
            String detalle = String.format("El monto ingresado de %.2f %s equivale a 💰 %.2f %s", monto, origen, resultado, destino);

            ConsolaUtil.imprimirColor("\n💹 Resultado de la conversión:", ConsolaUtil.CYAN);
            System.out.println(detalle);

            // Agrega al historial
            historial.add(detalle);
        } else {
            ConsolaUtil.imprimirColor("❌ No fue posible realizar la conversión.", ConsolaUtil.RED);
        }

            System.out.println();
    }

    scanner.close(); // Cierra el lector al salir del bucle
}
}