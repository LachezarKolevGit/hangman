package bg.proxiad.demo.hangman.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
@Transactional
public class GenericJpaDao<T> implements Dao<T> {

    private Class<T> implementedClass;

    @PersistenceContext
    private EntityManager entityManager;

    public void setClass(Class<T> classToSet) {
        this.implementedClass = classToSet;
    }

    public Optional<T> get(long id) {
        return Optional.ofNullable(entityManager.find(implementedClass, id));
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("from " + implementedClass.getName()).getResultList();
    }

    public void create(T t) {
        System.out.println("Create");
        if (entityManager == null) {
            System.out.println("Entity manager is null");
            return;
        }
        entityManager.persist(t);
    }

    public void update(T t) {
        entityManager.merge(t);
    }

    public void delete(T t) {
        entityManager.remove(t);
    }
}
