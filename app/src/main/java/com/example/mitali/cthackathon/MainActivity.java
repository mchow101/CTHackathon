package com.example.mitali.cthackathon;

import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static int num;
    private static int selected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact us!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            TabLayout tabLayout = rootView.findViewById(R.id.tabs);
            int tab_position=tabLayout.getSelectedTabPosition();

            num = getArguments().getInt(ARG_SECTION_NUMBER);
            switch(num) {
                case (1):
                    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.section_format));
                    Button button = (Button) rootView.findViewById(R.id.button2);
                    button.setText("Next Family");
                    TextView textView1 = (TextView) rootView.findViewById(R.id.family_name);
                    textView1.setText("");
                    rootView.findViewById(R.id.tabs).setVisibility(View.GONE);
                    break;
                case (2):
                    rootView.findViewById(R.id.button2).setVisibility(View.GONE);
                    if(tab_position==0){
                        TextView textView2 = (TextView) rootView.findViewById(R.id.about);
                        String s;
                        switch (selected) {
                            case (2):
                                s = "The Chowders\nWe immigrated to California in 2014, and we are currently in the San Fransisco Bay Area.";
                                break;
                            case (1):
                                s = "The Parrs\nOur family enjoys eating, working out, and combating supervillians in our free time.";
                                break;
                            case (0):
                                s = "The Martinezes\nWe came to the US four years ago and thank the CC community for their support!";
                                break;
                            default:
                                s = "Error";
                        }
                        textView2.setText(s);
                    }
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public void plus(View view) {
        if(selected < 2)
            selected++;
        else
            selected = 0;
        TextView textView1 = (TextView) findViewById(R.id.family_name);
        textView1.setText(getName(selected));
    }

    private String getName(int s) {
        switch (s) {
            case (2):
                return "The Chowders\nWe immigrated to California in 2014, and we are currently in the San Fransisco Bay Area.";
            case (1):
                return "The Parrs\nOur family enjoys eating, working out, and combating supervillians in our free time.";
            case (0):
                return "The Martinezes\nWe came to the US four years ago and thank the CC community for their support!";
            default:
                return "Error";
        }
    }

    public void update(View view) {
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        int tab_position=tabLayout.getSelectedTabPosition();
        if(tab_position==1){
            TextView textView2 = (TextView) view.findViewById(R.id.about);
            textView2.setText(getName(selected));
        }
    }
}
