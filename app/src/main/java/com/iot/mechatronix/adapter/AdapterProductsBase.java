package com.iot.mechatronix.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iot.mechatronix.Constant;
import com.iot.mechatronix.CreateColorFragment;
import com.iot.mechatronix.R;
import com.iot.mechatronix.responceLocal.ResponseProductBaseLocal;
import com.iot.mechatronix.retrofit.RresponceFetchBase;
import com.iot.mechatronix.retrofit.RresponceFetchBase;

import java.util.ArrayList;
import java.util.List;

public class AdapterProductsBase extends RecyclerView.Adapter<AdapterProductsBase.MyViewHolder> {

    private Context mContext;
    private final AdapterProductsBase.OnItemClickListener listener;
    private List<ResponseProductBaseLocal> NotificationList;


    private Fragment callingFragment;
    private FragmentManager fragmentManager;


    public interface OnItemClickListener {
        void onItemClick(ResponseProductBaseLocal item);

    }

    public AdapterProductsBase(Fragment fragment, Context mContext, List<ResponseProductBaseLocal> NotificationList, FragmentManager fragmentManager, AdapterProductsBase.OnItemClickListener listener) {
        this.NotificationList = NotificationList;
        this.listener = listener;
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.callingFragment = fragment;


    }


    @Override
    public AdapterProductsBase.MyViewHolder onCreateViewHolder( ViewGroup parent,
                                                                int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_type, parent, false);
        return new AdapterProductsBase.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterProductsBase.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        final ResponseProductBaseLocal notification = NotificationList.get(position);

        holder.txt_product.setText(notification.getName());
        holder.txt_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = mContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constant.PRODUCT_BASE_NAME, String.valueOf(notification.getName()  ));
                editor.putString(Constant.PRODUCT_BASE_ID, String.valueOf(notification.getId()  ));
                editor.apply();

                ((CreateColorFragment) callingFragment).RefreshAfterSelectProductBase(String.valueOf(notification.getId()), notification.getName(),position);


            }
        });


    }


    public void filterList(ArrayList<ResponseProductBaseLocal> filterdNames) {
//        Log.e(TAG, "ADAPTER -- filterList: " + filterdNames );
        this.NotificationList = filterdNames;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return NotificationList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {



        TextView txt_product;


        public MyViewHolder(View view) {
            super(view);


            txt_product = itemView.findViewById(R.id.txt_product);
        }


    }
}


