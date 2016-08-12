package disono.webmons.com.clean_architecture.util.sensor.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 8/12/2016 5:26 PM
 */
public class ManageConnectThread extends Thread {
    private Activity activity;

    private final String TAG = "ManageConnectThread:Class";

    public ManageConnectThread(Activity activity) {
        this.activity = activity;
    }

    /**
     * Send data
     *
     * @param socket
     * @param data
     * @throws IOException
     */
    public void sendData(BluetoothSocket socket, int data) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(4);
        output.write(data);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(output.toByteArray());
    }

    /**
     * Received data
     *
     * @param socket
     * @return
     * @throws IOException
     */
    public int receiveData(BluetoothSocket socket) throws IOException{
        byte[] buffer = new byte[4];
        ByteArrayInputStream input = new ByteArrayInputStream(buffer);
        InputStream inputStream = socket.getInputStream();

        inputStream.read(buffer);
        return input.read();
    }
}
