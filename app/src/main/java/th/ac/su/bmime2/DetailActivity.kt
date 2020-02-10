package th.ac.su.bmime2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val height = intent.getDoubleExtra("height",-1.0)
        val weight = intent.getIntExtra("weight",-1)
        var tvDetail = findViewById<TextView>(R.id.tvDetail)
        var tvHw = findViewById<TextView>(R.id.tvHw)

        var w = weight
        var h = height

        h = h/100

        var bmi:Double = w/(h*h)

        var result = "fat"

        if(bmi>30) {
            result = ("Obese")
        }
        else if (bmi> 25) {
            result = ("Overweight")
        }
        else if (bmi> 18) {
            result = ("Healthy")
        }
        else {
            result = ("Underweight")
        }



        tvBmi.setText(bmi.round(2).toString())

        tvDetail.setText(result.toString())

        tvHw.setText("height: "+height.toString()+" weight: "+weight.toString()+")")

        var btnShare = findViewById<Button>(R.id.btnShare)
        btnShare.setOnClickListener{

            val value = "My BMI is "+bmi.round(2)+"("+result+")"

            var intent = Intent();
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,value)
            intent.type = "text/plan"

            startActivity(Intent.createChooser(intent,"share info"))

        }

        var btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener{

            var intent = Intent()
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
}
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier



}