package space.example.project1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val liveData = MutableLiveData<String>() // sample live data

    init {
        liveData.value = "TEST"
    }

}