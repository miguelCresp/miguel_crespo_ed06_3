package org.ed06.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que se ocupa de realizar tareas relacionadas con reservas
 */
public class GestorReservas {

    private final Map<Integer, List<Reserva>> reservasPorHabitacion = new HashMap<>();
    GestorHabitaciones gestorHabitaciones;
    GestorClientes gestorClientes;

    public GestorReservas() {}

    public GestorReservas(GestorHabitaciones gestorHabitaciones, GestorClientes gestorClientes) {
        this.gestorHabitaciones = gestorHabitaciones;
        this.gestorClientes = gestorClientes;
    }


    public Map<Integer, List<Reserva>> getReservasPorHabitacion() {
        return reservasPorHabitacion;
    }

    /**
     *  Método para realizar una reserva.
     *  Comprueba si hay habitaciones disponibles, si existe el cliente y si las fechas son coherentes.
     * Si encuentra una habitación disponible del tipo solicitado,
     * crea una nueva reserva y la añade a la lista de reservas y devuelve el número de la habitación reservada.
     * Antes de crear la reserva, comprueba si el cliente pasa a ser VIP tras la nueva reserva,
     * en caso de que haya realizado más de 3 reservas en el último año.
     *
     * @param clienteId Identificador único de cada cliente
     * @param tipo Tipo de habitación
     * @return El número de la habitación reservada o el entero que representa cada posible error
     */
    public int reservarHabitacion(int clienteId, Habitacion.tipoHabitacion tipo, LocalDate fechaEntrada, LocalDate fechaSalida) {
        // Comprobamos si hay habitaciones en el hotel
        if (gestorHabitaciones.getHabitaciones().isEmpty()) {
            System.out.println("No hay habitaciones en el hotel");
            return -4;
        }
        //comprobamos si existe el cliente
        if (gestorClientes.getClientes().get(clienteId) == null) {
            System.out.println("No existe el cliente con id " + clienteId);
            return -3;
        }
        Cliente cliente = this.gestorClientes.getClientes().get(clienteId);
        // comprobamos si las fechas son coherentes
        if (!fechaEntrada.isBefore(fechaSalida)) {
            System.out.println("La fecha de entrada es posterior a la fecha de salida");
            return -2;
        }
        //buscamos una habitación disponible
        for (Habitacion habitacion : gestorHabitaciones.getHabitaciones()) {
            if (habitacion.getTipo().equals(tipo.toString()) && habitacion.isDisponible()) {
                // Comprobamos si el cliente pasa a ser vip tras la nueva reserva
                int numReservas = 0;
                for (List<Reserva> reservasHabitacion : reservasPorHabitacion.values()) {
                    for (Reserva reservaCliente : reservasHabitacion) {
                        if (reservaCliente.getCliente().equals(cliente)) {
                            if (reservaCliente.getFechaInicio().isAfter(LocalDate.now().minusYears(1))) {
                                numReservas++;
                            }
                        }
                    }
                }
                if (numReservas > 3 && !cliente.isEsVip()) {
                    cliente.setEsVip(true);
                    System.out.println("El cliente " + cliente.getNombre() + " ha pasado a ser VIP");
                }

                // Creamos la reserva
                Reserva reserva = new Reserva(reservasPorHabitacion.size() + 1, habitacion, cliente, fechaEntrada, fechaSalida);
                reservasPorHabitacion.get(habitacion.getNumero()).add(reserva);
                // Marcamos la habitación como no disponible
                habitacion.reservar();

                System.out.println("Reserva realizada con éxito");
                return habitacion.getNumero();
            }
        }
        // si no hay habitaciones disponibles del tipo solicitado, mostramos un mensaje
        System.out.println("No hay habitaciones disponibles del tipo " + tipo);
        return -1;
        //return 0;
    }

    /**
     * Imprime todas las reservas, los clientes que las han solicitado y la fecha de entrada y salida
     */
    public void listarReservas() {
        reservasPorHabitacion.forEach((key, value) -> {
            System.out.println("Habitación #" + key);
            value.forEach(reserva -> System.out.println(
                    "Reserva #" + reserva.getId() + " - Cliente: " + reserva.getCliente().getNombre()
                            + " - Fecha de entrada: " + reserva.getFechaInicio()
                            + " - Fecha de salida: " + reserva.getFechaFin()));
        });
    }
}
