package com.iot.mechatronix;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.google.android.material.navigation.NavigationView;
import com.md.animatedbottomnavigationbarlib.ABottomNavigation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


    public class DashBoardActivity extends AppCompatActivity {

        String ErrorMsg="";
   public LinearLayout search_layout;
    Button button_reconnect;
    //Assuming that you have device address and is connected
    private String partnerDevAdd = "";
    private boolean isConnected = true;
    MediaPlayer mp;
    ImageView btn_menu;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            android.Manifest.permission.BLUETOOTH_SCAN,
            android.Manifest.permission.BLUETOOTH_CONNECT,
            android.Manifest.permission.BLUETOOTH_PRIVILEGED
    };
    private static String[] PERMISSIONS_LOCATION = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            android.Manifest.permission.BLUETOOTH_SCAN,
            android.Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_PRIVILEGED
    };
    Toolbar toolbar;

    ActionBarDrawerToggle drawerToggle;
    DrawerLayout mDrawer;
    public NavigationView nvDrawer;

    LabeledSwitch switch_new;

    //   LinearLayout send_layout;
    OutputStream mmOutputStream;
    InputStream mmInputStream;

    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    public final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status

    RelativeLayout btn_next;
    // GUI Components
    public TextView mBluetoothStatus;
    public TextView mReadBuffer;
    private Button mScanBtn;

    private Button mOffBtn;
    private Button mListPairedDevicesBtn;
    private Button mDiscoverBtn;
    public ListView mDevicesListView;
    private CheckBox mLED1;

    private BluetoothAdapter mBTAdapter;
    private Set<BluetoothDevice> mPairedDevices;
    private ArrayAdapter<String> mBTArrayAdapter;

    private Handler mHandler; // Our main handler that will receive callback notifications
    private ConnectedThread mConnectedThread; // bluetooth background worker thread to send and receive data
    public BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path

    private static final String TAG = "DashBoardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        search_layout=findViewById(R.id.search_layout);
        mp = MediaPlayer.create(DashBoardActivity.this, R.raw.click_sound);
        button_reconnect = findViewById(R.id.button_reconnect);
        DashBoardFragment dashboardFragment = (DashBoardFragment) new DashBoardFragment();
        setFragment(dashboardFragment);

        ABottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new ABottomNavigation.Model(1, R.drawable.home,"Home"));
        bottomNavigation.add(new ABottomNavigation.Model(2, R.drawable.create,"Create"));
        bottomNavigation.add(new ABottomNavigation.Model(3, R.drawable.maintenance,"Maintenance"));

        //set default
        bottomNavigation.show(1, true);
        bottomNavigation.setOnClickMenuListener(new Function1<ABottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(ABottomNavigation.Model model) {
                Log.e(TAG, "invoke: Model ::" + model.getId());
                switch (model.getId()) {
                    case 1:
                        mp.start();
                        openFragment(DashBoardFragment.newInstance("", ""));
                       //  ConnectionStatus();

                        break;
                    case 2:
                        mp.start();
                        openFragment(CreateColorFragment.newInstance("", ""));

                        break;
                    case 3:
                        mp.start();
                        openFragment(MaintenanceFragment.newInstance("", ""));

                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnClickMenuListener(new Function1<ABottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(ABottomNavigation.Model model) {
                Log.e(TAG, "invoke: Model ::" + model.getId());
                switch (model.getId()) {
                    case 1:
                        mp.start();
                        openFragment(DashBoardFragment.newInstance("", ""));

                        break;
                    case 2:
                        mp.start();
                        
                        openFragment(CreateColorFragment.newInstance("", ""));

                        break;
                    case 3:
                        mp.start();
                        openFragment(MaintenanceFragment.newInstance("", ""));

                        break;
                }
                return null;
            }
        });



        mBluetoothStatus = (TextView) findViewById(R.id.bluetooth_status);
        mReadBuffer = (TextView) findViewById(R.id.read_buffer);


//        nvDrawer = (NavigationView) findViewById(R.id.nv_view);
//        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        mDrawer.addDrawerListener(drawerToggle);
//        drawerToggle = setupDrawerToggle();
//        drawerToggle.setDrawerIndicatorEnabled(true);
//        drawerToggle.syncState();
//        nvDrawer.setNavigationItemSelectedListener(this);

//            HomeLayout homeFragment = (HomeLayout) new HomeLayout();
//            setFragment(homeFragment);
//        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDrawer.openDrawer(GravityCompat.START);
//            }
//        });
        btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);

            }
        });
        mBTArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio

        mDevicesListView = (ListView) findViewById(R.id.devices_list_view);
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);




        // Ask for location permission if not already allowed
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_READ) {
                    String readMessage = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        readMessage = new String((byte[]) msg.obj, StandardCharsets.UTF_8);
                    }
                    mReadBuffer.setText(readMessage);

                    CreateColorProcessingFragment homeFragment = (CreateColorProcessingFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                    if (homeFragment != null) {
                        homeFragment.OutPutmethod(readMessage);

                    }
                }


                if (msg.what == CONNECTING_STATUS) {
                    char[] sConnected;
                    if (msg.arg1 == 1) {
                        mBluetoothStatus.setText(getString(R.string.BTConnected) + msg.obj);
                        mDevicesListView.setVisibility(View.GONE);
                        search_layout.setVisibility(View.GONE);
                        DashBoardFragment homeFragment = (DashBoardFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                        if (homeFragment != null) {
                            homeFragment.CheckScanningDeviceConnected();
                        }
                    //    img_connect.setImageDrawable(getResources().getDrawable(R.drawable.connected));
                        //  send_layout.setVisibility(View.VISIBLE);
                    } else {
                        mBluetoothStatus.setText(getString(R.string.BTconnFail));
                     //   img_connect.setImageDrawable(getResources().getDrawable(R.drawable.disconnected));

                    }
                }
            }
        };

        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            mBluetoothStatus.setText(getString(R.string.sBTstaNF));
            Toast.makeText(getApplicationContext(), getString(R.string.sBTdevNF), Toast.LENGTH_SHORT).show();
        } else {

            switch_new = findViewById(R.id.switch_new);
            if (mBTAdapter.isEnabled()) {

                switch_new.setOn(true);
            } else {
                switch_new.setOn(false);
            }
            switch_new.setOnToggledListener(new OnToggledListener() {
                @Override
                public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                    Log.e(TAG, "onSwitched: " + isOn);
                    if (isOn) {
                        bluetoothOn();
                    } else {
                        bluetoothOff();
                    }

                    Log.e(TAG, "onSwitched: Switch Status :" + isOn);
                }


            });

            button_reconnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences spp = getSharedPreferences(Constant.BLUETOOTH_PREF, Context.MODE_PRIVATE);
                    String Address = spp.getString(Constant.PREVIOUS_DEVICE_ADDRESS, "");
                    String Name = spp.getString(Constant.PREVIOUS_DEVICE_NAME, "");
                    Log.e(TAG, "onClick: "+partnerDevAdd );

                    if(!Address.equalsIgnoreCase("")){
                        new Thread() {
                            @Override
                            public void run() {
                                boolean fail = false;

                                BluetoothDevice device = mBTAdapter.getRemoteDevice(Address);

                                try {
                                    mBTSocket = createBluetoothSocket(device);
                                } catch (IOException e) {
                                    fail = true;
                                    Toast.makeText(getBaseContext(), getString(R.string.ErrSockCrea), Toast.LENGTH_SHORT).show();
                                }
                                // Establish the Bluetooth socket connection.
                                try {
                                    if (ActivityCompat.checkSelfPermission(DashBoardActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling
                                        //    ActivityCompat#requestPermissions
                                        // here to request the missing permissions, and then overriding
                                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                        //                                          int[] grantResults)
                                        // to handle the case where the user grants the permission. See the documentation
                                        // for ActivityCompat#requestPermissions for more details.
                                        return;
                                    }
                                    mBTSocket.connect();
                                } catch (IOException e) {
                                    try {
                                        fail = true;
                                        mBTSocket.close();
                                        mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                                .sendToTarget();
                                    } catch (IOException e2) {
                                        //insert code to deal with this
                                        Toast.makeText(getBaseContext(), getString(R.string.ErrSockCrea), Toast.LENGTH_SHORT).show();
                                    }
                                }
                                if (!fail) {
                                    mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                                    mConnectedThread.start();

                                    mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, Name)
                                            .sendToTarget();
                                }
                            }
                        }.start();
                    }

                }
            });
        }
        checkPermissions();

        //check bluetooth on or off
        if (!mBTAdapter.isEnabled()) {
            bluetoothOn();
          //  ConnectionStatus();

        }
