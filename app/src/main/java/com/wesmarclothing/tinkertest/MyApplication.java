package com.wesmarclothing.tinkertest;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import org.litepal.LitePal;
import org.litepal.tablemanager.callback.DatabaseListener;

import java.util.Locale;
import java.util.logging.Logger;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName MyApplication
 * @Date 2019/3/13 14:43
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Bugly.init(this, "3330fc1cb0", true);
        LitePal.initialize(this);
        LitePal.registerDatabaseListener(new DatabaseListener() {
            @Override
            public void onCreate() {
                Logger.getLogger("LitePal").info("数据库创建");
            }

            @Override
            public void onUpgrade(int oldVersion, int newVersion) {
                Logger.getLogger("LitePal").info("数据库更改oldVersion:" + oldVersion
                        + "--newVersion：" + newVersion);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();

        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplication(),
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {
                Toast.makeText(getApplication(), "补丁回滚", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
