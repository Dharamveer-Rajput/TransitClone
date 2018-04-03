package com.transitclone;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.transitclone.di.modules.SharedPrefsHelper;
import com.transitclone.manager.ApiService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by rajsm on 01/04/2018.
 */

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ApiService apiService;

    @Inject
    SharedPrefsHelper sharedPrefsHelper;
    protected Unbinder unbinder;

    protected ProgressDialog pDialog;
    protected AlertDialog alertDialog;

    public void showProgressDialog(String messageToShow) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(messageToShow + "...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(true);

    }


    @Override
    public void setContentView(int layoutRedID) {
        super.setContentView(layoutRedID);
        ((AppController) getApplication()).getComponent().inject(this);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }

    protected void showAlertDialog() {

        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialog = alertDialogBuilder.create();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((AppController) getApplication()).getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
