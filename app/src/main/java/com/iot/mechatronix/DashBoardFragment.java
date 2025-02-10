package com.iot.mechatronix;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFragment extends Fragment {
    private static final String TAG = "DashBoardFragment";
    ImageView img_connect;
    CardView btn_search;
TextView txt_connect;
    RelativeLayout  btn_create_color,btn_maintenance,btn_history;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_dash_board, container, false);
        btn_create_color=view.findViewById(R.id.btn_create_color);
        btn_maintenance=view.findViewById(R.id.btn_maintenance);
        img_connect=view.findViewById(R.id.img_connect);
        txt_connect=view.findViewById(R.id.txt_connect);
        btn_search=view.findViewById(R.id.btn_search);
        btn_history=view.findViewById(R.id.btn_history);



        SharedPreferences spp = requireActivity().getSharedPreferences(Constant.BLUETOOTH_PREF, Context.MODE_PRIVATE);
        String Address = spp.getString(Constant.PREVIOUS_DEVICE_ADDRESS, "");
        String Name = spp.getString(Constant.PREVIOUS_DEVICE_NAME, "");

        if(!Name.equalsIgnoreCase("")){
            txt_connect.setVisibility(View.VISIBLE);
            img_connect.setVisibility(View.VISIBLE);
            btn_search.setVisibility(View.GONE);
        }else {
            txt_connect.setVisibility(View.GONE);
            img_connect.setVisibility(View.GONE);
            btn_search.setVisibility(View.VISIBLE);
        }
//        String example = "ABC Results for draw\nno 2888";
//        Toast.makeText(requireActivity(),example.substring(example.lastIndexOf("\n") + 1),Toast.LENGTH_SHORT).show();

        Log.e(TAG, "onCreateView: BluetoothText :"+ ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString()+"***");
        if(((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("") || ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("Enabled") || ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("Bluetooth enabled") || ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("Connection Failed")){

            Log.e(TAG, "onCreateView: BluetoothText 1" );
            img_connect.setImageDrawable(getResources().getDrawable(R.drawable.disconnected));
            txt_connect.setText("Reconnect Device");
        }else {
            img_connect.setImageDrawable(getResources().getDrawable(R.drawable.connected));
            txt_connect.setText("Connected");
            Log.e(TAG, "onCreateView: BluetoothText 2***"+((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString()+"***" );
        }
        btn_create_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_connect.getText().toString().equalsIgnoreCase("Connected")){
                    openFragment(CreateColorFragment.newInstance("",""));

               }else {
                   Toast.makeText(requireContext(),"Please connect your device first !",Toast.LENGTH_SHORT).show();
               }
            }
        });
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFragment(HistoryFragment.newInstance("",""));

            }
        });

        btn_maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txt_connect.getText().toString().equalsIgnoreCase("Connected")){
                    openFragment(MaintenanceFragment.newInstance("",""));

                }else {
                    Toast.makeText(requireContext(),"Please connect your device first !",Toast.LENGTH_SHORT).show();
                }

            }
        });

        img_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DashBoardActivity)requireActivity()).ReConnectPreviousDevice();
                CheckConnection();


            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((DashBoardActivity)requireActivity()). discover();
                //     send_layout.setVisibility(View.GONE);
                ((DashBoardActivity)requireActivity()).search_layout.setVisibility(View.VISIBLE);

                ((DashBoardActivity)requireActivity()).mDevicesListView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void  CheckConnection(){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // do something...

                if(((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("") || ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("Enabled") || ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("Bluetooth enabled") || ((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("Connection Failed")){

            Log.e(TAG, "onCreateView: BluetoothText 1" );
            img_connect.setImageDrawable(getResources().getDrawable(R.drawable.disconnected));
            txt_connect.setText("Reconnect Device");
        }else {
            img_connect.setImageDrawable(getResources().getDrawable(R.drawable.connected));
            txt_connect.setText("Connected");
                    Log.e(TAG, "onCreateView: BluetoothText 2***"+((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString()+"***" );
        }
            }
        }, 2000);
    };
    public void UpdateSocketImage() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
        img_connect.setImageDrawable(getResources().getDrawable(R.drawable.connected));
        txt_connect.setText("Connected");
            }
        });
    }

    public void UpdateSocketImageDisconnected() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                img_connect.setImageDrawable(getResources().getDrawable(R.drawable.disconnected));
                txt_connect.setText("Connected");

            }
        });
    }
    public  void CheckScanningDeviceConnected(){
        SharedPreferences spp = requireActivity().getSharedPreferences(Constant.BLUETOOTH_PREF, Context.MODE_PRIVATE);
        String Address = spp.getString(Constant.PREVIOUS_DEVICE_ADDRESS, "");
        String Name = spp.getString(Constant.PREVIOUS_DEVICE_NAME, "");

        if(!Name.equalsIgnoreCase("")){
            txt_connect.setVisibility(View.VISIBLE);
            img_connect.setVisibility(View.VISIBLE);
            btn_search.setVisibility(View.GONE);
         //   txt_connect.setText("Connected");
        }else {
            txt_connect.setVisibility(View.GONE);
            img_connect.setVisibility(View.GONE);
            btn_search.setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    public void onResume() {
//
//
//        if(!((DashBoardActivity)requireActivity()).mBluetoothStatus.getText().toString().equalsIgnoreCase("")){
//            img_connect.setImageDrawable(getResources().getDrawable(R.drawable.connected));
//            txt_connect.setText("Connected");
//        }else {
//            img_connect.setImageDrawable(getResources().getDrawable(R.drawable.disconnected));
//            txt_connect.setText("Reconnect Device");
//        }
//        super.onResume();
//    }
}