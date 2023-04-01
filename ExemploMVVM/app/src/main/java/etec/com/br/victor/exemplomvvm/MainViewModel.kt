package etec.com.br.victor.exemplomvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    // O MutableLiveData PERMITE PUXAR AQUILO PARA ESTE MODEL
    private var textWelcome = MutableLiveData<String>()

    init {
        textWelcome.value = "Bem-vindo!"
    }

    fun welcome(): LiveData<String>{
        return textWelcome
    }

}