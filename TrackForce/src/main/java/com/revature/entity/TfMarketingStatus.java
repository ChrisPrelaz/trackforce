package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@XmlRootElement
@Entity
@Table(name = "TF_MARKETING_STATUS", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfMarketingStatus implements java.io.Serializable {

	private static final long serialVersionUID = -1638800519652509525L;
	
	@XmlElement
	@Id
	@Column(name = "TF_MARKETING_STATUS_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfMarketingStatusId;
	
	@XmlElement
	@Column(name = "TF_MARKETING_STATUS_NAME", length = 30)
	private String tfMarketingStatusName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfMarketingStatus")
	private Set<TfAssociate> tfAssociates = new HashSet<TfAssociate>(0);

	public TfMarketingStatus() {
	}

	public TfMarketingStatus(Integer tfMarketingStatusId) {
		this.tfMarketingStatusId = tfMarketingStatusId;
	}

	public TfMarketingStatus(Integer tfMarketingStatusId, String tfMarketingStatusName,
			Set<TfAssociate> tfAssociates) {
		this.tfMarketingStatusId = tfMarketingStatusId;
		this.tfMarketingStatusName = tfMarketingStatusName;
		this.tfAssociates = tfAssociates;
	}


	public Integer getTfMarketingStatusId() {
		return this.tfMarketingStatusId;
	}

	public void setTfMarketingStatusId(Integer tfMarketingStatusId) {
		this.tfMarketingStatusId = tfMarketingStatusId;
	}

	
	public String getTfMarketingStatusName() {
		return this.tfMarketingStatusName;
	}

	public void setTfMarketingStatusName(String tfMarketingStatusName) {
		this.tfMarketingStatusName = tfMarketingStatusName;
	}

	
	public Set<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(Set<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

    @Override
	public String toString() {
		return "TfMarketingStatus [tfMarketingStatusId=" + tfMarketingStatusId + ", tfMarketingStatusName="
				+ tfMarketingStatusName + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfAssociates == null) ? 0 : tfAssociates.hashCode());
		result = prime * result + ((tfMarketingStatusId == null) ? 0 : tfMarketingStatusId.hashCode());
		result = prime * result + ((tfMarketingStatusName == null) ? 0 : tfMarketingStatusName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfMarketingStatus other = (TfMarketingStatus) obj;
		if (tfAssociates == null) {
			if (other.tfAssociates != null)
				return false;
		} else if (!tfAssociates.equals(other.tfAssociates))
			return false;
		if (tfMarketingStatusId == null) {
			if (other.tfMarketingStatusId != null)
				return false;
		} else if (!tfMarketingStatusId.equals(other.tfMarketingStatusId))
			return false;
		if (tfMarketingStatusName == null) {
			if (other.tfMarketingStatusName != null)
				return false;
		} else if (!tfMarketingStatusName.equals(other.tfMarketingStatusName))
			return false;
		return true;
	}
	
	

}
