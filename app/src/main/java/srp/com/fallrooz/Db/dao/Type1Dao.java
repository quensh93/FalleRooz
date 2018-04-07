package srp.com.fallrooz.Db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import srp.com.fallrooz.Db.tables.Type1Db;
import srp.com.fallrooz.Db.tables.Type2Db;
import srp.com.fallrooz.Db.tables.Type3Db;
import srp.com.fallrooz.Db.tables.UinfoDb;

/**
 * Created by s.rahmanipour on 4/3/2018.
 */

@Dao
public interface Type1Dao {
    @Query("SELECT * FROM type1db")
    List<Type1Db> getAll();


    @Query("SELECT COUNT(*) from type1db")
    int countType1();
    @Query("SELECT COUNT(*) from type2db")
    int countType2();
    @Query("SELECT COUNT(*) from type3db")
    int countType3();
    @Query("SELECT COUNT(*) from uinfo")
    int countUinfo();

    @Update
    void update(UinfoDb... uinfoDbs);

    //@Query("SELECT * FROM type1db where cat_id LIKE  :cat_id AND model LIKE :model")
    @Query("SELECT * FROM type1db where cat_id = :cat_id AND model = :model LIMIT 1")
    Type1Db findByCatIdAndModel(int cat_id, int model);

    @Query("SELECT * FROM type1db where cat_id = :cat_id AND model = :model AND gender = :gender LIMIT 1")
    Type1Db findByCatIdAndModelAndGender(int cat_id, int model, int gender);

    @Query("SELECT * FROM type2db where cat_id = :cat_id LIMIT 1")
    Type2Db findByCatId(int cat_id);

    @Query("SELECT * FROM type3db where cat_id = :cat_id AND min <= :reng AND max >= :reng LIMIT 1")
    Type3Db findByCatIdAndRenge(int cat_id,int reng);

    @Query("SELECT * FROM type3db where cat_id = :cat_id AND min <= :reng1 AND max >= :reng2 LIMIT 1")
    Type3Db findByCatIdAndRenge(int cat_id,int reng1,int reng2);

    @Query("SELECT * FROM uinfo LIMIT 1")
    UinfoDb getUinfo();



    @Insert
    void insertAllType1(Type1Db... type1Dbs);
    @Insert
    void insertAllType2(Type2Db... type2Dbs);
    @Insert
    void insertAllType3(Type3Db... type3Dbs);
    @Insert
    void insertUinfo(UinfoDb... uinfoDbs);
}
