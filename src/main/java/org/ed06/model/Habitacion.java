package org.ed06.model;

/**
 * Clase con la información de las habitaciones y métodos complementarios
 */
public class Habitacion {
    private int numero;
    public enum tipoHabitacion {
        SIMPLE, DOBLE, SUITE, LITERAS
    }
    private tipoHabitacion tipo;
    private double precioBase;

    //Todo pendiente cambiar la forma de gestionar la disponibilidad en base a las fechas de las reservas
    private boolean disponible;

    public Habitacion(int numero, tipoHabitacion tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo.toString();
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Método que usa un switch para determinar el número máximo de huéspedes
     */

    public int obtenerNumMaxHuespedes() {
        return switch (tipo) {
            case SIMPLE -> 1;
            case DOBLE -> 3;
            case SUITE -> 4;
            case LITERAS -> 8;
        };
    }

    public void reservar() {
        if (disponible) {
            System.out.println("Habitación #" + numero + " ya reservada");
        }
        disponible = false;
    }
}
