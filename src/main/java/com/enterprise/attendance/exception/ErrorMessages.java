package com.enterprise.attendance.exception;

public enum ErrorMessages {
	 MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	    COULD_NOT_CREATE_USER_PROFILE("Could not create user profile"),
	    COULD_NOT_UPDATE_USER_PROFILE("Could not update user profile"),
	    COULD_NOT_DELETE_USER_PROFILE("Could not delete user profile"),
	    NO_RECORD_FOUND("No record found."),
	    RECORD_ALREADY_EXISTS("Record already exists"),
	    INTERNAL_SERVER_ERROR("Something went wrong. Please repeat this operation later.");
	    
	   private String errorMessage;
	   
	   ErrorMessages(String errorMessage)
	   {
	      this.errorMessage = errorMessage;    
	   }
	    /**
	     * @return the errorMessage
	     */
	    public String getErrorMessage() {
	        return errorMessage;
	    }
	    /**
	     * @param errorMessage the errorMessage to set
	     */
	    public void setErrorMessage(String errorMessage) {
	        this.errorMessage = errorMessage;
	    }
}
