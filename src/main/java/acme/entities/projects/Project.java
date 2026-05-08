
package acme.entities.projects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import acme.constraints.projects.ValidProject;
import acme.entities.campaigns.Campaign;
import acme.entities.campaigns.CampaignRepository;
import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionRepository;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.StrategyRepository;
import acme.realms.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidProject
public class Project extends AbstractEntity {

	@Autowired
	@Transient
	private transient InventionRepository	inventionRepository;

	@Autowired
	@Transient
	private transient CampaignRepository	campaignRepository;

	@Autowired
	@Transient
	private transient StrategyRepository	strategyRepository;

	private static final long				serialVersionUID	= 1L;

	@Mandatory
	@ValidHeader
	@Column
	private String							title;

	@Mandatory
	@ValidHeader
	@Column
	private String							keyWords;

	@Mandatory
	@ValidText
	@Column
	private String							description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date							kickOff;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date							closeOut;

	@Mandatory
	@Valid
	@Column
	private Boolean							draftMode;

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Manager							manager;


	@Mandatory
	@Valid
	@Transient
	public Double getEffort() {
		double totalMonths = 0.0;
		Set<Integer> uniqueMemberIds = new HashSet<>();

		if (this.inventionRepository != null)
			for (Invention inv : this.inventionRepository.findByProjectId(this.getId())) {
				totalMonths += inv.getMonthsActive();
				uniqueMemberIds.add(inv.getInventor().getId());
			}

		if (this.campaignRepository != null)
			for (Campaign cam : this.campaignRepository.findByProjectId(this.getId())) {
				totalMonths += cam.getMonthsActive();
				uniqueMemberIds.add(cam.getSpokesperson().getId());
			}

		if (this.strategyRepository != null)
			for (Strategy str : this.strategyRepository.findByProjectId(this.getId())) {
				totalMonths += str.getMonthsActive();
				uniqueMemberIds.add(str.getFundraiser().getId());
			}

		int numberOfPeople = uniqueMemberIds.size();

		if (numberOfPeople == 0)
			return 0.0;

		return totalMonths / numberOfPeople;
	}
}
