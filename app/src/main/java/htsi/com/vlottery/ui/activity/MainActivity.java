package htsi.com.vlottery.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import htsi.com.vlottery.ui.activity.base.BaseActivity;
import htsi.com.vlottery.ui.fragment.home.HomeContainerFragment;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;
import htsi.com.vlottery.ui.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseFragment hostFragment() {
        return new HomeFragment();
    }
}
