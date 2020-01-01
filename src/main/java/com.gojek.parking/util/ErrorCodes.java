package com.gojek.parking.util;

public class ErrorCodes {

    // 400 Related Error Codes go here - this would fill most of this class
    public static final String INVALID_DATA = "400.99";
    public static final String INVALID_INPUT = "400.100";
    public static final String INVALID_FILE = "400.101";

    public static final String CONSTRAINTS_VIOLATION = "400.105";
    public static final String INVALID_USER = "400.110";
    // 401 & 403 Related Error Codes go here
    public static final String INACTIVE_USER = "403.201";
    public static final String NOT_AUTHORIZED = "403.202";
    public static final String OBJ_NOT_FOUND = "404.150";

    // 500 Related Error Codes go here (typically UNKNOWN error would be used)
    public static final String UNKNOWN_ERROR = "500.1000";
    // Operation too dangerous, hence not supported
    public static final String DNGR_NOT_SUPPORTED = "501.1200";

    // Section for standard error messages - This will be replaced by error bundles
    // Message template for ErrorCodes.OBJ_NOT_FOUND
    public static final String MSG_OBJ_NOT_FOUND = "Object of type [%s] not found for Key [%s]";
    public static final String MSG_UNKNOWN_ERROR = "An unknown error occurred";
    public static final String MSG_DNGR_NOT_SUPP = "Operation too dangerous. Not supported.";
}
