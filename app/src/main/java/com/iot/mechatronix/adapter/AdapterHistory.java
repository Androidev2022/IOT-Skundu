package com.iot.mechatronix.adapter;

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
import com.iot.mechatronix.responceLocal.ResponseHistory;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder> {

    private Context mContext;
    private final AdapterHistory.OnItemClickListener listener;
    private List<ResponseHistory> NotificationList;


    private Fragment callingFragment;
    private FragmentManager fragmentManager;


    public interface OnItemClickListener {
        void onItemClick(ResponseHistory item);

    }

    public AdapterHistory(Fragment fragment, Context mContext, List<ResponseHistory> NotificationList, FragmentManager fragmentManager, AdapterHistory.OnItemClickListener listener) {
        this.NotificationList = NotificationList;
        this.listener = listener;
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.callingFragment = fragment;


    }


    @Override
    public AdapterHistory.MyViewHolder onCreateViewHolder( ViewGroup parent,
                                                                    int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_type, parent, false);
        return new AdapterHistory.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final AdapterHistory.MyViewHolder holder, final int position) {


        final ResponseHistory notification = NotificationList.get(position);

     //   holder.txt_product.setText(notification.getName());
        holder.txt_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SharedPreferences sp = mContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString(Constant.PRODUCT_ResponseHistory_CARD_NAME, String.valueOf(notification.getName()  ));
//                editor.putString(Constant.PRODUCT_ResponseHistory_CARD_ID, String.valueOf(notification.getId()  ));
//                editor.apply();
//
//                ((CreateColorFragment) callingFragment).RefreshAfterSelectResponseHistoryCard(String.valueOf(notification.getId()), notification.getName());


            }
        });


    }


    public void filterList(ArrayList<ResponseHistory> filterdNames) {
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
