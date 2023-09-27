package com.racanaa.services.account.constant;

/**
 * Global API constants
 *
 * @author Manohar
 * @since 20/Sep/2023
 */
public class ApiConstant {
    public static final String API_STATUS_RUNNING = "Account service up and running!";
    public static final String API_CONTEXT = "/api";
    public static final String DB_INSPECT_BEANS_CONFIG_KEY = "beans.inspect";
    public static final String DB_SERVICE_NAME_CONFIG_KEY = "service.name";
    public static final String DB_API_KEY_HEADER_NAME = "api.key.header.name";
    public static final String DB_API_KEY_VALUE = "api.key.value";
    public static final String DB_JWT_TOKEN_HEADER_NAME = "jwt.token.header.name";
    public static final String DB_JWT_TOKEN_SECRET = "jwt.token.secret";
    public static final String DB_JWT_TOKEN_EXPIRY_SECONDS = "jwt.token.expiry.seconds";
    public static final int DEFAULT_TOKEN_EXPIRY_SECONDS_DAY = 86400;
    public static final String DEFAULT_SERVICE_NAME = "account-service";

    public static final String PARAM_OFFSET_NAME = "offset";
    public static final String PARAM_OFFSET_DESC = "Offset - page number";
    public static final String PARAM_OFFSET_EXAMPLE = "1";
    public static final String PARAM_OFFSET_DEFAULT = "1";

    public static final String PARAM_LIMIT_NAME = "limit";
    public static final String PARAM_LIMIT_DESC = "Page size - number of records per pages";
    public static final String PARAM_LIMIT_EXAMPLE = "10";
    public static final String PARAM_LIMIT_DEFAULT = "10";

    public static final String PARAM_SORT_NAME = "sort";
    public static final String PARAM_SORT_DESC = "SortDto - list of sorting criteria";
    public static final String PARAM_SORT_EXAMPLE = """
            {"criteria":[{"by":"dateUpdated","order":"desc"}]}
            """;

    public static final String PARAM_SEARCH_NAME = "search";
    public static final String PARAM_SEARCH_DESC = "SearchDto - list of search criteria";
    public static final String PARAM_SEARCH_EXAMPLE = """
            {"dataOption":"any","criteria":[{"filterKey":"name","operation":"cn","value":"naveen"},{"filterKey":"email",
            "operation":"cn","value":"racanaa"}]}
            """;

    public static final String PARAM_ID_NAME = "id";
    public static final String PARAM_ID_DESC = "id";
    public static final String PARAM_ID_EXAMPLE = "402881888ac3ce6b018ac3ce6f840002";

    public static final String PARAM_ACCOUNT_ID_NAME = "accountId";
    public static final String PARAM_ACCOUNT_ID_DESC = "Account id";
    public static final String METHOD_AC_LIST_PATH = API_CONTEXT + "/accounts";
    public static final String METHOD_AC_LIST_SUMMARY = "List accounts";
    public static final String METHOD_AC_LIST_DESC = """
            Returns a list of account. Sorts data if Sort criteria is provided, otherwise sorts by a default criteria 
            that is dateUpdated desc.
            """;

    public static final String METHOD_AC_SEARCH_PATH = API_CONTEXT + "/accounts/search";
    public static final String METHOD_AC_SEARCH_SUMMARY = "Search accounts";
    public static final String METHOD_AC_SEARCH_DESC = """
            Returns a list of accounts based on the search criteria. Sorts data if Sort criteria is provided,otherwise 
            sorts by a default criteria that is dateUpdated desc.
            """;

    public static final String METHOD_AC_CREATE_PATH = API_CONTEXT + "/accounts";
    public static final String METHOD_AC_CREATE_SUMMARY = "Create account";
    public static final String METHOD_AC_CREATE_DESC = "Creates an account";

    public static final String METHOD_AC_PATH_ID = API_CONTEXT + "/accounts/{id}";

    public static final String METHOD_AC_GET_SUMMARY = "Get account";
    public static final String METHOD_AC_GET_DESC = "Get an account detail by account id";

    public static final String METHOD_AC_UPDATE_SUMMARY = "Update account";
    public static final String METHOD_AC_UPDATE_DESC = "Update account details for the account with account id";

    public static final String METHOD_AC_DELETE_SUMMARY = "Delete account";
    public static final String METHOD_AC_DELETE_DESC = "Delete account with account id";


