
package acme.features.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Administrator;
import acme.client.services.AbstractService;
import acme.entities.banner.Advertisement;

@Service
public class AdministratorBannerCreateService extends AbstractService<Administrator, Advertisement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository	repository;

	private Advertisement							banner;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		this.banner = super.newObject(Advertisement.class);
	}

	@Override
	public void authorise() {
		super.setAuthorised(true);
	}

	@Override
	public void bind() {
		super.bindObject(this.banner, "slogan", "target", "picture");
	}

	@Override
	public void validate() {
		super.validateObject(this.banner);
	}

	@Override
	public void execute() {
		this.repository.save(this.banner);
	}

	@Override
	public void unbind() {
		super.unbindObject(this.banner, "slogan", "target", "picture");
	}
}
