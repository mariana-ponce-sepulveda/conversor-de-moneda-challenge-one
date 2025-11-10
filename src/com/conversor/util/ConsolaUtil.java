package com.conversor.util;

/**
 * Clase de utilidades que mejora la interacción visual en consola.
 * Permite limpiar la pantalla, imprimir mensajes coloridos y formatear la salida.
 */

public class ConsolaUtil {

    // Códigos ANSI para colores en consola
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    /**
     * Limpia la consola (compatible con Windows, Mac y Linux).
     */
    public static void limpiar() {
        try {
            // Para sistemas Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Para sistemas Unix/Linux/Mac
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    /**
     * Imprime texto en color personalizado.
     * @param texto mensaje a mostrar
     * @param color código ANSI del color
     */
    public static void imprimirColor(String texto, String color) {
        System.out.println(color + texto + RESET);
    }

}
