package br.com.packapps.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.List;

import br.com.packapps.fragments.fragments.MyFragmentA;
import br.com.packapps.fragments.fragments.MyFragmentB;
import br.com.packapps.fragments.fragments.MyFragmentC;

public class MainActivity extends AppCompatActivity {
    private FrameLayout flContainerForFragment;
    BottomNavigationView navigation;
    private boolean noReplaceFragment;
    private static final String FRAGMENT_A = "FRAGMENT_A";
    private static final String FRAGMENT_B = "FRAGMENT_B";
    private static final String FRAGMENT_C = "FRAGMENT_C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flContainerForFragment = (FrameLayout) findViewById(R.id.flContainerForFragment);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //implementing fragment
        MyFragmentA fragmentA = new MyFragmentA();
        managerFragment(fragmentA, FRAGMENT_A);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(!noReplaceFragment) {//Frag será true apenas caso o usuario aperte o botao volta do device. Serve Apenas para mudar a cor do icon
                        MyFragmentA fragmentA = new MyFragmentA();
                        managerFragment(fragmentA, FRAGMENT_A);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(!noReplaceFragment) {
                        MyFragmentB fragmentB = new MyFragmentB();
                        managerFragment(fragmentB, FRAGMENT_B);
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(!noReplaceFragment) {
                        MyFragmentC fragmentC = new MyFragmentC();
                        managerFragment(fragmentC, FRAGMENT_C);
                    }
                    return true;
            }
            return false;
        }
    };

    private void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContainerForFragment, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mfragmentA = fragmentManager.findFragmentByTag(FRAGMENT_A);
        if(mfragmentA != null) {
            if (mfragmentA.isVisible()) {
                finish();
                return;
            }
        }
        super.onBackPressed();
        managerIconsOfBottomNavigation(fragmentManager);
    }

    /**
     * usado para gerenciar o estado o icon de BottomNavigation quando o botão voltar (onBackPressed())
     * é pressionado
     * @param fragmentManager
     */
    private void managerIconsOfBottomNavigation(FragmentManager fragmentManager) {
        //Apenas para mudar a cor do icones da BoottomNavigationVew
        Fragment mfragmentAaux = fragmentManager.findFragmentByTag(FRAGMENT_A);
        if(mfragmentAaux != null) {
            if (mfragmentAaux.isVisible()) {
                noReplaceFragment = true;
                navigation.setSelectedItemId(R.id.navigation_home);
                noReplaceFragment = false;
            }
        }

        Fragment mfragmentB = fragmentManager.findFragmentByTag(FRAGMENT_B);
        if(mfragmentB != null) {
            if (mfragmentB.isVisible()) {
                noReplaceFragment = true;
                navigation.setSelectedItemId(R.id.navigation_dashboard);
                noReplaceFragment = false;
            }
        }

        Fragment mfragmentC = fragmentManager.findFragmentByTag(FRAGMENT_C);
        if(mfragmentC != null) {
            if (mfragmentC.isVisible()) {
                noReplaceFragment = true;
                navigation.setSelectedItemId(R.id.navigation_notifications);
                noReplaceFragment = false;
            }
        }
    }
}



