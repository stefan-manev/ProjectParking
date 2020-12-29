package com.example.barking;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private Context context;
    private ArrayList city_id, city_name;

    private OnCityListener myOnCityListener;

    CityAdapter(Context context, ArrayList city_id, ArrayList city_name, OnCityListener onCityListener){
        this.context = context;
        this.city_id = city_id;
        this.city_name = city_name;
        this.myOnCityListener = onCityListener;
    }

    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.city_row, parent, false);

        return new CityViewHolder(view, myOnCityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityViewHolder holder, int position) {
        holder.city_id_txt.setText(String.valueOf(city_id.get(position)));
        holder.city_name_txt.setText(String.valueOf(city_name.get(position)));
    }

    @Override
    public int getItemCount() {
        return city_id.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView city_id_txt, city_name_txt;
        OnCityListener onCityListener;

        public CityViewHolder(@NonNull View itemView, OnCityListener onCityListener) {
            super(itemView);

            city_id_txt = itemView.findViewById(R.id.city_id_txt);
            city_name_txt = itemView.findViewById(R.id.city_name_txt);
            this.onCityListener = onCityListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCityListener.onCityClick(getAdapterPosition());
        }
    }

    public interface OnCityListener{
        void onCityClick(int position);
    }

}
