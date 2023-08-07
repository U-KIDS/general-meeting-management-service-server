package io.ukids.generalmeetingmanagementsystem.common.util;

import io.ukids.generalmeetingmanagementsystem.common.exception.BaseException;
import io.ukids.generalmeetingmanagementsystem.common.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    public static String getCurrentStudentNumber() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new BaseException(ErrorCode.NOT_AUTHENTICATED);
        }

        String studentNumber = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            studentNumber = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            studentNumber = (String) authentication.getPrincipal();
        }

        return studentNumber;
    }

}
