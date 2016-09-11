package disono.webmons.com.utilities.library.SocketIO;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import java.net.URISyntaxException;

import disono.webmons.com.clean_architecture.storage.Configurations;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/5/2016 4:39 PM
 * <p>
 * URL http://socket.io/blog/native-socket-io-and-android
 */
public class SocketIOConnector {
    private final String TAG = "SocketIO:Class";
    private Activity activity;
    private Context ctx;
    Socket mSocket;

    public SocketIOConnector(Activity activity) {
        this.activity = activity;
        ctx = this.activity.getApplication();
    }

    /**
     * Initialize
     *
     * @return
     */
    private Socket _init(String url, IO.Options options) {
        try {
            String path = (url != null) ? url : Configurations.envString("socketIOURL");
            return this.mSocket = IO.socket(path, options);
        } catch (URISyntaxException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * Run
     *
     * @return
     */
    private Socket _run() {
        if (this.mSocket != null) {
            this.mSocket = this.mSocket.connect();
        }

        return null;
    }

    /**
     * Setup
     *
     * @return
     */
    public Socket setUp() {
        return _init(null, null);
    }


    /**
     * Setup with url
     *
     * @param url
     * @return
     */
    public Socket setUp(String url) {
        return _init(url, null);
    }

    /**
     * Setup with url and options
     *
     * @param url
     * @param options
     * @return
     */
    public Socket setUp(String url, IO.Options options) {
        return _init(url, options);
    }

    /**
     * Setup with options
     *
     * @param options
     * @return
     */
    public Socket setUp(IO.Options options) {
        return _init(null, options);
    }

    /**
     * Connect to server
     *
     * @return
     */
    public Socket connect() {
        return _run();
    }

    /**
     * Send string
     *
     * @param key
     * @param value
     * @return
     */
    public Emitter sendString(String key, String value) {
        return mSocket.emit(key, value);
    }

    /**
     * Send integer
     *
     * @param key
     * @param value
     * @return
     */
    public Emitter sendInt(String key, int value) {
        return mSocket.emit(key, value);
    }

    /**
     * Send float
     *
     * @param key
     * @param value
     * @return
     */
    public Emitter sendFloat(String key, float value) {
        return mSocket.emit(key, value);
    }

    /**
     * Send character
     *
     * @param key
     * @param value
     * @return
     */
    public Emitter sendChar(String key, char value) {
        return mSocket.emit(key, value);
    }

    /**
     * Send boolean
     *
     * @param key
     * @param value
     * @return
     */
    public Emitter sendBoolean(String key, boolean value) {
        return mSocket.emit(key, value);
    }

    /**
     * Send JSON
     *
     * @param key
     * @param jsonObject
     * @return
     */
    public Emitter sendJSONObject(String key, JSONObject jsonObject) {
        return mSocket.emit(key, jsonObject);
    }

    /**
     * Listen for new data
     * Make sure to call connect
     *
     * @param key
     * @param listener
     */
    public void listen(String key, Emitter.Listener listener) {
        mSocket.on(key, listener);
    }

    /**
     * Disconnect
     */
    public void disconnect() {
        mSocket.disconnect();
    }

    /**
     * Disconnect with key
     *
     * @param key
     */
    public void disconnectKey(String key) {
        mSocket.off(key);
    }

    /**
     * Disconnect with key and listener
     *
     * @param key
     * @param listener
     */
    public void disconnectKey(String key, Emitter.Listener listener) {
        mSocket.off(key, listener);
    }
}