    public static final String PARAM_AP_ID_DESC = "Account preference id";
    // Account preference
    public static final String METHOD_APC_LIST_PATH = API_CONTEXT + "/accounts/{accountId}/preferences";
    public static final String METHOD_APC_LIST_SUMMARY = "List account preferences for the account";
    public static final String METHOD_APC_LIST_DESC = """
            Returns a list of account preferences for the account. Sorts data if Sort criteria is provided, 
            otherwise sorts by a default criteria that is dateUpdated desc.
            """;


    public static final String METHOD_APC_CREATE_PATH = API_CONTEXT + "/accounts/{accountId}/preferences";
    public static final String METHOD_APC_CREATE_SUMMARY = "Create an account preference";
    public static final String METHOD_APC_CREATE_DESC = "Creates an account preference";

    //common path for by id's
    public static final String METHOD_APC_PATH_ID = API_CONTEXT + "/accounts/{accountId}/preferences/{id}";

    public static final String METHOD_APC_GET_SUMMARY = "Get account preference detail";
    public static final String METHOD_APC_GET_DESC = "Get an account preference detail by account id";

    public static final String METHOD_APC_UPDATE_SUMMARY = "Update account preference detail";
    public static final String METHOD_APC_UPDATE_DESC = "Update account preference details for the account with account id";

    public static final String METHOD_APC_DELETE_SUMMARY = "Delete an account preference";
    public static final String METHOD_APC_DELETE_DESC = "Delete account preference with account id";



    public static final String PARAM_UP_ID_DESC = "User preference id";
    // Account preference
    public static final String METHOD_UPC_LIST_PATH = API_CONTEXT + "/users/{userId}/preferences";
    public static final String METHOD_UPC_LIST_SUMMARY = "List user preferences for the user";
    public static final String METHOD_UPC_LIST_DESC = """
            Returns a list of user preferences for the user. Sorts data if Sort criteria is provided, 
            otherwise sorts by a default criteria that is dateUpdated desc.
            """;
    public static final String METHOD_UPC_CREATE_PATH = API_CONTEXT + "/users/{userId}/preferences";
    public static final String METHOD_UPC_CREATE_SUMMARY = "Create user preference";
    public static final String METHOD_UPC_CREATE_DESC = "Creates an user preference";

    //common path for by id's
    public static final String METHOD_UPC_PATH_ID = API_CONTEXT + "/users/{userId}/preferences/{id}";

    public static final String METHOD_UPC_GET_SUMMARY = "Get user preference detail";
    public static final String METHOD_UPC_GET_DESC = "Get an user preference detail by user id";

    public static final String METHOD_UPC_UPDATE_SUMMARY = "Update user preference detail";
    public static final String METHOD_UPC_UPDATE_DESC = "Update user preference details for the user with user id";

    public static final String METHOD_UPC_DELETE_SUMMARY = "Delete an user preference";
    public static final String METHOD_UPC_DELETE_DESC = "Delete user preference with user id";


    public static final String PARAM_USER_ID_NAME = "userId";
    public static final String PARAM_USER_ID_DESC = "User id";
    public static final String METHOD_UC_LIST_PATH = API_CONTEXT + "/users";
    public static final String METHOD_UC_LIST_SUMMARY = "List users";
    public static final String METHOD_UC_LIST_DESC = """
            Returns a list of users. Sorts data if Sort criteria is provided, otherwise sorts by a default criteria 
            that is dateUpdated desc.
            """;

    public static final String METHOD_UC_SEARCH_PATH = API_CONTEXT + "/users/search";
    public static final String METHOD_UC_SEARCH_SUMMARY = "Search users";
    public static final String METHOD_UC_SEARCH_DESC = """
            Returns a list of users based on the search criteria. Sorts data if Sort criteria is provided,otherwise 
            sorts by a default criteria that is dateUpdated desc.
            """;

    public static final String METHOD_UC_CREATE_PATH = API_CONTEXT + "/users";
    public static final String METHOD_UC_CREATE_SUMMARY = "Create user";
    public static final String METHOD_UC_CREATE_DESC = "Creates a user";

//    public static final String METHOD_AC_PATH_ID = API_CONTEXT + "/accounts/{id}";
//
//    public static final String METHOD_AC_GET_SUMMARY = "Get account";
//    public static final String METHOD_AC_GET_DESC = "Get an account detail by account id";
//
//    public static final String METHOD_AC_UPDATE_SUMMARY = "Update account";
//    public static final String METHOD_AC_UPDATE_DESC = "Update account details for the account with account id";
//
//    public static final String METHOD_AC_DELETE_SUMMARY = "Delete account";
//    public static final String METHOD_AC_DELETE_DESC = "Delete account with account id";


