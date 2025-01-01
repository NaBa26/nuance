package com.ecommerce.nuance.dto;

import com.ecommerce.nuance.model.User;

import jakarta.servlet.http.HttpSession;

public class SessionManagement {

    public static final String USER = "user";  // Use the key "user" for storing the entire user object

    public static void setAttribute(HttpSession session, String key, Object value) {
        if (session != null) {
            session.setAttribute(key, value);
            System.out.println("Set session attribute: " + key + " - " + value);
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
            System.out.println("Removed session attribute: " + key);
        }
    }

    public static void setUser(HttpSession session, User user) {
    	System.out.println("Setting User: " + USER + " user object: " +user);
        setAttribute(session, USER, user);  // Store the entire user object in the session
    }

    public static User getUser(HttpSession session) {
    	System.out.println("The USER for the current session is: " + getAttribute(session, USER, User.class));
        return getAttribute(session, USER, User.class);  // Retrieve the entire user object
    }

    public static boolean isSessionValid(HttpSession session) {
        if(session != null && getUser(session) != null)
        {
        	return true;
        }
        return false;
    }


    public static void invalidateSession(HttpSession session) {
        if (session != null) {
            System.out.println("Invalidating session: " + session.getId());
            session.invalidate();
        }
    }

    public static void setSessionTimeout(HttpSession session, int timeoutInSeconds) {
        if (session != null && isSessionValid(session)) {
            session.setMaxInactiveInterval(timeoutInSeconds);
            System.out.println("Session timeout set to " + timeoutInSeconds + " seconds for session: " + session.getId());
        } else {
        	System.out.println("Cannot set session timeout. Session is not valid.");
        }
    }

}
