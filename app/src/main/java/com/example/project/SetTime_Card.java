package com.example.project;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SetTime_Card extends AppCompatActivity {
    CardView c1,c2,c3,c4;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time__card);

        c1 = (CardView)findViewById(R.id.settimeCard_1);

        tv1 = (TextView)findViewById(R.id.settime_tv1) ;
        tv2 = (TextView)findViewById(R.id.settime_tv2) ;

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddSetTime_Card.class);
                intent.putExtra("time",tv1.getText().toString());
                intent.putExtra("amount",tv2.getText().toString());
                startActivity(intent);
            }
        });
    }
}