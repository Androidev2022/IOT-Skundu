package com.iot.mechatronix;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateColorProcessingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateColorProcessingFragment extends Fragment {
    final String[] readMessage = {""};

    String reee;
    String FinalCommand = "";
    String ProductName = "";
    String ColorName = "";
    String ColorCode = "";
    String ColorBASE = "";
    String PackSize = "";
    CustPrograssbar custPrograssbar;
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    public TextView txt_out;

    Button button0, button1;
    OutputStream mmmOutputStream;
    private Handler mHandler; // Our main handler that will receive callback notifications
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    InputStream mmInputStream;
    private static final String TAG = "CreateColorProcessingFr";
    MediaPlayer mp;
    MediaPlayer mpFail;
    MediaPlayer mpChange;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateColorProcessingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateColorProcessingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateColorProcessingFragment newInstance(String param1, String param2) {
        CreateColorProcessingFragment fragment = new CreateColorProcessingFragment();
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
        final View view = inflater.inflate(R.layout.fragment_create_color_processing, container, false);

        txt_out = view.findViewById(R.id.txt_output);
        button1 = view.findViewById(R.id.button1);
        button0 = view.findViewById(R.id.button0);

        mp = MediaPlayer.create(getContext(), R.raw.success);
        mpFail = MediaPlayer.create(getContext(), R.raw.error);
        mpChange = MediaPlayer.create(getContext(), R.raw.beep);
        //Retrieve the value
        String CustomerName = getArguments().getString("cus_name");
        String CustomerAddress = getArguments().getString("cus_address");
        String CustomerCity = getArguments().getString("cus_city");
        String CustomerPin = getArguments().getString("cus_pin");
        String CustomerPhone = getArguments().getString("cus_ph");
        String CustomerEmail = getArguments().getString("cus_email");

        SharedPreferences spp = requireActivity().getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
        String Command1 = spp.getString(Constant.DISPENSING_COMMAND1, "");
        String Command3 = spp.getString(Constant.DISPENSING_COMMAND3, "");
        String Painter = "0";
        String Command2 = CustomerName + "|" + Painter + "|" + CustomerAddress + "|" + CustomerCity + "|" + CustomerPin + "|" + CustomerPhone + "|" + CustomerEmail + "|";


        Log.e(TAG, "onCreateView: Command1:" + Command1);
        Log.e(TAG, "onCreateView: Command2:" + Command3);
        Log.e(TAG, "onCreateView: Command2:" + Command2);

        FinalCommand = Command1 + Command2 + Command3;
   //   String  FinalCommand1="DC01222|20240225|143100|Luxary|Apple Green|9876|5|10|1|2|50|6|30|9|20|11|70|||Sherlock Holmes|1| 221B Baker Street|London|NW1 6XE|44-20-7224-3688|info@sherlock-holmes.co.uk|100|400|200|50|750";
   // String    FinalCommand2="DC01222|20240225|143100|Luxary|Apple Green|9876|5|10|15|2|50|6|200|9|20|11|70|||Sherlock Holmes|1| 221B Baker Street|London|NW1 6XE|44‑20‑7224‑3688|info@sherlock‑holmes.co.uk|100|400|200|50|750";
        //Custom Progressbar devloped by Subhasish kundu
        //Custom Progressbar devloped by Subhasish kundu
      custPrograssbar = new CustPrograssbar();
//        custPrograssbar.prograssCreate(getContext());

//        try {
//            ((DashBoardActivity) requireActivity()).sendData(FinalCommand,"Sending Command");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        Log.e(TAG, "onCreateView: FinalCommand ::" + FinalCommand);
//        ArrayList<String> itemList = new ArrayList<String>();
//
//        String cmd="FD000\nFD999\nFD111";
//        String[] split = cmd.split("\\n");
//        Scanner scanner = new Scanner(cmd);
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            System.out.println(line);
//            Log.e(TAG, "onCreateVieww:Output::::: "+line );
//        }
//        String re1  = cmd.substring(0, 5);
//        String re2  = cmd.substring(6, 11);
//        String re3  = cmd.substring(12, 17);
//
//        Log.e(TAG, "onCreateVieww:1: "+cmd );
//        Log.e(TAG, "onCreateVieww:2: "+re1 );
//        Log.e(TAG, "onCreateVieww:3: "+re2 );
//        Log.e(TAG, "onCreateVieww:4: "+re3 );
        custPrograssbar.prograssCreate(getContext());

        try {
            ((DashBoardActivity) requireActivity()).sendData(FinalCommand, "Progress");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Log.e(TAG, "onCreateVieww:4: "+itemList.get(0) );
//        Log.e(TAG, "onCreateVieww:5: "+itemList.get(1) );
//        Log.e(TAG, "onCreateVieww:6: "+itemList.get(2) );

//        button0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Custom Progressbar devloped by Subhasish kundu
//              //  CustPrograssbar custPrograssbar = new CustPrograssbar();
//                custPrograssbar.prograssCreate(getContext());
//
//                try {
//                    ((DashBoardActivity) requireActivity()).sendData(FinalCommand1, "Progress");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
////                Handler handler = new Handler();
////                handler.postDelayed(new Runnable() {
////                    public void run() {
////                        custPrograssbar.closePrograssBar();
////                    }
////                }, 5000);   //5 seconds
//            }
//        });
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                custPrograssbar.prograssCreate(getContext());
//                try {
//                    ((DashBoardActivity) requireActivity()).sendData(FinalCommand2, "Progress");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        });


        ;
        return view;
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void OutPutmethod(String readResult) {
          txt_out.setText(readResult);
         String TextViewResult=txt_out.getText().toString();
        Log.e(TAG, "OutPutmethod: TextViewOutPut :1:"+TextViewResult );
        String re11  = readResult.substring(0, 5);
        String re22  = readResult.substring(7, 12);
        String re33  = readResult.substring(14, 19);




        Log.e(TAG, "OutPutmethod: TextViewOutPut :2:"+re11 );
        Log.e(TAG, "OutPutmethod: TextViewOutPut :3:"+re22 );
        Log.e(TAG, "OutPutmethod: TextViewOutPut :4:"+re33 );
        Log.e(TAG, "OutPutmethod:1: "+readResult +"******" );

        String readMessageFirst2 = readResult.length() < 2 ? readResult : readResult.substring(0, 2);

        String result = readResult.split("\n")[0];

        Log.e(TAG, "OutPutmethod: New Length ::"+result.length() );
        Log.e(TAG, "OutPutmethod: New Result ::"+result );
        Log.e(TAG, "OutPutmethod: New Last data after new line :::"+readResult.substring(readResult.lastIndexOf("\n") + 1) );

        String LastData= readResult.substring(readResult.lastIndexOf("\n") + 1);
        reee  =  LastData.substring(0, 2);

        Log.e(TAG, "OutPutmethod: New Result FFFFFFFFF ::"+reee+"*****" );
        if(reee.equalsIgnoreCase("FD")){

            Log.e(TAG, "OutPutmethoddd: 1" );
            String re1  = readResult.substring(0, 5);
            readMessage[0] =re1;
            String re2  = readResult.substring(7, 12);
            String re3  = readResult.substring(14, 19);
            Log.e(TAG, "onCreateViewww:2: "+re1 );
            Log.e(TAG, "onCreateViewww:3: "+re2 );
            Log.e(TAG, "onCreateViewww:4: "+re3 );
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    readMessage[0] = readResult.substring(7, 12);
                    ShowResult(readMessageFirst2);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            readMessage[0] = readResult.substring(14, 19);
                            ShowResult(readMessageFirst2);

                        }
                    }, 2000);
                }
            }, 2000);


        }else if(reee.equalsIgnoreCase("ED")){

            Log.e(TAG, "OutPutmethoddd: 2" );
            String re1  = readResult.substring(0, 5);
            readMessage[0] =re1;
            String re2  = readResult.substring(7, 11);
            Log.e(TAG, "onCreateViewww:2: "+re1 );
            Log.e(TAG, "onCreateViewww:3: "+re2 );
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    readMessage[0] = readResult.substring(7, 11);
                    ShowResult(readMessageFirst2);
                }
            }, 2000);

        }else {
            Log.e(TAG, "OutPutmethoddd: 3" );
            readMessage[0] =  readResult.substring(0, 5);
            ShowResult(readMessageFirst2);

        }




        Log.e(TAG, "OutPutmethod: Length ::"+readResult.length() );
