package se.malmberg.ns.assignmentonechat;


import android.app.ListFragment;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class ChatLogFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private String chatRoomName = ARG_PARAM1;
    private Button buttonChatRoom;
    ArrayAdapter<String> adapter;
    ArrayList<String> myChatlog;

    // TODO: Rename and change types and number of parameters
    public static ChatLogFragment newInstance(String param1, String param2) {
        ChatLogFragment fragment = new ChatLogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChatLogFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        chatRoomName = mParam1;
        //username = (EditText)root.findViewById(R.id.userName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chatlog, container, false);
        buttonChatRoom = (Button)root.findViewById(R.id.button2);
        buttonChatRoom.setText(chatRoomName);
        //String[] values2 = new String[]{"Chat1", "Chat2", "Chat3", "Chat4", "Chat1", "Chat2", "Chat3", "Chat4", "Chat1", "Chat2", "Chat3", "Chat4"};
        String[] bla;
        bla =getResources().getStringArray(R.array.chatlogArray);
        myChatlog = new ArrayList<String>(Arrays.asList(bla));
        adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1, myChatlog);
        setListAdapter(adapter);
        return root;
    }


    public final void AddText(String text)
    {

        myChatlog.add(text);
        //adapter.add(text);
        adapter.notifyDataSetChanged();
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}
