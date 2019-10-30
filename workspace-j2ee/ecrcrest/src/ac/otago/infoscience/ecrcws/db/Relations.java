package ac.otago.infoscience.ecrcws.db;

import java.sql.Timestamp;

 

public class Relations {
	
	
	
	String id;
	Short colonCancer;
	Short colonCancerAge;
	Short multiplePolyps;
	Short otherCancer;
	Short otherCancerAge;
	Short deceased;
	Short deceasedAge;
	Short riskLevel;
	String ipAddress;

	
	public String getIpAddress() {
		return ipAddress;
	}



	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	Timestamp timest = null;
	Comment comment;

	 
	Short relationshipType;
	String erefID;
	public String getErefID() {
		return erefID;
	}



	public void setErefID(String erefID) {
		this.erefID = erefID;
	}
	
	private String name;


 


	public void setName(String name) {
		this.name = name;
	}



	public Short getRelationshipType() {
		return relationshipType;
	}



	public void setRelationshipType(Short relationshipType) {
		this.relationshipType = relationshipType;
	}



	public Short getColonCancer() {
		return colonCancer;
	}



	public void setColonCancer(Short colonCancer) {
		this.colonCancer = colonCancer;
	}



	public Short getColonCancerAge() {
		return colonCancerAge;
	}



	public void setColonCancerAge(Short colonCancerAge) {
		this.colonCancerAge = colonCancerAge;
	}



	public Short getMultiplePolyps() {
		return multiplePolyps;
	}



	public void setMultiplePolyps(Short multiplePolyps) {
		this.multiplePolyps = multiplePolyps;
	}



	public Short getOtherCancer() {
		return otherCancer;
	}



	public void setOtherCancer(Short otherCancer) {
		this.otherCancer = otherCancer;
	}



	public Short getOtherCancerAge() {
		return otherCancerAge;
	}



	public void setOtherCancerAge(Short otherCancerAge) {
		this.otherCancerAge = otherCancerAge;
	}



	public Short getDeceased() {
		return deceased;
	}



	public void setDeceased(Short deceased) {
		this.deceased = deceased;
	}



	public Short getDeceasedAge() {
		return deceasedAge;
	}



	public void setDeceasedAge(Short deceasedAge) {
		this.deceasedAge = deceasedAge;
	}

public Short getRiskLevel() {
	return riskLevel;
}



public Comment getComment() {
	return comment;
}



public void setComment(Comment comment) {
	this.comment = comment;
}



public void setRiskLevel(Short riskLevel) {
	this.riskLevel = riskLevel;
}
	
	public Relations() {
	
	}
	
	
	
	public Relations( Short relationshipType,
			String erefID, String name,
			Short colonCancer, Short colonCancerAge, Short multiplePolyps,
			Short otherCancer, Short otherCancerAge, Short deceased,
			Short deceasedAge, Short riskLevel, Timestamp timest, String ipAddress, Comment comment) {
		super();
	 
		this.relationshipType = relationshipType;
		this.erefID = erefID;
		this.name = name;
		this.colonCancer = colonCancer;
		this.colonCancerAge = colonCancerAge;
		this.multiplePolyps = multiplePolyps;
		this.otherCancer = otherCancer;
		this.otherCancerAge = otherCancerAge;
		this.deceased = deceased;
		this.deceasedAge = deceasedAge;
		this.timest = timest;
		this.riskLevel = riskLevel;
		this.ipAddress = ipAddress;
		this.comment = comment;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	 
	public Timestamp getTimest() {
		return timest;
	}
	public void setTimest(Timestamp timest) {
		this.timest = timest;
	}

	public String toXML() {
		 
		StringBuffer sb = new StringBuffer();
		sb.append("<RELATION>");
		
		sb.append("<TYPE>");
		sb.append(this.getRelationshipType());
		sb.append("</TYPE>");
		
		sb.append("<NAME>");
		sb.append(this.getName());
		sb.append("</NAME>");
		
		
		sb.append("<CC>");
		sb.append(this.getColonCancer());
		sb.append("</CC>");
		sb.append("<AGE_CC>");
		sb.append(this.getColonCancerAge());
		sb.append("</AGE_CC>");
		sb.append("<MULTPOLS>");
		sb.append(this.getMultiplePolyps());
		sb.append("</MULTPOLS>");
		sb.append("<HNPCC>");
		sb.append(this.getOtherCancer());
		sb.append("</HNPCC>");
		sb.append("<AGE_HNPCC>");
		sb.append(this.getOtherCancerAge());
		sb.append("</AGE_HNPCC>");
		sb.append("<DECEASED>");
		sb.append(this.getDeceased());
		sb.append("</DECEASED>");
		sb.append("<AGE_DECEASED>");
		sb.append(this.getDeceasedAge());
		sb.append("</AGE_DECEASED>");
		
		
		sb.append("<COMMENT>");
		if (this.getComment() != null) {
		
			sb.append(this.getComment().toString());
		}
		sb.append("</COMMENT>");
		sb.append("</RELATION>");
		return sb.toString();

	}



	private String getName() {
		return this.name;
	}
	
}
