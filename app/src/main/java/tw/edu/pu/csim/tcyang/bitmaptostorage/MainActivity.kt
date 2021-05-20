package tw.edu.pu.csim.tcyang.bitmaptostorage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.CustomTarget

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
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })
    }
}