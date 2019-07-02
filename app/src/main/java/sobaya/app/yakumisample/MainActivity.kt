package sobaya.app.yakumisample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with (mainRecycler) {
            adapter = SampleAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
//            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            mainYakumi.setRecyclerView(this)
        }
    }
}
