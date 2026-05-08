
package acme.constraints.sponsorships;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.sponsorships.DonationRepository;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.sponsorships.SponsorshipRepository;

@Validator
public class SponsorshipValidator extends AbstractValidator<ValidSponsorship, Sponsorship> {

	@Autowired
	private DonationRepository		donationRepository;

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;


	@Override
	protected void initialise(final ValidSponsorship annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Sponsorship sponsorship, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (sponsorship == null)
			result = true;
		else {

			// 0. Ticker único
			{
				boolean uniqueTicker;
				Sponsorship existing;

				existing = this.sponsorshipRepository.findSponsorshipByTicker(sponsorship.getTicker());
				uniqueTicker = existing == null || existing.equals(sponsorship);

				super.state(context, uniqueTicker, "ticker", "acme.validation.sponsorship.duplicated-ticker.message");
			}

			{
				boolean hasDonations;

				if (Boolean.TRUE.equals(sponsorship.getDraftMode()))
					hasDonations = true;
				else {
					long count = this.donationRepository.countBySponsorshipId(sponsorship.getId());
					hasDonations = count > 0;
				}

				super.state(context, hasDonations, "draftMode", "acme.validation.sponsorship.at-least-one-donation.message");
			}

			{
				boolean validInterval = false;

				if (sponsorship.getStartMoment() != null && sponsorship.getEndMoment() != null) {
					boolean startInFuture = MomentHelper.isAfter(sponsorship.getStartMoment(), MomentHelper.getCurrentMoment());
					boolean endAfterStart = MomentHelper.isAfter(sponsorship.getEndMoment(), sponsorship.getStartMoment());
					validInterval = startInFuture && endAfterStart;
				}

				super.state(context, validInterval, "startMoment", "acme.validation.sponsorship.invalid-interval.message");
			}

			return !super.hasErrors(context);
		}

		return result;
	}
}
