package com.adrianbl.mapsapplication

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.adrianbl.mapsapplication.databinding.ActivityMapsBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.TravelMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope

private const val LOCATION_PERMISSION_REQUEST_CODE = 2000
private const val DEFAULT_MAP_SCALE = 13.0f

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val baresClubes = mutableListOf<BaresClubes>()
    private lateinit var beerIcon: BitmapDescriptor
    private val userLocation = Location("")

    var poly: Polyline? = null

    private val DIRECTIONS_API_KEY = "TU_API"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            if(mMap.mapType == GoogleMap.MAP_TYPE_NORMAL){
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            } else {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }

        baresClubes.add(
            BaresClubes(
                "BAR BARECITO",
                19.94549471,
                -96.85168242
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR CENTRO BOTANERO EL CENTARIO",
                19.92974498,
                -96.8537597
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR CLIMA",
                19.92858363,
                -96.84999181
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR DOS HERMANOS",
                19.92431576,
                -96.85695598
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR EL GATO NEGRO",
                19.92347404,
                -96.85864603
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR EL PATIO",
                19.92304699,
                -96.85426504
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR EL SESTEO DE LAS AGUILAS",
                19.93739192,
                -96.85003448
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR EMPERADOR",
                19.92651621,
                -96.85454088
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR GARCIS",
                19.92542172,
                -96.85750936
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR JUMBO",
                19.92538877,
                -96.85697468
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA BARRA DE DANTE",
                19.93012421,
                -96.85276356
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA CASA DE LA SEÑORA DE LAS PLANTAS",
                19.920365837133392,
                -96.85823830555884
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA CATRINA",
                19.93000678,
                -96.84961932
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA CUEVA",
                19.9307954,
                -96.8493769
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA CHENTA",
                19.921614085889736,
                -96.86226161909204
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA JAROCHA",
                19.92880431,
                -96.85355029
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA NUEVA MISANTECA",
                19.93020844,
                -96.8495968
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR LA SIRENITA",
                19.92681269,
                -96.85241676
            )
        )
        baresClubes.add(
            BaresClubes(
                "BAR MARY",
                19.9295132,
                -96.84967294
            )
        )
        baresClubes.add(
            BaresClubes(
                "BARES, CANTINAS Y SIMILARES",
                19.93144367,
                -96.8487903
            )
        )
        baresClubes.add(
            BaresClubes(
                "BODEGA AUXILIAR DE VINOS Y LICORES",
                19.92538877,
                -96.85697468
            )
        )
        baresClubes.add(
            BaresClubes(
                "BOTANERO LOS COCOS",
                19.91950348,
                -96.86044087
            )
        )
        baresClubes.add(
            BaresClubes(
                "BOTANERO D'FILI",
                19.9462378033694,
                -96.85061873815745
            )
        )
        baresClubes.add(
            BaresClubes(
                "CENTRO BOTANERO SIN NOMBRE",
                19.92478609,
                -96.85494674
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA EL REFUGIO DE LA QUETA",
                19.9267405,
                -96.85390018
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA JACIVE",
                19.92706836,
                -96.84079529
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERÍA LOS CERRITOS",
                19.94438675,
                -96.84890846
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA MEMOS",
                19.92652592,
                -96.85445044
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA MEMOS",
                19.92652592,
                -96.85445044
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA MORELOS",
                19.9232807,
                -96.85415998
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA ROMADHI",
                19.9442837858024,
                -96.85012328832784
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA SIN NOMBRE",
                19.93746933,
                -96.85134989
            )
        )
        baresClubes.add(
            BaresClubes(
                "CERVECERIA SIN NOMBRE",
                19.92347453,
                -96.85888094
            )
        )
        baresClubes.add(
            BaresClubes(
                "CHELOS BAR",
                19.92506305,
                -96.85755458
            )
        )
        baresClubes.add(
            BaresClubes(
                "COFFE BAR LA SOPA",
                19.92581066,
                -96.85434734
            )
        )
        baresClubes.add(
            BaresClubes(
                "DISCOTHEQUE XANAT",
                19.92791185,
                -96.85494739
            )
        )
        baresClubes.add(
            BaresClubes(
                "EL BAR DEL PUEBLO",
                19.931305015204657,
                -96.84891611337663
            )
        )
        baresClubes.add(
            BaresClubes(
                "ICE CLUB",
                19.92938289,
                -96.84958032
            )
        )
        baresClubes.add(
            BaresClubes(
                "MERENDERO LOS REYES",
                19.93030139,
                -96.84825156
            )
        )

        beerIcon = getBeerIcon()

        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getUserLocation()
            } else {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            }
        } else {
            getUserLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                userLocation.latitude = location.latitude
                userLocation.longitude = location.longitude
                setupMap()
            }
        }
    }

    private fun setupMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getUserLocation()
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                showLocationPermissionRationaleDialog()
            } else {
                finish()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showLocationPermissionRationaleDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.need_location_permission_dialog_title)
            .setMessage(R.string.need_location_permission_dialog_message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            }.setNegativeButton(R.string.no) { _, _ ->
                finish()
            }
        dialog.show()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val userLatLng = LatLng(userLocation.latitude, userLocation.longitude)
        val userMarker = MarkerOptions().position(userLatLng)
        mMap.addMarker(userMarker)

        for (baresClubes in baresClubes) {
            val barclubPosition = LatLng(baresClubes.latitud, baresClubes.longitude)
            val barclubLocation = Location("")

            barclubLocation.latitude = baresClubes.latitud
            barclubLocation.longitude = baresClubes.longitude

            val distanceToIcon = barclubLocation.distanceTo(userLocation)

            val barclubMarkerOptions = MarkerOptions()
                .icon(beerIcon)
                .position(barclubPosition)
                .title(baresClubes.name)
                .snippet(getString(R.string.distance_to_format, distanceToIcon))
            mMap.addMarker(barclubMarkerOptions)
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, DEFAULT_MAP_SCALE))

        mMap.setOnMarkerClickListener { marker ->
            // Se itera sobre los objetos BaresClubes en la lista
            for (baresClubes in baresClubes) {
                // Crear un objeto LatLng para las coordenadas del objeto BaresClubes
                val baresClubesLatLng = LatLng(baresClubes.latitud, baresClubes.longitude)

                // Se compara las coordenadas del punto seleccionado con las coordenadas del objeto BaresClubes
                if (marker.position == baresClubesLatLng) {
                    // El punto seleccionado corresponde a uno de los puntos en la lista

                    // Se obtiene el objeto BaresClubes seleccionado
                    val selectedBarclub = baresClubes

                    // Se establece el punto de Destino con las coordenadas del punto seleccionado
                    val destination = LatLng(selectedBarclub.latitud, selectedBarclub.longitude)
                    getDirectionsToLocation(destination)

                    // Se cierra el bucle, ya que se encontró una coincidencia
                    break
                }
            }

            // Se devuelve false para permitir que el evento OnMarkerClickListener predeterminado
            // maneje las acciones del marcador
            false
        }

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(19.950462590967263, -96.84476405382158)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun getBeerIcon(): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(this, R.drawable.beer)
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth ?: 0,
            drawable?.intrinsicHeight ?: 0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable?.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    // Se genera la ruta y el trazo de las lineas
    private fun getDirectionsToLocation(destination: LatLng) {
        val origin = "${userLocation.latitude},${userLocation.longitude}"
        val destinationString = "${destination.latitude},${destination.longitude}"

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val context = GeoApiContext.Builder()
                    .apiKey(DIRECTIONS_API_KEY)
                    .build()

                val directionsResult = DirectionsApi.newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin(origin)
                    .destination(destinationString)
                    .await()

                if (directionsResult.routes.isNotEmpty()) {
                    val route = directionsResult.routes[0]
                    val points = route.overviewPolyline.decodePath()

                    val polylineOptions = PolylineOptions()
                        .addAll(points.map { LatLng(it.lat, it.lng) })
                        .color(Color.RED)
                        .width(5f)

                    if (poly != null) {
                        poly!!.remove()
                    }
                    poly = mMap.addPolyline(polylineOptions)
                } else {
                    Toast.makeText(this@MapsActivity, "No se encontró una ruta", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MapsActivity, "Error al obtener las direcciones: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e(TAG,"Error al obtener las direcciones: ${e.message}")
            }
        }
    }
}