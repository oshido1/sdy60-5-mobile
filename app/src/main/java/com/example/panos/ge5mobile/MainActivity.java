package com.example.panos.ge5mobile;

//import android.app.FragmentManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.View.FOCUS_BACKWARD;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnGetLoc;
    Button btnX;
    ConstraintLayout ConLaytest;

    MenuItem test_menu_item;



    NavigationView testnav  ;
    DrawerLayout  testdraw;
    public static boolean emfanise=true;
    View test_x;

    public  static Integer xristis=0;

    public DrawerLayout drawer1;
    public static View viewdrawer1root;
    public static String temp_string_date;   //17-4-18  date enarxis diadromis

    //18-4-18
    public static double temp_sumdistance=0.0;  // αρχικα 0 , μετά θα ανασύρω από βάση analoga to xristi sto getlocation me vasi to xristi
    public static double current_distance_user=0.0; //posi apostasi ekane o xristis se mia mono session bike or car or walk 20-4-18

    public static double sumdistance1=0.0;  //  synolo apostashs user1 se oles tis katigoriew athroistika
    public static double sumdistance2=0.0;  // synolo apostashs user2 se oles tis katigoriew athroistika
    public static double sumdistance3=0.0;  //
    public static double sumdistance4=0.0;  //
    public static double sumdistance5=0.0;  //
    //18-4-18

    //20-4-18
    public static double sumwalk=0.0;  //  synolo apostashs walk olwn twn user
    public static double sumcar=0.0;  // synolo apostashs car olwn twn user
    public static double sumbike=0.0;  // synolo apostashs bike olwn twn user

    //20-4-18


//19-4-18
    private DatabaseReference mDatabase;
    private DatabaseReference mMessageReference;

    private  FirebaseDatabase mref;
//19-4-18 end

  //19-4-18
    public static ArrayList temp_lista_lat = new ArrayList();
    public static ArrayList temp_lista_lon = new ArrayList();
    public static ArrayList temp_lista_LATLON = new ArrayList();
  //end 19-4-18


    public static Double pontoi=0.0;  //20-4-18  temp_pontoi during walk or bike or car drivin
    public static Double pontoi1=0.0;  //20-4-18  user1
    public static Double pontoi2=0.0;  //20-4-18
    public static Double pontoi3=0.0;  //20-4-18
    public static Double pontoi4=0.0;  //20-4-18
    public static Double pontoi5=0.0;  //20-4-18

    public static Double pontoi6=0.0;  //22-4-18

//21-4-18  VERY RISKY
private static final String TAG = MainActivity.class.getSimpleName();
    public class User {

        public String name;
        public String email;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public User() {
        }

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
    //END 21-4-18  VERY RISKY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* remove 21-4-18
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });  */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //panos 03-04-2018
        //NavigationView testnav= (NavigationView) findViewById(R.id.nav_view);
        testnav=  findViewById(R.id.nav_view);
        testdraw=   findViewById(R.id.drawer_layout);
        //end panos 03-04-2018

        //panos 04-04-2018 eskase sos!!!
        ConLaytest =findViewById(R.id.frameLayout);

        //panos 04-04-2018

        //panos 06-04-2018  disappear maap and left side menu
        //View test_x = findViewById(R.id.include_id);   //leitourgouse kai evala to view epanw
        View test_x = findViewById(R.id.include_id);   //08-04-2018 end
        //test_x.setVisibility(View.INVISIBLE);  //test gia ligo 08-04-2017




        //test_x.setVisibility(View.GONE);   // 13-04-18 original
        test_x.setVisibility(View.VISIBLE);   // 13-04-18 allagi

        //emfanise = false;      // 13-04-18 original
        emfanise = true;       // 13-04-18 allagi
        //end panos 06-04-2018


        //dokimi panos 09-04-2018
         drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);
         viewdrawer1root = drawer1.getRootView();
        // end dokimi panos 09-04-2018

        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);   //03-04-2018

        btnGetLoc.setVisibility(View.GONE);  //13-04-2018  mono gia ligo
        // PROS GEORGE SKIKO TO btnGetLoc.setOnClickListener(new View.OnClickListener()  EINAI AKYRO
        // OYSIASTIKA DEN YPARXEI.   HERE THERE WAS A LISTENER FOR BUTTON SAVE TO TXT GE5 FILE FOR SECURITY




        //PROS GEORGE SKIKO TO btnx το εχω καταργήσει ηταν κουμπι επιλογης χρηστων στα αριστέρα τώρα έχω επιλογή χρήστων από δεξια με 3 τελίτσες
        // PROS GEORGE SKIKO  EPILEGEIS XRHSTH APO TO BUTTON UPPER LEFT KAI META
        // AFOY EPILEXEIS TO BUTTON EXAFANIZETAI
        btnX = (Button) findViewById(R.id.buttonx);   //03-04-2018
        btnX.setVisibility(View.GONE); //added 21-4-18  SOS ΔΕΝ ΕΜΦΑΝΙΖΕΙ
        // HERE THERE WAS A LOT OF CODE WITH BUTTONX SAVED TO TXT GE5 DIRECORY FOR SECURITY REASONS!!

