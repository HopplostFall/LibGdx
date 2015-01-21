package se.malmberg.ns.assignmentonechat;


import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class GroupFragment extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private ChatActivity activity;

    public static GroupFragment newInstance(String param1, String param2) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GroupFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.activity = (ChatActivity) getActivity();
        View root = inflater.inflate(R.layout.fragment_group, container, false);
        String[] values = new String[]{"Hej1", "Hej2", "Hej3", "Hej4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        return root;
    }

    public void getActivity(ChatActivity activity){
        this.activity = activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        activity.StartChat(item);
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}
