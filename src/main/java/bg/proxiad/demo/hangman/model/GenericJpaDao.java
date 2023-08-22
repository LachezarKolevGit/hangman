package bg.proxiad.demo.hangman.model;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;

@Repository
@Scope("prototype")
public class GenericJpaDao<T> implements Dao<T> {

  private Class<T> implementedClass;

  @PersistenceContext(unitName = "entityManagerFactory")
  private EntityManager entityManager;

  public void setClass(Class<T> classToSet) {
    this.implementedClass = classToSet;
  }

  public Optional<T> get(long id) {
    // EntityManager entityManager = entityManagerFactory.createEntityManager();

    return Optional.ofNullable(entityManager.find(implementedClass, id));
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    //  EntityManager entityManager = entityManagerFactory.createEntityManager();

    return entityManager.createQuery("from " + implementedClass.getName()).getResultList();
  }

  public void create(T t) {
    // EntityManager entityManager = entityManagerFactory.createEntityManager();
    System.out.println("V create");
    if (entityManager == null) {
      System.out.println("Entity manager is null");
      return;
    }
    entityManager.persist(t);
    entityManager.flush();
    entityManager.clear();
  }

  public void update(T t) {
    //  EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.merge(t);
  }

  public void delete(T t) {
    // EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.remove(t);
  }
}
