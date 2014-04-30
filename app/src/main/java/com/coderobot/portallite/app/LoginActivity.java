package com.coderobot.portallite.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText mEdtID;
    private EditText mEdtPassword;
    private ProgressBar mPgbLoginProgress;
    private Button mBtnLogin;
    private LoginTask mLoginTask;

    private String mID = "", mPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findIDs();
    }

    private void findIDs() {
        mEdtID = (EditText) findViewById(R.id.edt_id);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mPgbLoginProgress = (ProgressBar) findViewById(R.id.pgb_login_progress);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mBtnLogin.getId() == v.getId()) {
            mBtnLogin.setEnabled(false);
            mEdtID.setEnabled(false);
            mEdtPassword.setEnabled(false);
            mLoginTask = new LoginTask();
            if (mEdtID.getText() != null && mEdtPassword.getText() != null) {
                mID = mEdtID.getText().toString();
                mPassword = mEdtPassword.getText().toString();
                mLoginTask.execute(Define.LOGIN_URL, mID, mPassword);
            }
        }
    }

    private class LoginTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... strs) {
            HttpPost httpRequest = new HttpPost(strs[0]);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("AUTH_KEY", "hci_final_project_password"));
            params.add(new BasicNameValuePair("opcode", "0"));
            params.add(new BasicNameValuePair("username", strs[1]));
            params.add(new BasicNameValuePair("password", strs[2]));
            publishProgress(5);
            try {
                // HTTP request
                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                // get HTTP response
                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
                publishProgress(10);

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

        protected void onProgressUpdate(Integer... progress) {
            mPgbLoginProgress.setProgress(progress[0]);
        }

        protected void onPostExecute(String result) {
            log("result : " + result);
            publishProgress(15);
            String[] response = result.split("\n");
            if (response[0].equals("[ERROR CODE 00]")) {
                toast(Define.MSG_AUTH_FAIL);
            } else if (response[0].equals("[ERROR CODE 01]")) {
                toast(Define.MSG_WRONG_INFO);
            } else if (response[0].equals("[DONE CODE 01]")) {
                toast("sucess");
                parseCourseAndScheduleAndSave(response);
            }
        }
    }

    private void parseCourseAndScheduleAndSave(String[] response) {
        boolean isCourse = false, isSchedule = false;
        for (String line : response) {
            if (line.equals("[COURSE_LIST]")) {
                isCourse = true;
                isSchedule = false;
            }
            if (line.equals("[SCHEDULE]")) {
                isCourse = false;
                isSchedule = true;
            }
            if (line.equals("[END COURSE_LIST]")
                    || line.equals("[END SCHEDULE]")
                    || line.equals("[DONE CODE 01]")
                    || line.equals("[END]")) {
                isCourse = false;
                isSchedule = false;
            }
            if (isSchedule) {

            } else if (isCourse) {

            }

        }
    }

    public void log(String msg) {
        Log.d(Define.TAG, msg);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}