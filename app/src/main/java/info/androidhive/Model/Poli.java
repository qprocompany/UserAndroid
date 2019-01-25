package info.androidhive.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poli {

    @SerializedName("ServiceUnitName")
    @Expose
    private String serviceUnitName;
    @SerializedName("IconFileName")
    @Expose
    private String iconFileName;
    @SerializedName("ServiceUnitCode")
    @Expose
    private String serviceUnitCode;
    @SerializedName("ServiceUnitID")
    @Expose
    private Integer serviceUnitID;

    public String getServiceUnitName() {
        return serviceUnitName;
    }

    public void setServiceUnitName(String serviceUnitName) {
        this.serviceUnitName = serviceUnitName;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public void setIconFileName(String iconFileName) {
        this.iconFileName = iconFileName;
    }

    public String getServiceUnitCode() {
        return serviceUnitCode;
    }

    public void setServiceUnitCode(String serviceUnitCode) {
        this.serviceUnitCode = serviceUnitCode;
    }

    public Integer getServiceUnitID() {
        return serviceUnitID;
    }

    public void setServiceUnitID(Integer serviceUnitID) {
        this.serviceUnitID = serviceUnitID;
    }
}
