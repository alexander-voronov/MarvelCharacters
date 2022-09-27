package alexander.voronov.marvelcharacters

import alexander.voronov.marvelcharacters.databinding.ActivityMainBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainLoadButton.setOnClickListener{
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
        }
    }
}