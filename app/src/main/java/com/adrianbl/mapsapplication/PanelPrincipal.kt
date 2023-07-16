package com.adrianbl.mapsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrianbl.mapsapplication.databinding.ActivityMapsBinding
import com.adrianbl.mapsapplication.databinding.ActivityPanelPrincipalBinding

class PanelPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPanelPrincipalBinding

    private lateinit var adapter: MyAdapter
    private val baresCubles = mutableListOf<BaresClubes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanelPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIrMapa.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        baresCubles.add(
            BaresClubes(
                "BAR BARECITO",
                19.94549471,
                -96.85168242
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR CENTRO BOTANERO EL CENTARIO",
                19.92974498,
                -96.8537597
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR CLIMA",
                19.92858363,
                -96.84999181
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR DOS HERMANOS",
                19.92431576,
                -96.85695598
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR EL GATO NEGRO",
                19.92347404,
                -96.85864603
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR EL PATIO",
                19.92304699,
                -96.85426504
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR EL SESTEO DE LAS AGUILAS",
                19.93739192,
                -96.85003448
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR EMPERADOR",
                19.92651621,
                -96.85454088
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR GARCIS",
                19.92542172,
                -96.85750936
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR JUMBO",
                19.92538877,
                -96.85697468
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA BARRA DE DANTE",
                19.93012421,
                -96.85276356
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA CASA DE LA SEÑORA DE LAS PLANTAS",
                19.920365837133392,
                -96.85823830555884
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA CATRINA",
                19.93000678,
                -96.84961932
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA CUEVA",
                19.9307954,
                -96.8493769
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA CHENTA",
                19.921614085889736,
                -96.86226161909204
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA JAROCHA",
                19.92880431,
                -96.85355029
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA NUEVA MISANTECA",
                19.93020844,
                -96.8495968
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR LA SIRENITA",
                19.92681269,
                -96.85241676
            )
        )
        baresCubles.add(
            BaresClubes(
                "BAR MARY",
                19.9295132,
                -96.84967294
            )
        )
        baresCubles.add(
            BaresClubes(
                "BARES, CANTINAS Y SIMILARES",
                19.93144367,
                -96.8487903
            )
        )
        baresCubles.add(
            BaresClubes(
                "BODEGA AUXILIAR DE VINOS Y LICORES",
                19.92538877,
                -96.85697468
            )
        )
        baresCubles.add(
            BaresClubes(
                "BOTANERO LOS COCOS",
                19.91950348,
                -96.86044087
            )
        )
        baresCubles.add(
            BaresClubes(
                "BOTANERO D'FILI",
                19.9462378033694,
                -96.85061873815745
            )
        )
        baresCubles.add(
            BaresClubes(
                "CENTRO BOTANERO SIN NOMBRE",
                19.92478609,
                -96.85494674
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA EL REFUGIO DE LA QUETA",
                19.9267405,
                -96.85390018
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA JACIVE",
                19.92706836,
                -96.84079529
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERÍA LOS CERRITOS",
                19.94438675,
                -96.84890846
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA MEMOS",
                19.92652592,
                -96.85445044
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA MEMOS",
                19.92652592,
                -96.85445044
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA MORELOS",
                19.9232807,
                -96.85415998
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA ROMADHI",
                19.9442837858024,
                -96.85012328832784
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA SIN NOMBRE",
                19.93746933,
                -96.85134989
            )
        )
        baresCubles.add(
            BaresClubes(
                "CERVECERIA SIN NOMBRE",
                19.92347453,
                -96.85888094
            )
        )
        baresCubles.add(
            BaresClubes(
                "CHELOS BAR",
                19.92506305,
                -96.85755458
            )
        )
        baresCubles.add(
            BaresClubes(
                "COFFE BAR LA SOPA",
                19.92581066,
                -96.85434734
            )
        )
        baresCubles.add(
            BaresClubes(
                "DISCOTHEQUE XANAT",
                19.92791185,
                -96.85494739
            )
        )
        baresCubles.add(
            BaresClubes(
                "EL BAR DEL PUEBLO",
                19.931305015204657,
                -96.84891611337663
            )
        )
        baresCubles.add(
            BaresClubes(
                "ICE CLUB",
                19.92938289,
                -96.84958032
            )
        )
        baresCubles.add(
            BaresClubes(
                "MERENDERO LOS REYES",
                19.93030139,
                -96.84825156
            )
        )

        adapter = MyAdapter(baresCubles)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}