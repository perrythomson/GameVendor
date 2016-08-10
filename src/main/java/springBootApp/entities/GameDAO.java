package springBootApp.entities;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GameDAO extends CrudRepository<Game, Long> {


    List<Game> findByVendorId(long vendorId);

    List<Game> findByCategoryId(long categoryId);  //return type... name... receives***pass the id and returns a list of games

    //is the same as saying "You just added a SELECT statement with a WHERE clause in SQL"
    //    SELECT
    //            gameId, gameTitle, gameDescription, categoryId, gamePrice, vendorId
    //    FROM
    //            games
    //    WHERE
    //    categoryId = ?;    //? is used for secure statements - its a prepared statement


    @Query("SELECT g FROM Game g WHERE g.gameTitle LIKE ?1%")  //THIS IS HSQLD make sure you understand it
    List<Game> findByNameStartsWith(String name);




}
