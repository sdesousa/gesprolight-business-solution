package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;
import java.util.List;

public class GpProject implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private double amount;

	private Date creationDate;

	private String description;

	private Date endDate;

	private String name;

	private String projectCode;

	private Date startDate;

	private Date updateDate;

	private List<GpPhase> gpPhases;

	private GpProjectManager gpChefProjet;

	private GpOrganization gpOrganization;

	public GpProject() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectCode() {
		return this.projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<GpPhase> getGpPhases() {
		return this.gpPhases;
	}

	public void setGpPhases(List<GpPhase> gpPhases) {
		this.gpPhases = gpPhases;
	}

	public GpPhase addGpPhas(GpPhase gpPhas) {
		getGpPhases().add(gpPhas);
		gpPhas.setGpProject(this);

		return gpPhas;
	}

	public GpPhase removeGpPhas(GpPhase gpPhas) {
		getGpPhases().remove(gpPhas);
		gpPhas.setGpProject(null);

		return gpPhas;
	}

	public GpProjectManager getGpChefProjet() {
		return this.gpChefProjet;
	}

	public void setGpChefProjet(GpProjectManager gpChefProjet) {
		this.gpChefProjet = gpChefProjet;
	}

	public GpOrganization getGpOrganization() {
		return this.gpOrganization;
	}

	public void setGpOrganization(GpOrganization gpOrganization) {
		this.gpOrganization = gpOrganization;
	}

	@Override
	public String toString() {
		return "GpProject [id=" + id + ", amount=" + amount + ", creationDate=" + creationDate + ", description="
				+ description + ", endDate=" + endDate + ", name=" + name + ", projectCode=" + projectCode
				+ ", startDate=" + startDate + ", updateDate=" + updateDate + ", gpPhases=" + gpPhases
				+ ", gpChefProjet=" + gpChefProjet + ", gpOrganization=" + gpOrganization + "]";
	}

}