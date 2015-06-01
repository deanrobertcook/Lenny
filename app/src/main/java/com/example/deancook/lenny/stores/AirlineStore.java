package com.example.deancook.lenny.stores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.deancook.lenny.api.KayakService;
import com.example.deancook.lenny.database.Contract;
import com.example.deancook.lenny.database.DatabaseHelper;
import com.example.deancook.lenny.models.Airline;

import java.sql.SQLDataException;
import java.sql.SQLException;
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
    private DatabaseHelper dbHelper;

    private static String endpointURL = "https://www.kayak.com/h/mobileapis/directory";

    public AirlineStore(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

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
        this.dbHelper = null;
    }

    class RequestTask extends AsyncTask<String, Void, List<Airline>> {
        @Override
        protected List<Airline> doInBackground(String... uri) {
            List<Airline> airlines;

//            airlines = getAirlinesFromRetroFit(uri[0]);
            if (getDatabaseRowCount() > 0) {
                airlines = getAirlinesFromDatabase();
            } else {
                airlines = getAirlinesFromRetroFit(uri[0]);
                putAirlinesIntoDatabase(airlines);
            }
            return airlines;
        }

        private int getDatabaseRowCount() {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            int count = (int)DatabaseUtils.queryNumEntries(db, Contract.Airline.TABLE_NAME);
            return count;
        }

        private List<Airline> getAirlinesFromDatabase() {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ArrayList<Airline> airlines = new ArrayList<>();
            Cursor cursor = db.query(
                    Contract.Airline.TABLE_NAME,
                    new String[] {
                            Contract.Airline.COLUMN_NAME_CODE,
                            Contract.Airline.COLUMN_NAME_LOGOURL,
                            Contract.Airline.COLUMN_NAME_NAME,
                            Contract.Airline.COLUMN_NAME_PHONE,
                            Contract.Airline.COLUMN_NAME_SITE
                    },
                    null, null, null, null, null
            );
            int rows = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < rows; i++) {
                Airline airline = new Airline(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                airlines.add(airline);
                cursor.moveToNext();
            }
            return airlines;
        }

        //TODO this is black magic!!
        private List<Airline> getAirlinesFromRetroFit(String url) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(url)
                    .build();

            KayakService service = restAdapter.create(KayakService.class);

            List<Airline> airlines = service.listAirlines();
            return airlines;

        }
        
        private void putAirlinesIntoDatabase(List<Airline> airlines) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            for (Airline airline: airlines) {
                createRow(airline, db);
            }
        }

        private void createRow(Airline airline, SQLiteDatabase db) {
            ContentValues values = new ContentValues();
            values.put(Contract.Airline.COLUMN_NAME_CODE, airline.code);
            values.put(Contract.Airline.COLUMN_NAME_NAME, airline.name);
            values.put(Contract.Airline.COLUMN_NAME_LOGOURL, airline.logoURL);
            values.put(Contract.Airline.COLUMN_NAME_SITE, airline.site);
            values.put(Contract.Airline.COLUMN_NAME_PHONE, airline.phone);

            db.insert(Contract.Airline.TABLE_NAME, null, values);
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
