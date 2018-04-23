package com.example.panos.ge5mobile;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.panos.ge5mobile.MainActivity.viewdrawer1root;
import static com.example.panos.ge5mobile.MainActivity.xristis;



/**
 * A simple {@link Fragment} subclass.
 */




public class ContactUsFragment extends Fragment implements OnMapReadyCallback {

    public  static GoogleMap  map;   //DOKIMI 12-04-18

    //SOS NA MFYGEI TO SXOLIO BELOW LINE 12-04-18
    //GoogleMap map;     //arxiko  douleye ws 11-04-18


    int counter = 0;  //panos))))
    double latprev=37.149;
    double lngprev=22.182;

    double latnext=0.0;
    double lngnext=0.0;





    // 06-04-2018
    private DatabaseReference mDatabase;
    private DatabaseReference mMessageReference;

    int metritis_long_click=0;
    int metritis_click=0;
    // 06-04-2018

    View v;   //06-04-2018 late night

    String path_difficulty="";  //08-04-18

    public ContactUsFragment() {
        // Required empty public constructor


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 04-04-2018
        //btnGetLoc = (Button) findViewById(R.id.btnGetLoc);   //03-04-2018



        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_contact_us, container, false);  // arxiko
        //View v= inflater.inflate(R.layout.fragment_contact_us, container, false);  //03-04-18 arxiko swsot

         v= inflater.inflate(R.layout.fragment_contact_us, container, false);  //sos allagi exw kai View v epanw mazi isos skazei na fygoun
                                                                                          // kai h panw grammi

        //v1= inflater.inflate(R.layout.fragment_contact_us, container, false);  //06-04-18 late night



        // move below
        //SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map1); //03-04-18
        //mapFragment.getMapAsync(this);  //03-04-2018  move to
        //


        return v;   //03-04-18

    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {  //03-04-18
        super.onViewCreated(view, savedInstanceState);

        //na fygei an ginetai den yphre
        if (MainActivity.emfanise == false) {
            //view.setVisibility(View.INVISIBLE);  //test
            //Toast.makeText(MainActivity.this, " AORATO???", Toast.LENGTH_SHORT).show();
            //Toast.makeText(onCreateView().getContext(), " AORATO", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //view.setVisibility(View.VISIBLE);  //test
        }
        // end //na fygei an ginetai den yphre

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map1); //03-04-18
        mapFragment.getMapAsync(this);  //03-04-2018

    }

    @Override

    // PROS GEORGE SKIKO  TO EXW KATARGHSEI , XRHSIMEYEI GIA CLICK ON MAP KAI TRACE THE PATH WITH FINGER, NOT AYTOMATICALLY
    public void onMapReady(GoogleMap googleMap) {    //3-04-2018  olo





        map = googleMap;



        //Toast.makeText(getActivity(), "test kalamata onMapready  karfwto start ", Toast.LENGTH_SHORT).show();
        // gia dokimi kalytera sto mellon apo current location

//        LatLng pp = new LatLng(37.040137, 22.120180);
  //      MarkerOptions option = new MarkerOptions();
    //    option.position(pp).title("Kalamata");
      //  map.addMarker(option);
      //  map.getMaxZoomLevel(); //diko mou
      //  map.moveCamera(CameraUpdateFactory.newLatLng(pp));
      //  map.animateCamera(CameraUpdateFactory.newLatLng(pp));  //diko mou


  //      Toast.makeText(getActivity(), "test kalamata onMapready karfwto end", Toast.LENGTH_SHORT).show();


        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.040137, 22.120180), 22));




        //map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

          //  private int lengthLong;

//            @Override
  //          public void onMapClick(LatLng latLng) {

                // Creating a marker
    //            MarkerOptions markerOptions = new MarkerOptions();


                // Setting the position for the marker
      //          markerOptions.position(latLng);


                // Setting the title for the marker.
                // This will be displayed on taping the marker
        //        markerOptions.title(latLng.latitude + " : " + latLng.longitude);


                // Animating to the touched position
                //map.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                //map.addMarker(markerOptions);








                //if (counter == 0) {
                    //latnext = latLng.latitude;
                    //lngnext = latLng.longitude;



                //} else {
                  //  latprev = latnext;
                   // lngprev = lngnext;
                   // latnext = latLng.latitude;
                   // lngnext = latLng.longitude;


                //}

                //06-04-2018
                //mDatabase = FirebaseDatabase.getInstance().getReference();
                //mMessageReference = FirebaseDatabase.getInstance().getReference("message");
                //String temp_onoma;
                //String temp_child;

                //String temp_counter= String.valueOf(counter);
                //temp_onoma = "marker"+temp_counter;
                //temp_child= "user"+xristis;




                //mDatabase.child(temp_child).child(temp_onoma).child("lat").push().setValue(latnext)  ;  //08-04-18
                //mDatabase.child(temp_child).child(temp_onoma).child("log").push().setValue(lngnext)  ;    //08-04-18








                //ARXIKO ORIGINAL SOS XWRIS IF
                //if (counter >= 1)   //molis to evala  05-04-2018  palaia xwris if
                //{
                  //  Polyline line = map.addPolyline(new PolylineOptions()
