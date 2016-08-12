package disono.webmons.com.clean_architecture.util.sensor.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 8/8/2016 12:05 PM
 */
public class AcceptThread extends Thread {
    private Activity activity;
    private BluetoothServerSocket mmServerSocket;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mSocket = null;

    private final String TAG = "AcceptThread:Class";
    private String serverName = "BTServer";

    public AcceptThread(Activity activity) {
        this.activity = activity;
    }

    /**
     * Initialize
     *
     * @param UUID
     */
    public void init(String UUID) {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        mmServerSocket = null;

        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }

        try {
            // UUID is the app's UUID string, also used by the client code
            mmServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(serverName, java.util.UUID.fromString(UUID));
        } catch (IOException e) {
            Timber.e(TAG + ", Error: %s", e.getMessage());
        }
    }

    /**
     * Run
     */
    public void run() {
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                mSocket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }

            // If a connection was accepted
            if (mSocket != null) {
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    Timber.e(TAG + ", Error: %s", e.getMessage());
                }

                break;
            }
        }
    }

    /**
     * Get the bluetooth current socket
     *
     * @return
     */
    public BluetoothSocket getSocket() {
        return mSocket;
    }

    /**
     * Close the connection
     */
    public void cancel() {
        try {
            mSocket.close();
        } catch (IOException e) {
            Timber.e(TAG + ", Error: %s", e.getMessage());
        }
    }
}