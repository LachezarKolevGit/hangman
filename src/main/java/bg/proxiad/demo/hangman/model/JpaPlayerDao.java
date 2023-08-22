package bg.proxiad.demo.hangman.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.naming.spi.DirStateFactory.Result;
import org.hibernate.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaPlayerDao implements Dao<Player> {
  private final EntityManager manager;

  @Override
  public void setClass(Class<Player> classToSet) {
    // TODO Auto-generated method stub

  }

  @Override
  public Optional<Player> get(long id) { // test it, not sure if this is the best approach
    return executeInsideReadOnlyTransaction(
        (manager) -> Optional.ofNullable(manager.find(Player.class, id)));
  }

  @Override
  public List<Player> getAll() {
    manager.unwrap(Session.class).setReadOnly(manager, true);
    EntityTransaction tx = manager.getTransaction();
    List<?> players = null;
    try {
      tx.begin();
      players = manager.createQuery("SELECT p FROM Player p").getResultList();
      tx.commit();
    } catch (Exception e) {
      System.err.println("Exception occurred " + e);
      e.printStackTrace();
      tx.rollback();
    }

    return null;
  }

  @Override
  public void create(Player player) {
    executeInsideTransaction((manager) -> manager.persist(player));
  }

  @Override
  public void update(Player player) {
    // find a way to guess which fields have changed for the update
    executeInsideTransaction((manager) -> manager.merge(player));
  }

  @Override
  public void delete(Player player) {
    executeInsideTransaction((manager) -> manager.persist(player));
  }

  public void executeInsideTransaction(Consumer<EntityManager> action) {
    EntityTransaction transaction = manager.getTransaction();
    try {
      transaction.begin();
      action.accept(manager);
      transaction.commit();
    } catch (RuntimeException e) {
      transaction.rollback();
      throw e;
    }
  }

  public Optional<Player> executeInsideReadOnlyTransaction(
      Function<EntityManager, Optional<Player>> action) {
    manager.unwrap(Session.class).setDefaultReadOnly(true);
    EntityTransaction tx = manager.getTransaction();
    Optional<Player> playerOPtional = Optional.empty();
    try {
      tx.begin();
      playerOPtional = action.apply(manager);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    }
    return playerOPtional;
  }
}
