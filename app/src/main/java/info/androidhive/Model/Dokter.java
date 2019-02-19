package info.androidhive.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ydenn on 7/27/2018.
 */

public class Dokter {
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
    @SerializedName("WorkStationCode")
    @Expose
    private String workStationCode;
    @SerializedName("Days")
    @Expose
    private String days;
    @SerializedName("Times")
    @Expose
    private String times;

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

    public String getWorkStationCode() {
        return workStationCode;
    }

    public void setWorkStationCode(String workStationCode) {
        this.workStationCode = workStationCode;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

}
