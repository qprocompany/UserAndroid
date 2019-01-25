package info.androidhive.Model;

/**
 * Created by ravi on 16/11/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bed {
    @SerializedName("ClassName")
    @Expose
    private String className;
    @SerializedName("ClassCode")
    @Expose
    private Integer classCode;
    @SerializedName("ClassCode1")
    @Expose
    private String classCode1;
    @SerializedName("PatientPerRoomQty")
    @Expose
    private Integer patientPerRoomQty;
    @SerializedName("IsHasAC")
    @Expose
    private Boolean isHasAC;
    @SerializedName("IsHasPrivateBathRoom")
    @Expose
    private Object isHasPrivateBathRoom;
    @SerializedName("IsHasRefrigerator")
    @Expose
    private Object isHasRefrigerator;
    @SerializedName("IsHasTV")
    @Expose
    private Boolean isHasTV;
    @SerializedName("IsHasWifi")
    @Expose
    private Object isHasWifi;
    @SerializedName("Remarks")
    @Expose
    private Object remarks;
    @SerializedName("PictureFileName")
    @Expose
    private String pictureFileName;
    @SerializedName("DisplayPrice")
    @Expose
    private Object displayPrice;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

    public String getClassCode1() {
        return classCode1;
    }

    public void setClassCode1(String classCode1) {
        this.classCode1 = classCode1;
    }

    public Integer getPatientPerRoomQty() {
        return patientPerRoomQty;
    }

    public void setPatientPerRoomQty(Integer patientPerRoomQty) {
        this.patientPerRoomQty = patientPerRoomQty;
    }

    public Boolean getIsHasAC() {
        return isHasAC;
    }

    public void setIsHasAC(Boolean isHasAC) {
        this.isHasAC = isHasAC;
    }

    public Object getIsHasPrivateBathRoom() {
        return isHasPrivateBathRoom;
    }

    public void setIsHasPrivateBathRoom(Object isHasPrivateBathRoom) {
        this.isHasPrivateBathRoom = isHasPrivateBathRoom;
    }

    public Object getIsHasRefrigerator() {
        return isHasRefrigerator;
    }

    public void setIsHasRefrigerator(Object isHasRefrigerator) {
        this.isHasRefrigerator = isHasRefrigerator;
    }

    public Boolean getIsHasTV() {
        return isHasTV;
    }

    public void setIsHasTV(Boolean isHasTV) {
        this.isHasTV = isHasTV;
    }

    public Object getIsHasWifi() {
        return isHasWifi;
    }

    public void setIsHasWifi(Object isHasWifi) {
        this.isHasWifi = isHasWifi;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public Object getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(Object displayPrice) {
        this.displayPrice = displayPrice;
    }
}
