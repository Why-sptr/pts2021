package com.example.ptswahyu33;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptswahyu33.RealmHelper;
import com.example.ptswahyu33.DataModel;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFavoriteActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView tvName;
    private TextView tvDeskripsi;
    private ImageView imageView;
    private Button btnHapus;

    private String image;
    private String nama;
    private String deskripsi;
    private int id;

    private Realm realm;
    private RealmHelper realmHelper;
    private DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);


        imageView = findViewById(R.id.imageDetailFavorite);
        tvName = findViewById(R.id.tvNamaDetailFavorite);
        tvDeskripsi = findViewById(R.id.tvDeskripsiDetailFavorite);
        btnHapus = findViewById(R.id.btnButtonHapusFavorite);

        bundle = getIntent().getExtras();
        if (bundle != null){
            id = bundle.getInt("id", 0);
            image = bundle.getString("image");
            nama = bundle.getString("nama");
            deskripsi = bundle.getString("deskripsi");
        }

        tvName.setText(nama);
        tvDeskripsi.setText(deskripsi);
        Picasso.get()
                .load(image)
                .into(imageView);


        //Set up Realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        btnHapus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnHapus)){
            realmHelper.Delete(id);
            Toast.makeText(DetailFavoriteActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}