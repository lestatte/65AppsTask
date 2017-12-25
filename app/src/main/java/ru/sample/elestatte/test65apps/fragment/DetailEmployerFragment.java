package ru.sample.elestatte.test65apps.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.utility.Utils;

/**
 * Fragment for displaying detail info of employer (appears after click on item in list).
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
public class DetailEmployerFragment extends Fragment {

    private static String L_NAME = "l_name";
    private static String F_NAME = "f_name";
    private static String BIRTHDAY = "birthday";
    private static String AVATAR = "avatar";

    @BindView(R.id.avatar)
    ImageView mAvatar;

    @BindView(R.id.title_last_name)
    TextView mLastName;

    @BindView(R.id.title_first_name)
    TextView mFirstName;

    @BindView(R.id.title_birthday)
    TextView mBirthday;

    @BindView(R.id.title_specialities)
    TextView mSpecialities;

    public static DetailEmployerFragment create(Employer item) {
        DetailEmployerFragment fragment = new DetailEmployerFragment();
        Bundle args = new Bundle();
        args.putString(L_NAME, Utils.capitalize(item.lName).trim());
        args.putString(F_NAME, Utils.capitalize(item.fName).trim());
        args.putString(BIRTHDAY, Utils.reformatDate(item.birthday));
        args.putString(AVATAR, item.avatarUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_detail_employer, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        if (null != args) {
            String url = args.getString(AVATAR);
            if (null != url && !url.isEmpty()) {
                Picasso.with(getContext())
                        .load(url)
                        .placeholder(android.R.drawable.alert_dark_frame)
                        .error(android.R.drawable.alert_dark_frame)
                        .into(mAvatar);
            }
            mLastName.setText(getString(R.string.title_family, args.getString(L_NAME)));
            mFirstName.setText(getString(R.string.title_name, args.getString(F_NAME)));
            String birthday = args.getString(BIRTHDAY);
            if (null != birthday && !birthday.isEmpty()) {
                birthday = getString(R.string.year_ending, args.getString(BIRTHDAY));
            }
            mBirthday.setText(getString(R.string.title_birthday, birthday));
            mSpecialities.setText(getString(R.string.title_specialities, ""));
        }
        return view;
    }

}
