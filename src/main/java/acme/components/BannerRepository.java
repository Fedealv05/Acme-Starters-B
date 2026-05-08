
package acme.components;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.helpers.RandomHelper;
import acme.client.repositories.AbstractRepository;
import acme.entities.banner.Advertisement;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select count(b) from Advertisement b")
	int countBanners();

	@Query("select b from Advertisement b")
	List<Advertisement> findAllBanners(PageRequest pageRequest);

	default Advertisement findRandomBanner() {
		Advertisement result;
		int count, index;
		PageRequest page;
		List<Advertisement> list;

		count = this.countBanners();
		if (count == 0)
			result = null;
		else {
			index = RandomHelper.nextInt(0, count);

			page = PageRequest.of(index, 1, Sort.by(Direction.ASC, "id"));
			list = this.findAllBanners(page);
			result = list.isEmpty() ? null : list.get(0);
		}

		return result;
	}

}
