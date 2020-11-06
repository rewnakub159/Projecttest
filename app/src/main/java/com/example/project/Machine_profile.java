package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Machine_profile extends AppCompatActivity {

    Home_Menu homeMenu = new Home_Menu();
    String users = homeMenu.user.toString();

    String macname;
    String volume_now;
    String food_level;
    String timeno;
    String createdate;

    TextView tv1,tv2,tv3,tv4,tv5;

    Button bt1;
    ImageButton imb1;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_profile);

        macname = getIntent().getStringExtra("macname");
        volume_now = getIntent().getStringExtra("volume_now");
        food_level = getIntent().getStringExtra("food_level");
        timeno = getIntent().getStringExtra("timeno");
        createdate = getIntent().getStringExtra("createdate");

        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);

        tv1.setText(macname);
        tv2.setText(volume_now);
        tv3.setText(food_level);
        tv4.setText(createdate);
        tv5.setText(timeno);

        bt1 = (Button)findViewById(R.id.bt1);

        imb1 = (ImageButton)findViewById(R.id.imb1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("ลบรายการ");
                builder.setMessage("คุณต้องการลบรายการนี้ใช่หรือไม่");
                builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reference = FirebaseDatabase.getInstance().getReference().child("machineprofile").child(users).child(macname);
                        reference.removeValue();

                        reference=FirebaseDatabase.getInstance().getReference("machine2");
                        reference.child(macname).child("status").setValue("not");
                        reference.child(macname).child("username").setValue("null");

                        reference = FirebaseDatabase.getInstance().getReference().child("time").child(macname);
                        reference.removeValue();
                        finish();
                    }
                });
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}