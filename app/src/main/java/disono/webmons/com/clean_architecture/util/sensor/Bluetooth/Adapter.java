package disono.webmons.com.clean_architecture.util.sensor.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 8/8/2016 11:19 AM
 */
public class Adapter {
    private Activity activity;
    private final String TAG = "Adapter:Class";

    public final int REQUEST_ENABLE_BT = 100;

    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;
    List<BluetoothDevice> discoverDevicesAdapter;

    public Adapter(Activity activity) {
        this.activity = activity;
    }

    /**
     * Broadcast receiver for list of discovered devices
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                discoverDevicesAdapter.add(device);
            }
        }
    };

    /**
     * Enable bluetooth
     *
     * Result on onActivityResult()
     * REQUEST_ENABLE_BT
     */
    public void enable() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // is bluetooth supported on this device
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "Device does not support Bluetooth.");
        } else {
            // check if the bluetooth is currently enabled
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    /**
     * Enable discovery by other devices
     *
     * Result on onActivityResult()
     */
    public void enableDiscoverable(int duration) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, duration);
        activity.startActivity(discoverableIntent);
    }

    /**
     * Enable discovery by other devices (no duration set, default is 300)
     *
     * Result on onActivityResult()
     */
    public void enableDiscoverable() {
        this.enableDiscoverable(300);
    }

    /**
     * List all paired devices
     *
     * @return
     */
    public List<BluetoothDevice> pairedDevices() {
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<BluetoothDevice> pairedDevicesAdapter = new ArrayList<>();

        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                pairedDevicesAdapter.add(device);
            }
        }

        return pairedDevicesAdapter;
    }

    /**
     * List all discovered devices
     *
     * @return
     */
    public List<BluetoothDevice> discoverDevices() {
        // Register the BroadcastReceiver
        final IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        discoverDevicesAdapter = new ArrayList<>();

        // Don't forget to unregister during onDestroy
        activity.registerReceiver(mReceiver, filter);

        return discoverDevicesAdapter;
    }

    /**
     * Unregister discovery
     */
    public void unregisterDiscovery() {
        activity.unregisterReceiver(mReceiver);
    }
}
