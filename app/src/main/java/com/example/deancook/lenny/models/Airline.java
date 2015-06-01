package com.example.deancook.lenny.models;

/**
 * Created by deancook on 20/05/15.
 */
public class Airline {
    public final String code;
    public final String logoURL;
    public final String name;
    public final String phone;
    public final String site;

    public Airline (String code,
                    String logoURL,
                    String name,
                    String phone,
                    String site) {
        this.code = code;
        this.logoURL = logoURL;
        this.name = name;
        this.phone = phone;
        this.site = site;
    }

    @Override
    public String toString() {
        return this.code + "\n" +
                this.logoURL + "\n" +
                this.name + "\n" +
                this.phone + "\n" +
                this.site + "\n";
    }
}
