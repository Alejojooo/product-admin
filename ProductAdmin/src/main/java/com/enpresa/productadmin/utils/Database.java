package com.enpresa.productadmin.utils;

/**
 *
 * @author Alejo
 */
public class Database {

    private static Database instancia;

    private Database() {

    }

    public static Database getInstancia() {
        if (instancia == null) {
            instancia = new Database();
        }
        return instancia;
    }
}
