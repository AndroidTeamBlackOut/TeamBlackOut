package com.blackout.paidupdater.Themes;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blackout.paidupdater.MainActivity;
import com.blackout.paidupdater.Packages.PackageListFragment;
import com.blackout.paidupdater.R;

import java.util.concurrent.ExecutionException;

public class ThemeFragment extends Fragment {
    private ThemeAdapter adapter;
    private ListView lv;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ThemeFragment newInstance(int sectionNumber) {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ThemeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        getActivity().getActionBar().setTitle("Available Theme Packages");
        getActivity().getActionBar().setIcon(R.drawable.ic_launcher);
        try {
            GetThemeList task = new GetThemeList(getActivity());

            lv = (ListView) rootView.findViewById(R.id.listView);

            adapter = new ThemeAdapter(getActivity(),
                    R.layout.list_icon_row, task.execute().get());

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,int position, long id)
                {

                    ThemeList data = adapter.getItem(position);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, PackageListFragment.newInstance(data.title, data.url))
                            .addToBackStack(null)
                            .commit();
                }});

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}