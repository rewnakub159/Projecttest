package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrCode_addmachine extends AppCompatActivity {

    private Button scan_button;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_addmachine);
        et1=(EditText) findViewById(R.id.addmac_et1);
        scan_button = (Button) findViewById(R.id.scan_button);

        final Activity activity = this;

        scan_button.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);

                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);

                integrator.setPrompt("Scan");

                integrator.setCameraId(0);

                integrator.setBeepEnabled(false);

                integrator.setBarcodeImageEnabled(false);

                integrator.initiateScan();

            }

        });

    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null){

            if(result.getContents()== null){

                Toast.makeText(this, "You cancell scanning", Toast.LENGTH_LONG).show();

            }


            else{
                String qrtex = result.getContents();
                Toast.makeText(this,result.getContents()+"55555",Toast.LENGTH_LONG).show();
                et1.setText(qrtex);
            }

        }

        else {

            super.onActivityResult(requestCode, resultCode, data);


        }
    }
}