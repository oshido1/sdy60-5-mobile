package com.example.panos.ge5mobile;

import android.content.Context;
import android.graphics.Color;
import android.location.LocationListener;
import android.Manifest;
import android.app.LoaderManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.panos.ge5mobile.MainActivity.temp_string_date;
import static com.example.panos.ge5mobile.MainActivity.xristis;
import static java.lang.Math.abs;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GPSTracker implements LocationListener {
    Context context;
    int counter ;//= 0;  //panos))))   removed 12-4-18
    double latprev; //=37.149;           removed 12-4-18
    double lngprev;  //=22.182;    removed 12-4-18

    double latnext=0.0;
    double lngnext=0.0;
    double z2=0.0;  //20-4-18

     public GPSTracker(Context c){
     context = c;



         int counter = 0;  //panos))))


         // 06-04-2018

         int metritis_long_click=0;
         int metritis_click=0;
         // 06-04-2018

    /*Toast.makeText(context, "gpstracker mono mia fora ??? "+MainActivity.glo_x, Toast.LENGTH_SHORT).show();
     */

     }

    public Location getLocation(){

    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
        Toast.makeText(context, "Permission not Granted ", Toast.LENGTH_SHORT).show();
        return  null;
    }
    LocationManager lm= (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    if (isGPSEnabled){
        //Toast.makeText(context, "GPS ENERGOPOIHMENO !!!  pro", Toast.LENGTH_SHORT).show();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,10, this);
        //Toast.makeText(context, "GPS ENERGOPOIHMENO !!!  meta1", Toast.LENGTH_SHORT).show();
        Location l=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
     //   Toast.makeText(context, "GPS ENERGOPOIHMENO !!!  meta2 pro return", Toast.LENGTH_SHORT).show();
        //++MainActivity.metritis_click_me_gps;
        //mDatabase.child("METRHTHS  click  me  GPS").setValue(MainActivity.metritis_click_me_gps);


     // 13-4-18
        if (l!= null) {

            //panos 12-04-18
          //  Toast.makeText(context, "mono MIA FORA ISXYEI???", Toast.LENGTH_LONG).show();
            latprev=l.getLatitude();    // THN PRWTFH FORA MONO
            lngprev=l.getLongitude();    // THN PRWTFH FORA MONO
            counter=0;
            //end panos 12-04-18

            DatabaseReference mDatabase;
            DatabaseReference mMessageReference;
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("message");
            String temp_onoma;
            String temp_child;
            String temp_counter = String.valueOf(counter);

            //temp_onoma = "marker"+temp_counter;  original bis 17-4-18
            temp_onoma = temp_counter+"marker"; // allagi 17-4-18

            temp_child = "user" + xristis;


            /*  DOYLEYE MIA XARA EWS 17-4-18 POY EVALA KAI DATE STH VASI WSTE NA KSEXWRIZW DIADROMES
            //mDatabase.child(temp_child).child(temp_onoma).child("lat").push().setValue(latprev);  //08-04-18
            //mDatabase.child(temp_child).child(temp_onoma).child("log").push().setValue(lngprev);    //08-04-18
            */

            // sos 17-4-18  allagi sthn eggrafi vasis kai edw na einai idio database tree me locationchanged
            mDatabase.child(temp_child).child(temp_string_date).child(temp_onoma).child("lat").push().setValue(latprev)  ;  //17-04-18
            mDatabase.child(temp_child).child(temp_string_date).child(temp_onoma).child("log").push().setValue(lngprev)  ;  //17-04-18


            //19-4-18  prowsini lista me lat kai lon
            MainActivity.temp_lista_lat.add(latprev);
            MainActivity.temp_lista_lon.add(lngprev);
            //Toast.makeText(context, " loc change listalat proswrini"+MainActivity.temp_lista_lat, Toast.LENGTH_SHORT).show(); //19-4-18
            //end 19-4-18  prowsini lista me lat kai lon

            //20-4-18
            MainActivity.temp_lista_LATLON.add(latprev);
            MainActivity.temp_lista_LATLON.add(lngprev);
         //   Toast.makeText(context, " getlocation LATLON PROSWRI"+MainActivity.temp_lista_LATLON, Toast.LENGTH_SHORT).show(); //19-4-18
            //20-4-18


                    //18-4-18
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mMessageReference = FirebaseDatabase.getInstance().getReference("messagexx");
            mDatabase.child(temp_child).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //Toast.makeText(context, "datasnap shot getlocation only once", Toast.LENGTH_LONG).show();
                    //Integer pont1 = dataSnapshot.child("score1").getValue(Integer.class);
                    //pontoi1 = pont1;  //apo vasi retrieve and temporary store

                    // mDatabase.child(temp_child).child("trexousa apostasi").
                    if ((dataSnapshot.child("trexousa apostasi").getValue(Double.class)!=null) && (counter==0)) {
                        MainActivity.temp_sumdistance = dataSnapshot.child("trexousa apostasi").getValue(Double.class);
         //               Toast.makeText(context, " GET LOC CURRENT only once prepei TEMP_DISTANCE "+MainActivity.temp_sumdistance, Toast.LENGTH_SHORT).show(); //18-4-18
                    }

                //20-4-18
                   // if ((dataSnapshot.child("session_apostasi").getValue(Double.class)!=null) && (counter==0)) {
                   //     MainActivity.current_distance_user= dataSnapshot.child("session_apostasi").getValue(Double.class);
                   //     Toast.makeText(context, " GET LOC CURRENT only once prepei TEMP_DISTANCE "+MainActivity.current_distance_user, Toast.LENGTH_SHORT).show(); //18-4-18
                   // }
                //20-4-18


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
       //END 18-4-18


        }
     //END 13-4-18



        return l;
    }else {
        //++MainActivity.metritis_click_xwris_gps;
        //mDatabase.child("metritis_click  WITHOUT GPS").setValue(MainActivity.metritis_click_xwris_gps);
        //mDatabase.child("treasure1lat").setValue(0); //midenizei to lat sti vasi SOS  na vgei DOKIMI OK 220318
        //mDatabase.push().setValue(MainActivity.metritis_click);

        Toast.makeText(context, "Please Enable Gps ", Toast.LENGTH_LONG).show();
    }
    return null;

    }



    // PROS GEORGE SKIKO VERY CRUCIAL SEGMENT AUTO DETECT CHAGNE LOCATION  , SAVE TO FIREBASE
    @Override
    public void onLocationChanged(Location location){

    double lat = location.getLatitude();
    double lon = location.getLongitude();



        //12-04-18

        LatLng pp = new LatLng(lat,lon);
        MarkerOptions option = new MarkerOptions();
        option.position(pp);
        option.position(pp).title(" auto added "+counter+" "+pp.latitude + " : " + pp.longitude) ;

     //   Toast.makeText(context, "allaxe thesi douleyei?? "+lat+" "+lon, Toast.LENGTH_LONG).show();

        //ContactUsFragment.map.getMaxZoomLevel(); //diko mou
        ContactUsFragment.map.moveCamera(CameraUpdateFactory.newLatLng(pp));
        ContactUsFragment.map.addMarker(option);

        //Toast.makeText(context, "allaxe thesi douleyei ews end onLocationChanged ", Toast.LENGTH_LONG).show();


      //13-4-18
        int temp_color=-256;
        if (xristis==1) {temp_color=Color.RED ;}
        if (xristis==2) {temp_color=Color.GREEN;}
        if (xristis==3) {temp_color= Color.BLUE;}
        if (xristis==4) {temp_color= Color.BLACK;}
        if (xristis==5) {temp_color= Color.CYAN;}
        if (xristis==6) {temp_color= Color.YELLOW;} //22-4-18
        //13-4-18

        if (counter == 0) {
            latnext = pp.latitude;
            lngnext = pp.longitude;


//ADDED 13-4 DOKIMH NA PAREI KAI THN ARXIKH THESI ????
            //arxika htan exw apo to loop
            Polyline line = ContactUsFragment.map.addPolyline(new PolylineOptions()
                    //.add(new LatLng(latprev, lngprev), new LatLng(latnext, lngnext))
                    .add(new LatLng(latnext, lngnext), new LatLng(latnext, lngnext))
                    .width(5)
                    //.color(Color.YELLOW));
                    .color(temp_color));  //replace 13-4-18 each user different polyline color

//ADDED 13-4 DOKIMH NA PAREI KAI THN ARXIKH THESI

        } else {
            latprev = latnext;
            lngprev = lngnext;
            latnext = pp.latitude;
            lngnext = pp.longitude;


            //18-4-18
            Location loc1 = new Location("");
            loc1.setLatitude(lat);
            loc1.setLongitude(lon);

            Location loc2 = new Location("");
            //loc2.setLatitude(vor_lastlat);  arxika apo ge4
            //loc2.setLongitude(vor_lastlon); arxika apo ge4
            loc2.setLatitude(latprev);
            loc2.setLongitude(lngprev);
            double distanceInMeters = loc1.distanceTo(loc2);
      //      Toast.makeText(context, "DIS TA NCE  2 POINTS "+distanceInMeters, Toast.LENGTH_SHORT).show();
            //end 18-4-18

            //double z=distanceInMeters+ MainActivity.sumdistance1;  //270318
            //MainActivity.sumdistance1 = z;  //270318
            double z=distanceInMeters+ MainActivity.temp_sumdistance;  //18-4-18
            MainActivity.temp_sumdistance= z;  //18-4-18
         //   Toast.makeText(context, " change loc CURRENT TEMP_DISTANCE "+MainActivity.temp_sumdistance, Toast.LENGTH_SHORT).show(); //18-4-18

            z2=distanceInMeters+z2; //20-4-18
      //      Toast.makeText(context, " z2 auxanei +++??? "+z2, Toast.LENGTH_SHORT).show(); //18-4-18
            MainActivity.current_distance_user=z2;//20-4-18

            //mDatabase.child("DOKIMI").child("distance1").child("synolo").setValue(z);  //270318
            //mDatabase.child("DOKIMI").child("distance1").child("merikosynolo").push().setValue(distanceInMeters); // 270318

            //arxika htan exw apo to loop
            Polyline line = ContactUsFragment.map.addPolyline(new PolylineOptions()
                    .add(new LatLng(latprev, lngprev), new LatLng(latnext, lngnext))
                    .width(5)
                    //.color(Color.YELLOW));
                    .color(temp_color));  //replace 13-4-18 each user different polyline color

        }

        ++counter;


      //  Toast.makeText(context, " pontoi ++", Toast.LENGTH_SHORT).show(); //20-4-18
        MainActivity.pontoi=MainActivity.pontoi +1.0; //20-4-18  ++pontoi /7 gia car   /3 gia bike remain the same gia walker


        //13-4-18
        DatabaseReference mDatabase;
        DatabaseReference mMessageReference;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mMessageReference = FirebaseDatabase.getInstance().getReference("message");
        String temp_onoma;
        String temp_child;
        String temp_counter= String.valueOf(counter);

        //temp_onoma = "marker"+temp_counter;  original bis 17-4-18
        temp_onoma = temp_counter+"marker";  // allagi 17-4-18

        temp_child = "user" + xristis;


        /* //DOULEYE MIA XARA APLA EGINE ALLAGH GIATI THELW DIAFORETIKES DIADROMES ANA XRHSTH ME VASI TO DATE
        mDatabase.child(temp_child).child(temp_onoma).child("lat").push().setValue(latnext)  ;  //08-04-18
        mDatabase.child(temp_child).child(temp_onoma).child("log").push().setValue(lngnext)  ;    //08-04-18
        */

        //END 13-4-18



        mDatabase.child(temp_child).child(temp_string_date).child(temp_onoma).child("lat").push().setValue(latnext)  ;  //17-04-18
        mDatabase.child(temp_child).child(temp_string_date).child(temp_onoma).child("log").push().setValue(lngnext)  ;  //17-04-18

        //19-4-18  prowsini lista me lat kai lon
        MainActivity.temp_lista_lat.add(latnext);
        MainActivity.temp_lista_lon.add(lngnext);
     //   Toast.makeText(context, " loc change listalat proswrini"+MainActivity.temp_lista_lat, Toast.LENGTH_SHORT).show(); //19-4-18
        //end 19-4-18  prowsini lista me lat kai lon


        //20-4-18
        MainActivity.temp_lista_LATLON.add(latnext);
        MainActivity.temp_lista_LATLON.add(lngnext);
     //   Toast.makeText(context, " loc change LATLON PROSWRI"+MainActivity.temp_lista_LATLON, Toast.LENGTH_SHORT).show(); //19-4-18
        //20-4-18


    }






    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }




}


