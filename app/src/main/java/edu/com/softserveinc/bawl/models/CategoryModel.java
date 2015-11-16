package edu.com.softserveinc.bawl.models;

import com.google.common.base.Objects;
import edu.com.softserveinc.bawl.models.enums.CategoryState;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class for problem's category 
 * 
 * @author Nazar
 *
 */
@Entity
@Table(name = "CATEGORY")
public class CategoryModel {

    public static final Logger LOG=Logger.getLogger(CategoryModel.class);

    @Id
	@GeneratedValue
	@Column(unique=true, name = "ID")
	private int id;

    @NotNull
	@Column(unique=true, name = "NAME")
	private String name;

	@NotNull
	@Column(unique=false, name="STATE")
    @Enumerated(EnumType.ORDINAL)
	private CategoryState state;

    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    private List<IssueModel> issues;
	
	public CategoryModel() {
        setState(CategoryState.NEW);
    }
	
	public CategoryModel(String name) {
		this.name = name;
        setState(CategoryState.NEW);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoryState getState() { return state; }

	public void setState(CategoryState state) { this.state = state; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public List<IssueModel> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueModel> issues) {
        this.issues = issues;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryModel that = (CategoryModel) o;
		return Objects.equal(id, that.id) &&
				Objects.equal(name, that.name) &&
				Objects.equal(state, that.state) &&
				Objects.equal(issues, that.issues);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, state, issues);
	}
}
