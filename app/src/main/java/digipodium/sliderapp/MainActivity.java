package digipodium.sliderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager = findViewById(R.id.pager);
        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager(),0);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
    }

   class IntroAdapter extends FragmentStatePagerAdapter{

       public IntroAdapter(@NonNull FragmentManager fm, int behavior) {
           super(fm, behavior);
       }

       @NonNull
       @Override
       public Fragment getItem(int position) {
           switch (position){
               case 0: return new F1Fragment();
               case 1: return new F2Fragment();
               case 2: return new F3Fragment();
           }
           return null;
       }

       @Override
       public int getCount() {
           return 3;
       }
   }
}