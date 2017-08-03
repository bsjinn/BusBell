package com.example.sojin.busbellapp.db;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by sungmokang on 2017. 8. 3..
 */

public class Migration implements RealmMigration {
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        return obj instanceof Migration;
    }

    @Override
    public int hashCode() {
        return Migration.class.hashCode();
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if(oldVersion == 1){
            RealmObjectSchema realmObjectSchema = schema.get("Favorite");

            realmObjectSchema.addField("depart_station_seq",String.class);
            realmObjectSchema.removeField("arrive_station_seq");

            oldVersion++;
        }
    }
}
