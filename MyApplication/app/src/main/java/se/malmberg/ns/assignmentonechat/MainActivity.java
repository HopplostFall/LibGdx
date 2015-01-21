package se.malmberg.ns.assignmentonechat;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;

public class MainActivity extends Activity implements LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoginFragment frag = new LoginFragment();
        frag.getActivity(this);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, frag);
        //transaction.addToBackStack("");
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.action_about:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, AboutFragment.newInstance("", ""), "abtfragment");
                transaction.addToBackStack("abtfragment");
                transaction.commit();
                return true;
            default:
                return false;
        }
    }

    public void ChangeActivity(){
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        String incMsg = uri.toString();
        if(incMsg.equals("GoToRegister"))
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, RegisterFragment.newInstance("", ""), "regFrag");
            //transaction.addToBackStack("regFrag");
            transaction.commit();
        }
        else
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, LoginFragment.newInstance("", ""), "loginFrag");
            //transaction.addToBackStack("loginFrag");
            transaction.commit();
        }

    }
}
