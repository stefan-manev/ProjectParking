package com.example.barking;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ResViewHolder> {


//    public boolean addOneReservation(ReservationModel reservationModel){
//    SQLiteDatabase db = this.getWritableDatabase();
//    ContentValues cv = new ContentValues();
//    cv.put(column_res_id, reservationModel.getRes_id());
//    cv.put(column_res_parking, reservationModel.getParking_id());
//    cv.put(column_res_city, reservationModel.getCity());
//    cv.put(column_res_date, reservationModel.getDate());
//    cv.put(column_res_time, reservationModel.getTime());
//    cv.put(column_res_user, reservationModel.getUser());
////    cv.put(column_res_QR, reservationModel.getQR());
//
//    long insert = db.insert(reservation_table, null, cv);
//    if (insert == -1){
//        Toast.makeText(context, "Failed insert", Toast.LENGTH_SHORT).show();
//        return true;
//    }
//    else {
//        Toast.makeText(context, "Successful insert", Toast.LENGTH_SHORT).show();
//        return false;
//    }
//}

    private Context context;
    private ArrayList res_parking, res_date;

    private OnResListener myOnResListener;
    private OnResButtonListener myOnResButtonListener;

    ResAdapter(Context context, ArrayList res_parking, ArrayList res_date, OnResListener onResListener){
        this.context = context;
        this.res_parking = res_parking;
        this.res_date = res_date;
        this.myOnResListener = onResListener;
//        this.myOnResButtonListener = onResButtonListener;
    }

    @NonNull
    @Override
    public ResAdapter.ResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.res_row, parent, false);

        return new ResViewHolder(view, myOnResListener, myOnResButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResAdapter.ResViewHolder holder, int position) {
        holder.res_parking_tv.setText(String.valueOf(res_parking.get(position)));
        holder.res_date_tv.setText(String.valueOf(res_date.get(position)));
    }

    @Override
    public int getItemCount() {
        return res_parking.size();
    }

    public class ResViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView res_parking_tv, res_date_tv;
        OnResListener onResListener;
        OnResButtonListener onResButtonListener;

        public ResViewHolder(@NonNull View itemView, OnResListener onResListener, OnResButtonListener onResButtonListener) {
            super(itemView);

            res_parking_tv = itemView.findViewById(R.id.res_parking_tv);
            res_date_tv = itemView.findViewById(R.id.res_date_tv);
            this.onResListener = onResListener;
            this.onResButtonListener =onResButtonListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                onResListener.onResClick(getAdapterPosition());
        }

    }

    public interface OnResListener{
        void onResClick(int position);
    }

    public interface OnResButtonListener{
        void onResButtonClick(int position);
    }

}