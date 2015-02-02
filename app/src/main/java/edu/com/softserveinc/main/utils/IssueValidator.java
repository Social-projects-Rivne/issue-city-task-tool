package edu.com.softserveinc.main.utils;

import edu.com.softserveinc.main.models.IssueModel;

/**
 * Class for validation issues
 * 
 * @author nazar
 *
 */
public class IssueValidator {
	IssueModel issue;

	public IssueValidator(IssueModel issue) {
		this.issue = issue;
	}

	public boolean isValid() {

		if (issue.getDescription() != "" && issue.getMapPointer() != ""
				&& issue.getName() != "" && issue.getPriorityId() >= 0
				&& issue.getCategory_id() >= 0) {

			return true;
		}
		return false;
	}
}
