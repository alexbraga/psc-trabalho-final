package org.example.util;

import javax.persistence.EntityManagerFactory;

public interface EntityManagerFactoryProvider {
    EntityManagerFactory getEntityManagerFactory();
    void close();
}