//        if(mBTSocket!=null){
//            if(mBTSocket.isConnected()){
//                DashBoardFragment homeFragment = (DashBoardFragment) getSupportFragmentManager().findFragmentById(R.id.container);
//                if (homeFragment != null) {
//                    homeFragment.UpdateSocketImage();
//                }
//            }
//        }

    }

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        assert fragment != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        fragmentTransaction.replace(R.id.container, fragment);
        // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void bluetoothOn() {
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            mBluetoothStatus.setText(getString(R.string.BTEnable));
            Toast.makeText(getApplicationContext(), getString(R.string.sBTturON), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.BTisON), Toast.LENGTH_SHORT).show();
        }
    }

    // Enter here after user selects "yes" or "no" to enabling radio
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, Data);
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                mBluetoothStatus.setText(getString(R.string.sEnabled));
            } else
                mBluetoothStatus.setText(getString(R.string.sDisabled));
        }
    }

    private void bluetoothOff() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mBTAdapter.disable(); // turn off
        mBluetoothStatus.setText(getString(R.string.sBTdisabl));
        Toast.makeText(getApplicationContext(), "Bluetooth turned Off", Toast.LENGTH_SHORT).show();
    }

    public void discover() {
        // Check if the device is already discovering
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (mBTAdapter.isDiscovering()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mBTAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(), getString(R.string.DisStop), Toast.LENGTH_SHORT).show();
        } else {
            if (mBTAdapter.isEnabled()) {
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(), getString(R.string.DisStart), Toast.LENGTH_SHORT).show();
                registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.BTnotOn), Toast.LENGTH_SHORT).show();
            }
        }
    }

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name to the list
                if (ActivityCompat.checkSelfPermission(DashBoardActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                //              mBTArrayAdapter.add(device.getName());

                mBTArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    private void listPairedDevices() {
        mBTArrayAdapter.clear();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mPairedDevices = mBTAdapter.getBondedDevices();
        if (mBTAdapter.isEnabled()) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), getString(R.string.show_paired_devices), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), getString(R.string.BTnotOn), Toast.LENGTH_SHORT).show();
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (!mBTAdapter.isEnabled()) {
                Toast.makeText(getBaseContext(), getString(R.string.BTnotOn), Toast.LENGTH_SHORT).show();
                return;
            }

            mBluetoothStatus.setText(getString(R.string.cConnet));
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) view).getText().toString();
            final String address = info.substring(info.length() - 17);
            final String name = info.substring(0, info.length() - 17);

            Log.e(TAG, "onItemClick: "+address );
            partnerDevAdd = address;

            SharedPreferences sp = getSharedPreferences(Constant.BLUETOOTH_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(Constant.PREVIOUS_DEVICE_ADDRESS,address);
            editor.putString(Constant.PREVIOUS_DEVICE_NAME, name);
            editor.apply();

            // Spawn a new thread to avoid blocking the GUI one
            new Thread() {
                @Override
                public void run() {
                    boolean fail = false;

                    BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                    try {
                        mBTSocket = createBluetoothSocket(device);
                    } catch (IOException e) {
                        fail = true;
                        Toast.makeText(getBaseContext(), getString(R.string.ErrSockCrea), Toast.LENGTH_SHORT).show();
                    }
                    // Establish the Bluetooth socket connection.
                    try {
                        if (ActivityCompat.checkSelfPermission(DashBoardActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        mBTSocket.connect();
                        DashBoardFragment homeFragment = (DashBoardFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                        if (homeFragment != null) {
                            homeFragment.UpdateSocketImage();
                        }
                    } catch (IOException e) {
                        try {
                            fail = true;
                            mBTSocket.close();
                            mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                    .sendToTarget();
                        } catch (IOException e2) {
                            //insert code to deal with this
                            Toast.makeText(getBaseContext(), getString(R.string.ErrSockCrea), Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (!fail) {
                        mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                        mConnectedThread.start();

                        mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                .sendToTarget();
                    }
                }
            }.start();
        }
    };

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection", e);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        return device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }

    public void sendData(String s, String feedback) throws IOException {
        // String msg = edt_data.getText().toString();
        String msg = s;
        msg += "\n";
        if (mmOutputStream != null) {
            mmOutputStream.write(msg.getBytes());
            //   edt_data.setText("");
            hideKeyboard(DashBoardActivity.this);
            //      Toast.makeText(getBaseContext(), "Data Submitted ! ", Toast.LENGTH_SHORT).show();
            mHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == MESSAGE_READ) {
                        String readMessage = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            readMessage = new String((byte[]) msg.obj, StandardCharsets.UTF_8);
                        }
                        mReadBuffer.setText(readMessage);


                    }
                }
            };
            //           beginListenForData();
//           String InputMsg=mBTSocket.getInputStream().toString();
//
            //      Toast.makeText(getBaseContext(), "Data Recived :: "+readMessage, Toast.LENGTH_SHORT).show();


        } else {
            try {
                mmOutputStream = mBTSocket.getOutputStream();
                try {
                    sendData(s, "Progress");
                    Log.e(TAG, "onClick: Reached :: 11::");
                } catch (IOException e) {
                    Log.e(TAG, "onClick: Reached :: 22::" + e.getMessage());

                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), ("Fatal Error In onResume(), input and output stream creation failed:" + e.getMessage()), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);

    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        drawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggles
//        drawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    private void removeHighLight() {
//        int noOfItems = nvDrawer.getMenu().size();
//        for (int i = 0; i < noOfItems; i++) {
//            nvDrawer.getMenu().getItem(i).setChecked(false);
//        }
//    }
//
//
//    public void closeDrawer() {
//        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
//            mDrawer.closeDrawer(GravityCompat.START);
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.paired_btn:
//                removeHighLight();
//                closeDrawer();
//                mDevicesListView.setVisibility(View.VISIBLE);
//                //     send_layout.setVisibility(View.GONE);
//                listPairedDevices();
//                break;
//            case R.id.discover:
//                removeHighLight();
//                closeDrawer();
//                mDevicesListView.setVisibility(View.VISIBLE);
//                //     send_layout.setVisibility(View.GONE);
//                discover();
//
//        }
//        return true;
//
//    }

    private void checkPermissions() {
        int permission1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN);
        if (permission1 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    1
            );
        } else if (permission2 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_LOCATION,
                    1
            );
        }
    }

   public void ReConnectPreviousDevice(){
       SharedPreferences spp = getSharedPreferences(Constant.BLUETOOTH_PREF, Context.MODE_PRIVATE);
       String Address = spp.getString(Constant.PREVIOUS_DEVICE_ADDRESS, "");
       String Name = spp.getString(Constant.PREVIOUS_DEVICE_NAME, "");

       if(!Address.equalsIgnoreCase("")){
           new Thread() {
               @Override
               public void run() {
                   boolean fail = false;

                   BluetoothDevice device = mBTAdapter.getRemoteDevice(Address);

                   try {
                       Log.e(TAG, "run: 11 ::");
                       mBTSocket = createBluetoothSocket(device);
                   } catch (IOException e) {
                       Log.e(TAG, "run: 22 ::");
                       fail = true;
                       Toast.makeText(getBaseContext(), getString(R.string.ErrSockCrea), Toast.LENGTH_SHORT).show();
                   }
                   // Establish the Bluetooth socket connection.
                   try {
                       if (ActivityCompat.checkSelfPermission(DashBoardActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                           // TODO: Consider calling
                           //    ActivityCompat#requestPermissions
                           // here to request the missing permissions, and then overriding
                           //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                           //                                          int[] grantResults)
                           // to handle the case where the user grants the permission. See the documentation
                           // for ActivityCompat#requestPermissions for more details.
                           return;
                       }
                       mBTSocket.connect();
                    //   img_connect.setImageDrawable(getResources().getDrawable(R.drawable.connected));
                   } catch (IOException e) {
                       try {
                           ErrorMsg=e.getMessage();
                           Log.e(TAG, "run: 33 ::"+e.getMessage());
                           fail = true;
                           mBTSocket.close();
                           mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                   .sendToTarget();
                       } catch (IOException e2) {
                           //insert code to deal with this
                           Toast.makeText(getBaseContext(), getString(R.string.ErrSockCrea), Toast.LENGTH_SHORT).show();
                       }
                   }
                   if (!fail) {
                       mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                       mConnectedThread.start();

                       mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, Name)
                               .sendToTarget();
                   }else {
                       if(ErrorMsg.equalsIgnoreCase("read failed, socket might closed or timeout, read ret: -1")){
                         new Handler(Looper.getMainLooper()).post(new Runnable() {
                               @Override
                               public void run() {
                                   Toast.makeText(getBaseContext(), "Might be your device is OFF ! Please turn or your Device .", Toast.LENGTH_SHORT).show();

                               }
                           });
                       }
                       Log.e(TAG, "run: 44 ::");
                   }
               }
           }.start();
       }
   };

    public void ConnectionStatus(){
        if(mBTSocket.isConnected()){
            DashBoardFragment homeFragment = (DashBoardFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (homeFragment != null) {
                homeFragment.UpdateSocketImage();
            }
        }else {
            DashBoardFragment homeFragment = (DashBoardFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (homeFragment != null) {
                homeFragment.UpdateSocketImageDisconnected();
            }
        }
    };

    // Create a BroadcastReceiver for bluetooth related checks





        public void statusCheck() {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();

            }
        }

        private void buildAlertMessageNoGps() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
}