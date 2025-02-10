package com.iot.mechatronix;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.mechatronix.Database.DatabaseHelper;
import com.iot.mechatronix.Database.DatabaseQueryClass;
import com.iot.mechatronix.adapter.AdapterColor;
import com.iot.mechatronix.adapter.AdapterColorCode;
import com.iot.mechatronix.adapter.AdapterPackSize;
import com.iot.mechatronix.adapter.AdapterProductName;
import com.iot.mechatronix.adapter.AdapterProductShadeCard;
import com.iot.mechatronix.adapter.AdapterProductsBase;
import com.iot.mechatronix.adapter.AdapterProductsType;
import com.iot.mechatronix.responceLocal.Product;
import com.iot.mechatronix.responceLocal.ProductType;
import com.iot.mechatronix.responceLocal.ResponseColorLocal;
import com.iot.mechatronix.responceLocal.ResponseProductBaseLocal;
import com.iot.mechatronix.responceLocal.ResponseProductPackLocal;
import com.iot.mechatronix.responceLocal.Shade;
import com.iot.mechatronix.retrofit.ReesponceFetchColorNameDB;
import com.iot.mechatronix.retrofit.ReesponceFetchProductName;
import com.iot.mechatronix.retrofit.ReesponceFetchShadeCard;
import com.iot.mechatronix.retrofit.ResponceFetchCalculatePrice;
import com.iot.mechatronix.retrofit.ResponceFetchProductType;
import com.iot.mechatronix.retrofit.ResponseFetchTints;
import com.iot.mechatronix.retrofit.RestClient;
import com.iot.mechatronix.retrofit.RresponceFetchBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateColorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateColorFragment extends Fragment {

    String PackAmount="";

    String Tint1Id,Tint2Id,Tint3Id,Tint4Id,Tint5Id;
    String Tint1Value,Tint2Value,Tint3Value,Tint4Value,Tint5Value;

    private final DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(CreateColorFragment.this.getContext());

    String User_ID = "3";
    String Color_ID = "";
    String Color_Code = "";
    String Base_ID = "";
    String Pack_ID = "";
    String ProductType_ID = "";
    String Product_ID = "";
    String Shade_Card_ID = "";
    ScrollView scrollView;
    TextView txt_total_price;
    CardView btn_calculate;
    AdapterProductsType adapterProductsType;
    List<ProductType> ProductCategoryLocal = new ArrayList<>();

    List<ResponceFetchProductType.Data.Category> ProductCategory = new ArrayList<>();

    AdapterProductName adapterProductName;
    List<ReesponceFetchProductName.Data.Product> ProductName = new ArrayList<>();
    List<Product> ProductNameDB = new ArrayList<>();

    AdapterProductShadeCard adapterProductShadeCard;
    List<ReesponceFetchShadeCard.Data.ProductsShadeCard> ProductSahdeCard = new ArrayList<>();
    List<Shade> ProductSahdeCardDB = new ArrayList<>();


    AdapterColorCode adapterColorCode;

    AdapterColor adapterColor;
    List<ReesponceFetchColorNameDB.Data.Colour> ProductColor = new ArrayList<>();
    List<ResponseColorLocal> ColorsListDB = new ArrayList<>();

    AdapterProductsBase adapterProductsBase;
    List<RresponceFetchBase.Data.Basis> ProductBase = new ArrayList<>();

    List<ResponseProductBaseLocal> ProductBaseDB = new ArrayList<>();

    AdapterPackSize adapterPackSize;
    List<RresponceFetchBase.Data.Basis.Pack> ProductPackSize = new ArrayList<>();

    List<ResponseProductPackLocal> ProductPackDB = new ArrayList<>();

    List<ResponseFetchTints.Data.Tint> ProductColorTint = new ArrayList<>();


    private static final String TAG = "CreateColorFragment";
    Dialog filterDialog;
    ImageView img_close;
    RecyclerView recycler_list;
    EditText edt_product_type, edt_product_name, edt_shade_card, edt_color_name, edt_color_code, edt_base, edt_color_pack_size, edt_no_of_can;
    CardView btn_next;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateColorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateColorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateColorFragment newInstance(String param1, String param2) {
        CreateColorFragment fragment = new CreateColorFragment();
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
        final View view = inflater.inflate(R.layout.fragment_create_color, container, false);
        btn_next = view.findViewById(R.id.btn_next);
        btn_calculate = view.findViewById(R.id.btn_calculate);
        scrollView = view.findViewById(R.id.scrollView);
        txt_total_price = view.findViewById(R.id.txt_total_price);

        edt_product_type = view.findViewById(R.id.edt_product_type);
        edt_product_name = view.findViewById(R.id.edt_product_name);
        edt_shade_card = view.findViewById(R.id.edt_shade_card);
        edt_color_name = view.findViewById(R.id.edt_color_name);
        edt_color_code = view.findViewById(R.id.edt_color_code);
        edt_base = view.findViewById(R.id.edt_base);
        edt_color_pack_size = view.findViewById(R.id.edt_color_pack_size);

        edt_no_of_can = view.findViewById(R.id.edt_no_of_can);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(CreateColor2Fragment.newInstance("", ""));
            }
        });

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalculatePrice();
      //          FetchCalculatedPrice();
            }
        });

        FetchProductTypeslist();
        FetchProductNamesListDB();
        FetchShadeCardlistDB();
        FetchProductColorListDB();
        FetchProductBase();
        FetchProductColorTintDB();

        ProductCategoryLocal.clear();
        ProductCategoryLocal.addAll(databaseQueryClass.getAllProductType(requireContext()));


        edt_product_type.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                edt_product_type.setClickable(false);

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);
                recycler_list = rootView.findViewById(R.id.recycler_list);
                img_close = rootView.findViewById(R.id.img_close);
                TextView txt_header = rootView.findViewById(R.id.txt_header);
                txt_header.setText("Select Product type");
                builder.setView(rootView);
                filterDialog = builder.create();
                WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                Window window = filterDialog.getWindow();
                filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                lp2.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp2);
                Window dialogWindow = filterDialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setGravity(Gravity.BOTTOM);

                Log.e(TAG, "onClick: Dialoggg ::" + ProductCategory.size());


                adapterProductsType = new AdapterProductsType(CreateColorFragment.this, requireContext(), ProductCategoryLocal, getFragmentManager(), new AdapterProductsType.OnItemClickListener() {
                    @Override
                    public void onItemClick(ProductType item) {

                    }
                });
                RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                recycler_list.setLayoutManager(mmLayoutManager);
                recycler_list.setItemAnimator(new DefaultItemAnimator());
                recycler_list.setAdapter(adapterProductsType);
                adapterProductsType.notifyDataSetChanged();

                filterDialog.show();

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterDialog.dismiss();
                    }
                });


            }
        });
        edt_product_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProductType_ID.equalsIgnoreCase("")) {
                    Toast.makeText(requireActivity(), "Please Select Product Type First !", Toast.LENGTH_SHORT).show();
                } else {
                    edt_product_name.setClickable(false);


                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    // Get the layout inflater
                    LayoutInflater inflater = getLayoutInflater();
                    View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                    final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);

                    recycler_list = rootView.findViewById(R.id.recycler_list);
                    img_close = rootView.findViewById(R.id.img_close);
                    TextView txt_header = rootView.findViewById(R.id.txt_header);
                    txt_header.setText("Select Product");

                    builder.setView(rootView);
                    filterDialog = builder.create();
                    WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                    Window window = filterDialog.getWindow();
                    filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                    lp2.copyFrom(window.getAttributes());
                    //This makes the dialog take up the full width
                    lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp2);
                    Window dialogWindow = filterDialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    dialogWindow.setGravity(Gravity.BOTTOM);


                    Log.e(TAG, "onClick: ProductsNames ::" + ProductNameDB.size());


                    adapterProductName = new AdapterProductName(CreateColorFragment.this, requireContext(), ProductNameDB, getFragmentManager(), new AdapterProductName.OnItemClickListener() {
                        @Override
                        public void onItemClick(Product item) {

                        }
                    });
                    RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                    recycler_list.setLayoutManager(mmLayoutManager);
                    recycler_list.setItemAnimator(new DefaultItemAnimator());
                    recycler_list.setAdapter(adapterProductName);
                    ProductNameDB.clear();
                    ProductNameDB.addAll(databaseQueryClass.getAllProduct(requireContext(), ProductType_ID));
                    Log.e(TAG, "RefreshAfterSelectProductType: Size ::"+ProductNameDB.size());

                    adapterProductName.notifyDataSetChanged();
                    filterDialog.show();

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filterDialog.dismiss();
                        }
                    });


                }
            }
        });
        edt_shade_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Product_ID.equalsIgnoreCase("")) {
                    Toast.makeText(requireActivity(), "Please Select Product First !", Toast.LENGTH_SHORT).show();
                } else {
                    edt_shade_card.setClickable(false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    // Get the layout inflater
                    LayoutInflater inflater = getLayoutInflater();
                    View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                    final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);

                    recycler_list = rootView.findViewById(R.id.recycler_list);
                    img_close = rootView.findViewById(R.id.img_close);
                    TextView txt_header = rootView.findViewById(R.id.txt_header);
                    txt_header.setText("Select Shade Card");

                    builder.setView(rootView);
                    filterDialog = builder.create();
                    WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                    Window window = filterDialog.getWindow();
                    filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                    lp2.copyFrom(window.getAttributes());
                    //This makes the dialog take up the full width
                    lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp2);
                    Window dialogWindow = filterDialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    dialogWindow.setGravity(Gravity.BOTTOM);


                    adapterProductShadeCard = new AdapterProductShadeCard(CreateColorFragment.this, requireContext(), ProductSahdeCardDB, getFragmentManager(), new AdapterProductShadeCard.OnItemClickListener() {
                        @Override
                        public void onItemClick(Shade item) {

                        }
                    });
                    RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                    recycler_list.setLayoutManager(mmLayoutManager);
                    recycler_list.setItemAnimator(new DefaultItemAnimator());
                    recycler_list.setAdapter(adapterProductShadeCard);
                    adapterProductShadeCard.notifyDataSetChanged();

                    filterDialog.show();


                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filterDialog.dismiss();
                        }
                    });

                }
            }
        });
        edt_color_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Shade_Card_ID.equalsIgnoreCase("")) {
                    Toast.makeText(requireActivity(), "Please Select ShadeCard First !", Toast.LENGTH_SHORT).show();
                } else {
                    edt_color_name.setClickable(false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    // Get the layout inflater
                    LayoutInflater inflater = getLayoutInflater();
                    View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                    final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);

                    recycler_list = rootView.findViewById(R.id.recycler_list);
                    img_close = rootView.findViewById(R.id.img_close);
                    TextView txt_header = rootView.findViewById(R.id.txt_header);
                    txt_header.setText("Select Color Name");

                    builder.setView(rootView);
                    filterDialog = builder.create();
                    WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                    Window window = filterDialog.getWindow();
                    filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                    lp2.copyFrom(window.getAttributes());
                    //This makes the dialog take up the full width
                    lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp2);
                    Window dialogWindow = filterDialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    dialogWindow.setGravity(Gravity.BOTTOM);


                    adapterColor = new AdapterColor(CreateColorFragment.this, requireContext(), ColorsListDB, getFragmentManager(), new AdapterColor.OnItemClickListener() {
                        @Override
                        public void onItemClick(ResponseColorLocal item) {

                        }
                    });
                    RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                    recycler_list.setLayoutManager(mmLayoutManager);
                    recycler_list.setItemAnimator(new DefaultItemAnimator());
                    recycler_list.setAdapter(adapterColor);

                    filterDialog.show();
                    adapterColor.notifyDataSetChanged();

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filterDialog.dismiss();
                        }
                    });

                }
            }
        });

        edt_color_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Shade_Card_ID.equalsIgnoreCase("")) {
                    Toast.makeText(requireActivity(), "Please Select ShadeCard First !", Toast.LENGTH_SHORT).show();
                } else {
                    edt_color_code.setClickable(false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    // Get the layout inflater
                    LayoutInflater inflater = getLayoutInflater();
                    View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                    final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);

                    recycler_list = rootView.findViewById(R.id.recycler_list);
                    img_close = rootView.findViewById(R.id.img_close);
                    TextView txt_header = rootView.findViewById(R.id.txt_header);
                    txt_header.setText("Select Color Code");

                    builder.setView(rootView);
                    filterDialog = builder.create();
                    WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                    Window window = filterDialog.getWindow();
                    filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                    lp2.copyFrom(window.getAttributes());
                    //This makes the dialog take up the full width
                    lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp2);
                    Window dialogWindow = filterDialog.getWindow();
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    dialogWindow.setGravity(Gravity.BOTTOM);


                    adapterColorCode = new AdapterColorCode(CreateColorFragment.this, requireContext(), ColorsListDB, getFragmentManager(), new AdapterColorCode.OnItemClickListener() {
                        @Override
                        public void onItemClick(ResponseColorLocal item) {

                        }
                    });
                    RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                    recycler_list.setLayoutManager(mmLayoutManager);
                    recycler_list.setItemAnimator(new DefaultItemAnimator());
                    recycler_list.setAdapter(adapterColorCode);

                    filterDialog.show();
                    adapterColorCode.notifyDataSetChanged();

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filterDialog.dismiss();
                        }
                    });

                }
            }
        });

        edt_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_base.setClickable(false);

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);

                recycler_list = rootView.findViewById(R.id.recycler_list);
                img_close = rootView.findViewById(R.id.img_close);
                TextView txt_header = rootView.findViewById(R.id.txt_header);
                txt_header.setText("Select Color Base");

                builder.setView(rootView);
                filterDialog = builder.create();
                WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                Window window = filterDialog.getWindow();
                filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                lp2.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp2);
                Window dialogWindow = filterDialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setGravity(Gravity.BOTTOM);


                adapterProductsBase = new AdapterProductsBase(CreateColorFragment.this, requireContext(), ProductBaseDB, getFragmentManager(), new AdapterProductsBase.OnItemClickListener() {
                    @Override
                    public void onItemClick(ResponseProductBaseLocal item) {

                    }
                });
                RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                recycler_list.setLayoutManager(mmLayoutManager);
                recycler_list.setItemAnimator(new DefaultItemAnimator());
                recycler_list.setAdapter(adapterProductsBase);

                filterDialog.show();
                adapterProductsBase.notifyDataSetChanged();

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterDialog.dismiss();
                    }
                });

            }
        });
        edt_color_pack_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_color_pack_size.setClickable(false);

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                View rootView = inflater.inflate(R.layout.bottom_sheet_list, null);
                final ImageView close = (ImageView) rootView.findViewById(R.id.img_close);

                recycler_list = rootView.findViewById(R.id.recycler_list);
                img_close = rootView.findViewById(R.id.img_close);
                TextView txt_header = rootView.findViewById(R.id.txt_header);
                txt_header.setText("Select Pack Size");

                builder.setView(rootView);
                filterDialog = builder.create();
                WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                Window window = filterDialog.getWindow();
                filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                filterDialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);

                lp2.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp2.width = ViewGroup.LayoutParams.MATCH_PARENT;
                lp2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp2);
                Window dialogWindow = filterDialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setGravity(Gravity.BOTTOM);


                adapterPackSize = new AdapterPackSize(CreateColorFragment.this, requireContext(), ProductPackDB, getFragmentManager(), new AdapterPackSize.OnItemClickListener() {
                    @Override
                    public void onItemClick(ResponseProductPackLocal item) {

                    }
                });
                RecyclerView.LayoutManager mmLayoutManager = new LinearLayoutManager(getContext());
                recycler_list.setLayoutManager(mmLayoutManager);
                recycler_list.setItemAnimator(new DefaultItemAnimator());
                recycler_list.setAdapter(adapterPackSize);
                adapterPackSize.notifyDataSetChanged();

                filterDialog.show();

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterDialog.dismiss();
                    }
                });

            }
        });

        //   SyncLocalData(ProductCategory);
        return view;
    }



