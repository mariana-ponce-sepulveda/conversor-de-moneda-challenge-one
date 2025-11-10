package com.conversor.modelo;
/**
 * Clase modelo que representa la información recibida desde la API de conversión.
 * Contiene los datos principales: moneda base, moneda destino y la tasa de conversión.
 */
public class Conversor {
    private String base_code;         // Moneda de origen (por ejemplo: "USD")
    private String target_code;       // Moneda de destino (por ejemplo: "CLP")
    private double conversion_rate;   // Tasa de conversión (valor unitario)

    /**
     * Devuelve el código de la moneda base.
     */
    public String getBase_code() {
        return base_code;
    }

    /**
     * Devuelve el código de la moneda destino.
     */
    public String getTarget_code() {
        return target_code;
    }

    /**
     * Devuelve la tasa de conversión obtenida desde la API.
     */
    public double getConversion_rate() {
        return conversion_rate;
    }

    /**
     * Calcula el resultado de la conversión multiplicando el monto por la tasa.
     * También formatea el texto con los valores de origen y destino.
     *
     * @param monto valor ingresado por el usuario en la moneda base
     * @return texto con el resultado final de la conversión
     */
    public String mostrarResultado(double monto) {
        double resultado = monto * conversion_rate;
        return String.format("💵 %.2f %s = 💰 %.2f %s", monto, base_code, resultado, target_code);
    }

    // Retorna el símbolo de la moneda según el código ISO
    private String obtenerSimbolo(String codigoMoneda) {
        return switch (codigoMoneda) {
            case "USD" -> "$";
            case "CLP" -> "$";
            case "ARS" -> "$";
            case "EUR" -> "€";
            case "BRL" -> "R$";
            default -> codigoMoneda;
        };
    }

    @Override
    public String toString() {
        return "Conversor{" +
                "Moneda origen='" + base_code + '\'' +
                ", Moneda destino='" + target_code + '\'' +
                ", Tasa conversión=" + conversion_rate +
                '}';
    }

    public void setResult(String s) {
    }
}
