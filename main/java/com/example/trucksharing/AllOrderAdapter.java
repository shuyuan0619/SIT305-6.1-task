package com.example.trucksharing;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.MyViewHolder> {


    private OnItemClickListener mOnItemClickListener;
    private List<ItemBean> mData;
    private Context context;

    private DatabaseHelper dbHelper;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int index = position;
        Log.e("TAG", "onBindViewHolder: "+mData.get(position).vehicleType);
        switch (mData.get(position).vehicleType){

            case "Truck":
                holder.ic_vehicle_type.setBackgroundResource(R.drawable.truck);
                break;
            case "Van":
                holder.ic_vehicle_type.setBackgroundResource(R.drawable.van);
                break;
            case "Refrigerated truck":
                holder.ic_vehicle_type.setBackgroundResource(R.drawable.refrigerated_truck);
                break;
            case "Mini truck":
                holder.ic_vehicle_type.setBackgroundResource(R.drawable.mini_truck);
                break;
            default:
                holder.ic_vehicle_type.setBackgroundResource(R.drawable.other_vehecle);
                break;
        }
        holder.text_date_time.setText(mData.get(position).pickUpDetailDate);
        holder.text_content.setText(mData.get(position).goodDetail);
        holder.btn_share.setBackgroundResource(R.drawable.btn_share);
        holder.cl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,index);
            }
        });

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("isPublic","true");
                db.update("OrderList",contentValues,"u_id=?",new String[]{mData.get(position).u_id});
                Toast.makeText(context, "share successfully！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public void addItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView ic_vehicle_type;
        public TextView text_date_time;
        public TextView text_content;
        public Button btn_share;
        public ConstraintLayout cl_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ic_vehicle_type = itemView.findViewById(R.id.ic_vehicle_type);
            text_date_time = itemView.findViewById(R.id.text_date_time);
            text_content = itemView.findViewById(R.id.text_content);
            btn_share = itemView.findViewById(R.id.btn_share);
            cl_layout = itemView.findViewById(R.id.cl_layout);
        }
    }

    public AllOrderAdapter(List<ItemBean> mData, Context context){
        this.mData = mData;
        this.context = context;
        dbHelper = new DatabaseHelper(context,"LocalDatabase.db",null,2);

    }


}
