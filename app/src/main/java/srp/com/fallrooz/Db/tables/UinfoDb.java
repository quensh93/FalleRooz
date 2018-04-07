package srp.com.fallrooz.Db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import srp.com.fallrooz.BR;


/**
 * Created by s.rahmanipour on 1/28/2018.
 */
@Entity(tableName = "uinfo")
public class UinfoDb extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "mobile")
    private String Mobile;
    @ColumnInfo(name = "is_valid")
    private Boolean IsValid;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Boolean getIsValid() {
        return IsValid;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    @Bindable
    public void setIsValid(Boolean valid) {
        IsValid = valid;
        notifyPropertyChanged(BR.isValid);
    }


}