//        }
        Log.e(TAG, "OutPutmethod: Get From textView :::" + txt_out.getText().toString());

        Log.e(TAG, "OutPutmethod:2:"+readMessageFirst2 );



    }


    public  void ShowResult(String readMessageFirst2 ){
        if (readMessageFirst2.equalsIgnoreCase("FD")) {
            Log.e(TAG, "OutPutmethod:3: "+"reached" );
            Log.e(TAG, "OutPutmethod:4: "+ readMessage[0]);
            if (readMessage[0].equalsIgnoreCase("FD999")) {
                mp.start();
                custPrograssbar.updateTextPrograssBar("Dispensing completed and data stored in database file.");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        custPrograssbar.closePrograssBar();
                        openFragment(CreatedColorDetailsFragment.newInstance("", ""));
                    }
                }, 3000);   //5 seconds
            } else if (readMessage[0].equalsIgnoreCase("FD000")) {
                mpChange.start();
                custPrograssbar.updateTextPrograssBar("Dispensing Command received");

            } else if (readMessage[0].equalsIgnoreCase("FD010")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.1 ‘Can in place’");

            } else if (readMessage[0].equalsIgnoreCase("FD011")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.1 ‘Can’ completed");

            } else if (readMessage[0].equalsIgnoreCase("FD020")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.2 ‘Can in place’");

            } else if (readMessage[0].equalsIgnoreCase("FD021")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.2 ‘Can’ completed");

            } else if (readMessage[0].equalsIgnoreCase("FD110")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("Starting dispensing of No.1 colorant");

            } else if (readMessage[0].equalsIgnoreCase("FD111")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.1 colorant dispensing completed");

            } else if (readMessage[0].equalsIgnoreCase("FD120")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("Starting dispensing of No.2 colorant");
            } else if (readMessage[0].equalsIgnoreCase("FD121")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.2 colorant dispensing completed");
            } else if (readMessage[0].equalsIgnoreCase("FD130")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("Starting dispensing of No.3 colorant");
            } else if (readMessage[0].equalsIgnoreCase("FD131")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.3 colorant dispensing completed");
            } else if (readMessage[0].equalsIgnoreCase("FD140")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("Starting dispensing of No.4 colorant");
            } else if (readMessage[0].equalsIgnoreCase("FD141")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.4 colorant dispensing completed");
            } else if (readMessage[0].equalsIgnoreCase("FD150")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("Starting dispensing of No.5 colorant");
            } else if (readMessage[0].equalsIgnoreCase("FD151")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No.1 colorant dispensing completed");
            }

            else if (readMessage[0].equalsIgnoreCase("FD019")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No1 Can removed");

            }else if (readMessage[0].equalsIgnoreCase("FD029")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No2 Can removed");

            }else if (readMessage[0].equalsIgnoreCase("FD039")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("No3 Can removed");

            }
        } else if (readMessageFirst2.equalsIgnoreCase("ED")) {
            //Error Section
            if (readMessage[0].equalsIgnoreCase("ED03")) {

                mpFail.start();
                custPrograssbar.updateTextPrograssBar("Abnormal Stop during operation");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        custPrograssbar.closePrograssBar();
                        openFragment(DashBoardFragment.newInstance("", ""));
                    }
                }, 3000);   //5 seconds

            } else if (readMessage[0].equalsIgnoreCase("ED00")) {
                mpFail.start();
                custPrograssbar.updateTextPrograssBar("Command format does not match");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        custPrograssbar.closePrograssBar();
                        openFragment(CreateColorFragment.newInstance("", ""));
                    }
                }, 3000);   //5 seconds

            } else if (readMessage[0].equalsIgnoreCase("ED01")) {
                mpChange.start();

                custPrograssbar.updateTextPrograssBar("Machine not ready");

            } else if (readMessage[0].equalsIgnoreCase("ED02")) {
                mpFail.start();
                custPrograssbar.updateTextPrograssBar("Insufficient colorant");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        custPrograssbar.closePrograssBar();
                        openFragment(CreateColorFragment.newInstance("", ""));
                    }
                }, 3000);   //5 seconds
            }
        } else {

        }


    }
}