package com.sample.adq;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.qwertlab.adq.main.ADQManager;
import com.qwertlab.adq.utils.ADQConstants;
import com.qwertlab.adq.utils.XAdsFunc;


public class MainActivity extends AppCompatActivity {


    /**
     * More Information : https://api.qwertlab.com/adq/index.php
     * Admin Site       : https://agent.qwertlab.com/
     * Home Page        : https://www.qwertlab.com/
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionTxt = findViewById(R.id.sample_version);
        versionTxt.setText(XAdsFunc.getVersionname());

        //-- ADQ Check Permission ------------------------------------------------------------------
        if(!ADQManager.isNotificationPermission(getApplicationContext())){
            //-- Request Notification Permission. [For - QuickBar]
            ADQManager.requestADQPermission(MainActivity.this, registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
                if(ADQManager.isNotificationPermission(getApplicationContext())){
                    ADQManager.initADQ(MainActivity.this, "c67e9d88c5e208d38a5c8c66e35a8785");
                }
            }));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * If you want to use the Custom QuickBar function, please refer to the API Documents.
         * (https://api.qwertlab.com/adq/api_doc.php#quickbar_api)
         */
        //-- ADQ -----------------------------------------------------------------------------------
        if(ADQManager.isNotificationPermission(getApplicationContext())){
            ADQManager.initADQ(MainActivity.this, "c67e9d88c5e208d38a5c8c66e35a8785", ADQConstants.FLAG_QUICK_BAR_DEFAULT);
        }
        //------------------------------------------------------------------------------------------
    }
}