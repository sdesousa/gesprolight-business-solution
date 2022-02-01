package af.cmr.indyli.gespro.light.business.entity;

import java.util.List;

public class GpOrganization implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String adrWeb;

	private String contactEmail;

	private String contactName;

	private String name;

	private String orgCode;

	private int phoneNumber;

	private List<GpAddress> gpAddresses;

	private List<GpProject> gpProjects;

	public GpOrganization() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdrWeb() {
		return this.adrWeb;
	}

	public void setAdrWeb(String adrWeb) {
		this.adrWeb = adrWeb;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<GpAddress> getGpAddresses() {
		return this.gpAddresses;
	}

	public void setGpAddresses(List<GpAddress> gpAddresses) {
		this.gpAddresses = gpAddresses;
	}

	public GpAddress addGpAddress(GpAddress gpAddress) {
		getGpAddresses().add(gpAddress);
		gpAddress.setGpOrganization(this);

		return gpAddress;
	}

	public GpAddress removeGpAddress(GpAddress gpAddress) {
		getGpAddresses().remove(gpAddress);
		gpAddress.setGpOrganization(null);

		return gpAddress;
	}

	public List<GpProject> getGpProjects() {
		return this.gpProjects;
	}

	public void setGpProjects(List<GpProject> gpProjects) {
		this.gpProjects = gpProjects;
	}

	public GpProject addGpProject(GpProject gpProject) {
		getGpProjects().add(gpProject);
		gpProject.setGpOrganization(this);

		return gpProject;
	}

	public GpProject removeGpProject(GpProject gpProject) {
		getGpProjects().remove(gpProject);
		gpProject.setGpOrganization(null);

		return gpProject;
	}

	@Override
	public String toString() {
		return "GpOrganization [id=" + id + ", adrWeb=" + adrWeb + ", contactEmail=" + contactEmail + ", contactName="
				+ contactName + ", name=" + name + ", orgCode=" + orgCode + ", phoneNumber=" + phoneNumber
				+ ", gpAddresses=" + gpAddresses + ", gpProjects=" + gpProjects + "]";
	}

}