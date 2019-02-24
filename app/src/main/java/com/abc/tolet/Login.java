package com.abc.tolet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abc.tolet.helpers.InputValidation;
import com.abc.tolet.sql.DatabaseHelper;



public class Login extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity =Login.this;


	private AppCompatEditText email;
	private AppCompatEditText password;
	private Button bt;
	private TextView register;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private RelativeLayout relativelayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

		email=findViewById(R.id.email);
		password=findViewById(R.id.password);
		bt=findViewById(R.id.button);
		register=findViewById(R.id.register);
		relativelayout=findViewById(R.id.relative);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        bt.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /*
     * This implemented method is to listen the click on view
     *
     * @param
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                verifyFromSQLite();
                break;
            case R.id.register:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), Register.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(email,email, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(email,email, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password,email, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(email.getText().toString().trim()
                , password.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, Homepage.class);
            startActivity(accountsIntent);
            emptyInputEditText();


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(relativelayout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        email.setText(null);
        password.setText(null);
    }
}
