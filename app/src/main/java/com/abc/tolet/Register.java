package com.abc.tolet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
/*import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;*/
import android.support.v7.app.AppCompatActivity;
/*import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;*/
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.abc.tolet.helpers.InputValidation;
import com.abc.tolet.model.User;
import com.abc.tolet.sql.DatabaseHelper;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = Register.this;
    private RelativeLayout relativelayout;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText conpassword;
    private Button button;
    private TextView login;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        /*nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);*/
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        conpassword = findViewById(R.id.conpassword);
        button = findViewById(R.id.button);
        login = findViewById(R.id.login);
        relativelayout=findViewById(R.id.relative);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        button.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /*
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button:
                postDataToSQLite();
                break;

            case R.id.login:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(name, name, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(email, email, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(email, email, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(password, password, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(password, conpassword, conpassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(email.getText().toString().trim())) {

            user.setName(name.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(relativelayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(relativelayout, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        name.setText(null);
        email.setText(null);
        password.setText(null);
        conpassword.setText(null);
    }
}
