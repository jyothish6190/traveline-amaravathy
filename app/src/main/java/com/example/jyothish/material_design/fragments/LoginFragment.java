package com.example.jyothish.material_design.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jyothish.material_design.R;
import com.example.jyothish.material_design.app.AppController;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LoginFragment";

    private EditText nameEdit;
    private EditText phoneEdit;
    private Button loginButton;

    ProgressDialog progress;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        nameEdit = (EditText) view.findViewById(R.id.name);
        phoneEdit = (EditText) view.findViewById(R.id.mobno);
        loginButton = (Button) view.findViewById(R.id.log_btn);

        loginButton.setOnClickListener(this);

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Registering ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCanceledOnTouchOutside(false);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.log_btn:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        progress.show();

        String url = "http://ec2-54-169-120-253.ap-southeast-1.compute.amazonaws.com:9000/sync/1455866814949";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        loginSuccess();
                        Log.d(TAG, "success : " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.d(TAG, "Error: " + error.getMessage());
            }
        });

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG);
    }

    private void loginSuccess() {
        nameEdit.setText("");
        phoneEdit.setText("");

        try {
            Fragment friendsFragment = new FriendsFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container_body, friendsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } catch (Exception e) {

        }
    }

}
