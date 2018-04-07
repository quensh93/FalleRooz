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
@Entity(tableName = "type3db")
public class Type3Db extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "cat_id")
    private int CatId;
    @ColumnInfo(name = "min")
    private int Min;
    @ColumnInfo(name = "max")
    private int Max;
    @ColumnInfo(name = "gender")
    private String Gender;
    @ColumnInfo(name = "description")
    private String Description;

    public int getCatId() {
        return CatId;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getMax() {
        return Max;
    }

    public int getMin() {
        return Min;
    }

    public String getGender() {
        return Gender;
    }

    public void setMax(int max) {
        Max = max;
    }

    public void setMin(int min) {
        Min = min;
    }

}
