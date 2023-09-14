package il.ac.shenkar.model;

import java.util.List;

/**
 * DAO (Data Access Object) interface defines the standard operations for CRUD (Create, Read, Update, Delete) operations on entities.
 *
 * @param <T> The type of entity this DAO operates on.
 */
public interface DAO<T> {
    /**
     * Retrieve an entity by its unique identifier (id).
     *
     * @param id The unique identifier of the entity.
     * @return The retrieved entity.
     * @throws Exception If an error occurs during retrieval.
     */
    T get(int id) throws Exception;

    /**
     * Retrieve all entities of the specified type.
     *
     * @return A list containing all entities of the specified type.
     * @throws Exception If an error occurs during retrieval.
     */
    List<T> getAll() throws Exception;

    /**
     * Save (create) a new entity.
     *
     * @param t The entity to be saved.
     * @throws Exception If an error occurs during saving.
     */
    void save(T t) throws Exception;

    /**
     * Update an existing entity with a given identifier.
     *
     * @param id The unique identifier of the entity to update.
     * @param t  The updated entity.
     * @throws Exception If an error occurs during updating.
     */
    void update(int id, T t) throws Exception;

    /**
     * Delete an entity.
     *
     * @param t The entity to be deleted.
     * @throws Exception If an error occurs during deletion.
     */
    void delete(T t) throws Exception;
}
