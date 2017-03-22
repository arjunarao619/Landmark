package landmark.arjunarao.arjunrao.com.landmark;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecFragment extends Fragment implements OnItemClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    double latitude,longitude;

    ProgressBar progressBar;
    static final LauncherIcon[] ICONS = {
            new LauncherIcon(R.drawable.airport, "Bar"),
            new LauncherIcon(R.drawable.car_wash, "Theatre"),
            new LauncherIcon(R.drawable.library, "Library"),
            new LauncherIcon(R.drawable.bowling, "Bowling"),
            new LauncherIcon(R.drawable.book, "Books"),
            new LauncherIcon(R.drawable.gym, "Gym"),
            new LauncherIcon(R.drawable.club, "Night Club"),
            new LauncherIcon(R.drawable.zoo, "Zoo"),
            new LauncherIcon(R.drawable.casino, "Casino"),
            new LauncherIcon(R.drawable.cafe, "Cafe"),
            new LauncherIcon(R.drawable.temple, "Hindu Temple"),
            new LauncherIcon(R.drawable.church, "Church"),
            new LauncherIcon(R.drawable.museum, "Museum"),
            new LauncherIcon(R.drawable.paint, "Gallery"),
            new LauncherIcon(R.drawable.fish, "Aquarium"),
            new LauncherIcon(R.drawable.amuse1, "Amusement Park"),




    };

    public RecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank,container,false);
        //some code
        GridView gridview = (GridView) view.findViewById(R.id.dashboard_grid);
        gridview.setAdapter(new ImageAdapter(view.getContext()));
        gridview.setOnItemClickListener(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        switch (position){
            case 0:
                StringBuilder sbValue = new StringBuilder(sbMethod("bar"));
                PlacesTask placesTask = new PlacesTask();
                placesTask.execute(sbValue.toString());
                break;
            case 1:
                StringBuilder sbValue1 = new StringBuilder(sbMethod("theatre"));
                PlacesTask placesTask1 = new PlacesTask();
                placesTask1.execute(sbValue1.toString());
                break;
            case 2:
                StringBuilder sbValue2 = new StringBuilder(sbMethod("library"));
                PlacesTask placesTask2 = new PlacesTask();
                placesTask2.execute(sbValue2.toString());
                break;
            case 3:
                StringBuilder sbValue3 = new StringBuilder(sbMethod("bowling_alley"));
                PlacesTask placesTask3 = new PlacesTask();
                placesTask3.execute(sbValue3.toString());
                break;
            case 4:
                StringBuilder sbValue4 = new StringBuilder(sbMethod("book_store"));
                PlacesTask placesTask4 = new PlacesTask();
                placesTask4.execute(sbValue4.toString());
                break;
            case 5:
                StringBuilder sbValue5 = new StringBuilder(sbMethod("gym"));
                PlacesTask placesTask5 = new PlacesTask();
                placesTask5.execute(sbValue5.toString());
                break;
            case 6:
                StringBuilder sbValue6 = new StringBuilder(sbMethod("night_club"));
                PlacesTask placesTask6 = new PlacesTask();
                placesTask6.execute(sbValue6.toString());
                break;
            case 7:
                StringBuilder sbValue7 = new StringBuilder(sbMethod("zoo"));
                PlacesTask placesTask7 = new PlacesTask();
                placesTask7.execute(sbValue7.toString());
                break;
            case 8:
                StringBuilder sbValue8 = new StringBuilder(sbMethod("casino"));
                PlacesTask placesTask8 = new PlacesTask();
                placesTask8.execute(sbValue8.toString());
                break;
            case 9:
                StringBuilder sbValue9 = new StringBuilder(sbMethod("cafe"));
                PlacesTask placesTask9 = new PlacesTask();
                placesTask9.execute(sbValue9.toString());
                break;

            case 10:
                StringBuilder sbValue0 = new StringBuilder(sbMethod("hindu_temple"));
                PlacesTask placesTask0 = new PlacesTask();
                placesTask0.execute(sbValue0.toString());
                break;

            case 11:
                StringBuilder sbValue11 = new StringBuilder(sbMethod("church"));
                PlacesTask placesTask11 = new PlacesTask();
                placesTask11.execute(sbValue11.toString());
                break;

            case 12:
                StringBuilder sbValue112 = new StringBuilder(sbMethod("museum"));
                PlacesTask placesTask112 = new PlacesTask();
                placesTask112.execute(sbValue112.toString());
                break;

            case 13:
                StringBuilder sbValue71 = new StringBuilder(sbMethod("gallery"));
                PlacesTask placesTask71 = new PlacesTask();
                placesTask71.execute(sbValue71.toString());
                break;

            case 14:
                StringBuilder sbValue72 = new StringBuilder(sbMethod("aquarium"));
                PlacesTask placesTask72 = new PlacesTask();
                placesTask72.execute(sbValue72.toString());
                break;
            case 15:
                StringBuilder sbValue721 = new StringBuilder(sbMethod("amusement_park"));
                PlacesTask placesTask721 = new PlacesTask();
                placesTask721.execute(sbValue721.toString());
                break;






        }
    }

    @Override
    public void onConnected(Bundle bundle){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        checkPermission(getActivity());
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);

    }
    public static boolean checkPermission(final Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location){




        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }


    static class LauncherIcon {
        final String text;
        final int imgId;


        public LauncherIcon(int imgId, String text) {
            super();
            this.imgId = imgId;
            this.text = text;

        }

    }

    static class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return ICONS.length;
        }

        @Override
        public LauncherIcon getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        static class ViewHolder {
            public ImageView icon;
            public TextView text;
        }

        // Create a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ViewHolder holder;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

                v = vi.inflate(R.layout.dashboard_icon, null);
                holder = new ViewHolder();
                holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
                holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }

            holder.icon.setImageResource(ICONS[position].imgId);
            holder.text.setText(ICONS[position].text);

            return v;
        }
    }


    public StringBuilder sbMethod(String place) {

        //use your current location here


        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + latitude + "," + longitude);
        sb.append("&radius=2000");
        sb.append("&types=" + place);
        sb.append("&sensor=true");
        sb.append("&key=AIzaSyAX7LgOR7FyUaGSjp4gxwLA8VzyMF-UuX0");



        return sb;
    }

    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        @Override
        protected void onPreExecute(){
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);


        }

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                //TODO SHOW PROGRESSDIALOG

            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParserTask
            parserTask.execute(result);
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
        }
    }


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {

        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;


        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            Place_JSON placeJson = new Place_JSON();

            try {
                jObject = new JSONObject(jsonData[0]);

                places = placeJson.parse(jObject);

            } catch (Exception e) {

            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {


            // Clears all the existing markers;




            // Creating a marker
            try{
                // Getting a place from the places list


                // Getting latitude of the place

                HashMap<String, String> hmPlace = list.get(0);
                final double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                final  double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");



                // Getting vicinity
                String vicinity = hmPlace.get("vicinity");


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Directions");
                builder.setMessage("Do You Want To Be Directed To " + name + "? \n\nAddress : "+ vicinity);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LatLng latLng = new LatLng(lat, lng);

                        // Setting the position for the marker
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?saddr=" + String.valueOf(latitude) + "," + String.valueOf(longitude) +  "&daddr=" + String.valueOf(lat) + "," + String.valueOf(lng)));
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

            }catch(Exception exc){
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("ERROR");
                dialog.setMessage("Your Location is Unavailable. Please try again");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }





        }
    }



}