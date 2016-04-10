package fr.doodoodle.server.db.business;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by Thiebaud on 10/04/2016.
 */
public interface CrudRepository<T, ID extends Serializable>
        extends Repository<T, ID> {


    T findOneById(ID id);

    Iterable<T> findAll();

    Long count();

    void delete(T entity);

    T save(T entity);

    boolean exists(ID primaryKey);
}