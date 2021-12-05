package com.example.ptswahyu33;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ptswahyu33.FavoriteAdapter;
import com.example.ptswahyu33.RealmHelper;
import com.example.ptswahyu33.DataModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<DataModel> dataModelList;

    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_favorite);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;


                    case R.id.nav_favorite:


                }
            }
        });


        recyclerView = findViewById(R.id.recyclerviewFavorite);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        dataModelList = new ArrayList<>();

        dataModelList= realmHelper.getAllMahasiswa();

        show();


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        favoriteAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        favoriteAdapter = new FavoriteAdapter(dataModelList, new FavoriteAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailFavoriteActivity.class);
                intent.putExtra("id", dataModelList.get(position).getId());
                intent.putExtra("image", dataModelList.get(position).getImage());
                intent.putExtra("nama", dataModelList.get(position).getNama());
                intent.putExtra("deskripsi", dataModelList.get(position).getDeskripsi());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(favoriteAdapter);
    }
}