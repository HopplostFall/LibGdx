package se.malmberg.ns.assignmentonechat;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;




public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private EditText username;
    private EditText password;
    private OnFragmentInteractionListener mListener;
    private MainActivity activity;


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public boolean Login()
    {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        return user == pass;
    }

    public boolean Register()
    {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        return user == pass;
    }

    public void LoginClick()
    {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if (!user.isEmpty()) {
            if (user.equals(pass)) {
                activity.ChangeActivity();
            }
        }
    }

    public void getActivity(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        username = (EditText)root.findViewById(R.id.userName);
        password = (EditText)root.findViewById(R.id.passWord);
        final Button loginButton = (Button) root.findViewById(R.id.LoginButton);
        final Button registerButton = (Button) root.findViewById(R.id.RegButton);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginClick();

            }
        });
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri msg = Uri.parse("GoToRegister");
                mListener.onFragmentInteraction(msg);
                Register();
            }
        });

        return root;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
