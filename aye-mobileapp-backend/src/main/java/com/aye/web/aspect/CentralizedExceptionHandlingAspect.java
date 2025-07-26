//package com.aye.web.aspect;
//
//import com.aye.web.dto.ErrorMessage;
//import com.aye.web.exception.CustomExceptions;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Aspect
//@Component
//public class CentralizedExceptionHandlingAspect {
//
//    @Pointcut("execution(* com.aye.web.controller..*(..))")
//    public void controllerMethods(){}
//
//    @AfterThrowing(pointcut = "controllerMethods()", throwing = "ex")
//    public ResponseEntity<ErrorMessage> handleControllerExceptions(Exception ex) throws IOException {
//        ErrorMessage errorMessage = new ErrorMessage();
//
//        if (ex instanceof CustomExceptions.ResourceNotFoundException) {
//            errorMessage.setMessage(ex.getMessage());
//            errorMessage.setErrorCode("NOT_FOUND");
//            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
//        }
//        // Handling BadRequestException
//        else if (ex instanceof CustomExceptions.BadRequestException) {
//            errorMessage.setMessage(ex.getMessage());
//            errorMessage.setErrorCode("BAD_REQUEST");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }
//        // Default exception handler
//        else {
//            errorMessage.setMessage("An unexpected error occurred");
//            errorMessage.setErrorCode("INTERNAL_SERVER_ERROR");
//            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//}
