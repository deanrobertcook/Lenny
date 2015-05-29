package com.example.deancook.lenny.api;

import com.example.deancook.lenny.models.Airline;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by deancook on 28/05/15.
 */
public interface KayakService {
    @GET("/airlines")
    List<Airline> listAirlines();
}
