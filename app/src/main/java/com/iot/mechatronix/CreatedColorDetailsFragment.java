package com.iot.mechatronix;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatedColorDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatedColorDetailsFragment extends Fragment {

    RelativeLayout btn_Done;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreatedColorDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatedColorDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatedColorDetailsFragment newInstance(String param1, String param2) {
        CreatedColorDetailsFragment fragment = new CreatedColorDetailsFragment();
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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button even
                //    Toast.makeText(requireContext(), "readMessageBack Pressed !",Toast.LENGTH_SHORT).show();

                Log.d("BACKBUTTON", "Back button clicks");
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_created_color_details, container, false);
        btn_Done=view.findViewById(R.id.btn_Done);
        btn_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(DashBoardFragment.newInstance("",""));

            }
        });
        SharedPreferences spp = requireActivity().getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
        String ColorName = spp.getString(Constant.BILL_COLOR_NAME, "");
        String Total_prize = spp.getString(Constant.BILL_COLOR_NAME, "");

        TextView txt_color_name=view.findViewById(R.id.txt_color_name);
      txt_color_name.setText(ColorName);

        TextView txt_price=view.findViewById(R.id.txt_price);
        txt_price.setText(Total_prize);



        return view;
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}