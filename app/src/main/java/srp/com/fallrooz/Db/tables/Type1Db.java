package srp.com.fallrooz.Db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import srp.com.fallrooz.BR;

/**
 * Created by s.rahmanipour on 1/9/2018.
 */

@Entity(tableName = "type1db")
public class Type1Db extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "description")
    private String Description;
    @ColumnInfo(name = "cat_id")
    private int CatId;
    @ColumnInfo(name = "model")
    private int Model;
    @ColumnInfo(name = "gender")
    private int Gender;

    public int getCatId() {
        return CatId;
    }

    public int getGender() {
        return Gender;
    }

    public int getModel() {
        return Model;
    }

    public long getId() {
        return id;
    }

    @Bindable
    public String getDescription() {
        return Description;
    }

    public void setCatId(int catId) {
        CatId = catId;
    }

    public void setDescription(String description) {
        Description = description;
        notifyPropertyChanged(BR.description);
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setModel(int model) {
        Model = model;
    }

}
