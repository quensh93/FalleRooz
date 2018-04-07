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
@Entity(tableName = "type2db")
public class Type2Db extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "cat_id")
    private int CatId;

    @ColumnInfo(name = "description")
    private String Description;

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        Description = description;
        notifyPropertyChanged(BR.description);
    }

    public void setCatId(int catId) {
        CatId = catId;
    }
    @Bindable
    public String getDescription() {
        return Description;
    }

    public long getId() {
        return id;
    }

    public int getCatId() {
        return CatId;
    }

}