//                            .add(new LatLng(latprev, lngprev), new LatLng(latnext, lngnext))
  //                          .width(5)
    //                        .color(Color.YELLOW));

      //          }

        //        ++counter;


          //  }
        //});



        // good luck 05 04 18
        // PROS GEORGE SKIKO  TO EXW KATARGHSEI , XRHSIMEYEI GIA LONG CLICK ON MAP KAI TRACE THE PATH WITH FINGER, NOT AYTOMATICALLY
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {


                //06-04-2018
                ++metritis_long_click;
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mMessageReference = FirebaseDatabase.getInstance().getReference("message");
                mDatabase.child("LONG CLICKS ").setValue(metritis_long_click);  //dokimi
                //06-04-2018


                Toast.makeText(getActivity(), "epitelous long click sto map ", Toast.LENGTH_SHORT).show();


                onMapLongClick1(v);
            }


            //06-04-18  good luck night
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public  void onMapLongClick1(View view) {


                Toast.makeText(getActivity(), "GOOD LUCK START long click sto map ", Toast.LENGTH_SHORT).show();
                final PopupMenu popupMenu;


                // sos low line no commments 09-04-2018
                PopupMenu popupMenupro = new PopupMenu(ContactUsFragment.super.getActivity(), view, Gravity.CENTER); // +GRAVITY CENTER ALLA TIPOTA 07-04-18


                popupMenupro.getMenuInflater().inflate(R.menu.problems_popup, popupMenupro.getMenu());



                popupMenupro.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //08-04-18 good luck
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mMessageReference = FirebaseDatabase.getInstance().getReference("message");
                        String temp_onoma;
                        String temp_child;
                        int reduced_counter;

                        //String temp_counter= String.valueOf(counter);
                        reduced_counter= counter-1;
                        String red_counter= String.valueOf(reduced_counter);  // add last counter type of abnormality in road
                        temp_onoma = "marker"+red_counter;
                        temp_child= "user"+xristis;

                        //08-04-18 good luck

                        switch (menuItem.getItemId()){
                            case  R.id.one:
                                Toast.makeText(ContactUsFragment.super.getActivity(), ""+"object", Toast.LENGTH_SHORT).show();
                                path_difficulty="object";   //ok work 08-04-18
                                mDatabase.child(temp_child).child(temp_onoma).child(path_difficulty).push().setValue("mikri"); //ayto tha kaleitai me long klick
                                return true;
                            case R.id.two:
                                Toast.makeText(ContactUsFragment.super.getActivity(), ""+"no ramp", Toast.LENGTH_SHORT).show();
                                path_difficulty="no ramp";  //ok work 08-04-18
                                mDatabase.child(temp_child).child(temp_onoma).child(path_difficulty).push().setValue("mikri"); //ayto tha kaleitai me long klick
                                return true;
                            case R.id.three:
                                Toast.makeText(ContactUsFragment.super.getActivity(), ""+"surface", Toast.LENGTH_SHORT).show();
                                path_difficulty="surface abnormal";   //ok work 08-04-18
                                mDatabase.child(temp_child).child(temp_onoma).child(path_difficulty).push().setValue("mikri"); //ayto tha kaleitai me long klick
                                return true;

                            case R.id.four:
                                Toast.makeText(ContactUsFragment.super.getActivity(), ""+"premature end", Toast.LENGTH_SHORT).show();
                                path_difficulty="premature end";   //ok work 08-04-18
                                mDatabase.child(temp_child).child(temp_onoma).child(path_difficulty).push().setValue("mikri"); //ayto tha kaleitai me long klick
                                return true;
                        }



                        return true;

                    }



                });


                popupMenupro.show();

                Toast.makeText(getActivity(), "GOOD LUCK END long click sto map ", Toast.LENGTH_SHORT).show();




            }
            //06-04-18  good luck night  END


        });
        // end good luck 05 04 18





    }
}
