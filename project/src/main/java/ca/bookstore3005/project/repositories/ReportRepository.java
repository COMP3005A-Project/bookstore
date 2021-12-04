package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.ReportEntry;

@Repository
public interface ReportRepository extends CrudRepository<ReportEntry, String> {

    @Query("SELECT * FROM sales_per_author(:publisherName)")
    List<ReportEntry> findAuthorsVsSalesReport(@Param("publisherName") String publisherName);

    @Query("SELECT * FROM sales_per_genre(:publisherName)")
    List<ReportEntry> findGenresVsSalesReport(@Param("publisherName") String publisherName);

}
