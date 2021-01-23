package pl.gregorymartin.newsportal.exception;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    MISSING_REQUIRED_FIELDS("Missing required field."),
    INCORRECT_FIELDS("Provided fields are incorrect"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record/s not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),

    CATEGORY_NAME_CANNOT_BE_BLANK("Category Name cannot be blank")


    ;

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