    public static final String DEFAULT_SORT_FIELD_NAME = "sort";
    public static final String DEFAULT_SORT_BY = "dateUpdated";
    public static final String DEFAULT_SORT_ORDER = "desc";

    public static final String SORT_ORDER_ASC = "asc";
    public static final String SORT_ORDER_DESC = "desc";
    public static final String PASSWORD_SECRET = "NB~Kd1Ar^D9Xg7TX%Xh$7uWA*TK.Y(HV";
    public static final String ACCOUNT_TYPE_ROOT = "ROOT";
    public static final String ACCOUNT_TYPE_AGENCY = "AGENCY";
    public static final String ACCOUNT_TYPE_CLIENT = "CLIENT";
    public static final String VALIDATION_MSG_INVALID_CONTACT = "Invalid contact number";
    public static final String VALIDATION_FAILED_ERROR_MESSAGE = "Input validation failed";
    public static final String VALIDATION_MESSAGE_EMAIL_MANDATORY = "Email is mandatory";
    public static final String VALIDATION_MESSAGE_EMAIL_VALID = "Must be a valid email id";


    // password validation
    public static final String SUCCESS = "Success";

    /** The Constant FAILURE. */
    public static final String FAILURE = "Failure";

    /** The Constant COMMA. */
    public static final String COMMA = ",";

    /** The Constant PASSWORD_INPUT_PARAM. */
    public static final String PASSWORD_INPUT_PARAM = "password";
    public static final String VALIDATION_MSG_INVALID_PASSWORD = "Invalid password";
    public static final String VALIDATION_MESSAGE_PASSWORD_MANDATORY = "Password is mandatory";
    public static final String VALIDATION_MESSAGE_PASSWORD_VALID = "Must be a valid email id";

    public static final String VALIDATION_MESSAGE_NAME_LENGTH = "Name should have at least 2 characters";
    public static final String VALIDATION_MESSAGE_NAME_MANDATORY = "Name is mandatory";
    public static final String VALIDATION_MESSAGE_VALUE_MANDATORY = "Value is mandatory";
    public static final String VALIDATION_MESSAGE_ACCOUNT_NOT_FOUND = "Account not found with id: ";
    public static final String VALIDATION_MESSAGE_ACCOUNT_EXISTS_NAME = "An account already exists with name: ";
    public static final String VALIDATION_MESSAGE_ACCOUNT_EXISTS_DOMAIN = "An account already exists with domain: ";
    public static final String VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_NOT_FOUND = "Account preference not found with id: ";
    public static final String VALIDATION_MESSAGE_ACCOUNT_PREFERENCE_EXISTS_NAME = "An account preference already exists for account with name: ";

    public static final String VALIDATION_MESSAGE_USER_PREFERENCE_NOT_FOUND = "User preference not found with id: ";
    public static final String VALIDATION_MESSAGE_USER_PREFERENCE_EXISTS_NAME = "User preference already exists for user with name: ";
    public static final String UTILITY_CLASS_NO_CONSTRUCTOR = "Utility class doesn't have a public constructor";
    public static final String STACK_TRACE_ALWAYS = "ALWAYS";
    public static final String STACK_TRACE_PROPERTY = "server.error.include-stacktrace";
    public static final String AUTH_API_KEY_REQUIRED = "API Key required";
    public static final String AUTH_API_KEY_INVALID = "Invalid API Key";
    public static final String AUTH_TOKEN_REQUIRED = "Authorization header not found";
    public static final String AUTH_TOKEN_INVALID = "Authorization header is invalid";
    public static final String AUTH_TOKEN_EXPIRED = "JWT Token expired";
    public static final String AUTH_INVALID_TENANT = "Invalid tenant";
    public static final String AUTH_TOKEN_BEARER_TEXT = "Bearer ";
    public static final String EMPTY_STRING = "";
    public static final String REQUEST_ATTRIBUTE_ACCOUNT_KEY = "account";
    public static final String REQUEST_ATTRIBUTE_USER_KEY = "user";

    /**
     * Private constructor to avoid new object creation
     */
    private ApiConstant() {
        throw new IllegalStateException(UTILITY_CLASS_NO_CONSTRUCTOR);
    }
}
