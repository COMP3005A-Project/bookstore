package ca.bookstore3005.project.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region, String> {

    @Modifying
    @Query("INSERT INTO region values(:address_street_postal, :city, :province) ON CONFLICT DO NOTHING")
    void addRegion(@Param("address_street_postal") String address_street_postal,
                     @Param("city") String city,
                     @Param("province") String province);
}
