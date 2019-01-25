package info.androidhive.response;

/**
 * Created by ydenn on 8/16/2018.
 */

import com.google.gson.annotations.SerializedName;

public class OverviewPolyline{

    @SerializedName("points")
    private String points;

    public void setPoints(String points){
        this.points = points;
    }

    public String getPoints(){
        return points;
    }

    @Override
    public String toString(){
        return
                "OverviewPolyline{" +
                        "points = '" + points + '\'' +
                        "}";
    }
}