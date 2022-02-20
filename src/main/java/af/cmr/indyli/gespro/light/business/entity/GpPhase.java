package af.cmr.indyli.gespro.light.business.entity;

import java.util.Date;
import java.util.List;
public class GpPhase implements IEntity {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private double amount;
	
	private Date creationDate;

	private String description;

	private Date endDate;

	private byte isEnded;

	private String phaseCode;

	private Date startDate;

	private byte status;

	private Date updateDate;

	private List<GpBill> gpBills;

	private List<GpDeliverable> gpDeliverables;

	private List<GpEmpReaPhase> gpEmpReaPhases;

	private GpProject gpProject;

	public GpPhase() {
		this.gpProject = new GpProject();
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

	public byte getIsEnded() {
		return this.isEnded;
	}

	public void setIsEnded(byte isEnded) {
		this.isEnded = isEnded;
	}

	public String getPhaseCode() {
		return this.phaseCode;
	}

	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<GpBill> getGpBills() {
		return this.gpBills;
	}

	public void setGpBills(List<GpBill> gpBills) {
		this.gpBills = gpBills;
	}

	public GpBill addGpBill(GpBill gpBill) {
		getGpBills().add(gpBill);
		gpBill.setGpPhase(this);

		return gpBill;
	}

	public GpBill removeGpBill(GpBill gpBill) {
		getGpBills().remove(gpBill);
		gpBill.setGpPhase(null);

		return gpBill;
	}

	public List<GpDeliverable> getGpDeliverables() {
		return this.gpDeliverables;
	}

	public void setGpDeliverables(List<GpDeliverable> gpDeliverables) {
		this.gpDeliverables = gpDeliverables;
	}

	public GpDeliverable addGpDeliverable(GpDeliverable gpDeliverable) {
		getGpDeliverables().add(gpDeliverable);
		gpDeliverable.setGpPhase(this);

		return gpDeliverable;
	}

	public GpDeliverable removeGpDeliverable(GpDeliverable gpDeliverable) {
		getGpDeliverables().remove(gpDeliverable);
		gpDeliverable.setGpPhase(null);

		return gpDeliverable;
	}

	public List<GpEmpReaPhase> getGpEmpReaPhases() {
		return this.gpEmpReaPhases;
	}

	public void setGpEmpReaPhases(List<GpEmpReaPhase> gpEmpReaPhases) {
		this.gpEmpReaPhases = gpEmpReaPhases;
	}

	public GpEmpReaPhase addGpEmpReaPhas(GpEmpReaPhase gpEmpReaPhas) {
		getGpEmpReaPhases().add(gpEmpReaPhas);
		gpEmpReaPhas.setGpPhase(this);

		return gpEmpReaPhas;
	}

	public GpEmpReaPhase removeGpEmpReaPhas(GpEmpReaPhase gpEmpReaPhas) {
		getGpEmpReaPhases().remove(gpEmpReaPhas);
		gpEmpReaPhas.setGpPhase(null);

		return gpEmpReaPhas;
	}

	public GpProject getGpProject() {
		return this.gpProject;
	}

	public void setGpProject(GpProject gpProject) {
		this.gpProject = gpProject;
	}

}