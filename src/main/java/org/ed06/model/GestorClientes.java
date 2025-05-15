package org.ed06.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que se ocupa de realizar tareas relacionadas con clientes
 */
public class GestorClientes {

    private static final Map<Integer, Cliente> clientes = new HashMap<>();
    GestorReservas gestorReservas;
    GestorHabitaciones gestorHabitaciones;

    public GestorClientes() {}

    public GestorClientes(GestorReservas gestorReservas, GestorHabitaciones gestorHabitaciones) {
        this.gestorReservas = gestorReservas;
        this.gestorHabitaciones = gestorHabitaciones;
    }

    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    /**
     * Método para listar los clientes y sus datos
     */
    public static void listarClientes() {
        for (Cliente cliente : clientes.values()) {
            System.out.println("Cliente #" + cliente.getId() + " - Nombre: " + cliente.getNombre() + " - DNI: " + cliente.getNombre() + " - VIP: " + cliente.isEsVip());
        }
    }

    /**
     * Crea un cliente con los parámetros y le asigna un id
     */
    public static void registrarCliente(String nombre, String email, String dni, boolean esVip) {
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, dni, email, esVip);
        clientes.put(cliente.getId(), cliente);
    }
}
