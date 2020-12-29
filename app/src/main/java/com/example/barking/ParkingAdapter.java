package com.example.barking;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {

    private Context context;
    private ArrayList parking_id, parking_name, available;

    private OnParkingListener myOnParkingListener;

    ParkingAdapter(Context context, ArrayList parking_id, ArrayList parking_name, ArrayList available, OnParkingListener onParkingListener){
        this.context = context;
        this.parking_id = parking_id;
        this.parking_name = parking_name;
        this.available = available;
        this.myOnParkingListener = onParkingListener;
    }

    @NonNull
    @Override
    public ParkingAdapter.ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.parking_row, parent, false);

        return new ParkingViewHolder(view, myOnParkingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingAdapter.ParkingViewHolder holder, int position) {
        holder.parking_id_txt.setText(String.valueOf(parking_id.get(position)));
        holder.parking_name_txt.setText(String.valueOf(parking_name.get(position)));
        holder.available_tv.setText(String.valueOf(available.get(position)));
    }

    @Override
    public int getItemCount() {
        return parking_id.size();
    }

    public class ParkingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView parking_id_txt, parking_name_txt, available_tv;
        OnParkingListener onParkingListener;

        public ParkingViewHolder(@NonNull View itemView, OnParkingListener onParkingListener) {
            super(itemView);

            parking_id_txt = itemView.findViewById(R.id.parking_id_txt);
            parking_name_txt = itemView.findViewById(R.id.parking_name_txt);
            available_tv = itemView.findViewById(R.id.available_tv);
            this.onParkingListener = onParkingListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onParkingListener.onParkingClick(getAdapterPosition());
        }
    }

    public interface OnParkingListener{
        void onParkingClick(int position);
    }

}