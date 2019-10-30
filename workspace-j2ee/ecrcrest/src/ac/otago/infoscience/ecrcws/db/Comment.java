package ac.otago.infoscience.ecrcws.db;
 
import java.util.Set;

public class Comment {

	String id;
	String comment; 
	
	public Comment() {
	}
	
	public Comment(String com) {
		super();
		this.comment = com;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<Relations> getRelation() {
		return relation;
	}
	public void setRelation(Set<Relations> relation) {
		this.relation = relation;
	}
	Set<Relations> relation;
	
	public String toString() {
		return this.comment;
	}
	
	
}
