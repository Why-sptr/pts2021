package com.example.ptswahyu33;

import android.util.Log;

import com.example.ptswahyu33.DataModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // untuk menyimpan data
    public void Save(final DataModel dataModel){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(DataModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    dataModel.setId(nextId);
                    realm.copyToRealm(dataModel);

                }
                else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }


    // untuk memanggil semua data
    public List<DataModel> getAllMahasiswa(){
        RealmResults<DataModel> results = realm.where(DataModel.class).findAll();
        return results;
    }


    // untuk menghapus data
    public void Delete(int id){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<DataModel> model = realm.where(DataModel.class).equalTo("id", id).findAll();
                model.deleteFromRealm(0);
            }
        });
    }
}

