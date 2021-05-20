package tw.edu.pu.csim.tcyang.bitmaptostorage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.CustomTarget
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.img)
        //val bmp: Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pict_s)
        //val bmp: Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pict)
        //img.setImageBitmap(bmp)

        GlideApp.with(this)
                .asBitmap()
                .load(R.drawable.pict)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap,
                                                 transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                        img.setImageBitmap(resource)
                        SaveToStorage(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
    }

    fun SaveToStorage(bmp:Bitmap){
        //將圖片換成byteArray
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val pictData = baos.toByteArray()

        //val filename = "images/pict.jpg"  //設定子節點與檔名
        //根據系統時間設定檔名
        val sdf = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS")  //定義時間格式
        val dt = Date()  //取得現在時間
        val dts = sdf.format(dt) //將目前日期轉為字串
        val filename = "images/" + dts + ".jpg"  //設定子節點與檔名

        val reference = FirebaseStorage.getInstance().getReference().child(filename)
        //上傳到Firebase
        reference.putBytes(pictData)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "上傳成功", Toast.LENGTH_SHORT).show()
                }

                .addOnFailureListener {
                    Toast.makeText(baseContext, "上傳失敗", Toast.LENGTH_SHORT).show()
                }
    }
}