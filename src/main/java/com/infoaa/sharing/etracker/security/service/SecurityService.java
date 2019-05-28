package com.infoaa.sharing.etracker.security.service;

public interface SecurityService {

	String findLoggedInUsername();
    void autologin(String username, String password);
}

