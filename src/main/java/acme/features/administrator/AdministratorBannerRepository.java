
package acme.features.administrator;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.banner.Advertisement;

@Repository
public interface AdministratorBannerRepository extends AbstractRepository {

	@Query("select b from Advertisement b where b.id = :id")
	Advertisement findBannerById(int id);

	@Query("select b from Advertisement b")
	Collection<Advertisement> findAllBanners();

}