//20-4-18

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mMessageReference = FirebaseDatabase.getInstance().getReference("messagexx");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pontoi1=dataSnapshot.child("user1").child("score").getValue(Double.class);
                pontoi2=dataSnapshot.child("user2").child("score").getValue(Double.class);
                pontoi3=dataSnapshot.child("user3").child("score").getValue(Double.class);
                pontoi4=dataSnapshot.child("user4").child("score").getValue(Double.class);
                pontoi5=dataSnapshot.child("user5").child("score").getValue(Double.class);
                pontoi6=dataSnapshot.child("user6").child("score").getValue(Double.class); //22-4-18
                //Toast.makeText(MainActivity.this, "Pontoi user1 from database" +pontoi1 , Toast.LENGTH_SHORT).show();  //20-4-18

                //20-4-18 night
                sumwalk=dataSnapshot.child("Sum_Walk").getValue(Double.class);
                sumcar=dataSnapshot.child("Sum_Car").getValue(Double.class);
                sumbike=dataSnapshot.child("Sum_Bike").getValue(Double.class);
                //Toast.makeText(MainActivity.this, "Sum_Walk from base " +sumwalk, Toast.LENGTH_SHORT).show();  //20-4-18
                //20-4-18 night




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//20-4-18


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        /* remove 21-4-18
        if (id == R.id.action_settings) {
            return true;
        } */

       //16-4-18

        //According George Skikos wish   an mporw meta na kanw to menou disable
        if  (id==R.id.user_1){
            Toast.makeText(MainActivity.this, " G E O R G E",  Toast.LENGTH_SHORT).show();
            //dokimi
 //21-4-18
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            temp_string_date = formattedDate;

             ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();
            xristis=1;
//end 21-4-18

            return  true;
        }
        if  (id==R.id.user_2){
            Toast.makeText(MainActivity.this, "E L E N I",  Toast.LENGTH_SHORT).show();
            //21-4-18
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            temp_string_date = formattedDate;

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();
            xristis=2;
//end 21-4-18

            return  true;
        }
        if  (id==R.id.user_3){
            Toast.makeText(MainActivity.this, "J O H N",  Toast.LENGTH_SHORT).show();
            //21-4-18
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            temp_string_date = formattedDate;

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();
            xristis=3;
//end 21-4-18

            return  true;
        }
        if  (id==R.id.user_4){
            Toast.makeText(MainActivity.this, "M A R I A",  Toast.LENGTH_SHORT).show();
            //21-4-18
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            temp_string_date = formattedDate;

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();
            xristis=4;
//end 21-4-18

            return  true;
        }
        if  (id==R.id.user_5){
            Toast.makeText(MainActivity.this, "PANAGIOTIS",  Toast.LENGTH_SHORT).show();
            //21-4-18
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            temp_string_date = formattedDate;

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();
            xristis=5;
//end 21-4-18

            return  true;
        }

        if  (id==R.id.user_6){
            Toast.makeText(MainActivity.this, "N I K I",  Toast.LENGTH_SHORT).show();
            //21-4-18
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            temp_string_date = formattedDate;

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();
            xristis=6;
//end 21-4-18

            return  true;
        }
        //16-4-18

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




               // PROS GEORGE SKIKO retrieve from databasse TOTAL DISTANCE PER USER TWRA MONO PEDESTRIAN META KAI CAR AND BIKE
        //  and dokimi me listes lat, lon ana bike or car or pedestria
        if (id == R.id.sel_user) {
//good luck 19 april midnight

            //19-4-18 start
            String temp_onoma;
            String temp_child;
            temp_child = "user" + xristis;
            //19-4-18 start

//21-4-18
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("messagexx");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Toast.makeText(getApplicationContext(), "before new adventure", Toast.LENGTH_LONG).show();

                        for (DataSnapshot dsp1 :  dataSnapshot.child("pedestrian LATLON").getChildren()) {
                            ArrayList distancelist = new ArrayList();  // diki mou metartorpoi  ok douley????    20-4-18 noon
                            //sos na kanw diplo for kai na diavasw
                            Toast.makeText(getApplicationContext(), "@@@last attempt pedest dsp1.getValue() = " + dsp1.getValue(), Toast.LENGTH_LONG).show();
                            // Toast.makeText(getApplicationContext(), "@@@distancelist "+distancelist+"  distancelistsize= "+distancelist.size() , Toast.LENGTH_LONG).show();

        //                    Toast.makeText(getApplicationContext(), "@@@last attempt dsp1.getKey()= " + dsp1.getKey(), Toast.LENGTH_LONG).show();
                            String alabourn;
                            alabourn=dsp1.getKey();
                            Toast.makeText(getApplicationContext(), " #number children pedest "+
                                    dataSnapshot.child("pedestrian LATLON").child(alabourn).getChildrenCount() , Toast.LENGTH_LONG).show();
                            //epitelous  6 kai 8 paidia

                            // Toast.makeText(getApplicationContext(), " #isws??? "+
                                    //dataSnapshot.child("pedestrian LATLON").child(alabourn).getChildren() , Toast.LENGTH_LONG).show();

                            //21-4-18 sos risky !!!!
                            List<Double> list_lat_lon = new ArrayList<Double>();
                            //ArrayList  lista_lat_lon= new ArrayList();

                               for (DataSnapshot postSnapshot: dataSnapshot.child("pedestrian LATLON").child(alabourn).getChildren())
                                    { Double post = postSnapshot.getValue(Double.class);
     //                                   Toast.makeText(getApplicationContext(), " #makairi"+ post , Toast.LENGTH_LONG).show();  // ok !!!
                                        //nai work!!!!                      //ara exw to post th mia fora 6 fores  3 zeugi
                                        // thn allh 8 fores 4 zeugi

                                        list_lat_lon.add(post);
                                    }

                            //int mikos_listas= lista_lat_lon.size();
                              int mikos_listas= list_lat_lon.size();
                            double lat1=0.0; //=37.149;           removed 12-4-18
                            double lng1=0.0;  //=22.182;    removed 12-4-18

                            double lat2=0.0;
                            double lng2=0.0;
                            Toast.makeText(getApplicationContext(), " #mikos listas pedes"+ mikos_listas, Toast.LENGTH_LONG).show();
                            if (mikos_listas>=4){
                                for (int i = 0; i+4 < mikos_listas; i+=2) {
                                    Toast.makeText(getApplicationContext(), " #makari lista pedes= "+list_lat_lon , Toast.LENGTH_LONG).show();

                                    //Toast.makeText(getApplicationContext(), " #makari i= "+i+" lista[0]"+list_lat_lon.get(0) , Toast.LENGTH_LONG).show();
                                    lat1= list_lat_lon.get(i);
                                    lng1 = list_lat_lon.get(i+1);
                                    lat2= list_lat_lon.get(i+2);
                                    lng2 = list_lat_lon.get(i+3);
                                    // prin to polyline na fortosw to xarti
                                    Toast.makeText(getApplicationContext(), " #makairi latlng ana 2 pedes"+ lat1+" "+lng1+" "+lat2+" "+lng2 , Toast.LENGTH_LONG).show();
                                    // risk !! prepei na fortwthei o xartis prwta
                                    Polyline line = ContactUsFragment.map.addPolyline(new PolylineOptions()
                                            .add(new LatLng(lat1, lng1), new LatLng(lat2, lng2))
                                            .width(5)
                                            //.color(Color.YELLOW));
                                            .color(Color.MAGENTA));  //replace 13-4-18 each user different polyline color
                                 } //end for gia polyline
                            } //end if mikolistas>4

                        }  //end for katigorias edw

                    // katigoria car lons
                    for (DataSnapshot dsp1 :  dataSnapshot.child("car lons").getChildren()) {
                        ArrayList distancelist = new ArrayList();  // diki mou metartorpoi  ok douley????    20-4-18 noon
                        //sos na kanw diplo for kai na diavasw
                        Toast.makeText(getApplicationContext(), "@@@last attempt car dsp1.getValue() = " + dsp1.getValue(), Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), "@@@distancelist "+distancelist+"  distancelistsize= "+distancelist.size() , Toast.LENGTH_LONG).show();

                        //                    Toast.makeText(getApplicationContext(), "@@@last attempt dsp1.getKey()= " + dsp1.getKey(), Toast.LENGTH_LONG).show();
                        String alabourn;
                        alabourn=dsp1.getKey();
                        Toast.makeText(getApplicationContext(), " #number children car"+
                                dataSnapshot.child("car lons").child(alabourn).getChildrenCount() , Toast.LENGTH_LONG).show();
                        //epitelous  6 kai 8 paidia

                        // Toast.makeText(getApplicationContext(), " #isws??? "+
                        //dataSnapshot.child("pedestrian LATLON").child(alabourn).getChildren() , Toast.LENGTH_LONG).show();

                        //21-4-18 sos risky !!!!
                        List<Double> list_lat_lon = new ArrayList<Double>();
                        //ArrayList  lista_lat_lon= new ArrayList();

                        for (DataSnapshot postSnapshot: dataSnapshot.child("car lons").child(alabourn).getChildren())
                        { Double post = postSnapshot.getValue(Double.class);
                            //                                   Toast.makeText(getApplicationContext(), " #makairi"+ post , Toast.LENGTH_LONG).show();  // ok !!!
                            //nai work!!!!                      //ara exw to post th mia fora 6 fores  3 zeugi
                            // thn allh 8 fores 4 zeugi

                            list_lat_lon.add(post);
                        }

                        //int mikos_listas= lista_lat_lon.size();
                        int mikos_listas= list_lat_lon.size();
                        double lat1=0.0; //=37.149;           removed 12-4-18
                        double lng1=0.0;  //=22.182;    removed 12-4-18

                        double lat2=0.0;
                        double lng2=0.0;
                        Toast.makeText(getApplicationContext(), " #mikos listas car"+ mikos_listas, Toast.LENGTH_LONG).show();
                        if (mikos_listas>=4){
                            for (int i = 0; i+4 < mikos_listas; i+=2) {
                                Toast.makeText(getApplicationContext(), " #makari lista car= "+list_lat_lon , Toast.LENGTH_LONG).show();

                                //Toast.makeText(getApplicationContext(), " #makari i= "+i+" lista[0]"+list_lat_lon.get(0) , Toast.LENGTH_LONG).show();
                                lat1= list_lat_lon.get(i);
                                lng1 = list_lat_lon.get(i+1);
                                lat2= list_lat_lon.get(i+2);
                                lng2 = list_lat_lon.get(i+3);
                                // prin to polyline na fortosw to xarti
                                Toast.makeText(getApplicationContext(), " #makairi latlng car ana 2"+ lat1+" "+lng1+" "+lat2+" "+lng2 , Toast.LENGTH_LONG).show();
                                // risk !! prepei na fortwthei o xartis prwta
                                Polyline line = ContactUsFragment.map.addPolyline(new PolylineOptions()
                                        .add(new LatLng(lat1, lng1), new LatLng(lat2, lng2))
                                        .width(5)
                                        //.color(Color.YELLOW));
                                        .color(Color.RED));  //replace 13-4-18 each user different polyline color
                            } //end for gia polyline
                        } //end if mikolistas>4

                    }  //end for katigorias edw

                    // katigoria bike lons
                    for (DataSnapshot dsp1 :  dataSnapshot.child("bike lons").getChildren()) {
                        ArrayList distancelist = new ArrayList();  // diki mou metartorpoi  ok douley????    20-4-18 noon
                        //sos na kanw diplo for kai na diavasw
                        Toast.makeText(getApplicationContext(), "@@@last attempt bike dsp1.getValue() = " + dsp1.getValue(), Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), "@@@distancelist "+distancelist+"  distancelistsize= "+distancelist.size() , Toast.LENGTH_LONG).show();

                        //                    Toast.makeText(getApplicationContext(), "@@@last attempt dsp1.getKey()= " + dsp1.getKey(), Toast.LENGTH_LONG).show();
                        String alabourn;
                        alabourn=dsp1.getKey();
                        Toast.makeText(getApplicationContext(), " #number children bike"+
                                dataSnapshot.child("bike lons").child(alabourn).getChildrenCount() , Toast.LENGTH_LONG).show();
                        //epitelous  6 kai 8 paidia

                        // Toast.makeText(getApplicationContext(), " #isws??? "+
                        //dataSnapshot.child("pedestrian LATLON").child(alabourn).getChildren() , Toast.LENGTH_LONG).show();

                        //21-4-18 sos risky !!!!
                        List<Double> list_lat_lon = new ArrayList<Double>();
                        //ArrayList  lista_lat_lon= new ArrayList();

                        for (DataSnapshot postSnapshot: dataSnapshot.child("bike lons").child(alabourn).getChildren())
                        { Double post = postSnapshot.getValue(Double.class);
                            //                                   Toast.makeText(getApplicationContext(), " #makairi"+ post , Toast.LENGTH_LONG).show();  // ok !!!
                            //nai work!!!!                      //ara exw to post th mia fora 6 fores  3 zeugi
                            // thn allh 8 fores 4 zeugi

                            list_lat_lon.add(post);
                        }

                        //int mikos_listas= lista_lat_lon.size();
                        int mikos_listas= list_lat_lon.size();
                        double lat1=0.0; //=37.149;           removed 12-4-18
                        double lng1=0.0;  //=22.182;    removed 12-4-18

                        double lat2=0.0;
                        double lng2=0.0;
                        Toast.makeText(getApplicationContext(), " #mikos listas bike"+ mikos_listas, Toast.LENGTH_LONG).show();
                        if (mikos_listas>=4){
                            for (int i = 0; i+4 < mikos_listas; i+=2) {
                                Toast.makeText(getApplicationContext(), " #makari lista bike= "+list_lat_lon , Toast.LENGTH_LONG).show();

                                //Toast.makeText(getApplicationContext(), " #makari i= "+i+" lista[0]"+list_lat_lon.get(0) , Toast.LENGTH_LONG).show();
                                lat1= list_lat_lon.get(i);
                                lng1 = list_lat_lon.get(i+1);
                                lat2= list_lat_lon.get(i+2);
                                lng2 = list_lat_lon.get(i+3);
                                // prin to polyline na fortosw to xarti
                                Toast.makeText(getApplicationContext(), " #makairi latlng bike ana 2"+ lat1+" "+lng1+" "+lat2+" "+lng2 , Toast.LENGTH_LONG).show();
                                // risk !! prepei na fortwthei o xartis prwta
                                Polyline line = ContactUsFragment.map.addPolyline(new PolylineOptions()
                                        .add(new LatLng(lat1, lng1), new LatLng(lat2, lng2))
                                        .width(5)
                                        //.color(Color.YELLOW));
                                        .color(Color.BLUE));  //replace 13-4-18 each user different polyline color
                            } //end for gia polyline
                        } //end if mikolistas>4

                    }  //end for katigorias edw



                }  //end onDatachange


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

                    //sos 19
                    //for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                        /*for (DataSnapshot dsp : dataSnapshot.child("pedestrian lats").getChildren()) {
                    //for (DataSnapshot dsp : dataSnapshot.child("user2").getChildren()) {
                        //dsp.child("user2").getValue();  18-4-18  MONO GIA user2 ok
                        //dsp.child(temp_child).getValue(); //19-4-18  night
                        dsp.getValue();
                        //Toast.makeText(getApplicationContext(), "!!!!***pedestrian lat value ??? " + dsp.getValue(), Toast.LENGTH_LONG).show(); //ok deixnei
                        // kateytheian to polyline gia ta getvalue
                        //new_lista_LATLON.add(dsp.getValue());
                       //MAKE POLYLINE
                        //new_lista_lat=null;

                        //new_lista_lat.add(dsp.getValue());
                        // Toast.makeText(getApplicationContext(), "!!!!***new listlat pou ayxanei " + new_lista_lat, Toast.LENGTH_LONG).show(); //ok deixnei

                        //deixnei ta LUAb5J4-7avk
                        //Toast.makeText(getApplicationContext(), "!!!!***pedestrian key  " + dsp.getKey(), Toast.LENGTH_LONG).show(); //19-4-18

                        //childDataSnapshot.getKey()
                    }  */




                }  //end R.id.sel_user

           //19-4-18




