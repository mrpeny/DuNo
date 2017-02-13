package com.example.android.duno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void notifiedButton(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    private String[] getDudesEmailAddresses() {
        List<String> emailAddressesList = new ArrayList<>();

        if (((CheckBox) findViewById(R.id.szapi_checkbox)).isChecked()) {
            emailAddressesList.add("szapi@szapinet.hu");
        }
        if (((CheckBox) findViewById(R.id.cifka_nori_checkbox)).isChecked()) {
            emailAddressesList.add("cifka@cifkanet.hu");
            emailAddressesList.add("nori@norinet.hu");
        }
        if (((CheckBox) findViewById(R.id.führer_checkbox)).isChecked()) {
            emailAddressesList.add("führer@führernet.hu");
        }
        if (((CheckBox) findViewById(R.id.homoki_checkbox)).isChecked()) {
            emailAddressesList.add("homoki@homikinet.hu");
        }
        if (((CheckBox) findViewById(R.id.gabi_bea_checkbox)).isChecked()) {
            emailAddressesList.add("gabi@gabinet.hu");
            emailAddressesList.add("bea@beanet.hu");
        }
        if (((CheckBox) findViewById(R.id.konyak_checkbox)).isChecked()) {
            emailAddressesList.add("bencekonya@gmail.com");
        }

        String[] emailAddresses = emailAddressesList.toArray(new String[emailAddressesList.size()]);

        return emailAddresses;
    }

    private String getMessages() {
        boolean isAnyChecked = false;
        String message = getResources().getString(R.string.message_greeting) + "\n\n" +
                getResources().getString(R.string.message_lead);

        if (((CheckBox) findViewById(R.id.wanna_talk_checkbox)).isChecked()) {
            message += "\n- " + getResources().getString(R.string.wanna_talk_checkbox);
            isAnyChecked = true;
        }
        if (((CheckBox) findViewById(R.id.wanna_play_checkbox)).isChecked()) {
            message += "\n- " + getResources().getString(R.string.wanna_play_checkbox);
            isAnyChecked = true;
        }
        if (((CheckBox) findViewById(R.id.wanna_go_crazy_checkbox)).isChecked()) {
            message += "\n- " + getResources().getString(R.string.wanna_go_crazy_checkbox);
            isAnyChecked = true;
        }
        if (((CheckBox) findViewById(R.id.need_drink_checkbox)).isChecked()) {
            message += "\n- " + getResources().getString(R.string.need_drink_checkbox);
            isAnyChecked = true;
        }
        if (((CheckBox) findViewById(R.id.need_food_checkbox)).isChecked()) {
            message += "\n- " + getResources().getString(R.string.need_food_checkbox);
            isAnyChecked = true;
        }
        message += "\n\n" + getResources().getString(R.string.good_bye) +
                "\n" + getResources().getString(R.string.signature);

        if (!isAnyChecked) {
            message = getResources().getString(R.string.message_greeting) + " "
                    + getResources().getString(R.string.fucking_well) + " \n"
                    + getResources().getString(R.string.signature);
        }

        return message;
    }

    public void notifyDudes(View view) {
        String[] addresses = getDudesEmailAddresses();
        String message = getMessages();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
