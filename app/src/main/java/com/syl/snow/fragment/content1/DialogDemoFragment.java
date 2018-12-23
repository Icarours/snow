package com.syl.snow.fragment.content1;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;
import com.syl.snow.utils.LogUtils;
import com.syl.snow.utils.ScreenSizeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bright on 2018/12/23.
 *
 * @Describe 常用dialog对话框
 * @Called
 */
public class DialogDemoFragment extends BaseFragment {
    private static final String TAG = DialogDemoFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_demo, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.btn_dialog1, R.id.btn_dialog2, R.id.btn_dialog3, R.id.btn_dialog4, R.id.btn_dialog5,
            R.id.btn_dialog6, R.id.btn_dialog7, R.id.btn_dialog8, R.id.btn_dialog9, R.id.btn_dialog10,})
    public void onViewClick(View view) {
        switch (view.getId()) {
            default:
            case R.id.btn_dialog1:
                dialog1();
                break;
            case R.id.btn_dialog2:
                dialog2();
                break;
            case R.id.btn_dialog3:
                dialog3();
                break;
            case R.id.btn_dialog4:
                dialog4();
                break;
            case R.id.btn_dialog5:
                dialog5();
                break;
            case R.id.btn_dialog6:
                dialog6();
                break;
            case R.id.btn_dialog7:
                dialog7();
                break;
            case R.id.btn_dialog8:
                dialog8();
                break;
            case R.id.btn_dialog9:
                dialog9();
                break;
            case R.id.btn_dialog10:
                dialog10();
                break;
        }
    }

    /**
     * 普通对话框
     * AlertDialog类中有一个静态内部类Builder。所以可以看出对话框使用了一个建造者模式在调用函数的时候
     * 就可以一直直点点点链式调用。 需要注意的是：NegativeButton这个按钮是在对话框的左边,PositiveButton
     * 在对话框的右边;如果你还想再加一个按钮也是可以的只需要在调用.setNeutralButton("第三个按钮",listener)即可。
     */
    private void dialog1() {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("普通对话框")
                .setMessage("这是一个普通对话框..")
                .setNegativeButton("取消", (dialog12, which) -> {
                    LogUtils.d(TAG, "普通对话框-->取消");
                    Toast.makeText(getContext(), "普通对话框-->取消", Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("确认", (dialog1, which) -> {
                    LogUtils.d(TAG, "普通对话框-->确认");
                    Toast.makeText(getContext(), "普通对话框-->确认", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("第三个按钮", (dialog13, which) -> {
                    LogUtils.d(TAG, "普通对话框-->第三个按钮");
                    Toast.makeText(getContext(), "普通对话框-->第三个按钮", Toast.LENGTH_SHORT).show();
                }).create();
        dialog.show();
    }

    /**
     * 列表对话框
     * 当给用户的选择就那么几条路的时候，就可在对话框上放置一个列表供用户自己选择
     * 源码已经为我们预留好了设置方法，所以我们只需要调用.setItems()即可，第一个参数即要显示item的数组,第二个参数也就是点击item后的监听事件还是so easy的。
     * 列表对话库不能设置  .setMessage("这是一个多选列表对话库")
     * * 否则,列表不会出现
     */
    private void dialog2() {
        String[] items = {"item1", "item2", "item3", "item4"};
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("列表对话库")
//                .setMessage("这是一个列表对话框")
                .setItems(items, (dialog1, which) -> {
                    LogUtils.d(TAG, "列表对话框->" + items[which]);
                    Toast.makeText(getContext(), "列表对话框->" + items[which], Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("确认", (dialog12, which) -> {
                    LogUtils.d(TAG, "列表对话库->确认");
                    Toast.makeText(getContext(), "列表对话库->确认", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialog13, which) -> {
                    LogUtils.d(TAG, "列表对话库->取消");
                    Toast.makeText(getContext(), "列表对话库->取消", Toast.LENGTH_SHORT).show();
                }).create();
        dialog.show();
    }

    /**
     * 单选列表对话框
     * .setSingleChoiceItems(items, 1, listener)第一个参数:设置单选的资源数组;第二个参数:设置默认选中哪一项。
     * 列表对话库不能设置  .setMessage("这是一个多选列表对话库")
     * * 否则,列表不会出现
     */
    private void dialog3() {
        String[] items = {"item1", "item2", "item3", "item4"};
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("单选列表对话框")
                .setSingleChoiceItems(items, 1, (dialog1, which) -> {
                    LogUtils.d(TAG, "单选列表对话框->" + items[which]);
                    Toast.makeText(getContext(), "单选列表对话框->" + items[which], Toast.LENGTH_SHORT).show();
                })
                .setPositiveButton("确认", (dialog12, which) -> {
                    LogUtils.d(TAG, "单选列表对话框->确认");
                    Toast.makeText(getContext(), "单选列表对话框->确认", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialog13, which) -> {
                    LogUtils.d(TAG, "单选列表对话框->取消");
                    Toast.makeText(getContext(), "单选列表对话框->取消", Toast.LENGTH_SHORT).show();
                }).create();
        dialog.show();
    }

    /**
     * 多选列表对话库
     * .setMultiChoiceItems(items, checkedItems, listener)//第一个参数:设置单选的资源;第二个参数:设置默认选中哪几项（数组）;
     * 列表对话库不能设置  .setMessage("这是一个多选列表对话库")
     * 否则,列表不会出现
     */
    private void dialog4() {
        String[] items = {"item1", "item2", "item3", "item4"};
        boolean[] checked = {false, false, true, true};
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("多选列表对话框")
                .setMultiChoiceItems(items, checked, (dialog1, which, isChecked) -> checked[which] = isChecked)
                .setPositiveButton("确认", (dialog12, which) -> {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < items.length; i++) {
                        if (checked[i])
                            list.add(items[i]);
                    }
                    LogUtils.d(TAG, "多选列表对话库->确认" + list.toString());
                    Toast.makeText(getContext(), "多选列表对话库->确认" + list.toString(), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialog13, which) -> {
                    LogUtils.d(TAG, "多选列表对话库->取消");
                    Toast.makeText(getContext(), "多选列表对话库->取消", Toast.LENGTH_SHORT).show();
                }).create();
        dialog.show();
    }

    /**
     * 半自定义对话库
     * 上面代码中half_dialog_view.xml中我就放置了一个EditText;在这里好多人在找自己布局中的控件时候经常报NullpointException，
     * 原因也很简单就是没有使用加载的布局.findViewbyId()。到了这一步基本上就能满足开发中80%的需求了，看官如果还不能满足那别急慢慢往下看。
     */
    private void dialog5() {
        View view = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
        EditText et = view.findViewById(R.id.et);
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("半自定义对话框")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("这是一个半自定义的对话框")
                .setView(view)
                .setPositiveButton("确定", (dialog1, which) -> {
                    String str = et.getText().toString();
                    LogUtils.d(TAG, "str==确定--");
                    Toast.makeText(getContext(), "str==确定--" + str, Toast.LENGTH_SHORT).show();
                }).setNegativeButton("取消", (dialog12, which) -> {
                    LogUtils.d(TAG, "取消 str==");
                    Toast.makeText(getContext(), "取消 ", Toast.LENGTH_SHORT).show();
                }).create();
        dialog.show();
    }

    /**
     * 自定义对话库
     */
    private void dialog6() {
        final Dialog dialog = new Dialog(getContext(), R.style.NormalDialogStyle);
        View view = View.inflate(getContext(), R.layout.dialog_normal, null);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        TextView confirm = view.findViewById(R.id.tv_ok);
        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenWidth() * 0.75f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        cancel.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(getContext(), "自定义dialog-->取消", Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "自定义dialog-->取消");
        });
        confirm.setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(getContext(), "自定义dialog-->确定", Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "自定义dialog-->确定");
        });
        dialog.show();
    }

    /**
     * 底部对话框
     */
    private void dialog7() {
        Dialog dialog = new Dialog(getContext(), R.style.NormalDialogStyle);
        View view = View.inflate(getContext(), R.layout.dialog_bottom, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(getContext()).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(getContext()).getScreenWidth() * 0.9f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        view.findViewById(R.id.btn_camera).setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(getContext(), "相机", Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "相机");
        });
        view.findViewById(R.id.btn_gallery).setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(getContext(), "相册", Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "相册");
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            dialog.dismiss();
            Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
            LogUtils.d(TAG, "取消");
        });
        dialog.show();
    }

    /**
     * 圆形进度条对话框
     */
    private void dialog8() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("正在加载中");
        dialog.show();
    }

    /**
     * 水平进度条对话框
     */
    private void dialog9() {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("正在加载中");
        dialog.setMax(100);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                dialog.setProgress(progress += 5);
                if (progress >= 100) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
        dialog.show();
    }

    /**
     * BottomSheetDialog，一个可以上下拖动的对话框使用方法和Dialog还是差不多的。
     */
    private void dialog10() {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_demo, null);
        dialog.setContentView(view);//设置显示的内容，我这为了方便就直接把主布局设置进去了。
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
