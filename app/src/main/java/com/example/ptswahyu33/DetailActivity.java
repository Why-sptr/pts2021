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
import com.example.ptswahyu33.ModelSports;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView tvName;
    private TextView tvDeskripsi;
    private ImageView imageView;
    private Button btnSimpan;

    private String image;
    private String nama;
    private String deskripsi;

    private Realm realm;
    private RealmHelper realmHelper;
    private DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageDetail);
        tvName = findViewById(R.id.tvNamaDetail);
        tvDeskripsi = findViewById(R.id.tvDeskripsiDetail);
        btnSimpan = findViewById(R.id.btnButtonSimpan);

        bundle = getIntent().getExtras();
        if (bundle != null){
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

        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnSimpan)){
            Toast.makeText(getApplicationContext(), "Berhasil disimpan!", Toast.LENGTH_SHORT).show();

            bundle = getIntent().getExtras();
            if (bundle != null){
                image = bundle.getString("image");
                nama = bundle.getString("nama");
                deskripsi = bundle.getString("deskripsi");
            }

            dataModel = new DataModel();
            dataModel.setImage(image);
            dataModel.setNama(nama);
            dataModel.setDeskripsi(deskripsi);

            realmHelper = new RealmHelper(realm);
            realmHelper.Save(dataModel);
        }
    }
}