package com.example.deancook.lenny.stores;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.deancook.lenny.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Created by deancook on 21/05/15.
 */
public class AirlineStore {

    private List<Airline> airlines = new ArrayList<>();
    private Set<Observer> observers = new HashSet<>();

    private static String url = "https://www.kayak.com/h/mobileapis/directory/airlines";

    public void registerObserver(Observer observer) {
        this.observers.add(observer);

        //pass in the current list to a newly registered observer in case
        //the list is already full
        observer.onAirlineListHasChanged(this.airlines);
    }

    public void unregisterObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {

        for (Observer observer: observers) {
            observer.onAirlineListHasChanged(this.airlines);
        }
    }

    public void fetchArlines() {
        new RequestTask().execute(url);
    }

    public void tearDown() {

    }

    class RequestTask extends AsyncTask<String, Void, List<Airline>> {

        @Override
        protected List<Airline> doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            List<Airline> airlines = getAirlinesFromJSON(responseString);
            return airlines;
        }

        @Override
        protected void onPostExecute(List<Airline> result) {
            super.onPostExecute(result);
            airlines = result;
            notifyObservers();
        }

        private List<Airline> getAirlinesFromJSON(String json) {
            List<Airline> airlines = new ArrayList<>();
            try {
                JSONArray airlinesJSON = new JSONArray(json);
                for (int i = 0; i < airlinesJSON.length(); i++) {
                    JSONObject airlineJSON = airlinesJSON.getJSONObject(i);
                    Airline airline = new Airline(
                            airlines.size(),
                            airlineJSON.getString("code"),
                            airlineJSON.getString("logoURL"),
                            airlineJSON.getString("name"),
                            airlineJSON.getString("phone"),
                            airlineJSON.getString("site")
                    );
                    airlines.add(airline);

                    if (i == 0)
                        Log.v(MainActivity.TAG, airline.toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return airlines;
        }
    }

    /*
    The Observer Pattern - any object wishing to listen to this Store must implement
    this interface so that it can add itself to the list of observers. If the list changes,
    then each observer is notified and passed the new list of airlines
     */
    public interface Observer {
        void onAirlineListHasChanged(List<Airline> airlines);
    }
}
