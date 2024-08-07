package com.practice.myfirstproject.HomeWifi;

import org.springframework.stereotype.Component;

@Component
public class InternetCompany {

    public String airtel() {
        return ("Airtel Fiber");
    }

    public String jio() {
        return ("Jio Fiber");
    }

    public String starlink() {
        return ("Starlink Broadband");
    }
}
