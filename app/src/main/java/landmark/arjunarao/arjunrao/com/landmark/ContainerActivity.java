package landmark.arjunarao.arjunrao.com.landmark;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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
import android.widget.SeekBar;
import android.widget.Toast;

public class ContainerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Dialog yourDialog;
    public String radius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sp = getSharedPreferences("radius", Activity.MODE_PRIVATE);
        radius = String.valueOf(sp.getInt("radius", 2000));

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
        } else if (id == R.id.nav_send) {
            //TODO FEEDBACK DIALOG
        }
        else if(id == R.id.radius){
            yourDialog = new Dialog(this);
            LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.radius_bar, (ViewGroup)findViewById(R.id.your_dialog_root_element));
            yourDialog.setContentView(layout);

            Button yourDialogButton = (Button)layout.findViewById(R.id.your_dialog_button);
            SeekBar yourDialogSeekBar = (SeekBar)layout.findViewById(R.id.your_dialog_seekbar);


            SeekBar.OnSeekBarChangeListener yourSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Toast.makeText(ContainerActivity.this,radius,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    //add code here
                }

                @Override
                public void onProgressChanged(SeekBar seekBark, int progress, boolean fromUser) {
                    radius = String.valueOf(progress);
                }
            };
            yourDialogSeekBar.setOnSeekBarChangeListener(yourSeekBarListener);


            yourDialog.show();

            yourDialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = getSharedPreferences("radius", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("radius", Integer.valueOf(radius));
                    editor.commit();
                }
            });

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
