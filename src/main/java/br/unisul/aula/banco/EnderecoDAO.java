package br.unisul.aula.banco;

import br.unisul.aula.modelo.Endereco;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EnderecoDAO {

    public void insert(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(endereco);
        entityManager.getTransaction().commit();
    }

    public void remove(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Endereco endereco = findById(id);

        entityManager.remove(entityManager.contains(endereco)?endereco:entityManager.merge(endereco));
        entityManager.getTransaction().commit();
    }

    public void update(Endereco endereco) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(endereco);
        entityManager.getTransaction().commit();
    }

    public List<Endereco> findAll() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        return entityManager
                .createQuery("SELECT e FROM Endereco e", Endereco.class)
                .getResultList();
    }

    public Endereco findByCep(Integer cep){
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Endereco> query = entityManager
                .createQuery("SELECT e FROM Endereco e where e.cep =:cep", Endereco.class);
        return query.setParameter("cep", cep).getSingleResult();
    }
    public Endereco findById(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Endereco> query = entityManager
                .createQuery("SELECT e FROM Endereco e where e.id =:id", Endereco.class);
        return query.setParameter("id", id).getSingleResult();
    }

    public Integer existeCEP(Integer cep){
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Endereco> query = entityManager
                .createQuery("SELECT e FROM Endereco e where e.cep =:cep", Endereco.class);
        return query.setParameter("cep", cep).getResultList().size();
    }
}