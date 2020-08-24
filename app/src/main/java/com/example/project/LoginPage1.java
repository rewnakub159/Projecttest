package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage1 extends AppCompatActivity {
public static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page1);
        final Button b1 = (Button)findViewById(R.id.button1);
        final EditText e1 = (EditText)findViewById(R.id.editText1);

    EditText e2 = (EditText)findViewById(R.id.editText2);

        e1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, 0, 0);
        e1.setHint(" USERNAME");
        e2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock1, 0, 0, 0);
        e2.setHint(" PASSWORD");

        final DatabaseReference dblogin = FirebaseDatabase.getInstance().getReference("user");
        b1.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                EditText e1 = (EditText)findViewById(R.id.editText1);
                EditText e2 = (EditText)findViewById(R.id.editText2);
                sigin(e1.getText().toString(),e2.getText().toString());

            }

 

            private void sigin (final String username,final String password){
                dblogin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(username).exists()){
                            if (!username.isEmpty()){
                                Register_DB login = dataSnapshot.child(username).getValue(Register_DB.class);
                                if (login.getPassword().equals(password)){
                                    Toast.makeText(LoginPage1.this,"Success Login",Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(LoginPage1.this,Menu_beta.class);
                                    String users = e1.getText().toString();
                                    user = users;
                                    i.putExtra("username",e1.getText().toString());
                                      startActivity(i);
                                }else{
                                    Toast.makeText(LoginPage1.this,"Password is wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {
                            Toast.makeText(LoginPage1.this,"Username is Not Registered",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


}
