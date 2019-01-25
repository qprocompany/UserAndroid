package info.androidhive.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ydenn on 8/9/2018.
 */

public class Jadwal {
    @SerializedName("keyField")
    @Expose
    private String keyField;
    @SerializedName("ServiceUnitName")
    @Expose
    private String serviceUnitName;
    @SerializedName("ParamedicName")
    @Expose
    private String paramedicName;
    @SerializedName("PictureFileName")
    @Expose
    private String pictureFileName;
    @SerializedName("ParamedicID")
    @Expose
    private Integer paramedicID;
    @SerializedName("ServiceUnitID")
    @Expose
    private Integer serviceUnitID;
    @SerializedName("CalenderDate")
    @Expose
    private String calenderDate;

    public Jadwal(String image, String name, String services) {
        this.pictureFileName = image;
        this.paramedicName = name;
        this.serviceUnitName = services;
    }

    public String getKeyField() {
        return keyField;
    }

    public void setKeyField(String keyField) {
        this.keyField = keyField;
    }

    public String getServiceUnitName() {
        return serviceUnitName;
    }

    public void setServiceUnitName(String serviceUnitName) {
        this.serviceUnitName = serviceUnitName;
    }

    public String getParamedicName() {
        return paramedicName;
    }

    public void setParamedicName(String paramedicName) {
        this.paramedicName = paramedicName;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public Integer getParamedicID() {
        return paramedicID;
    }

    public void setParamedicID(Integer paramedicID) {
        this.paramedicID = paramedicID;
    }

    public Integer getServiceUnitID() {
        return serviceUnitID;
    }

    public void setServiceUnitID(Integer serviceUnitID) {
        this.serviceUnitID = serviceUnitID;
    }

    public String getCalenderDate() {
        return calenderDate;
    }

    public void setCalenderDate(String calenderDate) {
        this.calenderDate = calenderDate;
    }

}