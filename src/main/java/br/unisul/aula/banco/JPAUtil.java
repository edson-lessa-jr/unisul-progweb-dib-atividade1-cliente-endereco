package br.unisul.aula.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager(){
        if (entityManagerFactory==null){
            entityManagerFactory = Persistence.createEntityManagerFactory("clientes");
        }
        return entityManagerFactory.createEntityManager();
    }
}