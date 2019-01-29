package info.androidhive.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import info.androidhive.recyclerviewsearch.R;

public class BaseCalendarListAdapter <T extends BaseCalendarItemModel> extends BaseAdapter {
    protected Context mContext;
    //key:date("yyyy-MM-dddd"),value: you custom CalendarItemModel must extend BaseCalendarItemModel
    protected TreeMap<String, T> dayModelList = new TreeMap<>();
    //list to keep dayModelList's key that convenient for get key by index.
    protected List<String> indexToTimeMap = new ArrayList<>();
//test update
    public TreeMap<String, T> getDayModelList() {
        return dayModelList;
    }

    public void setDayModelList(TreeMap<String, T> dayModelList) {
        this.dayModelList = dayModelList;
        indexToTimeMap.clear();
        for (String time : this.dayModelList.keySet()) {
            indexToTimeMap.add(time);
        }
    }

    public List<String> getIndexToTimeMap() {
        return indexToTimeMap;
    }

    @Override
    public int getCount() {
        return dayModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //default calendar item view，We are appreciate of you override this function to custom your View items.
    public View getView(String date, T model, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.user_row_jadwal, null);
        }
        //TextView dayNum = (TextView) view.findViewById(R.id.tv_day_num);
        //dayNum.setText(model.getDayNumber());


        //view.setBackgroundResource(R.drawable.bg_shape_calendar_item_normal);

        //if (model.getStatus() == BaseCalendarItemModel.Status.DISABLE) {
        //  dayNum.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        //}

        //if (!model.isCurrentMonth()) {
        //  dayNum.setTextColor(mContext.getResources().getColor(R.color.gray_bbbbbb));
        // view.setClickable(true);
        //}

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String date = indexToTimeMap.get(position);
        View view = getView(date, dayModelList.get(date), convertView, parent);
        // GridView.LayoutParams layoutParams = new GridView.LayoutParams(CalendarView.mItemWidth, CalendarView.mItemHeight);
        // view.setLayoutParams(layoutParams);
        return view;
    }


}