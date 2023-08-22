package bg.proxiad.demo.hangman.model;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

  void setClass(Class<T> classToSet);

  Optional<T> get(long id);

  List<T> getAll();

  void create(T t);

  void update(T t);

  void delete(T t);
}
