package landmark.arjunarao.arjunrao.com.landmark;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class ContainerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String radius;

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        SharedPreferences sp = getSharedPreferences("radius", Activity.MODE_PRIVATE);
        radius = sp.getString("radius", "2");
        int intradius = Integer.valueOf(radius);
        intradius*=1000;
        radius = String.valueOf(intradius);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        setupNavigationView(toolbar);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.container, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.developer) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContainerActivity.this);
            alertDialog.setTitle("Developer");

            alertDialog.setMessage("Name : Arjun Rao\nEmail : arjunarao619@gmail.com");
            alertDialog.setIcon(R.drawable.dev);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            //TODO SHARE THE APP!

            String shareBody = "https://play.google.com/store/apps/details?id=com.arjunarao.arjunrao.beacon";

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "APP NAME (Open it in Google Play Store to Download the Application)");

            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.nav_rate) {
            String shareBody = "https://play.google.com/store/apps/details?id=com.arjunarao.arjunrao.beacon";


            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(shareBody));
            startActivity(i);
        }
        else if (id == R.id.nav_send){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, "arjunarao619@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback on Landmark");


            startActivity(Intent.createChooser(intent, "Send Email"));
        }
        else if(id == R.id.radius){
            LayoutInflater li = LayoutInflater.from(this);
            View promptsView = li.inflate(R.layout.radius_bar, null);



            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            ImageButton wtf = (ImageButton) promptsView.findViewById(R.id.wtf);
            wtf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContainerActivity.this);
                    alertDialog.setTitle("Search Radius");
                    alertDialog.setMessage("If you can't find a particular landmark, it's probably because the landmark isn't within the previously set search radius or the default radius (2 kilometers)\nIf you wish to search a broader landmark, you can enter the radius in kilometers!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.radiusText);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {

                                    radius = userInput.getText().toString();
                                    //validate
                                    if(radius.equals("") || radius.length() > 3){
                                        //NOOOOOO
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContainerActivity.this);
                                        alertDialog.setMessage("Please enter a valid seach radius");
                                        alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                    else{
                                        SharedPreferences settings = getSharedPreferences("radius", MODE_PRIVATE);

                                        // Writing data to SharedPreferences
                                        SharedPreferences.Editor editor = settings.edit();
                                        editor.putString("radius", radius);
                                        editor.commit();
                                    }


                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });



            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        //TODO INCOMPLETE CRITICAL

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupNavigationView(final Toolbar toolbar) {
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();

            selectFragment(menu.getItem(1));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);

                            item.setEnabled(true);
                            switch (item.getItemId()) {
                                case R.id.action_commute:
                                    // Action to perform when Home Menu item is selected.

                                    toolbar.setTitle("Travel landmarks");
                                    break;
                                case R.id.action_popular:
                                    // Action to perform when Bag Menu item is selected.
                                    toolbar.setTitle("Popular Landmarks");
                                    break;
                                case R.id.action_recreation:
                                    // Action to perform when Account Menu item is selected.
                                    toolbar.setTitle("Recreation Landmarks");
                                    break;
                            }


                            return false;
                        }
                    });
        }
    }


    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_commute:
                // Action to perform when Home Menu item is selected.

                pushFragment(new CommuteFragment());
                break;
            case R.id.action_popular:
                // Action to perform when Bag Menu item is selected.
                pushFragment(new BlankFragment());
                break;
            case R.id.action_recreation:
                // Action to perform when Account Menu item is selected.
                pushFragment(new RecFragment());
                break;
        }
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,
                        R.animator.fragment_slide_right_exit);
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}
