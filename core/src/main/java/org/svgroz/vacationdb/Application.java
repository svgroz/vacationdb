package org.svgroz.vacationdb;

import org.svgroz.vacationdb.datastore.api.model.cell.CellFactory;

import java.util.Optional;
import java.util.ServiceLoader;

public class Application {
    public static void main(String[] args) throws Exception {
        Optional<CellFactory> first = ServiceLoader.load(CellFactory.class).findFirst();

        System.out.println(first);

    }
}
