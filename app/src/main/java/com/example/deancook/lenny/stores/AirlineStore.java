package com.example.deancook.lenny.stores;

import android.os.AsyncTask;

import com.example.deancook.lenny.api.KayakService;
import com.example.deancook.lenny.models.Airline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit.RestAdapter;

/**
 * Created by deancook on 21/05/15.
 */
public class AirlineStore {

    private List<Airline> airlines = new ArrayList<>();
    private Airline selectedAirline;
    private Set<ListObserver> listObservers = new HashSet<>();
    private Set<ItemObserver> itemObservers = new HashSet<>();

    private static String endpointURL = "https://www.kayak.com/h/mobileapis/directory";

    public void registerListObserver(ListObserver listObserver) {
        this.listObservers.add(listObserver);

        //pass in the current list to a newly registered observer in case
        //the list is already full
        listObserver.onAirlineListHasChanged(this.airlines);
    }

    public void unregisterListObserver(ListObserver listObserver) {
        this.listObservers.remove(listObserver);
    }

    public void registerItemObserver(ItemObserver itemObserver) {
        this.itemObservers.add(itemObserver);
        itemObserver.onAirlineHasChanged(this.selectedAirline);
    }

    public void unregisterItemObserver(ItemObserver itemObserver) {
        this.itemObservers.remove(itemObserver);
    }

    public void notifyListObservers() {
        for (ListObserver listObserver : listObservers) {
            listObserver.onAirlineListHasChanged(this.airlines);
        }
    }

    public void notifyItemObservers() {
        for (ItemObserver itemObserver: itemObservers) {
            itemObserver.onAirlineHasChanged(this.selectedAirline);
        }
    }

    public void fetchArlines() {
        new RequestTask().execute(endpointURL);
    }

    public void setAirline(Airline selectedAirline) {
        this.selectedAirline = selectedAirline;
        this.notifyItemObservers();
    }

    public void tearDown() {
        this.airlines = null;
        this.selectedAirline = null;
        this.listObservers = null;
        this.itemObservers = null;
    }

    class RequestTask extends AsyncTask<String, Void, List<Airline>> {
        @Override
        protected List<Airline> doInBackground(String... uri) {
            return getAirlinesRetroFit(uri[0]);
        }

        //TODO this is black magic!!
        private List<Airline> getAirlinesRetroFit(String url) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(url)
                    .build();

            KayakService service = restAdapter.create(KayakService.class);

            List<Airline> airlines = service.listAirlines();
            return airlines;

        }

        @Override
        protected void onPostExecute(List<Airline> result) {
            super.onPostExecute(result);
            airlines = result;
            selectedAirline = airlines.get(0);
            notifyListObservers();
        }
    }

    /*
    The Observer Pattern - any object wishing to listen to this Store must implement
    this interface so that it can add itself to the list of observers. If the list changes,
    then each observer is notified and passed the new list of airlines
     */
    public interface ListObserver {
        void onAirlineListHasChanged(List<Airline> airlines);
    }

    public interface ItemObserver {
        void onAirlineHasChanged(Airline airline);
    }
}
