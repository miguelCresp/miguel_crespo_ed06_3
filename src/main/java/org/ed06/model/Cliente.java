package org.ed06.model;

/**
 * Clase para almacenar y obtener datos de clientes, con validaciones de atributos en el constructor
 */
public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    private String email;
    private boolean esVip;

    public Cliente(int id, String nombre, String dni, String email, boolean esVip) {
        this.id = id;
        if(validarNombre(nombre)) {
            this.nombre = nombre;
        }
        if(validarDni(dni)) {
            this.dni = dni;
        }
        if(validarEmail(email)) {
            this.email = email;
        }
        this.esVip = esVip;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEsVip() {
        return esVip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    public static boolean validarNombre(String nombre) {
        // Comprobamos que el nombre no sea nulo, esté vacio y tenga al menos 3 caracteres eliminando espacios inciales y finales
        if (nombre == null || nombre.trim().length() < 3) {
            throw new IllegalArgumentException("El nombre no es válido");
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("El email no es válido");
        }
        return true;
    }

    public static boolean validarDni(String dni) {
        if (!dni.matches("[0-9]{8}[A-Z]")) {
            throw new IllegalArgumentException("El DNI no es válido");
        }
        return true;
    }

}