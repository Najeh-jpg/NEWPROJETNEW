package com.example.android.recycleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recycleview.Adapter.MyAdapter;
import com.example.android.recycleview.Model.Listitem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Listitem> listitems;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerviewID);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();


        Listitem item1 =new Listitem("Offre de cheveux","Soin de cheveux:Proteine,Collagène,Caviar...","Réduction jusqu'à 70%", R.drawable.cheveux);
        Listitem item2 =new Listitem("Offre de coloration","coloration cheveux sans Ammoniac","Réduction jusqu'à 70%", R.drawable.coloration);
        Listitem item3 =new Listitem("Offre de Soin de visage","Soin de visage Spécifique","Réduction jusqu'à 50%", R.drawable.soin_visage);
        Listitem item4 =new Listitem("Offre de gel et faux ongle ","Gel et Capsules","Réduction jusqu'à 70%", R.drawable.gel);
        Listitem item5 =new Listitem("Offre de pédicure spécifique et médical","Soin profond et soin anti-champignon","Réduction jusqu'à 50%", R.drawable.pedecure);
        Listitem item6 =new Listitem("Offre SPA","Relaxation et soin hydrothérapeutique","Réduction jusqu'à 50%", R.drawable.spa);
        Listitem item7 =new Listitem("Offre de Epilation","Epilation à cire et à Laser","Réduction jusqu'à 70%", R.drawable.epil);
        Listitem item8 =new Listitem("Offre d'abonnement de sport","Abonnement de 3 mois et 6 mois","Réduction jusqu'à 50%", R.drawable.sport);
        Listitem item9=new Listitem("Offre d'abonnement de piscine","Accès piscine,Aquagym,Aquabike","Réduction jusqu'à 50%",R.drawable.pisyne);
        Listitem item10=new Listitem("Chirurgie esthétique","Correction facial et corporel","Réduction jusqu'à 30%", R.drawable.chirgi_est);
        Listitem item11=new Listitem("Esthétique dentaire","Blanchissement de dents et correction","Réduction jusqu'à 30%", R.drawable.esthetique_dent);
        Listitem item12=new Listitem("PRP cheveux "," La pousse du cheveux par injection","Réduction jusqu'à 30%", R.drawable.cheveuxest);
        Listitem item13=new Listitem("Extention de cils"," Sublimez votre regard avec une extension cil à cil en soie","Réduction jusqu'à 30%", R.drawable.cils);



        listitems.add(item1);
        listitems.add(item2);
        listitems.add(item3);
        listitems.add(item4);
        listitems.add(item5);
        listitems.add(item6);
        listitems.add(item7);
        listitems.add(item8);
        listitems.add(item9);
        listitems.add(item10);
        listitems.add(item11);
        listitems.add(item12);
        listitems.add(item13);

        adapter = new MyAdapter(this, listitems);
        recyclerView.setAdapter(adapter);



        // for (int x=1;x<12;x++){
        //    Listitem listitem= new Listitem(
        //           "OFFRE"+(x+1),"Details","70%"

        //   );
        //Listitems.add(listitem);}


    }
}
