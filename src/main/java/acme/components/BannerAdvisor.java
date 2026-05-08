
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.client.helpers.LoggerHelper;
import acme.entities.banner.Advertisement;

@ControllerAdvice
public class BannerAdvisor {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BannerRepository repository;

	// Beans ------------------------------------------------------------------


	@ModelAttribute("advertisement")
	public Advertisement getBanner() {
		Advertisement result;

		try {
			result = this.repository.findRandomBanner();
		} catch (final Throwable oops) {
			LoggerHelper.error(oops.getLocalizedMessage());
			result = null;
		}

		return result;
	}

}