//    private void SyncLocalData(List<ResponceFetchProductType.Data.Category> productCategory) {
//        Log.e(TAG, "onCreateView: Reached InsertionProcessCategory :1:"+productCategory.size());
//
//        //insert faq
//        if (isEmpty(Config.TABLE_PRODUCT_CATEGORY)) {
//            DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
//            Log.e(TAG, "onCreateView: Reached InsertionProcessCategory :2:"+ProductCategory.size());
//            //call Section insert process
//            databaseHelper.insertCategory(ProductCategory);
//
//
//        }
//        if (isEmpty(Config.TABLE_PRODUCT)) {
//            DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
//            Log.e(TAG, "onCreateView: Reached InsertionProcess Product ::");
//            //call Section insert process
//            databaseHelper.insertProduct(ProductNameDB);
//
//
//        }
//        if (isEmpty(Config.TABLE_SHADE)) {
//            DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
//            Log.e(TAG, "onCreateView: Reached InsertionProcess Shade ::");
//            //call Section insert process
//            databaseHelper.insertShadeCard(ProductSahdeCardDB);
//
//
//        }
//    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void FetchProductTypeslist() {

        ProductCategory.clear();
        //  adapterProductsType.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");

        RestClient.getClient().FetchProductType(UserID).enqueue(new Callback<ResponceFetchProductType>() {
            @Override
            public void onResponse(Call<ResponceFetchProductType> call, Response<ResponceFetchProductType> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    ResponceFetchProductType.Data listResponse = response.body().getData();


                    for (ResponceFetchProductType.Data.Category list : listResponse.getCategories()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductCategory.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }
                    Log.e(TAG, "onResponse: Categoryyysize ::" + ProductCategory.size());

                    //insert faq
                    if (isEmpty(Config.TABLE_PRODUCT_CATEGORY)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcessCategory :2:" + ProductCategory.size());
                        //call Section insert process
                        databaseHelper.insertCategory(ProductCategory);


                    }
                    //  adapterProductsType.notifyDataSetChanged();
                    edt_product_type.setClickable(true);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponceFetchProductType> call, Throwable t) {

            }
        });
    }

    private void FetchProductBase() {

        ProductBase.clear();
        //  adapterProductsType.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");

        RestClient.getClient().FetchBase(UserID).enqueue(new Callback<RresponceFetchBase>() {
            @Override
            public void onResponse(Call<RresponceFetchBase> call, Response<RresponceFetchBase> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    RresponceFetchBase.Data listResponse = response.body().getData();


                    for (RresponceFetchBase.Data.Basis list : listResponse.getBases()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductBase.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }

                    //insert faq
                    if (isEmpty(Config.TABLE_BASE)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcessBase :" + ProductBase.size());
                        //call Section insert process
                        databaseHelper.insertProductBase(ProductBase);


                    }
                    if (isEmpty(Config.TABLE_PACK)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcessBase :" + ProductBase.size());
                        //call Section insert process
                        databaseHelper.insertProductPack(ProductBase);


                    }
                    //  adapterProductsType.notifyDataSetChanged();
                    edt_base.setClickable(true);

                } else {

                }
            }

            @Override
            public void onFailure(Call<RresponceFetchBase> call, Throwable t) {

            }
        });
    }


    private void FetchPackSize(int position) {
        ProductPackSize.clear();
        //  adapterProductsType.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");

        RestClient.getClient().FetchBase(UserID).enqueue(new Callback<RresponceFetchBase>() {
            @Override
            public void onResponse(Call<RresponceFetchBase> call, Response<RresponceFetchBase> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    RresponceFetchBase.Data listResponse = response.body().getData();


                    for (RresponceFetchBase.Data.Basis.Pack list : listResponse.getBases().get(position).getPacks()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductPackSize.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }
                    //  adapterProductsType.notifyDataSetChanged();
                    edt_color_pack_size.setClickable(true);

                } else {

                }
            }

            @Override
            public void onFailure(Call<RresponceFetchBase> call, Throwable t) {

            }
        });
    }

    public void RefreshAfterSelectProductType(String id, String productCategoryName) {
        edt_product_type.setText(productCategoryName);
        filterDialog.dismiss();

        ProductType_ID = id;

        Log.e(TAG, "FILTER :: ProductType ID :: "+ ProductType_ID);



//        FetchProductNameslist();
    }

    public void RefreshAfterSelectProductName(String id, String productName) {
        edt_product_name.setText(productName);
        filterDialog.dismiss();

        Product_ID = id;
        Log.e(TAG, "FILTER :: Product ID :: "+ Product_ID);

        ProductSahdeCardDB.clear();
        ProductSahdeCardDB.addAll(databaseQueryClass.getAllShade(requireContext(), Product_ID));

    }


    public void RefreshAfterSelectShadeCard(String id, String shadeCardName) {
        edt_shade_card.setText(shadeCardName);
        filterDialog.dismiss();

        Shade_Card_ID = id;

        Log.e(TAG, "FILTER :: ShadeCard ID :: "+ Shade_Card_ID);

        ColorsListDB.clear();
        ColorsListDB.addAll(databaseQueryClass.getAllColor(requireContext(), Shade_Card_ID));

    }

    public void RefreshAfterSelectColor(String id, String colourName, String colourCode, String tint1, String tint1Value, String tint2, String tint2Value, String tint3, String tint3Value, String tint4, String tint4Value, String tint5, String tint5Value) {

        edt_color_name.setText(colourName);
        edt_color_code.setText(colourCode);
        Color_ID = id;

        Log.e(TAG, "FILTER :: Color ID :: "+ Color_ID);

        Color_Code = colourCode;
        filterDialog.dismiss();

        ProductBaseDB.clear();
        ProductBaseDB.addAll(databaseQueryClass.getAllBase(requireContext()));

        Tint1Id=tint1;
        Tint1Value=tint1Value;


        Tint2Id=tint2;
        Tint2Value=tint2Value;

        Tint3Id=tint3;
        Tint3Value=tint3Value;

        Tint4Id=tint4;
        Tint4Value=tint4Value;

        Tint5Id=tint5;
        Tint5Value=tint5Value;


    }

    public void RefreshAfterSelectProductBase(String id, String productBaseName, int position) {
        edt_base.setText(productBaseName);
        filterDialog.dismiss();
        Base_ID = id;

        Log.e(TAG, "FILTER :: Base ID :: "+ Base_ID);

        //       FetchPackSize(position);
        ProductPackDB.clear();
        ProductPackDB.addAll(databaseQueryClass.getAllPack(requireContext(), Base_ID));

    }


    public void RefreshAfterSelectPackSize(String id, String productSizeName,String packAmount) {
        edt_color_pack_size.setText(productSizeName);
        filterDialog.dismiss();
        Pack_ID = id;

        Log.e(TAG, "FILTER :: PackSize ID :: "+ Pack_ID);


        PackAmount=packAmount;
    }
    private void CalculatePrice() {

        Log.e(TAG, "FILTER :: NoOfCan  :: "+ edt_no_of_can.getText().toString());

        int SingleTint1CostLtr= Integer.parseInt(databaseQueryClass.getTintCost(Tint1Id));

        float SingleTint1CostMl= ((float) Integer.parseInt(databaseQueryClass.getTintCost(Tint1Id)) /1000)*Integer.parseInt(Tint1Value);


        Log.e(TAG, "CalculatePrice: Tintt 1Price ::"+SingleTint1CostMl );
        float SingleTint2CostMl= ((float) Integer.parseInt(databaseQueryClass.getTintCost(Tint2Id)) /1000)*Integer.parseInt(Tint2Value);
        Log.e(TAG, "CalculatePrice: Tintt 2Price ::"+SingleTint2CostMl );

        float SingleTint3CostMl= ((float) Integer.parseInt(databaseQueryClass.getTintCost(Tint3Id)) /1000)*Integer.parseInt(Tint3Value);
        Log.e(TAG, "CalculatePrice: Tintt 3Price ::"+SingleTint3CostMl );

        float SingleTint4CostMl= ((float) Integer.parseInt(databaseQueryClass.getTintCost(Tint4Id)) /1000)*Integer.parseInt(Tint4Value);
        Log.e(TAG, "CalculatePrice: Tintt 4Price ::"+SingleTint4CostMl );

        float SingleTint5CostMl= ((float) Integer.parseInt(databaseQueryClass.getTintCost(Tint5Id)) /1000)*Integer.parseInt(Tint5Value);
        Log.e(TAG, "CalculatePrice: Tintt 5Price ::"+SingleTint5CostMl );

        float TotalTintAmount=SingleTint1CostMl+SingleTint2CostMl+SingleTint3CostMl+SingleTint4CostMl+SingleTint5CostMl;
        Log.e(TAG, "CalculatePrice: TotalTintAmount ::"+TotalTintAmount );

     //   float totalTintAmtWithCan=TotalTintAmount*Integer.parseInt(edt_no_of_can.getText().toString().trim());
        //   Toast.makeText(requireContext(),"PerMLAmount :"+String.valueOf(SingleTint1CostMl)+"LiterAmount :"+String.valueOf(SingleTint1CostLtr),Toast.LENGTH_SHORT).show();
//        Toast.makeText(requireContext(),"Total :"+String.valueOf(TotalAmount),Toast.LENGTH_SHORT).show();

        int BaseAmount=Integer.parseInt(PackAmount);

      //  int BaseAmount= (Integer.parseInt(PackAmount)*Integer.parseInt(edt_no_of_can.getText().toString().trim()));

        Log.e(TAG, "CalculatePrice: TotalBaseAmt ::"+BaseAmount );

        float totalBaseWithTint=(BaseAmount+TotalTintAmount);

        Log.e(TAG, "CalculatePrice: TotalBaseWithTint ::"+totalBaseWithTint );

        double amountOnlyMarin = (totalBaseWithTint / 100.0f) * 10;

        Log.e(TAG, "CalculatePrice: OnlyMargin ::"+amountOnlyMarin );

       double amountWithMarin=amountOnlyMarin+totalBaseWithTint;

        Log.e(TAG, "CalculatePrice: WithMargin ::"+amountWithMarin );

        double amountOnlyGST = (amountWithMarin / 100.0f) * 18;

        Log.e(TAG, "CalculatePrice: OnlyGST ::"+amountOnlyGST );

       double SinglePackAmountWithGST=amountOnlyGST+amountWithMarin;

        Log.e(TAG, "CalculatePrice: WithGST ::"+SinglePackAmountWithGST );

        double AllPackTotalAmount=SinglePackAmountWithGST*Integer.parseInt(edt_no_of_can.getText().toString());


        double AllPackTotalAmountRound = roundMyData(AllPackTotalAmount,2);

        Toast.makeText(requireContext(),"Total :"+AllPackTotalAmountRound,Toast.LENGTH_SHORT).show();


        txt_total_price.setVisibility(View.VISIBLE);
        txt_total_price.setText("Total Price : " + AllPackTotalAmountRound);


        btn_next.setVisibility(View.VISIBLE);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        //Creating Command
        DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        String Stamp = "SL" + df.format(Calendar.getInstance().getTime());
        //current Date
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd");
        Date myDate = new Date();
        String CurrentDate = timeStampFormat.format(myDate);
        //currentTime
        SimpleDateFormat timeStampFormat2 = new SimpleDateFormat("HHmm");
        Date myDate2 = new Date();
        String CurrentTime = timeStampFormat2.format(myDate2);

        String ColorName = edt_color_name.getText().toString();
        ColorName = ColorName.replace(" ", "_");
        String base = Base_ID;
        String packSize = edt_color_pack_size.getText().toString();
        String noOfCan = edt_no_of_can.getText().toString();

        String colorant1 = Tint1Id;
        String amount1 = Tint1Value;
        String colorant2 =Tint2Id;
        String amount2 =Tint2Value;
        String colorant3 = Tint3Id;
        String amount3 = Tint3Value;
        String colorant4 = Tint4Id;
        String amount4 = Tint4Value;
        String colorant5 = Tint5Id;
        String amount5 = Tint5Value;

        String Command1 = "DC01" + "|" + Stamp + "|" + CurrentDate + "|" + CurrentTime + "|" + Product_ID + "|" + ColorName + "|" + Color_Code + "|" + base + "|" + packSize + "|" + noOfCan + "|" +
                colorant1 + "|" + amount1 + "|" + colorant2 + "|" + amount2 + "|" + colorant3 + "|" + amount3 + "|" + colorant4 + "|" + amount4 + "|" + colorant5 + "|" + amount5 + "|";

        //Starting from base prize
        String basePrice = PackAmount;
        String ColorantPrice = String.valueOf(TotalTintAmount);
        String Margin = String.valueOf(amountOnlyMarin);
        String Gst = String.valueOf(amountOnlyGST);
        String Total = String.valueOf(AllPackTotalAmountRound);

        String Command3 = basePrice + "|" + ColorantPrice + "|" + Margin + "|" + Gst + "|" + Total;

        SharedPreferences sp = requireContext().getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constant.DISPENSING_COMMAND1, Command1);
        editor.putString(Constant.DISPENSING_COMMAND3, Command3);
        editor.putString(Constant.BILL_TOTAL_PRICE, String.valueOf(AllPackTotalAmountRound));
        editor.putString(Constant.BILL_COLOR_NAME, edt_color_name.getText().toString());
        editor.apply();

    }
    private void FetchCalculatedPrice() {
        if (Color_ID.equalsIgnoreCase("")) {
            Toast.makeText(requireActivity(), "Please Select Color !", Toast.LENGTH_SHORT).show();
        } else if (Color_Code.equalsIgnoreCase("")) {
            Toast.makeText(requireActivity(), "Please Select Color !", Toast.LENGTH_SHORT).show();
        } else if (Base_ID.equalsIgnoreCase("")) {
            Toast.makeText(requireActivity(), "Please Select Color Base !", Toast.LENGTH_SHORT).show();
        } else if (Pack_ID.equalsIgnoreCase("")) {
            Toast.makeText(requireActivity(), "Please Select Pack !", Toast.LENGTH_SHORT).show();
        } else if (edt_no_of_can.getText().toString().isEmpty()) {
            Toast.makeText(requireActivity(), "Please Enter No Of Can !", Toast.LENGTH_SHORT).show();
        } else {

            Log.e(TAG, "FetchCalculatedPrice: " + User_ID + " / " + Color_ID + " / " + Color_Code + " / " + Base_ID + " / " + Pack_ID + " / " + edt_no_of_can.getText().toString());

            RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), User_ID);
            RequestBody ColorID = RequestBody.create(MediaType.parse("txt/plain"), Color_ID);
            RequestBody ColorCode = RequestBody.create(MediaType.parse("txt/plain"), Color_Code);
            RequestBody BaseID = RequestBody.create(MediaType.parse("txt/plain"), Base_ID);
            RequestBody PackID = RequestBody.create(MediaType.parse("txt/plain"), Pack_ID);
            RequestBody NoOfPack = RequestBody.create(MediaType.parse("txt/plain"), edt_no_of_can.getText().toString().trim());

            RestClient.getClient().FetchCalculatedPrice(UserID, ColorID, ColorCode, BaseID, PackID, NoOfPack).enqueue(new Callback<ResponceFetchCalculatePrice>() {
                @Override
                public void onResponse(Call<ResponceFetchCalculatePrice> call, Response<ResponceFetchCalculatePrice> response) {
                    Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                    Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                    if (response.isSuccessful()) {
                        ResponceFetchCalculatePrice.Data listResponse = response.body().getData();

                        txt_total_price.setVisibility(View.VISIBLE);
                        txt_total_price.setText("Total Price : " + response.body().getData().getPrice().getAllPackTotal().toString());


                        btn_next.setVisibility(View.VISIBLE);
                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        });

                        //Creating Command
                        DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
                        String Stamp = "SL" + df.format(Calendar.getInstance().getTime());
                        //current Date
                        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd");
                        Date myDate = new Date();
                        String CurrentDate = timeStampFormat.format(myDate);
                        //currentTime
                        SimpleDateFormat timeStampFormat2 = new SimpleDateFormat("HHmm");
                        Date myDate2 = new Date();
                        String CurrentTime = timeStampFormat2.format(myDate2);

                        String ColorName = edt_color_name.getText().toString();
                        ColorName = ColorName.replace(" ", "_");
                        String base = listResponse.getBase().getId().toString();
                        String packSize = listResponse.getBase().getPacks().get(0).getProductPackSize().toString();
                        String noOfCan = edt_no_of_can.getText().toString();

                        String colorant1 = listResponse.getColour().getTintType1().toString();
                        String amount1 = listResponse.getColour().getTintType1Value().toString();
                        String colorant2 = listResponse.getColour().getTintType2().toString();
                        String amount2 = listResponse.getColour().getTintType2Value().toString();
                        String colorant3 = listResponse.getColour().getTintType3().toString();
                        String amount3 = listResponse.getColour().getTintType3Value().toString();
                        String colorant4 = listResponse.getColour().getTintType4().toString();
                        String amount4 = listResponse.getColour().getTintType4Value().toString();
                        String colorant5 = listResponse.getColour().getTintType5().toString();
                        String amount5 = listResponse.getColour().getTintType5Value().toString();

                        String Command1 = "DC01" + "|" + Stamp + "|" + CurrentDate + "|" + CurrentTime + "|" + Product_ID + "|" + ColorName + "|" + Color_Code + "|" + base + "|" + packSize + "|" + noOfCan + "|" +
                                colorant1 + "|" + amount1 + "|" + colorant2 + "|" + amount2 + "|" + colorant3 + "|" + amount3 + "|" + colorant4 + "|" + amount4 + "|" + colorant5 + "|" + amount5 + "|";

                        //Starting from base prize
                        String basePrice = listResponse.getPrice().getSinglePackBasePrice();
                        String ColorantPrice = listResponse.getPrice().getSinglePackTintPrice();
                        String Margin = listResponse.getPrice().getSinglePackMargin();
                        String Gst = listResponse.getPrice().getSinglePackTax();
                        String Total = listResponse.getPrice().getAllPackTotal();

                        String Command3 = basePrice + "|" + ColorantPrice + "|" + Margin + "|" + Gst + "|" + Total;

                        SharedPreferences sp = requireContext().getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(Constant.DISPENSING_COMMAND1, Command1);
                        editor.putString(Constant.DISPENSING_COMMAND3, Command3);
                        editor.putString(Constant.BILL_TOTAL_PRICE, response.body().getData().getPrice().getAllPackTotal());
                        editor.putString(Constant.BILL_COLOR_NAME, response.body().getData().getColour().getColourName());
                        editor.apply();
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ResponceFetchCalculatePrice> call, Throwable t) {

                }
            });
        }
    }

    public boolean isEmpty(String TableName) {
        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        long NoOfRows = DatabaseUtils.queryNumEntries(database, TableName);

        if (NoOfRows == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void FetchProductNamesListDB() {


        ProductNameDB.clear();
        //  adapterProductName.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");

        RestClient.getClient().FetchProductNameDB(UserID).enqueue(new Callback<ReesponceFetchProductName>() {
            @Override
            public void onResponse(Call<ReesponceFetchProductName> call, Response<ReesponceFetchProductName> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    ReesponceFetchProductName.Data listResponse = response.body().getData();


                    for (ReesponceFetchProductName.Data.Product list : listResponse.getProducts()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductName.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }
                    if (isEmpty(Config.TABLE_PRODUCT)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcess Product ::");
                        //call Section insert process
                        databaseHelper.insertProduct(ProductName);


                    }
                    // adapterProductsType.notifyDataSetChanged();
                    edt_product_name.setClickable(true);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ReesponceFetchProductName> call, Throwable t) {

            }
        });

    }
    private void FetchProductColorListDB() {


        ProductNameDB.clear();
        //  adapterProductName.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");

        RestClient.getClient().FetchProductColorDB(UserID).enqueue(new Callback<ReesponceFetchColorNameDB>() {
            @Override
            public void onResponse(Call<ReesponceFetchColorNameDB> call, Response<ReesponceFetchColorNameDB> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    ReesponceFetchColorNameDB.Data listResponse = response.body().getData();


                    for (ReesponceFetchColorNameDB.Data.Colour list : listResponse.getColours()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductColor.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }
                    if (isEmpty(Config.TABLE_COLOR)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcess Product ::");
                        //call Section insert process
                        databaseHelper.insertColor(ProductColor);


                    }
                    // adapterProductsType.notifyDataSetChanged();
                    edt_product_name.setClickable(true);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ReesponceFetchColorNameDB> call, Throwable t) {

            }
        });

    }

    private void FetchShadeCardlistDB() {


        ProductSahdeCardDB.clear();
        //  adapterProductsType.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");
        //     RequestBody ProductID = RequestBody.create(MediaType.parse("txt/plain"), Product_ID);

        RestClient.getClient().FetchShadeCardDB(UserID).enqueue(new Callback<ReesponceFetchShadeCard>() {
            @Override
            public void onResponse(Call<ReesponceFetchShadeCard> call, Response<ReesponceFetchShadeCard> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    ReesponceFetchShadeCard.Data listResponse = response.body().getData();


                    for (ReesponceFetchShadeCard.Data.ProductsShadeCard list : listResponse.getProductsShadeCards()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductSahdeCard.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }
                    if (isEmpty(Config.TABLE_SHADE)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcess Shade ::");
                        //call Section insert process
                        databaseHelper.insertShadeCard(ProductSahdeCard);


                    }
                    //  adapterProductsType.notifyDataSetChanged();
                    edt_shade_card.setClickable(true);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ReesponceFetchShadeCard> call, Throwable t) {

            }
        });

    }

    private void FetchProductColorTintDB() {


        ProductNameDB.clear();
        //  adapterProductName.notifyDataSetChanged();
        RequestBody UserID = RequestBody.create(MediaType.parse("txt/plain"), "3");

        RestClient.getClient().FetchProductColorTint(UserID).enqueue(new Callback<ResponseFetchTints>() {
            @Override
            public void onResponse(Call<ResponseFetchTints> call, Response<ResponseFetchTints> response) {
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.code());
                Log.e(TAG, "onResponse 2 QuestionPalette :: " + response.isSuccessful());


                if (response.isSuccessful() && response.body() != null) {
                    ResponseFetchTints.Data listResponse = response.body().getData();


                    for (ResponseFetchTints.Data.Tint list : listResponse.getTints()) {
                        Log.e(TAG, "onResponse: block LIst " + list);
                        ProductColorTint.add(list);
                        Log.e(TAG, "onResponse: Blick List :" + list);

                    }
                    if (isEmpty(Config.TABLE_TINT)) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(requireActivity());
                        Log.e(TAG, "onCreateView: Reached InsertionProcess Tint ::");
                        //call Section insert process
                        databaseHelper.insertTint(ProductColorTint);


                    }
                    // adapterProductsType.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseFetchTints> call, Throwable t) {

            }
        });

    }

    public static double roundMyData(double Rval, int numberOfDigitsAfterDecimal) {
        double p = (float)Math.pow(10,numberOfDigitsAfterDecimal);
        Rval = Rval * p;
        double tmp = Math.floor(Rval);
        System.out.println("~~~~~~tmp~~~~~"+tmp);
        return (double)tmp/p;
    }
}