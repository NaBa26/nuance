package com.ecommerce.nuance.dto;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManagement {

    private static final Logger logger = LoggerFactory.getLogger(SessionManagement.class);

    public static final String USERNAME = "username";

    public static void setAttribute(HttpSession session, String key, Object value) {
        if (session != null) {
            session.setAttribute(key, value);
            logger.info("Set session attribute: {} = {}", key, value);
        }
    }

    public static <T> T getAttribute(HttpSession session, String key, Class<T> type) {
        if (session != null) {
            Object value = session.getAttribute(key);
            if (type.isInstance(value)) {
                return type.cast(value);
            }
        }
        return null;
    }

    public static void removeAttribute(HttpSession session, String key) {
        if (session != null) {
            session.removeAttribute(key);
            logger.info("Removed session attribute: {}", key);
        }
    }

    public static void setUsername(HttpSession session, String username) {
        setAttribute(session, USERNAME, username);
    }

    public static String getUsername(HttpSession session) {
        return getAttribute(session, USERNAME, String.class);
    }

    public static boolean isSessionValid(HttpSession session) {
        return session != null && getAttribute(session, USERNAME, String.class) != null;
    }

    public static void invalidateSession(HttpSession session) {
        if (session != null) {
            logger.info("Invalidating session: {}", session.getId());
            session.invalidate();
        }
    }

    public static void setSessionTimeout(HttpSession session, int timeoutInSeconds) {
        if (session != null) {
            session.setMaxInactiveInterval(timeoutInSeconds);
            logger.info("Session timeout set to {} seconds for session: {}", timeoutInSeconds, session.getId());
        }
    }
}
