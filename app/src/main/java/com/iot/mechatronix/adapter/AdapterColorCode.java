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
import com.iot.mechatronix.responceLocal.ResponseColorLocal;
import com.iot.mechatronix.retrofit.ResopnceFetchColors;

import java.util.ArrayList;
import java.util.List;

public class AdapterColorCode extends RecyclerView.Adapter<AdapterColorCode.MyViewHolder> {

    private Context mContext;
    private final AdapterColorCode.OnItemClickListener listener;
    private List<ResponseColorLocal> NotificationList;


    private Fragment callingFragment;
    private FragmentManager fragmentManager;


    public interface OnItemClickListener {
        void onItemClick(ResponseColorLocal item);

    }

    public AdapterColorCode(Fragment fragment, Context mContext, List<ResponseColorLocal> NotificationList, FragmentManager fragmentManager, AdapterColorCode.OnItemClickListener listener) {
        this.NotificationList = NotificationList;
        this.listener = listener;
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.callingFragment = fragment;


    }


    @Override
    public AdapterColorCode.MyViewHolder onCreateViewHolder( ViewGroup parent,
                                                         int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_type, parent, false);
        return new AdapterColorCode.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final AdapterColorCode.MyViewHolder holder, final int position) {


        final ResponseColorLocal notification = NotificationList.get(position);

        holder.txt_product.setText(notification.getName());
//        holder.txt_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedPreferences sp = mContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString(Constant.PRODUCT_COLOR, String.valueOf(notification.getName()  ));
//                editor.putString(Constant.PRODUCT_COLOR_CODE, String.valueOf(notification.getCode()  ));
//                editor.putString(Constant.PRODUCT_COLOR_ID, String.valueOf(notification.getId()  ));
//                editor.apply();
//
//                ((CreateColorFragment) callingFragment).RefreshAfterSelectColor(String.valueOf(notification.getId()), notification.getName(),notification.getCode());
//
//
//            }
//        });


    }


    public void filterList(ArrayList<ResponseColorLocal> filterdNames) {
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
