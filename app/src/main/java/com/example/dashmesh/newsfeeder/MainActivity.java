package com.example.dashmesh.newsfeeder;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  ListView listview;
    List<RssItem> itcItems;
    private MainActivity local;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_newsfeed);

        initComponents();

        // Set reference to this activity
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;

        local = this;
        if (connected == true) {



            GetRSSDataTask task = new GetRSSDataTask();

            // Start download RSS task
            task.execute("http://indianexpress.com/section/sports/football/feed/");

            // Debug the thread name
            Log.d("ITCRssReader", Thread.currentThread().getName());
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "No Network Connection";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
           // Intent noconnect = new Intent(MainActivity.this,Noconnection.class);
            //MainActivity.this.startActivity(noconnect);
        }
    }

    private void initComponents() {
        listview = (ListView) findViewById(android.R.id.list);
        //listview.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                //this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
        ProgressDialog waitingDialog;
        @Override
        protected void onPreExecute() {

            waitingDialog = new ProgressDialog(MainActivity.this);
            waitingDialog.setMessage("Loading...");
            waitingDialog.show();
            super.onPreExecute();
            //ProgressDialog pDialog;
            //pDialog = new ProgressDialog(MainActivity.this);
            //pDialog.setMessage("Getting Data ...");
            //pDialog.setIndeterminate(false);
            //pDialog.setCancelable(true);
            //pDialog.show();

        }

        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            //Log.d("ITCRssReader", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {

                Log.e("ITCRssReader", e.getMessage());
            }

            return null;
        }


        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            waitingDialog.dismiss();
            ListView itcItems = (ListView) findViewById(R.id.listView);

            // Create a list adapter
            CustomArrayAdapter adapter = new CustomArrayAdapter(local,R.layout.listview,result);
            //ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,R.layout.listview,R.id.text1,result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);



            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(result,local));
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
