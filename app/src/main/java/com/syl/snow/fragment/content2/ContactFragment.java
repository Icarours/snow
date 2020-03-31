package com.syl.snow.fragment.content2;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syl.snow.R;
import com.syl.snow.adpater.TextAdapter2;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.bean.PersonE2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Bright
 * @date 2020/3/31 13:40
 * @describe 读取手机通讯录
 */
public class ContactFragment extends BaseFragment {
    private static final String TAG = ContactFragment.class.getSimpleName();
    private RecyclerView mRv;
    private List<PersonE2> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        mRv = rootView.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL));

        getContact2();

        return rootView;
    }

    private PersonE2 tempPersonE = new PersonE2();

    /**
     * 手机通讯录中有些重复数据,需要手动去除
     */
    private void getContact2() {
        Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                null, null, null, ContactsContract.Data.RAW_CONTACT_ID);
        if (cursor != null) {
            Log.d(TAG, "当前通讯录数量===" + cursor.getCount());
            while (cursor.moveToNext()) {
                PersonE2 personE2;
                int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
                if (tempPersonE.getContactId() == contactId) {
                    personE2 = tempPersonE;
                } else {
                    personE2 = new PersonE2();
                }
                personE2.setContactId(contactId);
                String mimetype = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE)); // 取得mimetype类型,扩展的数据都在这个类型里面
                if (ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE.equals(mimetype)) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                    personE2.setDisplayName(displayName);
                }
                if (ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE.equals(mimetype)) {
                    String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    personE2.setPhoneNumber(phoneNumber);
                }
                if (ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE.equals(mimetype)) {
                    String address = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    personE2.setAddress(address);
                }
                //去除重复数据
                if (tempPersonE.getContactId() != personE2.getContactId()) {
                    mList.add(personE2);
                }
                tempPersonE = personE2;
            }
            cursor.close();
        }

        TextAdapter2 adapter = new TextAdapter2(R.layout.rv_text1, mList);
        mRv.setAdapter(adapter);
    }
}
