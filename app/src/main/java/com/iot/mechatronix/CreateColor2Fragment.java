package com.iot.mechatronix;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateColor2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateColor2Fragment extends Fragment {


    EditText edt_customer_name,edt_address,edt_city,edt_pin,edt_phone,edt_email;

    private static final String TAG = "CreateColor2Fragment";
    RelativeLayout btn_next,btn_skip;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateColor2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateColor2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateColor2Fragment newInstance(String param1, String param2) {
        CreateColor2Fragment fragment = new CreateColor2Fragment();
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
        final View view= inflater.inflate(R.layout.fragment_create_color2, container, false);
        btn_next=view.findViewById(R.id.btn_next);
        btn_skip=view.findViewById(R.id.btn_skip);
        edt_customer_name=view.findViewById(R.id.edt_customer_name);
        edt_address=view.findViewById(R.id.edt_address);
        edt_city=view.findViewById(R.id.edt_city);
        edt_pin=view.findViewById(R.id.edt_pin);
        edt_phone=view.findViewById(R.id.edt_phone);
        edt_email=view.findViewById(R.id.edt_email);


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
                String date = "CUS"+df.format(Calendar.getInstance().getTime());

                Log.e(TAG, "onClick: DATESTAMP ::"+date );
                openFragment(CreateColorProcessingFragment.newInstance("",""),date,"EMPTY","EMPTY","000000","0000000000","noemail@com.com");
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(requireActivity(),MainActivity.class);
//                startActivity(intent);
                openFragment(CreateColorProcessingFragment.newInstance("",""),edt_customer_name.getText().toString(),edt_address.getText().toString(),edt_city.getText().toString(),edt_pin.getText().toString(),edt_phone.getText().toString(),edt_email.getText().toString());

                }
        });
        return  view;
    }
    public void openFragment(Fragment fragment,String name,String address,String city,String pin,String ph,String email) {
        Bundle args = new Bundle();
        args.putString("cus_name", name);
        args.putString("cus_address", address);
        args.putString("cus_city", city);
        args.putString("cus_pin", pin);
        args.putString("cus_ph", ph);
        args.putString("cus_email", email);
        fragment.setArguments(args);

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}