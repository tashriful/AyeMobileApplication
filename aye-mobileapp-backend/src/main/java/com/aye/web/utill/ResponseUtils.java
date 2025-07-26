package com.aye.web.utill;

import com.aye.web.dto.ApiResponse;

public class ResponseUtils {

    public static <T> ApiResponse<T> success(int status, String message, T responseData) {
        return new ApiResponse<>(status, message, responseData);
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }

}
