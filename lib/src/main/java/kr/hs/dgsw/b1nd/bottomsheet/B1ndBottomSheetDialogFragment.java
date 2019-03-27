package kr.hs.dgsw.b1nd.bottomsheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;

public class B1ndBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private String mName;
    private String mEmail;
    private String mTemper;

    private Drawable mProfileDrawable;
    private Drawable mSubMenuDrawable;

    private OnBottomSheetSubItemClickedListener onBottomSheetSubItemClickedListener;
    private OnBottomSheetOptionsItemSelectedListener onBottomSheetOptionsItemSelectedListener;

    private NavigationMenu navigationMenu;
    private NavigationMenuPresenter navigationMenuPresenter;
    private MenuInflater menuInflater;

    @MenuRes
    private int menuRes;

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationMenu = new NavigationMenu(requireContext());
        navigationMenuPresenter = new NavigationMenuPresenter();
        navigationMenu.addMenuPresenter(navigationMenuPresenter);
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.bottom_sheet, container, false);
        ImageView profileView = viewGroup.findViewById(R.id.profileView);
        ImageButton subIcon = viewGroup.findViewById(R.id.subIcon);
        TextView nameView = viewGroup.findViewById(R.id.nameView);
        TextView emailView = viewGroup.findViewById(R.id.emailView);
        TextView temperView = viewGroup.findViewById(R.id.temperView);

        subIcon.setOnClickListener(v -> {
            if (onBottomSheetSubItemClickedListener != null)
                onBottomSheetSubItemClickedListener.onBottomSheetSubItemClicked(v);
            dismiss();
        });
        profileView.setImageDrawable(mProfileDrawable);
        subIcon.setImageDrawable(mSubMenuDrawable);
        nameView.setText(mName);
        emailView.setText(mEmail);
        temperView.setText(mTemper);
        viewGroup.addView((View) navigationMenuPresenter.getMenuView(viewGroup));
        navigationMenu.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                dismiss();
                return onBottomSheetOptionsItemSelectedListener != null && onBottomSheetOptionsItemSelectedListener.onBottomSheetOptionsItemSelected(item);
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {
            }
        });

        navigationMenuPresenter.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#8a000000")));
        navigationMenuPresenter.setItemHorizontalPadding(dpToPx(24));
        navigationMenuPresenter.setItemIconPadding(dpToPx(24));

        if (menuRes != 0)
            getMenuInflater().inflate(menuRes, navigationMenu);

        return viewGroup;
    }

    public void menuInflate(@MenuRes int menuRes) {
        if (getContext() != null)
            getMenuInflater().inflate(menuRes, navigationMenu);
        this.menuRes = menuRes;
    }

    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void setOnBottomSheetSubItemClickedListener(OnBottomSheetSubItemClickedListener onBottomSheetSubItemClickedListener) {
        this.onBottomSheetSubItemClickedListener = onBottomSheetSubItemClickedListener;
    }

    public void setOnBottomSheetOptionsItemSelectedListener(OnBottomSheetOptionsItemSelectedListener onBottomSheetItemSelectedListener) {
        this.onBottomSheetOptionsItemSelectedListener = onBottomSheetItemSelectedListener;
    }

    @Override
    public int getTheme() {
        return R.style.Theme_B1ND_BottomSheetDialog;
    }

    public void setProfileImageDrawable(Drawable drawable) {
        mProfileDrawable = drawable;
    }

    public void setProfileImageResource(@DrawableRes int resource, Resources resources) {
        mProfileDrawable = resources.getDrawable(resource, resources.newTheme());
    }

    public void setProfileImageBitmap(Bitmap bitmap) {
        mProfileDrawable = new BitmapDrawable(Resources.getSystem(), bitmap);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setProfileImageIcon(Icon icon, Context context) {
        mProfileDrawable = icon.loadDrawable(context);
    }

    public void setSubIconImageDrawable(Drawable drawable) {
        mSubMenuDrawable = drawable;
    }

    public void setSubIconImageResource(@DrawableRes int resource, Resources resources) {
        mSubMenuDrawable = resources.getDrawable(resource, resources.newTheme());
    }

    public void setSubIconImageBitmap(Bitmap bitmap) {
        mSubMenuDrawable = new BitmapDrawable(Resources.getSystem(), bitmap);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setSubIconImageIcon(Icon icon, Context context) {
        mSubMenuDrawable = icon.loadDrawable(context);
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setTemper(String mTemper) {
        this.mTemper = mTemper;
    }

    @SuppressLint("RestrictedApi")
    public MenuInflater getMenuInflater() {
        if (menuInflater == null) {
            menuInflater = new SupportMenuInflater(getContext());
        }
        return menuInflater;
    }

    public interface OnBottomSheetSubItemClickedListener {
        void onBottomSheetSubItemClicked(@NonNull View view);
    }

    public interface OnBottomSheetOptionsItemSelectedListener {
        boolean onBottomSheetOptionsItemSelected(@NonNull MenuItem item);
    }
}
