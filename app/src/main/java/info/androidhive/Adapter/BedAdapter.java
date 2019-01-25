package info.androidhive.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.Model.Bed;
import info.androidhive.recyclerviewsearch.R;


public class BedAdapter  extends RecyclerView.Adapter<BedAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Bed> bedList;
    private List<Bed> bedListFiltered;
    private ContactsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone,avail;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            phone = view.findViewById(R.id.phone);
            thumbnail = view.findViewById(R.id.thumbnail);
            avail = view.findViewById(R.id.avail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(bedListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public BedAdapter(Context context, List<Bed> bedList, ContactsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.bedList = bedList;
        this.bedListFiltered = bedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_item_tempattidur, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Bed bed = bedListFiltered.get(position);
        holder.name.setText(bed.getClassName());
        holder.phone.setText(bed.getClassCode1());
        //Picasso.with(context).load(bed.getPictureFileName()).into(holder.thumbnail);

        Picasso.get()
                .load(bed.getPictureFileName())
                .into(holder.thumbnail);

        holder.avail.setText(bed.getClassCode().toString());
        //final byte[] decodedBytes = Base64.decode(bed.getPictureFileName(), Base64.DEFAULT);
        /*Glide.with(context)
                .load(decodedBytes)
                .apply(RequestOptions.centerInsideTransform())
                .into(holder.thumbnail);*/

        //Glide.with(context).load(bed.getPictureFileName()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return bedListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    bedListFiltered = bedList;
                } else {
                    List<Bed> filteredList = new ArrayList<>();
                    for (Bed row : bedList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getClassName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    bedListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = bedListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                bedListFiltered = (ArrayList<Bed>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Bed bed);
    }
}
