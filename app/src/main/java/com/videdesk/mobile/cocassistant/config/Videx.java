package com.videdesk.mobile.cocassistant.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.videdesk.mobile.cocassistant.models.Verse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Videx {

    private Context mContext;

    public Videx(){}

    public Videx(Context context){
        this.mContext = context;
    }

    public boolean isConn(){
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }

    @NonNull
    public static String toProper(String words){
        String[] strArray = words.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        return builder.toString();
    }

    public void setPref(String key, String value){
        cutPref(key);
        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
    }

    public void cutPref(String key){
        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.remove(key);
        prefEditor.commit();
    }

    public String getPref(String key){
        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(mContext);
        return prefs.getString(key, null);
    }

    public static String getNode(){
        long date  = System.currentTimeMillis();
        SimpleDateFormat calNode = new SimpleDateFormat("yyMMddHHmmss", Locale.US);
        Random rnd = new Random();
        int n = 1000000 + rnd.nextInt(9000000);
        return calNode.format(date) + ""  + n;
    }

    public static boolean isOutdated(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date strDate = sdf.parse(date);
            return new Date().after(strDate);
        }catch (ParseException ex){
            Log.w("Expired Date Error", ex.getMessage());
            return false;
        }
    }

    public static String getDated(){
        long date  = System.currentTimeMillis();
        SimpleDateFormat calDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return calDate.format(date);
    }

    public static String getDateAfterDays(int days){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        Date d = c.getTime();
        SimpleDateFormat calDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return calDate.format(d);
    }

    public static String getTimed(){
        long date  = System.currentTimeMillis();
        SimpleDateFormat calDate = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return calDate.format(date);
    }

    public static String getDatedTimed(){
        long date  = System.currentTimeMillis();
        SimpleDateFormat calDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return calDate.format(date);
    }

    @NonNull
    public static String getCode(){
        // Generate random id, for example 2839520-V8M32QK
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder((1000000 + rnd.nextInt(9000000)) + "-");
        for (int i = 0; i < 7; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);

        return sb.toString();
    }


    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = mContext.getResources().getIdentifier("mdcolor_" + typeColor, "array", mContext.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = mContext.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    public void downloadFile(String fileUrl, String localFolder) {
        String directory = toProper(localFolder);
        File outputFile = externalDirectory(directory) != null ? externalDirectory(directory) : internalDirectory(directory);
        if(outputFile != null  && outputFile.isDirectory()) {
            try {
                URL u = new URL(fileUrl);
                URLConnection conn = u.openConnection();
                int contentLength = conn.getContentLength();

                DataInputStream stream = new DataInputStream(u.openStream());

                byte[] buffer = new byte[contentLength];
                stream.readFully(buffer);
                stream.close();

                DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
                fos.write(buffer);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                return ; // swallow a 404
            } catch (IOException e) {
                return ; // swallow a 404
            }
        }
    }

    private static File externalDirectory(String folder){
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

        if(isSDSupportedDevice && isSDPresent) {
            File dir = new File(Environment.getExternalStorageDirectory() + "/VideDesk/COC/" + folder);
            if (dir.isDirectory()) {
                return dir;
            }
            if (dir.mkdir()) {
                return dir;
            }
            return null;
        }
        return null;
    }

    private static File internalDirectory(String folder){
        File dir = new File("/VideDesk/COC/" + folder);
        if (dir.isDirectory()) {
            return dir;
        }
        if (dir.mkdir()) {
            return dir;
        }
        return null;
    }

    public String loadJSONFromAsset(String file_name) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open(file_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private List<Verse> loadJSON(String file_name){
        /*
        SAMPLE JSON FILE
        {
        "formules": [
            {
              "formule": "Linear Motion",
              "url": "qp1"
            },
            {
              "formule": "Constant Acceleration Motion",
              "url": "qp2"
            },
            {
              "formule": "Projectile Motion",
              "url": "qp3"
            }
        ]
         */
        List<Verse> verseList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(file_name));
            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            Verse verse = new Verse();

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                String formula_value = jo_inside.getString("formule");
                String url_value = jo_inside.getString("url");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return verseList;
    }
}
