package com.example.u1tema5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MiMapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowLongClickListener {
    GoogleMap mapa;
    LatLng ubicacion;
    ArrayList<Marker> markers=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_mapa);
// Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        ubicacion = new LatLng(-18.013766, -70.255331); //Nos ubicamos en la UNJBG
        mapa.addMarker(new MarkerOptions().position(ubicacion).title("Marcador Tacna"));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        mapa.setOnMapClickListener(this);

        mapa.setOnInfoWindowLongClickListener(this);
    }
    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions()
                .position(mapa.getCameraPosition().target)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .draggable(true));
    }
    @Override public void onMapClick(LatLng puntoPulsado) {
        markers.add(mapa.addMarker(new MarkerOptions()
                .position(puntoPulsado)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.markermap))
                .title("Marker onMapClick")
                .snippet("Este marker es producto del evento de pulsar en el mapa")));
    }
    public void eliminar(View view) {
        if(markers!=null && markers.size()!=0 )
            markers.get(markers.size()-1).remove();
            markers.remove(markers.size()-1);
    }


    @Override
    public void onInfoWindowLongClick(Marker marker) {
                marker.remove();
            }

}