//end good luck 19 april midnight


        // PROS GEORGE SKIKO STATISTIKA
        //else if (id == R.id.sel_problems) {
        else if (id == R.id.sel_statistics) {
//21-4-18 MORNING


            Toast.makeText(MainActivity.this, "ΣΤΑΤΙΣΤΙΚΑ  ΣΚΟΡ", Toast.LENGTH_SHORT).show();
            // ΣΚΟΡ ΧΡΗΣΤΗ 1 .... 5
             // ΜΗΚΟΣ ΔΙΑΔΡΟΜΗΣ ΜΕ ΤΑ ΠΟΔΙΑ, ΟΧΗΜΑ, ΠΟΔΗΛΑΤΟ
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("messagexx");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    pontoi1=dataSnapshot.child("user1").child("score").getValue(Double.class);
                    pontoi2=dataSnapshot.child("user2").child("score").getValue(Double.class);
                    pontoi3=dataSnapshot.child("user3").child("score").getValue(Double.class);
                    pontoi4=dataSnapshot.child("user4").child("score").getValue(Double.class);
                    pontoi5=dataSnapshot.child("user5").child("score").getValue(Double.class);
                    pontoi6=dataSnapshot.child("user6").child("score").getValue(Double.class);  //22-4-18
                    //Toast.makeText(MainActivity.this, " STATS: SCORE USER1,USER2,USER 3" +pontoi1+""+pontoi2+" "+pontoi3 , Toast.LENGTH_SHORT).show();  //21-4-18
                   // Toast.makeText(MainActivity.this, "STATS: SCORE USER4,USER5 " +pontoi4+""+pontoi5, Toast.LENGTH_SHORT).show();  //21-4-18


                    sumwalk=dataSnapshot.child("Sum_Walk").getValue(Double.class);
                    sumcar=dataSnapshot.child("Sum_Car").getValue(Double.class);
                    sumbike=dataSnapshot.child("Sum_Bike").getValue(Double.class);
                    //Toast.makeText(MainActivity.this, "STATS: DISTANCE WITH WALK, CAR, BIKE" +sumwalk+""+sumcar+" "+sumbike , Toast.LENGTH_SHORT).show();  //21-4-18

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Toast.makeText(MainActivity.this, " STATS: SCORE USER1,USER2,USER 3 " +pontoi1+" "+pontoi2+" "+pontoi3 , Toast.LENGTH_SHORT).show();  //21-4-18
            Toast.makeText(MainActivity.this, " STATS: SCORE USER4,USER5, USER6 " +pontoi4+" "+pontoi5+" "+pontoi6, Toast.LENGTH_SHORT).show();  //21-4-18
            Toast.makeText(MainActivity.this, " STATS: DISTANCE WITH WALK, CAR, BIKE " +sumwalk+" "+sumcar+" "+sumbike , Toast.LENGTH_SHORT).show();  //21-4-18




//END 21-4-18 MORNING
        }

        // PROS GEORGE SKIKO EDW START THE APP TO TRACE AUTOMATICALLY THE PATH AND SAVE TO FIREBASE
        else if (id == R.id.cur_location) {


            //12-04-18  DOKIMI
            GPSTracker g = new GPSTracker(getApplicationContext());
            Location l= g.getLocation();
            //Toast.makeText(getApplicationContext(), "mesa sto main meta thn synartisi l null??? ", Toast.LENGTH_LONG).show();

            if (l !=null) {
                /*double lat = l.getAltitude(); */
                //Toast.makeText(getApplicationContext(), "mesa sto main meta thn synartisi oxi null to l ", Toast.LENGTH_LONG).show();
                final double lat = l.getLatitude();
                final double lon = l.getLongitude();

                Toast.makeText(MainActivity.this, "apo menou current location yparxei "+lat,  Toast.LENGTH_SHORT).show();   //remove 260318

                LatLng pp = new LatLng(lat,lon);
                MarkerOptions option = new MarkerOptions();
                option.position(pp).title("current location first marker");
                ContactUsFragment.map.addMarker(option);
                ContactUsFragment.map.getMaxZoomLevel(); //diko mou
                ContactUsFragment.map.moveCamera(CameraUpdateFactory.newLatLng(pp));
                ContactUsFragment.map.animateCamera(CameraUpdateFactory.newLatLng(pp));  //diko mou

                ContactUsFragment.map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 22));














            }
            else{
                Toast.makeText(MainActivity.this, "apo menou current location  = null " , Toast.LENGTH_SHORT).show();   //remove 260318
            }






            //ενδ 12-04-18



        } else if (id == R.id.nav_manage) {


        }

        else if  (id== R.id.subitem1) {   //pedstrian
            //17-4-18  stop the trace and  mark the route as bike or car or pedestrian  save this to firebase
            DatabaseReference mDatabase;
            DatabaseReference mMessageReference;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("message");

            String temp_child;
            temp_child = "user" + xristis;
            mDatabase.child(temp_child).child(temp_string_date).child("The route is good for ").push().setValue("pedestrian")  ;  //17-04-18

            //18-4-18
             // sos save public temp_sumdistance katw apo to xristi poy exvw
            //mDatabase.child(temp_child).child(temp_string_date).child("trexousa apostasi").push().setValue(temp_sumdistance)  ;  //arxiki
            //mDatabase.child(temp_child).child("trexousa apostasi").push().setValue(temp_sumdistance)  ;  //17-04-18  xwris push
            mDatabase.child(temp_child).child("trexousa apostasi").setValue(temp_sumdistance)  ;  //17-04-18  xwris push
            //end 18-4-18

            //19-4-18
            //mDatabase.child("pedestrian lats").push().setValue(temp_lista_lat);  douleye ws 20-4-18
            //mDatabase.child("pedestrian lons").push().setValue(temp_lista_lon);
            //19-4-18

            //20-4-18
            //mDatabase.child("DOKIMI").child("distance2").child("merikosynolo").push().setValue(distanceInMeters); // 270318
            // na valw kai date an thelw mDatabase.child("pedestrian LATLON").child(datecalend).push().setValue(temp_lista_LATLON);
            mDatabase.child("pedestrian LATLON").push().setValue(temp_lista_LATLON);
            //20-4-18

            //20-4-18 evening
            //Toast.makeText(MainActivity.this, "Pontoi prin ++sto xristi ped"+pontoi , Toast.LENGTH_SHORT).show();  //20-4-18
            double pontoi_from_database=0.0;
            if (xristis==1) pontoi_from_database=pontoi1;
            if (xristis==2) pontoi_from_database=pontoi2;
            if (xristis==3) pontoi_from_database=pontoi3;
            if (xristis==4) pontoi_from_database=pontoi4;
            if (xristis==5) pontoi_from_database=pontoi5;
            if (xristis==6) pontoi_from_database=pontoi6;
            mDatabase.child(temp_child).child("score").setValue(pontoi+pontoi_from_database);  //20-4-18 palaioi pontoi kai neoi atofioi  walk
            //20-4-18

            //20-4-18
            Toast.makeText(MainActivity.this, "apostasi session "+current_distance_user, Toast.LENGTH_SHORT).show();  //20-4-18
            //mDatabase.child(temp_child).child("session_apostasi").setValue(current_distance_user)  ;  //????
            //end 20-4-18


            //20-4-18 night
            sumwalk=sumwalk+current_distance_user;
            mDatabase.child("Sum_Walk").setValue(sumwalk);
            //20-4-18 end night


            //stop the programm 17 -4-18
            finish();
            System.exit(0);
            //end 17-4-18


        }
        else if  (id== R.id.subitem2) { //car route
            //17-4-18  stop the trace and  mark the route as bike or car or pedestrian  save this to firebase
            DatabaseReference mDatabase;
            DatabaseReference mMessageReference;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("message");

            String temp_child;
            temp_child = "user" + xristis;
            mDatabase.child(temp_child).child(temp_string_date).child("The route is good for ").push().setValue("car")  ;  //17-04-18
            //end 17-4-18

            //18-4-18
            // sos save public temp_sumdistance katw apo to xristi poy exvw
            //mDatabase.child(temp_child).child(temp_string_date).child("trexousa apostasi").push().setValue(temp_sumdistance)  ;  // arxiki
            //mDatabase.child(temp_child).child("trexousa apostasi").push().setValue(temp_sumdistance)  ;  //17-04-18 xwris push
            mDatabase.child(temp_child).child("trexousa apostasi").setValue(temp_sumdistance)  ;  //17-04-18  xwris push

            //end 18-4-18

            //19-4-18
            //mDatabase.child("car lats").push().setValue(temp_lista_lat);
            //mDatabase.child("car lons").push().setValue(temp_lista_lon);
            //19-4-18

            //20-4-18
            mDatabase.child("car lons").push().setValue(temp_lista_LATLON);
            //20-4-18

            //20-4-18 evening
            //Toast.makeText(MainActivity.this, "Pontoi prin ++sto xristi ped"+pontoi , Toast.LENGTH_SHORT).show();  //20-4-18
            double pontoi_from_database=0.0;
            if (xristis==1) pontoi_from_database=pontoi1;
            if (xristis==2) pontoi_from_database=pontoi2;
            if (xristis==3) pontoi_from_database=pontoi3;
            if (xristis==4) pontoi_from_database=pontoi4;
            if (xristis==5) pontoi_from_database=pontoi5;
            if (xristis==6) pontoi_from_database=pontoi6;
            mDatabase.child(temp_child).child("score").setValue(pontoi/7.0+pontoi_from_database);  //20-4-18 palaioi pontoi kai neoi/7 car
            //20-4-18

            //Toast.makeText(MainActivity.this, "Pontoi prin ++sto xristi ped"+pontoi/7.0 , Toast.LENGTH_SHORT).show();  //20-4-18

           //20-4-18 night
            sumcar=sumcar+current_distance_user;
            mDatabase.child("Sum_Car").setValue(sumcar);
            //20-4-18 end night

            //stop the programm 17 -4-18
            finish();
            System.exit(0);
            //end 17-4-18



        }
        else if  (id== R.id.subitem3) {  //bike
            //17-4-18  stop the trace and  mark the route as bike or car or pedestrian  save this to firebase
            DatabaseReference mDatabase;
            DatabaseReference mMessageReference;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("message");
            //String temp_onoma;
            String temp_child;

            temp_child = "user" + xristis;
            mDatabase.child(temp_child).child(temp_string_date).child("The route is good for ").push().setValue("bike")  ;  //17-04-18
            //end 17-4-18

            //18-4-18
            // sos save public temp_sumdistance katw apo to xristi poy exvw
             //mDatabase.child(temp_child).child(temp_string_date).child("trexousa apostasi").push().setValue(temp_sumdistance)  ;  //arxiki
            //mDatabase.child(temp_child).child("trexousa apostasi").push().setValue(temp_sumdistance)  ;  //17-04-18 xw
            mDatabase.child(temp_child).child("trexousa apostasi").setValue(temp_sumdistance)  ;  //17-04-18  xwris push
            //end 18-4-18


            //19-4-18
            //mDatabase.child("bike lats").push().setValue(temp_lista_lat);
            //mDatabase.child("bike lons").push().setValue(temp_lista_lon);
            //19-4-18

            //20-4-18
            mDatabase.child("bike lons").push().setValue(temp_lista_LATLON);
            //20-4-18


            //20-4-18 evening
            //Toast.makeText(MainActivity.this, "Pontoi prin ++sto xristi ped"+pontoi , Toast.LENGTH_SHORT).show();  //20-4-18
            double pontoi_from_database=0.0;
            if (xristis==1) pontoi_from_database=pontoi1;
            if (xristis==2) pontoi_from_database=pontoi2;
            if (xristis==3) pontoi_from_database=pontoi3;
            if (xristis==4) pontoi_from_database=pontoi4;
            if (xristis==5) pontoi_from_database=pontoi5;
            if (xristis==6) pontoi_from_database=pontoi6;  //24-4-18
            mDatabase.child(temp_child).child("score").setValue(pontoi/3.0+pontoi_from_database);  //20-4-18  palaioi pontoi kai neoi/3 bike
            //20-4-18

            //20-4-18 night
            sumbike=sumbike+current_distance_user;
            mDatabase.child("Sum_Bike").setValue(sumbike);
            //20-4-18 end night


            //stop the programm 17 -4-18
            finish();
            System.exit(0);
            //end 17-4-18


        }


       // PROS GEORGE SKIKO DOKIMI DEN PAIZEI KAPOIO ROLO
         else if (id == R.id.nav_share) {

            // good luck 03-04-2018
            //buttonX.setVisibility(View.INVISIBLE); //added 250318 night
            btnGetLoc.setVisibility(View.VISIBLE);
            testnav.setBackgroundColor(Color.BLUE);
            testdraw.setBackgroundColor(Color.RED);

            View test_x = findViewById(R.id.include_id);  // exafanizei to menu afou prwta to kanei mple
            test_x.setVisibility(View.INVISIBLE);
            emfanise = false;



        }
    // PROS GEORGE SKIKO DOKIMI HTAN TO PALAIO START DEN PAIZEI KAPOIO ROLO
        else if (id == R.id.start_app) {

            //added according to youtube video 03-04-2018
            ContactUsFragment contactUsFragment = new ContactUsFragment(); //arxiko
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, contactUsFragment).commit();

            emfanise=true;
            // end 03-04-2018




        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
