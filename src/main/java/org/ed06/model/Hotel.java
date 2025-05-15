package org.ed06.model;

import java.time.LocalDate;
import java.util.*;

/**
 * Clase que tiene la información del hotel y llama a los métodos de los gestores
 */
public class Hotel {
    private final String nombre;
    private final String direccion;
    private final String telefono;

    private static final Map<Integer, Cliente> clientes = new HashMap<>();

    GestorHabitaciones gestorHabitaciones;
    GestorClientes gestorClientes;
    GestorReservas gestorReservas;


    public Hotel(String nombre, String direccion, String telefono, GestorHabitaciones gestorHabitaciones, GestorClientes gestorClientes, GestorReservas gestorReservas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.gestorHabitaciones = gestorHabitaciones;
        this.gestorClientes = gestorClientes;
        this.gestorReservas = gestorReservas;
    }

    public void registrarHabitacion(Habitacion.tipoHabitacion tipo, double precioBase) {
        gestorHabitaciones.registrarHabitacion(gestorHabitaciones.getHabitaciones().size() + 1, tipo, precioBase);
    }

    public void registrarHabitaciones(List<Habitacion.tipoHabitacion> tipos, List<Double> preciosBase) {
        gestorHabitaciones.registrarHabitaciones(tipos, preciosBase);
    }

    public void listarHabitacionesDisponibles() {
        gestorHabitaciones.listarHabitacionesDisp();
    }


    public int reservarHabitacion(int clienteId, Habitacion.tipoHabitacion tipo, LocalDate fechaEntrada, LocalDate fechaSalida) {
        return gestorReservas.reservarHabitacion(clienteId, tipo, fechaEntrada, fechaSalida);
    }

    public void listarReservas() {
        gestorReservas.listarReservas();
    }

    public void registrarCliente(String nombre, String email, String dni, boolean esVip) {
        gestorClientes.registrarCliente(nombre, email, dni, esVip);
    }

    public void listarClientes() {
        gestorClientes.listarClientes();
    }
}
