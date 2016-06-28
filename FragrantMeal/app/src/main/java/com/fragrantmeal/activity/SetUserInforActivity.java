package com.fragrantmeal.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.fragrantmeal.App;
import com.fragrantmeal.EventBus.EventBusSelect;
import com.fragrantmeal.R;
import com.fragrantmeal.entity.UserInfo;
import com.fragrantmeal.util.Config;
import com.fragrantmeal.util.GsonUtil;
import com.fragrantmeal.util.ShowToast;
import com.fragrantmeal.view.SelectPicPopupWindow;
import com.mj.core.util.L;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.mime.TypedFile;
import rx.android.app.AppObservable;
import rx.schedulers.Schedulers;

/**
 * Created by CaoBin on 2016/4/18.
 */
public class SetUserInforActivity extends Activity{
    @InjectView(R.id.user_icon)
    CircleImageView userIcon;
    @InjectView(R.id.user_alias)
    EditText userAlias;
    @InjectView(R.id.user_age)
    EditText userAge;
    @InjectView(R.id.user_sex_woman)
    RadioButton userSexWoman;
    @InjectView(R.id.user_sex_man)
    RadioButton userSexMan;
    @InjectView(R.id.user_signature)
    EditText userSignature;

    private Intent intent;
    private UserInfo user;
    private SelectPicPopupWindow menuWindow;
    private File iconFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfor);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        init();
    }

    public void init() {
        intent = getIntent();
        user = (UserInfo) intent.getSerializableExtra("user");
        App.getImage(this, userIcon, user.getUser_icon());
        userAlias.setText(user.getUser_alias());
        userAge.setText("" + user.getUser_age());
        setUserSex(user.getUser_sex());
        userSignature.setText(user.getUser_signature());

    }

    @OnClick({R.id.btn_back, R.id.btn_save,R.id.user_icon,R.id.user_sex_man,R.id.user_sex_woman})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                UserInfo user=new UserInfo();
                user.setUser_alias(userAlias.getText().toString().trim());
                user.setUser_age(Integer.parseInt(userAge.getText().toString().trim()));
                user.setUser_signature(userSignature.getText().toString().trim());
                user.setU_id(this.user.getU_id());
                user.setUsername(this.user.getUsername());
                user.setPassword(this.user.getPassword());
                user.setToken(this.user.getToken());
                if (userSexMan.isChecked()){
                    user.setUser_sex(userSexMan.getText().toString().trim());
                }
                if (userSexWoman.isChecked()){
                    user.setUser_sex(userSexWoman.getText().toString().trim());
                }
                if (iconFile!=null){
                    sendIcon(user);
                }else {
                    updateUser(user);
                }
                break;
            case R.id.user_icon:
                menuWindow = new SelectPicPopupWindow(this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(this.findViewById(R.id.setUserInfor), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.user_sex_man:
                userSexMan.setChecked(true);
                userSexWoman.setChecked(false);
                break;
            case R.id.user_sex_woman:
                userSexMan.setChecked(false);
                userSexWoman.setChecked(true);
                break;

        }

    }


    public void setUserSex(String sex){
        switch (sex){
            case "男":
                userSexMan.setChecked(true);
                userSexWoman.setChecked(false);
                break;
            case "女":
                userSexMan.setChecked(false);
                userSexWoman.setChecked(true);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    iconFile=new File(Environment.getExternalStorageDirectory() ,Config.IMAGE_FILE_NAME);
                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(iconFile));
                    startActivityForResult(takeIntent,Config.REQUESTCODE_TAKE);
                    break;
                case R.id.btn_pick_photo:
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Config.IMAGE_UNSPECIFIED);
                    startActivityForResult(pickIntent,  Config.REQUESTCODE_PICK);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case Config.REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                    ContentResolver cr=this.getContentResolver();
                    Cursor c=cr.query(data.getData(), null, null, null, null);
                    c.moveToFirst();
                    String iconPaht=c.getString(c.getColumnIndex("_data"));
                    iconFile=new File(iconPaht);
                    L.e(""+iconPaht);
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case Config.REQUESTCODE_TAKE:// 调用相机拍照
                startPhotoZoom(Uri.fromFile(iconFile));
                break;
            case Config.REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, Config.IMAGE_UNSPECIFIED);
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Config.REQUESTCODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        Bitmap bitmap=extras.getParcelable("data");
        userIcon.setImageBitmap(bitmap);
    }

    public void updateUser(UserInfo user){
        AppObservable.bindActivity(SetUserInforActivity.this,App.getfMealNet().updateUser(user)).subscribe(
                success -> {
                    ShowToast.showToast(this, "上传成功");
                    EventBus.getDefault().post(new EventBusSelect(Config.USERINFOR, GsonUtil.toJson(success)));
                    this.finish();
                },

                e -> {
                    ShowToast.showToast(this, "上传失败");
                }
        );
    }

    public void sendIcon(UserInfo user){
        AppObservable.bindActivity(SetUserInforActivity.this,App.getfMealNet().sendIcon(new TypedFile(Config.IMAGE_UNSPECIFIED, iconFile),user.getU_id()))
                .subscribeOn(Schedulers.computation())
                .subscribe(
                        success -> {
                            updateUser(user);
                        },
                        e -> {


                        }
                );
    }

    public void onEventMainThread(EventBusSelect event) {

    }

//    //    Bitmap对象保存味图片文件
//    public void saveBitmapFile(Bitmap bitmap){
//        File file=new File("/mnt/sdcard/pic/01.jpg");//将要保存图片的路径
//        try {
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//            bos.flush();
//            bos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
