package org.ed06.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se ocupa de realizar tareas relacionadas con habitaciones
 */
public class GestorHabitaciones {

    private final List<Habitacion> habitaciones = new ArrayList<>();
    GestorReservas gestorReservas;
    GestorClientes gestorClientes;

    public GestorHabitaciones() {}

    public GestorHabitaciones(GestorReservas gestorReservas, GestorClientes gestorClientes) {
        this.gestorReservas = gestorReservas;
        this.gestorClientes = gestorClientes;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * Devuelve una habitación
     * @param numero Número de la habitación a buscar
     */
    public Habitacion getHabitacion(int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }

    /**
     * Método para agregar una nueva habitación al hotel
     */

    public void registrarHabitacion(int numero, Habitacion.tipoHabitacion tipo, double precioBase) {
        Habitacion habitacion = new Habitacion(habitaciones.size() + 1, tipo, precioBase);
        habitaciones.add(habitacion);
        gestorReservas.getReservasPorHabitacion().put(habitacion.getNumero(), new ArrayList<>());
    }

    /**
     * Método para listar las habitaciones disponibles
     */
    public void listarHabitacionesDisp() {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                System.out.println("Habitación #" + habitacion.getNumero() + " - Tipo: " + habitacion.getTipo() + " - Precio base: " + habitacion.getPrecioBase());
            }
        }
    }

    /**
     * Método para registrar varias habitaciones a la vez
     *
     * @param tipos lista de cada tipo de habitación
     * @param preciosBase lista de precios para cada habitación
     */
    public void registrarHabitaciones(List<Habitacion.tipoHabitacion> tipos, List<Double> preciosBase) {
        for (int i = 0; i < tipos.size(); i++) {
            Habitacion habitacion = new Habitacion(habitaciones.size() + 1, tipos.get(i), preciosBase.get(i));
            habitaciones.add(habitacion);
            gestorReservas.getReservasPorHabitacion().put(habitacion.getNumero(), new ArrayList<>());
        }
    }
}
