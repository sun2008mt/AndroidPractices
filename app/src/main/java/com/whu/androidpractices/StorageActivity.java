package com.whu.androidpractices;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageActivity extends AppCompatActivity {

    /*
    * Android使用与其他平台类似的基于磁盘的文件系统(disk-based file systems)
    * 所有的Android设备均有两个文件存储区域：internal和external(不可变存储internal storage和类似SD card的external storage这样可以卸载的存储部件)
    * 现在越来越多的设备将internal和external都做成了不可卸载的内置存储，虽然如此，但是这一整块还是从逻辑上被划分为internal和external，只是不再以是否可卸载进行区分了
    *
    * internal storage： 总是可用的；文件默认只能被app所访问；当用户卸载app的时候，系统会把internal内该app相关的文件都清除干净；是我们在想确保不被用户与其他app所访问的最佳存储区域
    *
    * external storage： 并不总是可用的，因为用户有时候会通过USB存储模式挂载外部存储器，当取下挂载的这部分后，就无法对其进行访问了；
    *                    是大家都可以访问的，因此保存在这里的文件可能被其他程序访问；当用户卸载app时，系统仅仅会删除external根目录(getExternalFilesDir())下的相关文件；
    *                    external是在不需要严格的访问权限并且希望这些文件能够被其他app所共享或者是允许用户通过电脑访问时的最佳存储区域
    *
    * 尽管app是默认被安装到internal storage的，我们还是可以通过在程序的manifest文件中声明android:installLocation属性来指定程序安装到external storage；
    * 当某个程序的安装文件很大并且用户的external storage空间大于internal storage时，用户会倾向于将程序安装到external storage中
    *
    * internal storage目录以app的包名作为标识存放在Android文件系统的data/data/[包名]目录下
    *
    * external storage对于用户或其他app是可以修改的，可以用来存储public files，如app拍摄下来的图片或者下载的文件；private files：对于其他app没有意义的私人文件，如app下载的缓存文件
    *
    * app被卸载后，系统会删除所有保存在internal storage的文件，以及所有使用getExternalFilesDir()方式保存在external storage的文件
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        //获取app的internal目录
        File internalStorage = getFilesDir();

        //获取app的internal缓存目录(这个目录下的文件需要能够再一旦不再需要的时候马上被删除，并有合适的大小限制，因为internal storage空间宝贵，系统内部空间不够时，会自行选择删除缓存文件)
        File cacheDir = getCacheDir();

        //创建目录下文件
        File file = new File(internalStorage, "test");

        //获取FileOutputStream用于写文件到internal目录
        try {
            //私有模式禁止其他app的访问
            FileOutputStream fos = openFileOutput("test", Context.MODE_PRIVATE);
            fos.write("Hello, World".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //在internal的缓存目录下创建临时文件
        try {
            File.createTempFile("temp", null, getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //external storage可能是不可用的，如SD卡被拔出等情况，因而需要再访问之前对其可用性进行检查
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //external可用

        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            //external可用但只读
        } else {
            //external不可用
        }

        //external storage目录下存储的图片类型的文件(public，app被卸载也不会被删除)
        File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "test");

        //private，app被卸载系统会自动删除
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "test");

        //getExternalFilesDir()方法创建的目录会在app被卸载时被系统删除，而getExternalStoragePublicDirectory()方法则不会
        
        //查询剩余空间
        long freeSpace = internalStorage.getFreeSpace();
        long totalSpace = internalStorage.getTotalSpace();

        //删除文件
        file.delete();

        //如果文件保存在internal storage中
        deleteFile("test");
    }
}
