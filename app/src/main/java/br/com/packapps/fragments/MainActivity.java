package br.com.packapps.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import br.com.packapps.fragments.fragments.MyFragmentA;
import br.com.packapps.fragments.fragments.MyFragmentB;
import br.com.packapps.fragments.fragments.MyFragmentC;

public class MainActivity extends AppCompatActivity {
    private FrameLayout flContainerForFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flContainerForFragment = (FrameLayout) findViewById(R.id.flContainerForFragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //implementing fragment
        MyFragmentA fragmentA = new MyFragmentA();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flContainerForFragment, fragmentA, "Fragment_A");
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MyFragmentA fragmentA = new MyFragmentA();
                    managerFragment(fragmentA, "Fragment_A");return true;
                case R.id.navigation_dashboard:
                    MyFragmentB fragmentB = new MyFragmentB();
                    managerFragment(fragmentB, "Fragment_B");
                    return true;
                case R.id.navigation_notifications:
                    MyFragmentC fragmentC = new MyFragmentC();
                    managerFragment(fragmentC, "Fragment_C");
                    return true;
            }
            return false;
        }
    };

    private void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flContainerForFragment, fragment, tag);
        fragmentTransaction.commit();
    }
}
