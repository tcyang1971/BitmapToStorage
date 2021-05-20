package tw.edu.pu.csim.tcyang.bitmaptostorage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.img)
        //val bmp: Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pict_s)
        val bmp: Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pict)
        img.setImageBitmap(bmp)
    }
}