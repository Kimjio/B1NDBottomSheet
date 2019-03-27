package kr.hs.dgsw.b1nd.bottomsheet.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import kr.hs.dgsw.b1nd.bottomsheet.B1ndBottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        B1ndBottomSheetDialogFragment bottomSheetDialogFragment = new B1ndBottomSheetDialogFragment();
        bottomSheetDialogFragment.setProfileImageResource(android.R.drawable.sym_def_app_icon, getResources());
        bottomSheetDialogFragment.setSubIconImageResource(android.R.drawable.ic_lock_power_off, getResources());
        bottomSheetDialogFragment.setName("Ji O Kim");
        bottomSheetDialogFragment.setEmail("kimjioh0927@gmail.com");
        bottomSheetDialogFragment.setTemper("ANDROID");
        bottomSheetDialogFragment.show(getSupportFragmentManager(), "bottom");
    }
}
