package org.example.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MariaDBEntityManagerFactoryProvider implements EntityManagerFactoryProvider {
    private final EntityManagerFactory EMF;

    public MariaDBEntityManagerFactoryProvider(String persistenceUnitName) {
        this.EMF = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }

    @Override
    public void close() {
        EMF.close();
    }
}
