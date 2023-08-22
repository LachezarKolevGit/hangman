package bg.proxiad.demo.hangman.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JpaRankingDao<T> extends AbstractJpaDao<T> implements Dao<T> {}
