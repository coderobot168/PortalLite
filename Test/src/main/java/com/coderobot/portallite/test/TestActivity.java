package com.coderobot.portallite.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new LonginTask().execute("http://portalite.revo.so/login", "s1003344", "19921011");
    }

    private class LonginTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strs) {
            HttpPost httpRequest = new HttpPost(strs[0]);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("AUTH_KEY", "hci_final_project_password"));
            params.add(new BasicNameValuePair("opcode", "0"));
            params.add(new BasicNameValuePair("username", strs[1]));
            params.add(new BasicNameValuePair("password", strs[2]));
            try
            {
                // HTTP request
                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                // get HTTP response
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);


                String strResult = "login failed";
                // check status
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                }

                log("over with code: " + httpResponse.getStatusLine().getStatusCode());
                return strResult;

            } catch (ClientProtocolException e) {
                log(e.getMessage());
            } catch (IOException e) {
                log(e.getMessage());
            } catch (Exception e) {
                log(e.getMessage());
            }
            return "failed";
        }

        protected void onProgressUpdate(Integer progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
            log("result : " + result);
            new GetCourseInfoTask().execute("http://portalite.revo.so/login", "s1003344", "19921011");
        }
    }

    private class GetCourseInfoTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strs) {
            HttpPost httpRequest = new HttpPost(strs[0]);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("AUTH_KEY", "hci_final_project_password"));
            params.add(new BasicNameValuePair("opcode", "1"));
            params.add(new BasicNameValuePair("username", strs[1]));
            params.add(new BasicNameValuePair("password", strs[2]));
            params.add(new BasicNameValuePair("params", "y=" + 102 + "&s=" + 2 + "&id=" + "CS377" + "&c=" + "A"));
            try
            {
                // HTTP request
                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                // get HTTP response
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);


                String strResult = "login failed";
                // check status
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                }

                log("over with code: " + httpResponse.getStatusLine().getStatusCode());
                return strResult;

            } catch (ClientProtocolException e) {
                log(e.getMessage());
            } catch (IOException e) {
                log(e.getMessage());
            } catch (Exception e) {
                log(e.getMessage());
            }
            return "failed";
        }

        protected void onProgressUpdate(Integer progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
            log("result : " + result);
        }
    }

    public void log(String msg){
        Log.d("Tony", msg);
    }
}
