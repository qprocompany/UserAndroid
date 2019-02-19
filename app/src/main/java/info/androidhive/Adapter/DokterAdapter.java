package info.androidhive.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.Model.Dokter;
import info.androidhive.recyclerviewsearch.R;

/**
 * Created by ydenn on 8/9/2018.
 */

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Dokter> DokterList;
    private List<Dokter> DokterListFiltered;
    private ContactsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone,day;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            day = view.findViewById(R.id.days);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(DokterListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public DokterAdapter(Context context, List<Dokter> DokterList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.DokterList = DokterList;
        this.DokterListFiltered = DokterList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Dokter Dokter = DokterListFiltered.get(position);
        holder.name.setText(Dokter.getParamedicName());
        holder.phone.setText(Dokter.getServiceUnitName());
        holder.day.setText(Dokter.getDays() + " " + Dokter.getTimes());
        Glide.with(context)
                .load(Dokter.getPictureFileName())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return DokterListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    DokterListFiltered = DokterList;
                } else {
                    List<Dokter> filteredList = new ArrayList<>();
                    for (Dokter row : DokterList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getParamedicName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    DokterListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = DokterListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                DokterListFiltered = (ArrayList<Dokter>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Dokter Dokter);
    }
}
//test edit denny