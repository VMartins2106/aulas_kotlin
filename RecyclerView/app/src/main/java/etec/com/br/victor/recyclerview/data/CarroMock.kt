package etec.com.br.victor.recyclerview.data

import etec.com.br.victor.recyclerview.model.Carro

class CarroMock {

    var listaCarros = ArrayList<Carro>()

    init{
        for(i in 1..5){
            listaCarros.add(Carro(i,i.toString()))
        }
    }

}