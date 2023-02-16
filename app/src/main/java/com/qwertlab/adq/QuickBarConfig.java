package com.qwertlab.adq;

import android.app.Activity;
import android.content.Intent;

import com.qwertlab.adq.browser.BrowserMainActivity;
import com.qwertlab.adq.quickbar.CustomQuickBarConfig;
import com.qwertlab.adq.quickbar.CustomQuickBarObject;
import com.qwertlab.adq.utils.ADQConstants;

/**
 * Custom QuickBar
 *
 * (More Information : https://api.qwertlab.com/adq/api_doc.php#quickbar_api)
 *
 */

public class QuickBarConfig extends CustomQuickBarConfig {

    @Override
    public CustomQuickBarObject getQuickBarObject1() {
        CustomQuickBarObject obj = new CustomQuickBarObject();
        //-- TODO : Change the icon path
        obj.setQuickBarIcon(R.drawable.quick_bar_icon_1);
        //-- TODO : Change the string path
        obj.setQuickBarText(R.string.quick_bar_icon_1);
        obj.setQuickBarAction(new CustomQuickBarObject.QuickBarAction() {
            @Override
            public void setQuickBarActionListener(Activity context, String name) {
                //TODO : Change the action
                Intent intent = new Intent(context, BrowserMainActivity.class);
                intent.putExtra(ADQConstants.SEARCH_KEYWORD, "https://m.naver.com/");
                context.startActivity(intent);
                //-- You must call the finish method.
                context.finish();
            }
        });
        return obj;
    }

    @Override
    public CustomQuickBarObject getQuickBarObject2() {
        return super.getQuickBarObject2();
    }

    @Override
    public CustomQuickBarObject getQuickBarObject3() {
        return super.getQuickBarObject3();
    }

    @Override
    public CustomQuickBarObject getQuickBarObject4() {
        return super.getQuickBarObject4();
    }

    @Override
    public CustomQuickBarObject getQuickBarObject5() {
        return super.getQuickBarObject5();
    }

}
