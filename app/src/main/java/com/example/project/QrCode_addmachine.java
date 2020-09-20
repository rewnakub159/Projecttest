package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrCode_addmachine extends AppCompatActivity {
    private DatabaseReference reference;
    private Button scan_button,bt1;
    EditText et1;
    TextView tv1,tv2;
    Machine_DB machine_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_addmachine);
        et1=(EditText) findViewById(R.id.addmac_et1);
        scan_button = (Button) findViewById(R.id.scan_button);
        bt1= (Button)findViewById(R.id.addmac_bt1);
        tv1 = (TextView)findViewById(R.id.addmac_tv1);
        tv2 = (TextView)findViewById(R.id.addmac_tv2);

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
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String macname = et1.getText().toString();
                Query query = FirebaseDatabase.getInstance().getReference("machine2").orderByChild("name").equalTo(macname);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() == 1) {
                            reference= FirebaseDatabase.getInstance().getReference("machine2").child(macname);
                            Toast.makeText(QrCode_addmachine.this, "1", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(QrCode_addmachine.this, "0", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


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