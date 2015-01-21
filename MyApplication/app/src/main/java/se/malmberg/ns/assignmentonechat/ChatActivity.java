package se.malmberg.ns.assignmentonechat;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.lang.reflect.Method;


public class ChatActivity extends Activity implements ChatFragment.OnFragmentInteractionListener{

    Fragment chatLogFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity2);

        Fragment GroupFrag = new GroupFragment();
        //GroupFrag.getActivity();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, GroupFrag, "chatGroup");
        transaction.commit();
    }

    public void StartChat(String chatGroup)
    {
        chatLogFrag = ChatLogFragment.newInstance(chatGroup,"");
        Fragment chatTextBox = new ChatFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, chatLogFrag, "chatlogFrag");
        transaction.add(R.id.container, chatTextBox, "chatFrag");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.action_about:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, AboutFragment.newInstance("", ""), "abtfragment");
                transaction.replace(R.id.container, AboutFragment.newInstance("", ""), "abtfragment");
                transaction.addToBackStack("abtfragment");
                transaction.commit();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        String incMsg = uri.toString();

        ChatLogFragment clf = (ChatLogFragment) getFragmentManager().findFragmentByTag("chatlogFrag");
        clf.AddText(incMsg);
    }
}
