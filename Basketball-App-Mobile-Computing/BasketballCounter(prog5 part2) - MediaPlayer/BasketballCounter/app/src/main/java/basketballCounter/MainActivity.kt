package basketballCounter

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import java.util.*


private const val TAG = "MainActivity"

/**
 * Main activity class for hosting fragments
 */

class MainActivity : AppCompatActivity(), BasketballGameFragment.Callbacks, GameListFragment.Callbacks {

    private lateinit var soundpool: SoundPool
    lateinit var MP: MediaPlayer

    private var sound1: Int = 0
    private var sound2: Int = 0

    private val gameListViewModel: GameListViewModel by lazy {
        ViewModelProviders.of(this).get(GameListViewModel::class.java)
    }

    /**
     * Overrides the onCreate method to load fragments
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = GameListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()

        }

       // MP= MediaPlayer.create(this, basketballCounter.R.raw.sound1)
       // createsoundpool()
    }

    fun MPplaysound1(v:View){
            MP= MediaPlayer.create(this, basketballCounter.R.raw.sound1)

        MP.start()
        MP.isLooping=false
        MP.setVolume(0.5f,0.5f)
       // MP.release()




           }

    fun MPplaysound2(v:View){
        MP= MediaPlayer.create(this, basketballCounter.R.raw.sound2)

        MP.start()
        MP.isLooping=false
        MP.setVolume(0.5f,0.5f)
        //MP.release()

    }






    /*fun createsoundpool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            soundpool = SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            soundpool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        }
        sound1 = soundpool.load(this, basketballCounter.R.raw.sound1, 1)
        sound2 = soundpool.load(this, basketballCounter.R.raw.sound2, 1)

    }*/


    /**
     * Override for the onDisplayClicked method that switches to the GameListFragment
     * and sends the winningTeam to it
     */
    override fun onDisplayClicked(winningTeam: String) {
        Log.d(TAG, "onDisplayClicked() called with Winning Team: $winningTeam")
        val fragment = GameListFragment.newInstance(winningTeam)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Override for the onGameSelected method that switches to the BasketballGameFragment
     * and sends the selected GameID to it so that the game data is displayed
     */
    override fun onGameSelected(gameId: UUID) {
        Log.d(TAG, "onGameSelected() called with Game ID: $gameId")
        val fragment = BasketballGameFragment.newInstance(gameId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    /**
     * Override for the getGameListViewModel val that get the value of
     * gameListViewModel
     */
    override val getGameListViewModel: GameListViewModel
        get() = gameListViewModel


    /**
     * Adding logs messages for onStart, onResume, onPause, onStop and onDestroy
     */
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
        MP.release()
        Log.d(TAG, "Media Player Released!")
        Toast.makeText(this,"Media Player Released", Toast.LENGTH_SHORT)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
        MP.release()
        Log.d(TAG, "Media Player Released!")
        Toast.makeText(this,"Media Player Released", Toast.LENGTH_SHORT)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
        MP.release()
        Log.d(TAG, "Media Player Released!")
        Toast.makeText(this,"Media Player Released", Toast.LENGTH_SHORT)
    }

   /* fun playsound1(v: View) {

        soundpool.play(sound1, 1F, 1F, 0, 0, 1F)
        soundpool.autoPause();


    }

    fun playsound2(v: View) {

        soundpool.play(sound2, 1F, 1F, 0, 0, 1F)
        soundpool.autoPause();
    }*/
}