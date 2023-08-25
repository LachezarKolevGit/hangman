package bg.proxiad.demo.hangman.utils;

import bg.proxiad.demo.hangman.model.Dao;
import bg.proxiad.demo.hangman.model.GenericJpaDao;
import bg.proxiad.demo.hangman.model.Player;

public class DaoFactory {

  public GenericJpaDao<Player> getDao(String type) {
    GenericJpaDao<Player> genericJpaDao = new GenericJpaDao<>();
    genericJpaDao.setClass(Player.class);
    return genericJpaDao;
  }
}
