package org.example.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MariaDBEntityManagerFactoryProvider implements EntityManagerFactoryProvider {
    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("mariadbPU");
    }
}
