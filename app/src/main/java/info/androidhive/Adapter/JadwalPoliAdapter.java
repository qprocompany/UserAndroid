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

import info.androidhive.Model.Poli;
import info.androidhive.recyclerviewsearch.R;

public class JadwalPoliAdapter extends RecyclerView.Adapter<JadwalPoliAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Poli> PoliList;
    private List<Poli> PoliListFiltered;
    private ContactsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.poliname);
            thumbnail = view.findViewById(R.id.poliimage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(PoliListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public JadwalPoliAdapter(Context context, List<Poli> DokterList, JadwalPoliAdapter.ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.PoliList = DokterList;
        this.PoliListFiltered = DokterList;
    }

    @Override
    public JadwalPoliAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_jadwal_poli, parent, false);

        return new JadwalPoliAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JadwalPoliAdapter.MyViewHolder holder, final int position) {
        final Poli poli = PoliListFiltered.get(position);
        holder.name.setText(poli.getServiceUnitName());
        if(poli.getIconFileName() != null && poli.getIconFileName() != "") {
            Glide.with(context)
                    .load(poli.getIconFileName())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.thumbnail);
        }
        else
        {
            Glide.with(context)
                    .load(null)
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return PoliListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    PoliListFiltered = PoliList;
                } else {
                    List<Poli> filteredList = new ArrayList<>();
                    for (Poli row : PoliList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getServiceUnitName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    PoliListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = PoliListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                PoliListFiltered = (ArrayList<Poli>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Poli poli);
    }
}
