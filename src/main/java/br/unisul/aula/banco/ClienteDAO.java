package br.unisul.aula.banco;

import br.unisul.aula.modelo.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO {
    public void insert(Cliente cliente) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    public void remove(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Cliente cliente = findById(id);
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }

    public void update(Cliente cliente) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    public List<Cliente> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        return entityManager
                .createQuery("SELECT s FROM Cliente s", Cliente.class)
                .getResultList();
    }

    public Cliente findById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
            TypedQuery<Cliente> query = entityManager
                .createQuery("SELECT s FROM Cliente s where s.id =:id", Cliente.class);
        return query.setParameter("id", id).getSingleResult();
    }

    public List<Cliente> findByCidade(String cidade){
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Cliente> query =
                entityManager.createQuery("SELECT c FROM Cliente c where c.endereco.cidade = :cidade",
                        Cliente.class);
        return query.setParameter("cidade", cidade).getResultList();
    }
}
