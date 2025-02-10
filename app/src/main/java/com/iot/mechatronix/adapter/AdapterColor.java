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

public class AdapterColor extends RecyclerView.Adapter<AdapterColor.MyViewHolder> {

    private Context mContext;
    private final AdapterColor.OnItemClickListener listener;
    private List<ResponseColorLocal> NotificationList;


    private Fragment callingFragment;
    private FragmentManager fragmentManager;


    public interface OnItemClickListener {
        void onItemClick(ResponseColorLocal item);

    }

    public AdapterColor(Fragment fragment, Context mContext, List<ResponseColorLocal> NotificationList, FragmentManager fragmentManager, AdapterColor.OnItemClickListener listener) {
        this.NotificationList = NotificationList;
        this.listener = listener;
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.callingFragment = fragment;


    }


    @Override
    public AdapterColor.MyViewHolder onCreateViewHolder( ViewGroup parent,
                                                                    int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_type, parent, false);
        return new AdapterColor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final AdapterColor.MyViewHolder holder, final int position) {


        final ResponseColorLocal notification = NotificationList.get(position);

        holder.txt_product.setText(notification.getName());
        holder.txt_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = mContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constant.PRODUCT_COLOR, String.valueOf(notification.getName()  ));
                editor.putString(Constant.PRODUCT_COLOR_CODE, String.valueOf(notification.getCode()  ));
                editor.putString(Constant.PRODUCT_COLOR_ID, String.valueOf(notification.getId()  ));
                editor.apply();

                String tint1=notification.getTint1();
                String tint1Value=notification.getTint1Value();

                String tint2=notification.getTint2();
                String tint2Value=notification.getTint2Value();

                String tint3=notification.getTint3();
                String tint3Value=notification.getTint3Value();

                String tint4=notification.getTint4();
                String tint4Value=notification.getTint4Value();

                String tint5=notification.getTint5();
                String tint5Value=notification.getTint5Value();

                ((CreateColorFragment) callingFragment).RefreshAfterSelectColor(String.valueOf(notification.getId()), notification.getName(),notification.getCode(),tint1,tint1Value,tint2,tint2Value,tint3,tint3Value,tint4,tint4Value,tint5,tint5Value);


            }
        });


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
