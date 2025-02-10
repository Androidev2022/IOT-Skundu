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
import com.iot.mechatronix.responceLocal.ResponseProductPackLocal;
import com.iot.mechatronix.retrofit.RresponceFetchBase;

import java.util.ArrayList;
import java.util.List;

public class AdapterPackSize extends RecyclerView.Adapter<AdapterPackSize.MyViewHolder> {

    private Context mContext;
    private final AdapterPackSize.OnItemClickListener listener;
    private List<ResponseProductPackLocal> NotificationList;


    private Fragment callingFragment;
    private FragmentManager fragmentManager;


    public interface OnItemClickListener {
        void onItemClick(ResponseProductPackLocal item);

    }

    public AdapterPackSize(Fragment fragment,Context mContext, List<ResponseProductPackLocal> NotificationList, FragmentManager fragmentManager, AdapterPackSize.OnItemClickListener listener) {
        this.NotificationList = NotificationList;
        this.listener = listener;
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
        this.callingFragment = fragment;


    }


    @Override
    public AdapterPackSize.MyViewHolder onCreateViewHolder( ViewGroup parent,
                                                                int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_type, parent, false);
        return new AdapterPackSize.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final AdapterPackSize.MyViewHolder holder, final int position) {


        final ResponseProductPackLocal notification = NotificationList.get(position);

        holder.txt_product.setText(notification.getName().toString());
        holder.txt_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = mContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Constant.PACK_SIZE, String.valueOf(notification.getSize().toString()  ));
                editor.putString(Constant.PACK_SIZE_ID, String.valueOf(notification.getId()));
                editor.apply();

                ((CreateColorFragment) callingFragment).RefreshAfterSelectPackSize(String.valueOf(notification.getId()), notification.getName(),notification.getAmount());


            }
        });


    }


    public void filterList(ArrayList<ResponseProductPackLocal> filterdNames) {
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


