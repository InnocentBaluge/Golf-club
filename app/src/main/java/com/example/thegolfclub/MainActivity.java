package com.example.thegolfclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.res.Resources;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import utils.LocaleHelper;

public class MainActivity extends AppCompatActivity {

    private String mLanguageCode = "sw";
    Context context;
    Resources resources;

    Button register, log_in;
    EditText First_Name, Last_Name, Email, Password,confirm_Password;
    String F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder;
    String finalResult;
    String HttpURL = "https://mygolfclub.000webhostapp.com/register.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //language changes

        findViewById(R.id.swahili).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change Application level locale
                LocaleHelper.setLocale(MainActivity.this, mLanguageCode);
                //resources = context.getResources();

                //It is required to recreate the activity to reflect the change in UI.
                recreate();

            }
        });

        //Assign Id'S
        First_Name = (EditText) findViewById(R.id.editTextF_Name);
        Last_Name = (EditText) findViewById(R.id.editTextL_Name);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Password = (EditText) findViewById(R.id.editTextPassword);
        confirm_Password = (EditText) findViewById(R.id.confirmPassword);

        register = (Button) findViewById(R.id.Submit);
        log_in = (Button) findViewById(R.id.Login);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();
                //validate the password fields
                if(validate()){

                    // startActivity(new Intent(RegistrationForm.this, Home.class));


                        if (CheckEditText) {

                            // If EditText is not empty and CheckEditText = True then this block will execute.

                            UserRegisterFunction(F_Name_Holder, L_Name_Holder, EmailHolder, PasswordHolder);

                        } else {

                            // If EditText is empty then this block will execute .
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.fill_all), Toast.LENGTH_LONG).show();

                        }
                }




            }

            private boolean validate() {
                boolean temp=true;
                String pass=Password.getText().toString();
                String cpass=confirm_Password.getText().toString();
                if(!pass.equals(cpass)){
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.Password_not_match),Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(Password.getText().toString().length()<8){
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.password_length),Toast.LENGTH_LONG).show();
                    temp=false;
                }
                return temp;
            }
        });

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot() {

        F_Name_Holder = First_Name.getText().toString();
        L_Name_Holder = Last_Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();


        if (TextUtils.isEmpty(F_Name_Holder) || TextUtils.isEmpty(L_Name_Holder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            CheckEditText = false;

        } else {

            CheckEditText = true;
        }

    }

    public void UserRegisterFunction(final String F_Name, final String L_Name, final String email, final String password) {

        class UserRegisterFunctionClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.loading), null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("f_name", params[0]);

                hashMap.put("L_name", params[1]);

                hashMap.put("email", params[2]);

                hashMap.put("password", params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(F_Name, L_Name, email, password);

    }
}